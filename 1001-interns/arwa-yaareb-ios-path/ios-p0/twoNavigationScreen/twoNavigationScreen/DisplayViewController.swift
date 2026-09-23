//
//  DisplayViewController.swift
//  twoNavigationScreen
//
//  Created by Arwa Yaareb on 23/09/2026.
//

import UIKit

class DisplayViewController: UIViewController {

// we should get the data passed from Prfileformviewcontroller through the init
// and we need to diplay them
    
    //intializing the values in this screen so we can pass values to them
    private let name: String
    private let email: String
        
        private let summaryLabel: UILabel = {
            let label = UILabel()
            label.numberOfLines = 0 // Allow multiple lines
            label.textAlignment = .center
            label.font = .systemFont(ofSize: 18, weight: .medium)
            return label
        }()
    init(name: String, email: String){
        self.name = name
        self.email = email
        super.init(nibName: nil, bundle: nil) // bc rn we do not have vlues for them so it's ok for the to be nil
    }
    required init?(coder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }
    

    override func viewDidLoad() {
        super.viewDidLoad()
        title = "Display Page"
        view.backgroundColor = .systemBackground
                
                // diisplaying the passed values
                summaryLabel.text = "Name: \(name)\nEmail: \(email)"
                
                setupLayout()
    }
    
    // the styling on the sscreen
    private func setupLayout() {
            summaryLabel.translatesAutoresizingMaskIntoConstraints = false
            view.addSubview(summaryLabel)
            
            NSLayoutConstraint.activate([
                summaryLabel.centerXAnchor.constraint(equalTo: view.centerXAnchor),
                summaryLabel.centerYAnchor.constraint(equalTo: view.centerYAnchor),
                summaryLabel.leadingAnchor.constraint(equalTo: view.leadingAnchor, constant: 20),
                summaryLabel.trailingAnchor.constraint(equalTo: view.trailingAnchor, constant: -20)
            ])
        }
}
