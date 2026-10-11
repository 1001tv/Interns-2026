import UIKit

final class LoadingVC: UIViewController {

    private let logoLabel = UILabel()
    private let spinner = UIActivityIndicatorView(style: .large)

    override func viewDidLoad() {
        super.viewDidLoad()
        view.backgroundColor = .black
        setupUI()
    }

    // runs when the screen is actually visible, so the user always sees loading first
    override func viewDidAppear(_ animated: Bool) {
        super.viewDidAppear(animated)
        checkSession()
    }

    private func setupUI() {
        logoLabel.text = "1001"
        logoLabel.font = .systemFont(ofSize: 48, weight: .bold)
        logoLabel.textColor = .white

        spinner.color = .white
        spinner.startAnimating()

        // creating our stack (like a column)
        let stack = UIStackView(arrangedSubviews: [logoLabel, spinner])
        stack.axis = .vertical
        stack.spacing = 24
        stack.alignment = .center
        stack.translatesAutoresizingMaskIntoConstraints = false // makes autolayout work
        view.addSubview(stack) // show it after we configured it above

        NSLayoutConstraint.activate([ // centers the stack on the screen
            stack.centerXAnchor.constraint(equalTo: view.centerXAnchor),
            stack.centerYAnchor.constraint(equalTo: view.centerYAnchor)
        ])
    }

    private func checkSession() {
        Task {
            
            try? await Task.sleep(nanoseconds: 2_000_000_000) // TEMPORARY: holds the loading screen for 2 seconds
            
            // 1. is there a token saved on the phone?
            guard let token = KeychainHelper.read(), !token.isEmpty else {
                AppRouter.shared.showLogin()
                return
            }

            // 2. there is one, so ask the server if it is still valid
            do {
                if try await AuthService.shared.validateToken(token) {
                    AppRouter.shared.showHome()      // server said valid
                } else {
                    KeychainHelper.delete()          // server said rejected
                    AppRouter.shared.showLogin()
                }
            } catch {
                // 3. could not reach the server: keep the token, do not log out
                print("Could not reach the server")
            }
        }
    }
}
