import Foundation

struct RegistrationInfo {
    let name: String
    let email: String
    let password: String
    let age: Int
    let favoriteGenre: [String] // this one is optional so it can be empty
    let securityQuestion: String
    let secruityAnswer: String
}
