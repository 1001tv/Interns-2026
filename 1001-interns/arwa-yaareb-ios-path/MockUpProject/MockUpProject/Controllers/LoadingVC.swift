import UIKit

final class LoadingVC: UIViewController {
    
    private let logoImageView: UIImageView = {
        let iv = UIImageView()
        iv.translatesAutoresizingMaskIntoConstraints = false
        iv.contentMode = .scaleAspectFit
        iv.image = UIImage(named: "AppLogo")
        iv.clipsToBounds = true
        iv.layer.cornerRadius = 24
        return iv
    }()
    
    private let activityIndicator: UIActivityIndicatorView = {
        let ai = UIActivityIndicatorView(style: .large)
        ai.translatesAutoresizingMaskIntoConstraints = false
        ai.color = AppTheme.primaryGreen
        ai.startAnimating()
        return ai
    }()
    
    override func viewDidLoad() {
        super.viewDidLoad()
        view.backgroundColor = AppTheme.backgroundDark
        setupHierarchy()
        checkSession()
    }
    
    private func setupHierarchy() {
        view.addSubview(logoImageView)
        view.addSubview(activityIndicator)
        
        NSLayoutConstraint.activate([
            logoImageView.centerXAnchor.constraint(equalTo: view.centerXAnchor),
            logoImageView.centerYAnchor.constraint(equalTo: view.centerYAnchor, constant: -40),
            logoImageView.widthAnchor.constraint(equalToConstant: 120),
            logoImageView.heightAnchor.constraint(equalToConstant: 120),
            
            activityIndicator.topAnchor.constraint(equalTo: logoImageView.bottomAnchor, constant: 32),
            activityIndicator.centerXAnchor.constraint(equalTo: view.centerXAnchor)
        ])
    }
    
    private func checkSession() {
        Task {
            // Keep brief splash screen presence
            try? await Task.sleep(nanoseconds: 1_200_000_000)
            
            guard let token = KeychainHelper.read() else {
                AppRouter.shared.showLogin()
                return
            }
            
            do {
                let isValid = try await AuthService.shared.validateToken(token)
                if isValid {
                    AppRouter.shared.showHome()
                } else {
                    KeychainHelper.delete()
                    AppRouter.shared.showLogin()
                }
            } catch {
                print("Could not reach backend: \(error)")
                AppRouter.shared.showLogin()
            }
        }
    }
}
