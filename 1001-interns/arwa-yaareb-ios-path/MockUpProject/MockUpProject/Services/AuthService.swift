import Foundation

enum AutheError {
    case network //if the issue was a netweork issue (e.g. could not reach the server)
    case other // if it was something else
}

final class AuthService { // finak class so iit cannot be subsclassed
    static let shared = AuthService()
    
    let useMock = true // ture until sarah is donw ith the backend (will change it to false later after hte backend link is fully developed)
    let baseURL = "" // BACKEND URL
    
    func validateToken(_ toke:String) async throws -> Bool { //this funciton takes token of type string, can take some time, and it can fail, and returns either ture/false
        
        if useMock{ // this is a fake test rn until the BE is ready
            try await Task.sleep(nanoseconds: 500_000_000) //0.5 second (used try bc await has to be used before any async
            return token == "test-token" // fake rn until BE is donw
        }
        
        guard let url = URL(string: baseURL + "/auth/me") else { // ‼️"/auth/me" (is the end point on the server, left to sarah to create it)
            
            throw AutheError.other
        } // if there is no url then give the other error
        
        var request = URLRequest(url: url)
        request.setValue("Bearer \(token)", forHTTPHeaderField: "Authorization")
        
        //send and recive the answer:
        do {
            let (_, response) = try await URLSession.shared.data(for: request)
            
            let statusCode = (response as? HTTPURLRespponse)? .statusCode ?? 0
            
            if statusCode == 200 {
                return true
            }
            if statusCode == 400 || statusCode == 404 {
                return false
            }
            throw AutheError.other
        }
        
        catch let error as AutheError {
            throw error
        } catch {
            throw AutheError.network
        }
        
        
    }
    
}
