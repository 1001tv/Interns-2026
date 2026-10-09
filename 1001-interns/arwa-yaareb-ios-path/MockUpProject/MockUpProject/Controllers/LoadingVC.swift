import UIKit

final class LoadingVC: UIViewController {
    private let logoLable = UILabel()
    private let spinner = UIActivityIndicatorView(style: .large)
    
    override func viewDidLoad() {
        super.viewDidLoad()
        view.backgroundColor = .black
        setupUI()
    }
    
    private func setupUI(){
        logoLable.text = "1001"
        logoLable.font = .systemFont(ofSize: 48, weight: .bold)
        logoLable.textColor = .white
        
        spinner.color = .white
        spinner.startAnimating()
        
        //creating our stack(like culumns)
        let stack = UIStackView(arrangedSubviews: [logoLable, spinner])
        stack.axis = .vertical
        stack.spacing = 24
        stack.alignment = .center
        stack.translatesAutoresizingMaskIntoConstraints = false //makes the autolayout work
        view.addSubview(stack) //show it after we have configured it above
        
        NSLayoutConstraint.activate([ //activating the position for each of them to be centered
            stack.centerXAnchor.constraint(equalTo: view.centerXAnchor),
            stack.centerYAnchor.constraint(equalTo: view.centerYAnchor)
        ])
        
        
    }
}
