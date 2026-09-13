# Weekly Report — Week 04

## Summary

This week I had to learn about QA how to think as a QA tester and learned a lot about test cases, test scenarios, positive (happy paths), negative and boundry testing. I also had to learn about function and non functional testing the importans of priority and severity in QA reports, the structure of of a QA bug report (Preconditions, steps to Steps to Reproduce, expected and actual results), and I learned how to make a test-case matrix



## Completed Tasks

### Task 1: learn the fundimentals of QA

* Researched what a QA is, the importance of it, and what it actually does 
* Watched Youtube videos and researched how to make a test case and how to report bugs if found, the structure of bug report tickets

### Task 2: Test plan in Markdown

* made a test plan in Markdown containing the Test-case matrix and 3 example bug reports

### Task 3: Test-case matrix

* Created a matrix containing ID, scenario, preconditions, steps, expected results, priority, and Auto Candidate?.
* made different scenarios each with its preconditions, steps, expected result, priority, and Auto Candidate.


### Task 4: 3 example bug reports based on intentionally imagined defects

* Made 3 example bug reports on imagined defects
* Each report was structered with bug summary, precondition(if needed), steps to reproduce, actual results, expected results, and priority / Severity

## Skills Learned

### Technical Skills

* How to make test cases and test environments
* Make bug reports and how to structure the reports
* know when to automate tests and when to do them manually 

### Tools or Frameworks

* **Playwright:** QA automation tool Developed by Microsoft it supports (TypeScript, Python, Java, .NET)

### Communication & Teamwork Skills

* Learned the importance of clear communication in QA-to-Engineering pipelines, specifically how minor criteria mismatches lead to rejected features.

## Challenges and Solutions

* **Challenge: Making a matrix in markdown file**
* *Issue:* there was no clear way to make a matrix in MD
* *Solution:* Researched ways to make a matrix in markdown and using vs code is so much better to structure a MD file so i had to setup vs code and download the extentions required.


* **Challenge: Finding the correct priority for each case**
* *Issue:* I couldn't tell which case had more priority over which
* *Solution:* giving priority the cases that would make the service unusable over asthetic or small bugs was the answer like sing up(happy path) is way more important than clicking sing up with an empty email field


* **Challenge: Automation candidates**
* *Issue:* I automating everything would make things faster and easier and I didn't know which tests i had to do manually
* *Solution:* most UI tests should be tested manually because sometimes the automation whould click the API of a button and it works but the button itself connects to the wrong API or it overlaps with a different button



---

## Feedback Received

* My last PR was much better and more orginized and my review was detaled enough.
* I have potential in QA testing so my focus from now on will be on QA


## Overall Progress

**Progress: 60%**

## Next Steps & Future Scope

* Build automated Authentication API regression tests