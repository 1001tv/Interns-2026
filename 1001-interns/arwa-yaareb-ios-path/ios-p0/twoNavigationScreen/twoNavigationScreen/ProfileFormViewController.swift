//
//  ProfileFormViewController.swift
//  twoNavigationScreen
//
//  Created by Arwa Yaareb on 22/09/2026.
//

import UIKit
//we need 3 things in here:
// 2 text inputs
// a submit button to trigger validation and navigation to  to the next screen
//UIStckView a stack view which helps allgihing those ui elements vertically in a stack view. this allows me to do auto layout so it can work nicely on different  screens
// a target action a #selector to connect the action didTabSubmit when it is triggered
// i need a validation method ( i will use the guard let method) and it will only check if the fields are empty (if emoty -> it should display an alert dialog).
// to trandsfer data from the form screen to the display screen i will use hte pushViewController


class ProfileFormViewController: UIViewController {
    // creating the ui elemets of the page:
    private let nameTextField: UITextField = {
        // the uiTextField is named nameTextField
        let tf = UITextField() //the variable in which the values will be saved to. it is a UITextField contant rn
        tf.placeholder = "enter full name" // placeholder inside the texg fiekld
        tf.borderStyle = .roundedRect //makes the field rounded
        return tf
        
    }()
    
    private let emailTextField: UITextField = {
        let tf = UITextField();
        tf.placeholder = "enter email address" // placeholder inside the texg fiekld
        tf.borderStyle = .roundedRect //makes the field rounded
        tf.keyboardType = .emailAddress //makese sure the type written in this texteField is emial type
        return tf
        
    }()
    
    private let submittionButton: UIButton = {
        let btn = UIButton(type: .system) // Added system type so the button text is visible and styled like iOS
        btn.setTitle("submit info", for: .normal) // this
        btn.titleLabel?.font = .boldSystemFont(ofSize: 16)
        return btn
    }()
    
    override func viewDidLoad() {
        super.viewDidLoad()

        //navigation bar title and background color:
        title = "Profile Form";
        view.backgroundColor = .systemBackground //this is a standard color
        
        setupLayout()
        
        // and wehne we load we want the action of submitButton to be attached to the button UI we created above.
        // FIX 1: Matched function name (didTapSubmit) and added dot prefix to .touchUpInside
        submittionButton.addTarget(self, action: #selector(didTapSubmit), for: .touchUpInside)// this is the logic that wehn the btn is touchUpInside (the best option for apps on a phone)
    }
    
    // setting up the layuout usign the setupLayout stack view :
    private func setupLayout(){
        let stackView = UIStackView(
            // arranging the elements inside this stack veritically|:
            arrangedSubviews:
                [nameTextField,
                 emailTextField,
                 submittionButton]
        )
        // adding styling
        stackView.axis = .vertical
        stackView.spacing = 16
        stackView.translatesAutoresizingMaskIntoConstraints = false //  this is what enable auto layout
                
        view.addSubview(stackView)
        //the  Constraints (centering and side margins)
        NSLayoutConstraint.activate([
            stackView.centerXAnchor.constraint(equalTo: view.centerXAnchor),
            stackView.centerYAnchor.constraint(equalTo: view.centerYAnchor),
            stackView.leadingAnchor.constraint(equalTo: view.leadingAnchor, constant: 20),
            stackView.trailingAnchor.constraint(equalTo: view.trailingAnchor, constant: -20)
        ])
    }
    
    // creating the action in which will be triggered when we tab on the btn
    @objc private func didTapSubmit() {
        // first we should check for empty strings using the guard let methid
        guard let name = nameTextField.text, !name.isEmpty,
              let email = emailTextField.text, !email.isEmpty
        else {  // triggered if fields are empty
            showAlert(message: "Please fill in all fields.")
            return
        }
        // second: we pass data to the display screen
        // Pass the unwrapped data to Screen 2
        let dataSent = DisplayViewController(name: name, email: email)
        
        // and then swithing to the second screen by pushing hte navigationcontroller stack
        navigationController?.pushViewController(dataSent, animated: true) // keeping hte animation true will result in smooth sliding screen transition
    }

    // helper method to present popup dialog when fields are empty
    private func showAlert(message: String) {
        let alert = UIAlertController(title: "Missing Information", message: message, preferredStyle: .alert)
        alert.addAction(UIAlertAction(title: "OK", style: .default))
        present(alert, animated: true)
    }
}


import SwiftUI

#Preview {
    UINavigationController(rootViewController: ProfileFormViewController())
}
