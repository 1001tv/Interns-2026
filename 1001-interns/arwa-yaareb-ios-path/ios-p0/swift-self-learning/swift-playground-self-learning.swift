import Playgrounds

#Playground {
//    let maximumNumberOfLoginAttempts = 10
//    var wlecomeMessage = "Hello"
//    
//    var environment = "development"
//    let maximumNumberOfLoginAttempts: Int // in this case the maximumNumberOfLoginAttempts does not have a value
//    
//    
//     if we did some checking:
//    if environment == "development" {
//        maximumNumberOfLoginAttempts = 100
//    } else {
//        maximumNumberOfLoginAttempts = 10
//    }
//    
//    type annotation
//
    // if we had a button and the button text was named nameTextField then we take te text of it and save it to a variable and check if it does exist then print it. if does not, then say text field is nil.
    
    if let actualName = nameTextField.text {
        print("user entered: \(actualName)")
    } else {
        print("text field is nil")
    }
    
    // ORR we can use the guard statement, it is faster nad done in one line.
    // we call this early exit.
    // here it exists early if the email is missigg or its empty usign the isEmpty. if not it will just return its value.
    
    // this one is nore used than the if condition and it is faster to implement
    
    guard let actualEmail = nameTextField.text, !actualEmail.isEmpty else return
        
    
    
}
