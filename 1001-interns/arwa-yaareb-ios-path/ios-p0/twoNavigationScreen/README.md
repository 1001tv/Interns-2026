
# twoNavigationScreen

A programmatic UIKit iOS application built using Swift that demonstrates screen navigation, form validation, and data passing between View Controllers using `UINavigationController`.

## Demo
use this link:
https://github.com/user-attachments/assets/7735dee8-8fbd-4e69-87c0-843b81a00f51


## Features

- **Programmatic UI Layout:** Built entirely without Storyboards using `UIStackView` and Auto Layout constraints.
- **Form Input & Validation:** Form validation ensures both Name and Email fields are populated, displaying an alert if any field is empty.
- **Data Passing:** Dynamically passes user input data (`name` and `email`) from `ProfileFormViewController` to `DisplayViewController` via custom initializers.
- **Navigation Controller Integration:** Seamless push navigation and stack management via `UINavigationController`.

## Application Flow

1. **Profile Form (`ProfileFormViewController`)**: 
   - Accepts user input for **Full Name** and **Email Address**.
   - Validates input using Swift's `guard let` syntax.
   - Triggers an alert dialog (`UIAlertController`) if input validation fails.
2. **Display Screen (`DisplayViewController`)**: 
   - Receives passed parameters upon successful form submission.
   - Formats and displays the received profile details in a centered label.

## Tech Stack

- **Language:** Swift
- **Framework:** UIKit (Programmatic UI)
- **Architecture:** Model-View-Controller (MVC)
- **IDE:** Xcode

## Requirements

- iOS 15.0+
- Xcode 13.0+
- Swift 5.5+

## How to Run

1. Clone the repository:
   ```bash
   git clone [https://github.com/your-username/twoNavigationScreen.git]
