# Weekly Report — Week 06

## Summary
Focused on completing the foundational onboarding ticket **[iOS][P0] Learn Swift & UIKit fundamentals before feature work (#31)**. The week centered on deep-diving into core Swift language concepts, UIKit programmatic UI design, Auto Layout constraints, and application/view controller lifecycles. Built and tested a practical two-screen application (`twoNavigationForm`) built entirely without storyboards, implemented field validation, configured programmatic screen transitions via `UINavigationController`, did a project documentation with README.md file, and recorded a video demo of the app in action.

## Completed Tasks
- **Completed P0 Foundation Ticket :** Studied foundational Swift and UIKit concepts to prepare for upcoming feature tickets (Authentication UI).
- **Built `twoNavigationForm` Application:**
  - Created a 2-screen programmatic UIKit application (Profile Form screen & Summary screen).
  - Configured root navigation programmatically inside `SceneDelegate.swift` using `UINavigationController`.
  - Implemented UI elements (`UITextField`, `UILabel`, `UIButton`, `UIStackView`) positioned via Auto Layout constraints (`NSLayoutConstraint`) rather than fixed frames.
  - Implemented data passing between view controllers and handled edge cases such as empty input validation.
  - Verified UI responsiveness across multiple iPhone simulator sizes.
- **Documentation & Media:**
  - Authored a comprehensive `README.md` file for the `twoNavigationForm` project explaining the setup, lifecycle concepts, and learnings.
  - Recorded a video walkthrough demonstrating the app's interactive functionality and navigation flow.
- **Git & GitHub Workflow:** Pushed project branch to GitHub and prepared the Pull Request for review with accompanying screenshots and recordings.


## Skills Learned
- **Technical Skills:**
  - **Programmatic UI & Auto Layout:** Building views and layouts directly in code to maintain cleaner version control and simplify PR reviews.
  - **Lifecycle Deep-Dive:** Gained an in-depth understanding of the **Application Lifecycle** (`Not Running`, `Inactive`, `Active`, `Background`, `Suspended`, `Terminating`) and the **UIViewController Lifecycle** (`viewDidLoad`, `viewWillAppear`, `viewDidAppear`, `viewWillDisappear`, `viewDidDisappear`).
  - **App Architecture Elements:** Clarified the distinct responsibilities of `AppDelegate` (system/process-level events, notifications, lifecycle) vs. `SceneDelegate` (multi-window scene lifecycle, UI window setup, root navigation setup).
  - **Event Handling & Data Passing:** Structuring target-action patterns for button taps and writing modular helper functions to cleanly manage state and input parsing.
- **Tools & Frameworks:**
  - Xcode, Swift, UIKit, iPhone Simulators (multiple display sizes), Git & GitHub PR workflow.
- **Research & Problem Solving:**
  - Effectively navigating developer documentation and community technical write-ups to independently debug programmatic setup issues.


## Challenges and Solutions
- **Challenge:** Transitioning to fully programmatic UI required configuring the initial window and navigation stack without relying on the default `Main.storyboard`.
  - **Solution:** Configured `windowScene` inside `SceneDelegate.swift`, initialized `UIWindow(windowScene:)`, embedded the root `ProfileFormViewController` inside a `UINavigationController`, and assigned it as `window?.rootViewController`.
- **Challenge:** Managing input validation logic and triggering transitions cleanly within button action handlers.
  - **Solution:** Modularized the code using helper functions to validate text input, handle empty states gracefully, and cleanly pass structured data forward to the summary controller.

## Feedback Received
- Received clear ticket specifications and acceptance criteria from my menor Amnah outlining the definition of done, and highlighting also the importance to present something and work on something.

## Overall Progress
- **Progress:** 100% on Ticket [iOS][P0]
- **Week 6 out of 8** 75% is done and 25% left to the end of the internship.
- **Next Steps:** Begin work on the P1 ticket on GitHub: **Authentication UI Foundation**.

