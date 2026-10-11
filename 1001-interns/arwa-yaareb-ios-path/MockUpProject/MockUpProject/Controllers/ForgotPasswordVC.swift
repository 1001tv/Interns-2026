import UIKit

final class ForgotPasswordVC: UIViewController {

    // the three steps of the screen // one case with 3 steps
    private enum Step { case email, answer, newPassword }

    private var step: Step = .email
    private var email = ""        // remembered from step 1
    private var resetToken = ""   // given by the server after a correct answer (step 2)

    private let instructionLabel = UILabel()
    private let emailField = FormTextField(placeholder: "Your account email")
    private let questionLabel = UILabel()
    private let answerField = FormTextField(placeholder: "Your answer")
    
    //the new password:
    private let newPasswordField = FormTextField(placeholder: "New password (at least 8 characters)")
    private let confirmField = FormTextField(placeholder: "Confirm new password")
    private let errorLabel = UILabel()
    private let actionButton = PrimaryButton(title: "Continue")

    override func viewDidLoad() {
        super.viewDidLoad()
        view.backgroundColor = .systemBackground
        title = "Forgot password"
        setupUI()
        updateUI()
    }

    // MARK: - the setup

    private func setupUI() {
        instructionLabel.numberOfLines = 0
        instructionLabel.font = .systemFont(ofSize: 16)
        instructionLabel.textColor = .secondaryLabel

        emailField.keyboardType = .emailAddress

        questionLabel.numberOfLines = 0
        questionLabel.font = .systemFont(ofSize: 18, weight: .semibold)

        newPasswordField.isSecureTextEntry = true
        confirmField.isSecureTextEntry = true

        errorLabel.textColor = .systemRed
        errorLabel.font = .systemFont(ofSize: 14)
        errorLabel.numberOfLines = 0
        errorLabel.isHidden = true

        actionButton.addTarget(self, action: #selector(actionTapped), for: .touchUpInside)

        let stack = UIStackView(arrangedSubviews: [instructionLabel, emailField, questionLabel,
                                                   answerField, newPasswordField, confirmField,
                                                   errorLabel, actionButton])
        stack.axis = .vertical
        stack.spacing = 16
        stack.translatesAutoresizingMaskIntoConstraints = false
        view.addSubview(stack)

        NSLayoutConstraint.activate([
            stack.topAnchor.constraint(equalTo: view.safeAreaLayoutGuide.topAnchor, constant: 32),
            stack.leadingAnchor.constraint(equalTo: view.leadingAnchor, constant: 24),
            stack.trailingAnchor.constraint(equalTo: view.trailingAnchor, constant: -24)
        ])
    }

    // shows only what the current step needs (hidden views take no space in a stack)
    private func updateUI() {
        showError(nil)

        emailField.isHidden = (step != .email)
        questionLabel.isHidden = (step != .answer)
        answerField.isHidden = (step != .answer)
        newPasswordField.isHidden = (step != .newPassword)
        confirmField.isHidden = (step != .newPassword)

        switch step {
        case .email:
            instructionLabel.text = "Enter the email of your account. We will ask you the security question you chose when you signed up."
            actionButton.configuration?.title = "Continue"
        case .answer:
            instructionLabel.text = "Answer your security question to prove it is you."
            actionButton.configuration?.title = "Verify answer"
        case .newPassword:
            instructionLabel.text = "Choose a new password."
            actionButton.configuration?.title = "Change password"
        }
    }

    // MARK: - Actions

    @objc private func actionTapped() {
        view.endEditing(true)
        showError(nil)

        switch step {
        case .email:       submitEmail()
        case .answer:      submitAnswer()
        case .newPassword: submitNewPassword()
        }
    }

    // step 1: find the account and show its security question
    private func submitEmail() {
        let typed = (emailField.text ?? "").trimmingCharacters(in: .whitespaces).lowercased()
        guard typed.contains("@"), typed.contains(".") else {
            showError("Please enter a valid email.")
            return
        }

        actionButton.setLoading(true)
        Task {
            defer { actionButton.setLoading(false) }

            do {
                let question = try await AuthService.shared.securityQuestion(for: typed)
                email = typed
                questionLabel.text = question
                step = .answer
                updateUI()
            } catch AuthError.userNotFound {
                showError("No account was found with this email.")
            } catch AuthError.tooManyAttempts {
                showError("Too many attempts. Please try again later.")
            } catch AuthError.network {
                showError("Can't reach the server. Check your connection.")
            } catch {
                showError("Something went wrong. Please try again.")
            }
        }
    }

    // step 2: check the answer, the server gives us a reset token if it is right
    private func submitAnswer() {
        let answer = (answerField.text ?? "").trimmingCharacters(in: .whitespaces)
        guard !answer.isEmpty else {
            showError("Please type your answer.")
            return
        }

        actionButton.setLoading(true)
        Task {
            defer { actionButton.setLoading(false) }

            do {
                resetToken = try await AuthService.shared.verifySecurityAnswer(email: email,
                                                                               answer: answer)
                step = .newPassword
                updateUI()
            } catch AuthError.wrongAnswer {
                showError("That answer is not correct. Please try again.")
            } catch AuthError.tooManyAttempts {
                showError("Too many wrong answers. Please try again later.")
            } catch AuthError.network {
                showError("Can't reach the server. Check your connection.")
            } catch {
                showError("Something went wrong. Please try again.")
            }
        }
    }

    // step 3: set the new password using the reset token
    private func submitNewPassword() {
        let newPassword = newPasswordField.text ?? ""
        let confirm = confirmField.text ?? ""

        guard newPassword.count >= 8 else {
            showError("Your password must be at least 8 characters.")
            return
        }
        guard newPassword == confirm else {
            showError("The two passwords do not match.")
            return
        }

        actionButton.setLoading(true)
        Task {
            defer { actionButton.setLoading(false) }

            do {
                try await AuthService.shared.resetPassword(resetToken: resetToken,
                                                           newPassword: newPassword)
                showSuccess()
            } catch AuthError.resetExpired {
                // the token is no longer valid, so start again from the beginning
                resetToken = ""
                step = .email
                updateUI()
                showError("This reset session expired. Please start again.")
            } catch AuthError.network {
                showError("Can't reach the server. Check your connection.")
            } catch {
                showError("Something went wrong. Please try again.")
            }
        }
    }

    // MARK: - Helpers

    private func showError(_ message: String?) {
        errorLabel.text = message
        errorLabel.isHidden = (message == nil)
    }

    private func showSuccess() {
        let alert = UIAlertController(title: "Password changed",
                                      message: "You can now log in with your new password.",
                                      preferredStyle: .alert)
        alert.addAction(UIAlertAction(title: "OK", style: .default) { [weak self] _ in
            self?.navigationController?.popViewController(animated: true)
        })
        present(alert, animated: true)
    }
}
