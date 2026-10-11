import UIKit

final class PrimaryButton: UIButton {

    enum Style {
        case filled        // thi is the normal main button
        case destructive   // red, for things like Log out
    }

    init(title: String, style: Style = .filled) {
        super.init(frame: .zero)

        var config = UIButton.Configuration.filled()
        config.title = title
        config.cornerStyle = .medium
        config.baseBackgroundColor = (style == .destructive) ? .systemRed : .systemBlue
        configuration = config

        translatesAutoresizingMaskIntoConstraints = false
        heightAnchor.constraint(equalToConstant: 50).isActive = true
    }

    required init?(coder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }

    // shows a spinner inside the button and blocks extra taps while a request is running
    func setLoading(_ loading: Bool) {
        configuration?.showsActivityIndicator = loading
        isEnabled = !loading
    }
}
