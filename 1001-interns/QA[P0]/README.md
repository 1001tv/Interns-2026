# Overview and Scope

This test plan covers the manual and automated testing strategy for the core Authentication module, including Sign Up, Login, Forgot Password, and Logout flows.

# Authentication Test Plan Matrix

| ID      | Scenario                            | Precondition                                     | Steps                                                                                       | Expected Result                                                              | Priority | Auto Candidate?      |
| :------ | :---------------------------------- | :----------------------------------------------- | :------------------------------------------------------------------------------------------ | :--------------------------------------------------------------------------- | :------: | :------------------- |
| AUTH-01 | Sign Up (Happy Path)                | User is on /signup. Email is unregistered.       | 1. Enter valid email<br>2. Enter valid password<br>3. Click Sign Up                         | Account created; user redirected to dashboard.                               |    P0    | Yes (API & UI)       |
| AUTH-02 | Submit with empty fields            | User is on /login                                | 1. Leave all fields blank<br>2. Click Login                                                 | Button is disabled or UI highlights required fields.                         |    p2    | Yes (UI)             |
| AUTH-03 | Invalid email formats               | User is on /signup.                              | 1. Enter an invalid email(ex. missing ".com")<br>2.Enter valid password<br>3. Click Sing Up | UI displays "Email is invalid".No crash                                      |    p1    | Yes (API)            |
| AUTH-04 | Sign Up password rules validation   | User is on /signup.                              | 1. Enter valid email<br>2. Enter 5-character password<br>3. Click Sign Up                   | UI displays "Password must be at least 8 characters".                        |    P2    | Yes (API)            |
| AUTH-05 | Login with incorrect credentials    | User is on /login and has a valid account.       | 1. Enter valid email<br> 2. Enter wrong password<br>3. Click Login                          | UI displays "Invalid email or password".                                     |    P1    | Yes (API)            |
| AUTH-06 | Sign Up with duplicate email        | User is on /signup. Email is already registered. | 1. Enter registered email<br>2. Enter valid password<br>3. Click Sign Up                    | UI displays "Email already in use" error. No crash.                          |    P1    | Yes (API)            |
| AUTH-07 | Loading and double-tap behavior     | User is on /login.                               | 1. Enter valid credentials<br>2. Rapidly double-click Login                                 | Button shows loading spinner; only ONE network request is sent.              |    P2    | No (Manual UI check) |
| AUTH-08 | Network/Server error handling       | User is on /login. Server is offline.            | 1. Enter valid credentials<br> 2. Click Login                                               | UI displays "Unable to connect to server"                                    |    P1    | Yes (API)            |
| AUTH-09 | Navigation/Back behavior security   | User has just successfully logged out.           | Click browser 'Back' button                                                                 | User is forced back to Login; cannot view authenticated dashboard.           |    P1    | No (Manual UI check) |
| AUTH-10 | Keyboard/Input behavior             | User is on /login.                               | 1. Type email<br>2. Press 'Tab'<br>3. Type password<br>4. Press 'Enter'                     | Focus moves correctly; 'Enter' submits the form.                             |    P2    | No (Manual UI check) |
| AUTH-11 | Small screen/Mobile viewport        | User is on /login. Viewport width is 375px.      | Observe UI layout                                                                           | Inputs do not overlap; button is full width and tappable.                    |    P2    | No (Manual UI check) |
| AUTH-12 | Basic accessibility (Screen Reader) | User is on /login using VoiceOver/NVDA           | Tab through inputs                                                                          | Screen reader announces "Email input", "Password input", and "Login button". |    P2    | No (Manual audit)    |

# Defect Reports

# Bug 01: Rapidly double-clicking Sign Up creates duplicate user profiles in database
Environment: Opera GX, Windows 11

Severity: High / Priority: P1

Preconditions: User is on the Sign Up page with an unregistered, valid email address.

Steps to Reproduce:

Enter a valid email and strong password.

Rapidly double-click the "Sign Up" button.

Expected Result: The button should disable upon the first click, display a loading state, and only generate one API request, creating a single user account.

Actual Result: The button remains active long enough to register two clicks. Two POST /api/register requests are sent, resulting in two identical user rows in the database, breaking the unique email constraint.

# Bug 02: Browser Back button after Logout allows unauthorized viewing of cached Dashboard
Environment: Safari, MacOS Sequoia

Severity: Critical / Priority: P0 (Security Risk)

Preconditions: User is actively logged in and viewing the secure Dashboard.

Steps to Reproduce:

Click the 'Logout' button.

Observe successful redirect to the /login screen.

Click the browser's native 'Back' arrow.

Expected Result: The application should recognize the session token is destroyed and force the user back to the login screen, or display an "Unauthorized" message.

Actual Result: The browser loads a cached version of the secure Dashboard, displaying sensitive user data without requiring re-authentication.

# bug 03: Casting shows a Black screen instead of phone screen

Environment: Mobile Application, Galaxy S25 Ultra, LG smart TV

Preconditions: User has a video playing on his mobile application

Steps to Reproduce:

Click casting button on the mobile app

Select your TV from the list

Actual Result: Video is not playing on the TV and you can only see a Black screen

Expected Result: Video will start casting on the LG smart TV

severity: Low/Priority: P2