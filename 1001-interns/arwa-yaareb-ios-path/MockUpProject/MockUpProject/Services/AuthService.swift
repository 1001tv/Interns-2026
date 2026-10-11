import Foundation

enum AuthError: Error { //CASES OF EROoRs
    case invalidCredentials // wrong password
    case userNotFound       // no account with this email
    case emailExists        // sign up with an email that is already taken
    case wrongAnswer        // wrong answer to the security question
    case resetExpired       // the reset session is no longer valid
    case tooManyAttempts    // the server is rate limiting us
    case network            // could not reach the server
    case other              // the server answered with something unexpected
}

// the shapes of the JSON that the backend sends back, any extra fields woll be ignored as this is the auth only
private struct LoginResponse: Decodable {
    let message: String
    let token: String
}

private struct QuestionResponse: Decodable {
    let question: String
}

private struct VerifyResponse: Decodable {
    let resetToken: String
}

@MainActor // every screen calls this from the main thread, so we say it out loud
final class AuthService {
    static let shared = AuthService()

    let useMock = true                          // true = fake answers, no server needed
    let baseURL = "http://10.21.4.84:3000"      // Sarah's server on ZeroTier

    // MARK: - this is hte mock database (for now it is only being usd when useMock is true anda  resets when the app restarts)

    private struct MockAccount {
        var password: String
        let question: String
        let answer: String
    }

    private var mockAccounts: [String: MockAccount] = [
        "sarah@test.com": MockAccount(password: "test123", //this is the account that sarah had created so far in the backedn database
                                      question: "What is your favorite color?",
                                      answer: "pink") ]
    
    
    private var mockWrongAnswers: [String: Int] = [:]     //email when there are wrong answers in a row
    private var mockResetTokens: [String: String] = [:]   // resets the token of this  email

    private func normalized(_ text: String) -> String {
        text.trimmingCharacters(in: .whitespaces).lowercased() //trim the strings, and make them all lower cased
    }

    // MARK: - this is our login  func finally:
    // returns the token if the login worked, throws an AuthError if not
    func login(email: String, password: String) async throws -> String {

        if useMock {
            try await Task.sleep(nanoseconds: 500_000_000)
            guard let account = mockAccounts[email.lowercased()] else {
                throw AuthError.userNotFound
            }
            if account.password != password { throw AuthError.invalidCredentials }
            return "test-token"
        }

        let (data, statusCode) = try await post("/auth/login",
                                                body: ["email": email, "password": password])
        switch statusCode {
        case 200:
            guard let result = try? JSONDecoder().decode(LoginResponse.self, from: data) else {
                throw AuthError.other
            }
            return result.token
        case 401: throw AuthError.invalidCredentials
        case 404: throw AuthError.userNotFound
        case 429: throw AuthError.tooManyAttempts
        default:  throw AuthError.other
        }
    }

    // MARK: - Register
    // creates the account. It does not return a token, the caller logs in right after
    func register(_ info: RegistrationInfo) async throws {

        if useMock {
            try await Task.sleep(nanoseconds: 500_000_000)
            let key = info.email.lowercased() //making all email lowercased so we are able to compare them in the darabase
            if mockAccounts[key] != nil { throw AuthError.emailExists }
            mockAccounts[key] = MockAccount(password: info.password,
                                            question: info.securityQuestion,
                                            answer: normalized(info.securityAnswer))
            return
        }

        let body: [String: Any] = [
            "name": info.name,
            "email": info.email,
            "password": info.password,
            "age": info.age,
            "favoriteGenres": info.favoriteGenres,
            "securityQuestion": info.securityQuestion,
            "securityAnswer": info.securityAnswer
        ]

        let (_, statusCode) = try await post("/auth/register", body: body)

        switch statusCode {
        case 200, 201: return
        case 409:      throw AuthError.emailExists
        case 429:      throw AuthError.tooManyAttempts
        default:       throw AuthError.other
        }
    }

    // MARK: - Forgot password (3 steps, no OTP)

    // step 1: which security question did this account choose?
    func securityQuestion(for email: String) async throws -> String {

        if useMock {
            try await Task.sleep(nanoseconds: 500_000_000)
            guard let account = mockAccounts[email.lowercased()] else {
                throw AuthError.userNotFound
            }
            return account.question
        }

        let (data, statusCode) = try await post("/auth/forgot/question", body: ["email": email])
        switch statusCode {
        case 200:
            guard let result = try? JSONDecoder().decode(QuestionResponse.self, from: data) else {
                throw AuthError.other
            }
            return result.question
        case 404: throw AuthError.userNotFound
        case 429: throw AuthError.tooManyAttempts
        default:  throw AuthError.other
        }
    }

    // step 2: is the answer correct? If yes, we get a short-lived reset token
    func verifySecurityAnswer(email: String, answer: String) async throws -> String {

        if useMock {
            try await Task.sleep(nanoseconds: 500_000_000)
            let key = email.lowercased()
            guard let account = mockAccounts[key] else { throw AuthError.userNotFound }

            if (mockWrongAnswers[key] ?? 0) >= 5 { throw AuthError.tooManyAttempts }

            if normalized(answer) != account.answer {
                mockWrongAnswers[key, default: 0] += 1
                if (mockWrongAnswers[key] ?? 0) >= 5 { throw AuthError.tooManyAttempts }
                throw AuthError.wrongAnswer
            }

            mockWrongAnswers[key] = 0
            let token = "reset-" + UUID().uuidString
            mockResetTokens[token] = key
            return token
        }

        let (data, statusCode) = try await post("/auth/forgot/verify",
                                                body: ["email": email, "answer": answer])
        switch statusCode {
        case 200:
            guard let result = try? JSONDecoder().decode(VerifyResponse.self, from: data) else {
                throw AuthError.other
            }
            return result.resetToken
        case 401: throw AuthError.wrongAnswer
        case 404: throw AuthError.userNotFound
        case 429: throw AuthError.tooManyAttempts
        default:  throw AuthError.other
        }
    }

    // step 3: set the new password, using the reset token from step 2
    func resetPassword(resetToken: String, newPassword: String) async throws {

        if useMock {
            try await Task.sleep(nanoseconds: 500_000_000)
            guard let key = mockResetTokens[resetToken] else { throw AuthError.resetExpired }
            mockAccounts[key]?.password = newPassword
            mockResetTokens[resetToken] = nil
            return
        }

        let (_, statusCode) = try await post("/auth/forgot/reset",
                                             body: ["resetToken": resetToken,
                                                    "newPassword": newPassword])
        switch statusCode {
        case 200: return
        case 401: throw AuthError.resetExpired
        default:  throw AuthError.other
        }
    }

    // MARK: - validating hte  token (loading screen uses it)
    // true = token valid, false = server rejected it, throws = could not reach the server
    func validateToken(_ token: String) async throws -> Bool {

        if useMock {
            try await Task.sleep(nanoseconds: 500_000_000)
            return token == "test-token"
        }

        guard let url = URL(string: baseURL + "/auth/me") else {
            throw AuthError.other
        }

        var request = URLRequest(url: url)
        request.setValue("Bearer \(token)", forHTTPHeaderField: "Authorization")

        let (_, statusCode) = try await send(request)

        if statusCode == 200 { return true }
        if statusCode == 401 || statusCode == 404 { return false }
        throw AuthError.other
    }

    // MARK: - the shared helpers
    // builds a POST request with a JSON body and sends it to the BE
    private func post(_ path: String, body: [String: Any]) async throws -> (Data, Int) {
        guard let url = URL(string: baseURL + path) else { throw AuthError.other }

        var request = URLRequest(url: url)
        request.httpMethod = "POST"
        request.setValue("application/json", forHTTPHeaderField: "Content-Type")
        request.httpBody = try? JSONSerialization.data(withJSONObject: body)
        return try await send(request)
    }

    // sends the request and returns the data plus the status code
    // if the server can't be reached at all, it throws .network
    private func send(_ request: URLRequest) async throws -> (Data, Int) {
        do {
            let (data, response) = try await URLSession.shared.data(for: request)
            let statusCode = (response as? HTTPURLResponse)?.statusCode ?? 0
            return (data, statusCode)
        } catch {
            print("AuthService network error:", error)
            throw AuthError.network
        }
    }
}
