import Foundation

enum AuthError: Error {
    case invalidCredentials // wrong password
    case userNotFound       // no account with this email
    case emailExists        // sign up with an email that is already taken
    case tooManyAttempts    // the server is rate limiting us
    case network            // could not reach the server
    case other              // the server answered with something unexpected
}

// the shape of the JSON Sarah's login sends back (extra fields are ignored)
private struct LoginResponse: Decodable {
    let message: String
    let token: String
}

@MainActor // every screen calls this from the main thread, so we say it out loud
final class AuthService {
    static let shared = AuthService()

    let useMock = true                          // true = fake answers, no server needed
    let baseURL = "http://10.21.4.84:3000"      // Sarah's server on ZeroTier

    // fake "database" used only in mock mode: email -> password (resets when the app restarts)
    private var mockAccounts = ["sarah@test.com": "test123"]

    // MARK: - Login
    // returns the token if the login worked, throws an AuthError if not
    func login(email: String, password: String) async throws -> String {

        if useMock {
            try await Task.sleep(nanoseconds: 500_000_000)
            guard let savedPassword = mockAccounts[email.lowercased()] else {
                throw AuthError.userNotFound
            }
            if savedPassword != password { throw AuthError.invalidCredentials }
            return "test-token"
        }

        guard let url = URL(string: baseURL + "/auth/login") else {
            throw AuthError.other
        }

        var request = URLRequest(url: url)
        request.httpMethod = "POST"
        request.setValue("application/json", forHTTPHeaderField: "Content-Type")
        request.httpBody = try? JSONSerialization.data(withJSONObject: ["email": email,
                                                                        "password": password])

        let (data, statusCode) = try await send(request)

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
            if mockAccounts[info.email.lowercased()] != nil { throw AuthError.emailExists }
            mockAccounts[info.email.lowercased()] = info.password
            return
        }

        guard let url = URL(string: baseURL + "/auth/register") else {
            throw AuthError.other
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

        var request = URLRequest(url: url)
        request.httpMethod = "POST"
        request.setValue("application/json", forHTTPHeaderField: "Content-Type")
        request.httpBody = try? JSONSerialization.data(withJSONObject: body)

        let (_, statusCode) = try await send(request)

        switch statusCode {
        case 200, 201: return
        case 409:      throw AuthError.emailExists
        case 429:      throw AuthError.tooManyAttempts
        default:       throw AuthError.other
        }
    }

    // MARK: - Validate token (used by the loading screen)
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

    // MARK: - Shared helper
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
