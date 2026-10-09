import UIKit

@MainActor //must run on the main thread
final class AppRouter {
    static let shared = AppRouter()
    var window: UIWindow?
    
    private func setRoot(_ vc:UIViewController){
        guard let window else { return }
        UIView.transition(with: window, duration: 0.3, options: .transitionCrossDissolve){
            window.rootViewController = vc //as the scren is fading it shows the vc in frame
        }
    }
    func showLogin(){
        setRoot(UINavigationController(rootViewController: LoginVC()))
    }
    
    func showHome(){
        setRoot(HomeVC())
    }
    
    func didLogin(token: String) { //if login happens then it takes the given token saves to our KeychainHelper adn diaplys home
            KeychainHelper.save(token)
            showHome()
        }
    func logout(){
        KeychainHelper.delete()
        showLogin()
    }
    
}
