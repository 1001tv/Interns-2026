import UIKit

final class PrimaryButton: UIButton {
    enum Style {
        case filled
        case destructive
        case outline
    }
    
    private let style: Style
    private let spinner = UIActivityIndicatorView(style: .medium)
    private var originalTitle: String?
    
    init(title: String, style: Style = .filled) {
        self.style = style
        super.init(frame: .zero)
        self.originalTitle = title
        
        setupStyle()
        setTitle(title, for: .normal)
    }
    
    required init?(coder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }
    
    private func setupStyle() {
        translatesAutoresizingMaskIntoConstraints = false
        heightAnchor.constraint(equalToConstant: 50).isActive = true
        layer.cornerRadius = 12
        titleLabel?.font = .systemFont(ofSize: 16, weight: .bold)
        
        spinner.translatesAutoresizingMaskIntoConstraints = false
        spinner.hidesWhenStopped = true
        addSubview(spinner)
        
        NSLayoutConstraint.activate([
            spinner.centerXAnchor.constraint(equalTo: centerXAnchor),
            spinner.centerYAnchor.constraint(equalTo: centerYAnchor)
        ])
        
        switch style {
        case .filled:
            backgroundColor = AppTheme.primaryGreen
            setTitleColor(.black, for: .normal)
            spinner.color = .black
        case .destructive:
            backgroundColor = AppTheme.errorRed
            setTitleColor(.white, for: .normal)
            spinner.color = .white
        case .outline:
            backgroundColor = .clear
            layer.borderWidth = 1.5
            layer.borderColor = AppTheme.primaryGreen.cgColor
            setTitleColor(AppTheme.primaryGreen, for: .normal)
            spinner.color = AppTheme.primaryGreen
        }
    }
    
    func setLoading(_ loading: Bool) {
        isEnabled = !loading
        if loading {
            setTitle("", for: .normal)
            spinner.startAnimating()
        } else {
            setTitle(originalTitle, for: .normal)
            spinner.stopAnimating()
        }
    }
}
