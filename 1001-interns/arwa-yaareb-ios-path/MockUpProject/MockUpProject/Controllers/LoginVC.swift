import UIKit

final class LoginVC: UIViewController {

    private let emailField = FormTextField(placeholder: "Email")
    private let passwordField = FormTextField(placeholder: "Password")
    private let errorLabel = UILabel()
    private let loginButton = PrimaryButton(title: "Log in")
    private let forgotButton = UIButton(type: .system)
    private let signUpButton = UIButton(type: .system)

    private var failedAttempts = 0 // counts wrong passwords, after 5 we offer to register

    override func viewDidLoad() {
        super.viewDidLoad()
        view.backgroundColor = .systemBackground
        title = "Log in"
        setupUI()
    }

    private func setupUI() {
        emailField.keyboardType = .emailAddress
        emailField.textContentType = .username

        passwordField.isSecureTextEntry = true
        passwordField.textContentType = .password

        errorLabel.textColor = .systemRed
        errorLabel.font = .systemFont(ofSize: 14)
        errorLabel.numberOfLines = 0
        errorLabel.isHidden = true

        loginButton.addTarget(self, action: #selector(loginTapped), for: .touchUpInside)

        forgotButton.setTitle("Forgot password?", for: .normal)
        forgotButton.addTarget(self, action: #selector(forgotTapped), for: .touchUpInside)

        signUpButton.setTitle("Create an account", for: .normal)
        signUpButton.addTarget(self, action: #selector(signUpTapped), for: .touchUpInside)

        let stack = UIStackView(arrangedSubviews: [
            emailField,
            passwordField,
            errorLabel,
            loginButton,
            forgotButton,
            signUpButton
        ])
        stack.axis = .vertical
        stack.spacing = 16
        stack.translatesAutoresizingMaskIntoConstraints = false
        view.addSubview(stack)

        NSLayoutConstraint.activate([
            stack.leadingAnchor.constraint(equalTo: view.leadingAnchor, constant: 24),
            stack.trailingAnchor.constraint(equalTo: view.trailingAnchor, constant: -24),
            stack.centerYAnchor.constraint(equalTo: view.centerYAnchor)
        ])
    }

    //MARK: here are the cctions

    @objc private func forgotTapped() { //takes the user to the ForgotPasswordVC
        navigationController?.pushViewController(ForgotPasswordVC(), animated: true)
    }

    @objc private func signUpTapped() { //takes the user to the SignUPVC
        navigationController?.pushViewController(SignUpVC(), animated: true)
    }

    @objc private func loginTapped() { //returns true so we can navigate to home, and checks the feild before sending the data to the server (layer 1 of securityt)
        view.endEditing(true) // hides the keyboard
        showError(nil)

        let email = (emailField.text ?? "").trimmingCharacters(in: .whitespaces).lowercased()
        let password = passwordField.text ?? ""

        //required stuff before sendig to the server
        guard email.contains("@"), email.contains("."), !password.isEmpty else {
            showError("Enter a valid email and password.")
            return
        }

        // 2. after data is confirmed send it to server and block extra taps while we wait
        loginButton.setLoading(true)
        
        Task {
            defer { loginButton.setLoading(false) }
                
            do {
                let token = try await AuthService.shared.login(email: email, password: password)
                AppRouter.shared.didLogin(token: token)
            } catch AuthError.userNotFound {
                offerRegister(message: "No account was found with this email. Want to create one?")
            } catch AuthError.invalidCredentials {
                failedAttempts += 1
                showError("Wrong email or password.")
                if failedAttempts >= 5 {
                    failedAttempts = 0
                    offerRegister(message: "Having trouble logging in? You can create an account instead.")
                }
            } catch AuthError.tooManyAttempts {
                showError("Too many attempts. Please try again later.")
            } catch AuthError.network {
                showError("Can't reach the server. Check your connection.")
            } catch {
                showError("Something went wrong. Please try again.")
            }
        }
    }

    // MARK: the helpers funcitons

    private func showError(_ message: String?) {
        errorLabel.text = message
        errorLabel.isHidden = (message == nil)
    }

    private func offerRegister(message: String) {
        let alert = UIAlertController(title: "Need an account?", message: message, preferredStyle: .alert)
        alert.addAction(UIAlertAction(title: "Register", style: .default) { [weak self] _ in
            self?.signUpTapped()
        })
        alert.addAction(UIAlertAction(title: "Not now", style: .cancel))
        present(alert, animated: true)
    }
}
