import UIKit

final class FormTextField: UITextField {

    init(placeholder: String) {
        super.init(frame: .zero)
        self.placeholder = placeholder
        borderStyle = .roundedRect
        autocorrectionType = .no
        autocapitalizationType = .none
        heightAnchor.constraint(equalToConstant: 48).isActive = true
    }

    required init?(coder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }
}
