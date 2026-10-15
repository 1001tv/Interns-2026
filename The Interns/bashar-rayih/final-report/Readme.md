# Final Report — Internship

## Introduction

This report is a summary of my 8 week internship. During these weeks I reviewed the 1001.tv streaming service, learned how to use GitHub, built an Android app using MIT App Inventor, and then focused on QA and test automation. By the end I was coding automated API tests using Playwright and looking into IOS test automation using XCUITest.

## Internship Objectives

* Learn how to work in an engineering team: using a GitHub repository, branches, pull requests, weekly reports, and standup meetings.
* Learn the fundamentals of QA like test cases, test plans, bug reports, and priority.
* Learn how to automate tests and know what to automate and what to test manually.
* Learn new tools and programming languages by reading documentation and researching on my own.
* Get better at communicating through reports, reviews, and meetings.

## Summary of Completed Tasks

### Week 1: 1001.tv Review

* Tested 1001.tv on my phone, my computer, and my TV and wrote a review about it.
* The review has my first thoughts, finding content, how it works on each device, a feature comparison, what I liked and didn't like, and a final rating (4 out of 5 stars).
* The search in Arabic and English and casting to the TV worked great, but the phone app has too much scrolling and the subscription rules for extra services like TOD are not clear.

### Week 2: GitHub and Version Control

* Joined the interns repository and learned about version control systems.
* Created my own folder, created a branch, and made a pull request to submit my week 1 report.

### Week 3: Android Development

* Found an xbox store UI on Dribbble and tried to replicate it in MIT App Inventor.
* Created 6 screens (3 category screens, 2 item screens, and a loading screen) with buttons to switch between screens and a back button.
* Built the .APK file and uploaded it on GitHub.
* Recorded a demo of the app and uploaded it to YouTube.

### Week 4: QA Fundamentals

* Learned what QA is, why it's important, and what a QA tester actually does.
* Learned about test cases, test scenarios, positive, negative, and boundary testing, functional and non functional testing, and priority and severity.
* Made a test plan in Markdown with a test-case matrix and 3 example bug reports.
* Learned the structure of a bug report (preconditions, steps to reproduce, expected and actual results).

### Week 5: Web Test Automation

* Chose Playwright as the tool and learned how to write automation using TypeScript.
* Coded registration tests: success, duplicate email, invalid email format, and missing required fields.
* Coded login tests: success, incorrect password, unknown user, missing login fields, and the forgot-password safe response.
* Made the tests so I can change the API I want to test from the .env file.

### Week 6: Improving My Automation Code

* Coded an automated test to make sure API tokens are protected and a user can't get access without a token.
* Learned how to use dummy tokens to make sure the code works right without needing an API.
* Optimized my code to be cleaner and fixed the errors I found.

### Week 7: IOS Test Automation

* Read about how to automate IOS app tests and the differences between IOS testing and web app testing.
* Chose XCUITest as the tool and learned the basics of Swift to write automation with it.
* Learned the difference between API automation and UI automation.
* Learned how to lead a standup meeting.

### Week 8: Final Report

* Wrote this final report.

## Skills and Technologies Learned

### Technical Skills

* **QA:** How to make test cases, test plans, test-case matrices, and bug reports, and how to give each case a priority.
* **Test automation:** How to code automated API tests, how to use dummy tokens to test without an API, and how to know when to automate and when to test manually.
* **TypeScript:** The structure of TypeScript and how to write code using it.
* **Swift:** The structure of Swift and how to write automation code using it.
* **GitHub:** How to create folders, branches, and pull requests.
* **MIT App Inventor:** How to create screens, make functioning buttons, and use HorizontalArrangements and VerticalArrangements.
* **Reading documentation:** How to read documentation and find what I need from it.

### Tools or Frameworks

* **Playwright:** How to set it up, write and run tests, and read the output using the html report.
* **XCUITest:** The basics of XCUITest to test IOS UI.
* **MIT App Inventor, MIT Companion, Dribbble:** Used to design and build the Android app.
* **GitHub and VS Code:** Used for version control and writing Markdown files.

### Communication & Teamwork Skills

* Learned the importance of clear communication in QA-to-Engineering pipelines, specifically how minor criteria mismatches lead to rejected features.
* Learned how to write more detailed reports.
* Learned how to lead a standup meeting and organize it so everyone gets their chance to speak.

## Main Challenges and Solutions

* **Challenge: Creating a folder in the repository (Week 2)**
  * *Issue:* I couldn't create a folder inside "interns" because it was a file, not a folder.
  * *Solution:* We made a new folder named "The Interns" so we can create our folders inside it and upload our work.

* **Challenge: MIT App Inventor limits (Week 3)**
  * *Issue:* MIT doesn't let you place things wherever you want, you can't put a text or a button on top of an image, and 1 button can't do 2 functions so I sometimes got stuck on a page or the loading screen.
  * *Solution:* I used HorizontalArrangements and VerticalArrangements to fill blanks, put the images as backgrounds of the arrangements, and added a when any function to close the screen I was on whenever I open a new screen.

* **Challenge: Finding the correct priority and what to automate (Week 4)**
  * *Issue:* I couldn't tell which case had more priority and which tests I had to do manually.
  * *Solution:* I gave priority to the cases that would make the service unusable over small or aesthetic bugs, and most UI tests should be tested manually because the automation can click the API of a button and it works but the button itself connects to the wrong API.

* **Challenge: No API to test (Weeks 5 and 6)**
  * *Issue:* I had no API to create my tests on and I couldn't verify if my tests actually work.
  * *Solution:* I made the tests not hard coded so I can easily change the API from the .env file, and I used dummy tokens to simulate a backend API.

* **Challenge: Coding the tests (Week 5)**
  * *Issue:* I had no idea how to code using TypeScript.
  * *Solution:* I did research and looked up what each line of code does from a sample provided by Playwright, and it turned out to be much simpler than I anticipated.

* **Challenge: MACOS required (Week 7)**
  * *Issue:* I don't have a mac or any way to get my hands on a MACOS machine to write my automation tests.
  * *Solution:* I haven't found a solution yet but I am thinking about writing everything and sending it to a mac owner to compile it on their mac.

## Important Achievements

* Wrote a full review of 1001.tv after testing it on 3 different devices.
* Built an Android app with 6 screens, uploaded the .APK on GitHub, and made a demo video.
* Made a QA test plan with a test-case matrix and 3 example bug reports.
* Coded 12 automated tests in Playwright: 9 for registration and login (success, negative, and security) and 3 for protected tokens.
* Made my tests work without a real API by using dummy tokens.
* Learned 2 programming languages (TypeScript and Swift) and 2 automation tools (Playwright and XCUITest).
* My PRs got better and more organized, and I got feedback that I have potential in QA testing.

## Lessons Learned

* Instead of wasting all my time on finding "the perfect" option, I should look for one that gets me close and start working.
* Not having an API or a Mac doesn't mean I have to stop working, I can find a way around it like using dummy tokens.
* API tests are fast, stable, and cheap so the detailed logic and edge cases should be tested there. UI tests are slow and break easily so they should check the real user journey.
* Not everything should be automated, a lot of UI tests are better tested manually.
* Reading documentation and looking up what each line of code does makes new languages much simpler than they look at first.
* My reports need enough detail to show what I learned during the week.
* When I face a challenge I need to find the solution myself.
* Cases that make the service unusable are more important than small bugs.

## Future Improvement Areas

* Put more effort into the issues assigned to me and speed up my development.
* Find a way to get my hands on a MACOS machine so I can write and run my XCUITest tests.
* Build automated Authentication API regression tests.
* Use API automation for the detailed logic checks and UI automation for only the main user journeys.
* Keep my focus on QA and learn more about test automation.

## Conclusion

During this internship I went from learning how to use GitHub and building an app with no code to coding automated tests for authentication. The most important thing I got out of it is finding that I want to focus on QA, and learning how to research and find solutions to my own problems. I still need to work faster and put more effort into my tasks, but I finished the internship with skills I can build on.

**Overall Progress: 100% (week 8 of 8)**
