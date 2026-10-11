import Foundation

// everything the sign up form collects, bundled into one value
struct RegistrationInfo {
    let name: String
    let email: String
    let password: String
    let age: Int
    let favoriteGenres: [String]   // optional on screen, so it can be empty
    let securityQuestion: String
    let securityAnswer: String
}
