import UIKit

final class LoginVC: UIViewController{
    override func viewDidLoad() {
        super.viewDidLoad()
        view.backgroundColor = .systemBackground
        title = "Log in"
        
        let button = UIButton(type: .system)
        button.setTitle("fake login temp", for: .normal)
        button.addTarget(self, action: #selector(fakeLogin), for: .touchUpInside)
        button.translatesAutoresizingMaskIntoConstraints = false
        view.addSubview(button)
        
        NSLayoutConstraint.activate([
            button.centerXAnchor.constraint(equalTo: view.centerXAnchor),
            button.centerYAnchor.constraint(equalTo: view.centerYAnchor)
        ])
        
    }
    
    @objc private func fakeLogin(){
        AppRouter.shared.didLogin(token: "test-token")
    }
    
    
}

