# Weekly Report — Week 05

## Summary

This week I focused on QA automation, I looked into playwrite a tool for automating web tests and learned how to write code to automate tests.

## Completed Tasks

### Task 1: Tool choice

* I chose playwrite: Read the Playwrite documentation on how to setup the tool. and how to use it
* learned the required coding skills to write automation useing typescript.

### Task 2: Registration Authentication Automation

* **Registration success:**  Coded a test that will input a uniqe email using a formula containing current time and a strong password, the test will check if the account is created successfully without any issues.
* **Negative: Duplicate registration:** Coded a test that inputs an already used email and a strong password and it checks if the api returns a conflict error and displayes 'Email already exists' message to the user.
* **Negative: Invalid email format** Coded a test taht inputs an incorrect email format and a strong password to check if the api returns a bad request error and displays a incorrect email format message.
* **Negative: Missing required fields** Coded a test that only inputs a password without an email to check if the api returns either a 'bad request' error or an 'Unprocessable Entity' error and displays a message saying this field is required.

### Task 3: Login Authentication Automation

* **Success: Login success:** Coded a test that inputs an already registered email and a correct password to check if the api returns an ok message and logs the user in.
* **Negative: Incorrect password:** Coded a test that inputs an already registered email with an incorrect password to check if the api returns an 'Unauthorized' error and displays a massege saying the password is incorrect.
* **Negative: Unknown user:** Coded a test that inputs an unkown email with a password to check if the api returns an 'Unauthorized' or a 'Not found' error and displays a massege saying email is unregistered.
* **Negative: Missing login fields:** Coded a test that only inputs an email without a password to check if the api returns a 'Bad request' error and displays this password is missing.
* **Security: Forgot-password safe response behavior:** Coded a test that goes to the forgot password page and inputs an email to check if the api sends an 'ok' massege wether the email is registared or not.



## Skills Learned

### Technical Skills

* **Typescript:** Learend the structure of Typescript and how to write code usnig it.
* **Reading documentation:** learnd how to read documentations and find what I need from the documentation.

### Tools or Frameworks

* **Playwrite:** Learned how to setup playwrite and how to use it to automate tests and learned how to run the tests and how to read the output of the tests using the html provided by Playwrite.


## Challenges and Solutions

* **Challenge: No API to test**
* *Issue:* I have no API to yet to creat my tests.
* *Solution:* I had to learn how to make the tests not hard coded and I can easly change what API I want to test from the .env file


* **Challenge: Coding the tests**
* *Issue:* I had no idea on how to code using Typescript.
* *Solution:* I did reaserch on how to code using Typescript at first it seamed complicated but I looked up what each line of code does from a sample provided by Playwrite and it turned out to be much simpler than I anticipated.




---

## Feedback Received

* The feedback i recieved this week is that I need to put in more work and try to speed up my development.

## Overall Progress

**Progress: 62.5% (week 5 out of 8)**
