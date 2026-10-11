import UIKit

final class SignUpVC: UIViewController {

    private let nameField = FormTextField(placeholder: "Name")
    private let emailField = FormTextField(placeholder: "Email")
    private let passwordField = FormTextField(placeholder: "Password (at least 8 characters)")
    private let ageField = FormTextField(placeholder: "Age")
    private let answerField = FormTextField(placeholder: "Your answer")
    private let questionButton = UIButton(type: .system)
    private let errorLabel = UILabel()
    private let signUpButton = PrimaryButton(title: "Create account")

    private var genreButtons: [UIButton] = []   // one chip per genre
    private var selectedQuestion: String?       // nil until the user picks one

    override func viewDidLoad() {
        super.viewDidLoad()
        view.backgroundColor = .systemBackground
        title = "Sign up"
        setupFields()
        setupQuestionMenu()
        setupLayout()
    }

    // MARK: - Setup

    private func setupFields() {
        nameField.autocapitalizationType = .words
        emailField.keyboardType = .emailAddress
        passwordField.isSecureTextEntry = true
        ageField.keyboardType = .numberPad

        errorLabel.textColor = .systemRed
        errorLabel.font = .systemFont(ofSize: 14)
        errorLabel.numberOfLines = 0
        errorLabel.isHidden = true

        signUpButton.addTarget(self, action: #selector(signUpTapped), for: .touchUpInside)
    }

    // the dropdown: a button that opens a menu with all the questions
    private func setupQuestionMenu() {
        var config = UIButton.Configuration.gray()
        config.title = "Choose a security question"
        config.cornerStyle = .medium
        questionButton.configuration = config
        questionButton.showsMenuAsPrimaryAction = true
        questionButton.heightAnchor.constraint(equalToConstant: 48).isActive = true

        let actions = SignUpOptions.securityQuestions.map { question in
            UIAction(title: question) { [weak self] _ in
                self?.selectedQuestion = question
                self?.questionButton.configuration?.title = question
            }
        }
        questionButton.menu = UIMenu(children: actions)
    }

    // genre chips: tap to select, tap again to deselect. 3 chips per row
    private func makeGenreRows() -> [UIStackView] {
        genreButtons = SignUpOptions.genres.map { genre in
            let button = UIButton(type: .system)
            button.changesSelectionAsPrimaryAction = true   // each tap flips isSelected
            button.configurationUpdateHandler = { button in
                var config = button.isSelected ? UIButton.Configuration.filled()
                                               : UIButton.Configuration.tinted()
                config.title = genre
                config.cornerStyle = .capsule
                button.configuration = config
            }
            button.setNeedsUpdateConfiguration()   // draws the chip the first time
            return button
        }

        var rows: [UIStackView] = []
        for start in stride(from: 0, to: genreButtons.count, by: 3) {
            let end = min(start + 3, genreButtons.count)
            let row = UIStackView(arrangedSubviews: Array(genreButtons[start..<end]))
            row.axis = .horizontal
            row.spacing = 8
            row.distribution = .fillEqually
            rows.append(row)
        }
        return rows
    }

    private func makeCaption(_ text: String) -> UILabel {
        let label = UILabel()
        label.text = text
        label.font = .systemFont(ofSize: 14, weight: .semibold)
        label.textColor = .secondaryLabel
        return label
    }

    private func setupLayout() {
        // a scroll view, because the form is longer than the screen
        let scrollView = UIScrollView()
        scrollView.keyboardDismissMode = .interactive   // drag down to hide the keyboard
        scrollView.translatesAutoresizingMaskIntoConstraints = false
        view.addSubview(scrollView)

        var rows: [UIView] = [nameField, emailField, passwordField, ageField,
                              makeCaption("Favorite genres (optional)")]
        rows += makeGenreRows()
        rows += [makeCaption("Security question (used to reset your password)"),
                 questionButton, answerField, errorLabel, signUpButton]

        let stack = UIStackView(arrangedSubviews: rows)
        stack.axis = .vertical
        stack.spacing = 16
        stack.translatesAutoresizingMaskIntoConstraints = false
        scrollView.addSubview(stack)

        NSLayoutConstraint.activate([
            scrollView.topAnchor.constraint(equalTo: view.safeAreaLayoutGuide.topAnchor),
            scrollView.leadingAnchor.constraint(equalTo: view.leadingAnchor),
            scrollView.trailingAnchor.constraint(equalTo: view.trailingAnchor),
            scrollView.bottomAnchor.constraint(equalTo: view.keyboardLayoutGuide.topAnchor), // moves up with the keyboard

            stack.topAnchor.constraint(equalTo: scrollView.contentLayoutGuide.topAnchor, constant: 24),
            stack.bottomAnchor.constraint(equalTo: scrollView.contentLayoutGuide.bottomAnchor, constant: -24),
            stack.leadingAnchor.constraint(equalTo: scrollView.contentLayoutGuide.leadingAnchor, constant: 24),
            stack.trailingAnchor.constraint(equalTo: scrollView.contentLayoutGuide.trailingAnchor, constant: -24),
            stack.widthAnchor.constraint(equalTo: scrollView.frameLayoutGuide.widthAnchor, constant: -48)
        ])
    }

    // MARK: - Actions

    @objc private func signUpTapped() {
        view.endEditing(true)
        showError(nil)

        // 1. check everything locally first
        guard let info = validate() else { return }

        // 2. create the account, then log in to get the token
        signUpButton.setLoading(true)
        Task {
            defer { signUpButton.setLoading(false) }

            do {
                try await AuthService.shared.register(info)
                let token = try await AuthService.shared.login(email: info.email,
                                                               password: info.password)
                AppRouter.shared.didLogin(token: token)
            } catch AuthError.emailExists {
                showError("An account with this email already exists. Try logging in.")
            } catch AuthError.tooManyAttempts {
                showError("Too many attempts. Please try again later.")
            } catch AuthError.network {
                showError("Can't reach the server. Check your connection.")
            } catch {
                showError("Something went wrong. Please try again.")
            }
        }
    }

    // MARK: - Helpers

    // returns the filled-in form, or nil after showing the first problem it finds
    private func validate() -> RegistrationInfo? {
        let name = (nameField.text ?? "").trimmingCharacters(in: .whitespaces)
        let email = (emailField.text ?? "").trimmingCharacters(in: .whitespaces).lowercased()
        let password = passwordField.text ?? ""
        let answer = (answerField.text ?? "").trimmingCharacters(in: .whitespaces)

        guard !name.isEmpty else {
            showError("Please enter your name."); return nil
        }
        guard email.contains("@"), email.contains(".") else {
            showError("Please enter a valid email."); return nil
        }
        guard password.count >= 8 else {
            showError("Your password must be at least 8 characters."); return nil
        }
        guard let age = Int(ageField.text ?? ""), age > 0, age < 120 else {
            showError("Please enter a valid age."); return nil
        }
        guard let question = selectedQuestion else {
            showError("Please choose a security question."); return nil
        }
        guard !answer.isEmpty else {
            showError("Please answer your security question."); return nil
        }

        // the genres whose chip is selected (optional, can be empty)
        let genres = zip(SignUpOptions.genres, genreButtons)
            .filter { $0.1.isSelected }
            .map { $0.0 }

        return RegistrationInfo(name: name, email: email, password: password, age: age,
                                favoriteGenres: genres, securityQuestion: question,
                                securityAnswer: answer)
    }

    private func showError(_ message: String?) {
        errorLabel.text = message
        errorLabel.isHidden = (message == nil)
    }
}
