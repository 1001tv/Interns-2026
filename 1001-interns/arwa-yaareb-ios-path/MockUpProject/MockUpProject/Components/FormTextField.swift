import UIKit

final class FormTextField: UITextField {
    private let padding = UIEdgeInsets(top: 0, left: 16, bottom: 0, right: 16)
    
    init(placeholder: String, isSecure: Bool = false) {
        super.init(frame: .zero)
        self.placeholder = placeholder
        self.isSecureTextEntry = isSecure
        
        setupUI()
    }
    
    required init?(coder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }
    
    private func setupUI() {
        translatesAutoresizingMaskIntoConstraints = false
        heightAnchor.constraint(equalToConstant: 50).isActive = true
        backgroundColor = AppTheme.surfaceDark
        textColor = AppTheme.textPrimary
        font = .systemFont(ofSize: 15, weight: .regular)
        layer.cornerRadius = 10
        layer.borderWidth = 1
        layer.borderColor = AppTheme.borderDark.cgColor
        
        autocorrectionType = .no
        autocapitalizationType = .none
        
        attributedPlaceholder = NSAttributedString(
            string: placeholder ?? "",
            attributes: [.foregroundColor: AppTheme.textSecondary]
        )
    }
    
    override func textRect(forBounds bounds: CGRect) -> CGRect {
        bounds.inset(by: padding)
    }
    
    override func editingRect(forBounds bounds: CGRect) -> CGRect {
        bounds.inset(by: padding)
    }
    
    override func placeholderRect(forBounds bounds: CGRect) -> CGRect {
        bounds.inset(by: padding)
    }
}
