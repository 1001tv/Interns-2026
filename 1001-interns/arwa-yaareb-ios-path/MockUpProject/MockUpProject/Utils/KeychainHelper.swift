//importance of this file is the user logs in once and stays logged in until they logout.
//how it works: The backend gives the app a token (a long secret string that proves who you are). The app must save it somewhere and it must survive hte closing state.

import Foundation //basic apple's toolkit. we are using it bc: keychain stores data in bytes and Data is the swift type for raw bytes.
import Security //apple framework for using keychain


enum KeychainHelper { //  using enum as a namespace just so we can group funcitons instead of scattering them around
    private static let account = "authToken" //

    static func save(_ token: String) {
        let query: [String: Any] = [
            kSecClass as String: kSecClassGenericPassword,
            kSecAttrAccount as String: account
        ]
        SecItemDelete(query as CFDictionary)
        var add = query
        add[kSecValueData as String] = Data(token.utf8)
        SecItemAdd(add as CFDictionary, nil)
    }

    static func read() -> String? {
        let query: [String: Any] = [
            kSecClass as String: kSecClassGenericPassword,
            kSecAttrAccount as String: account,
            kSecReturnData as String: true,
            kSecMatchLimit as String: kSecMatchLimitOne
        ]
        var result: AnyObject?
        guard SecItemCopyMatching(query as CFDictionary, &result) == errSecSuccess,
              let data = result as? Data else { return nil }
        return String(data: data, encoding: .utf8)
    }

    static func delete() {
        let query: [String: Any] = [
            kSecClass as String: kSecClassGenericPassword,
            kSecAttrAccount as String: account
        ]
        SecItemDelete(query as CFDictionary)
    }
}


//this helper will be called later in other files so we can store the token form the user after it is sent by the backend.
/*
 KeychainHelper.save(token)   // after login succeeds
 KeychainHelper.read()        // used when loading screen: is there a token?
 KeychainHelper.delete()      // when user logout
 */
