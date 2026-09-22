import Playgrounds

#Playground {
    // let maximumNumberOfLoginAttempts = 10
    var wlecomeMessage = "Hello"
    
    var environment = "development"
    let maximumNumberOfLoginAttempts: Int // in this case the maximumNumberOfLoginAttempts does not have a value
    
    
    // if we did some checking:
    if environment == "development" {
        maximumNumberOfLoginAttempts = 100
    } else {
        maximumNumberOfLoginAttempts = 10
    }
    
    //type annotation
}
