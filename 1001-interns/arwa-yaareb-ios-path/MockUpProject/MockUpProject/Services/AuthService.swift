import Foundation

enum AuthError: Error {
    case network // we could not reach the server (for example, no internet)
    case other   // the server answered with something unexpected
}

final class AuthService {
    static let shared = AuthService()

    let useMock = true  // true until Sarah's backend is ready, then change to false
    let baseURL = ""    // backend URL, Sarah gives it to us

    // true = token valid, false = server rejected it, throws = could not reach the server
    func validateToken(_ token: String) async throws -> Bool {

        if useMock {
            try await Task.sleep(nanoseconds: 500_000_000) // 0.5 seconds
            return token == "test-token"
        }

        // "/auth/me" is the endpoint Sarah creates on the server
        guard let url = URL(string: baseURL + "/auth/me") else {
            throw AuthError.other
        }

        var request = URLRequest(url: url)
        request.setValue("Bearer \(token)", forHTTPHeaderField: "Authorization")

        do {
            let (_, response) = try await URLSession.shared.data(for: request)
            let statusCode = (response as? HTTPURLResponse)?.statusCode ?? 0

            if statusCode == 200 { return true }
            if statusCode == 401 || statusCode == 404 { return false }
            throw AuthError.other
        } catch let error as AuthError {
            throw error
        } catch {
            throw AuthError.network
        }
    }
}
