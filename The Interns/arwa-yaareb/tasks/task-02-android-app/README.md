
## Task Title

Android App Builder Challe nge – AIExamGen MVP

## Task Objective

To design, implement, and deploy a lightweight, multi-screen Android application using a visual/no-code app builder (MIT App Inventor). The primary objective is to gain practical experience with the end-to-end Android delivery pipeline—from UI/UX conceptualization to building reusable layouts, managing client-side database logic, generating an APK, and performing device-level QA testing.

## Requirements

### Core Requirements

* **Design Selection:** Source a clean, mobile UI design from an inspiration platform ( I picked one from the Figma Community).
* **Development Platform:** Utilize a low-code/no-code builder capable of compiling standalone Android APK files (I used the MIT App Inventor).
* **Screen Count:** Build at least 2 distinct screens.
* **Component Reuse:** Structure layouts using reusable component concepts (e.g., cards, containers, custom navigation structures).
* **Navigation & State:** Implement linear screen transitions and operational hardware/software back navigation.
* **Interactive Logic:** Incorporate dynamic user behaviors beyond screen switching (e.g., user authentication and data persistence).
* **Device Build & Testing:** Compile a final `.apk` binary, deploy it onto a physical Android device, and verify execution stability across varying screen dimensions.

---

## Implementation Details

* **Design Source:** [Figma Interactive Mobile Prototype](https://cape-dull-38355396.figma.site/)
* **Development Tool:** MIT App Inventor 2
* **APK Download Link:** [AIExamGen](https://ai2a.appinventor.mit.edu)

### Architecture & Screens Developed

#### 1. Splash / Starter Screen

* **Clock Timer Component:** Configured a non-visible `Clock` component set to a 150ms interval to act as an automated splash screen.
* **Behavior:** Automatically transitions the user to the Authentication screen once loaded.

#### 2. Authentication Screen (Login / Registration)

* **Form Controls:** Utilized custom-styled `TextBox` and `PasswordTextBox` fields with clear feedback labels for authentication status.
* **Database Integration:** Integrated a local `TinyDB` instance for user credential management.
* **Validation Logic:** Programmed conditional block logic to store registered `Username`-`Password` key-value pairs and validate user entries during sign-in attempts.

#### 3. Home / Dashboard Screen

* **Data Presentation:** Designed a structured view displaying saved question paper records (e.g., "Math Midterm Exam").
* **Component Layouts:** Built modular content card wrappers and action interfaces (e.g., search bar placeholder, user profile avatar block).

## Challenges Faced

1. **Alignment & Centering Constraints:**
* *Issue:* MIT App Inventor lacks flexible default CSS flexbox/grid layout properties. Centering elements on the splash screen proved difficult.
* *Troubleshooting:* Experimented with layout properties until discovering that wrapping elements inside hidden/invisible `HorizontalArrangement` components enabled proper vertical and horizontal centering.


2. **Padding, Margins & Edge-Rounding:**
* *Issue:* The platform does not support native padding, margin, or border-radius properties for UI components or images.
* *Troubleshooting:* Researched platform capabilities for nearly 3 hours, discovering that standard components do not expose padding attributes natively. Attempted pre-cropping image masks in external design tools (Canva) before integrating them into the layout engine.


3. **Authentication Conditional Logic Bugs:**
* *Issue:* The login sequence consistently returned an "Incorrect Password" or account error notice, even when valid user data was supplied.
* *Troubleshooting:* Debugged the block editor logic iteratively. Discovered that nested `if-else` branching caused premature execution paths. Re-architected the block structure by isolating single condition checks, resolving the authentication validation loop.


4. **Performance Latency & Mobile Testing Setup:**
* *Issue:* Lack of a personal Android device required setting up real-time live testing using MIT AI2 Companion on a borrowed physical device.
* *Troubleshooting:* Managed local connections via companion code pairing to verify layout responsiveness and APK deployment stability under real device constraints.


## Solution

To overcome the design constraints of MIT App Inventor while preserving responsive behavior across diverse device display profiles:

* **Flexible Responsive Layouts:** Replaced fixed-pixel sizing with percentage-based dynamic dimensions (`Fill Parent` width/height alignments) inside `VerticalArrangement` and `HorizontalArrangement` wrappers.
* **CSS/Tailwind Mapping:** Applied my layout structure experience from web development frameworks (Tailwind/CSS) to emulate box-model padding using spacer elements and custom-nested arrangements.
* **Data Persistence:** Built a persistent `TinyDB` key-value system for registering and authorizing users, bridging local storage with interactive state updates without requiring complex API integrations.

---

## Testing Performed

* **Flow & Navigation Testing:** Verified the screen switching sequence from Splash Screen → Authentication Screen → Home Screen.
* **Authentication Validation Matrix:**
* Tested empty field submissions.
* Attempted login with an unregistered username (triggered: *"User does not exist. Please register first"*).
* Executed user registration (triggered: *"Registration successful! You can now log in."*).
* Attempted login with correct username but invalid password (triggered: *"Incorrect Password"*).
* Successfully logged in with matching credentials to launch the home view.


* **Hardware & Form Factor QA:** Deployed the compiled `.apk` onto a physical Android device via the MIT AI2 Companion environment to inspect UI text readability, image scaling, and component alignment across display sizes.

---

## Final Result

 **Starter Loading Screen** , ** Image 1** ,  Splash screen displaying logo mark, branding, loading dots, and a 150ms timer trigger.

 **User Not Found State** , **Image 2**  Login interface displaying error prompt when an unregistered username is entered.

**Successful Registration State** , **Image 3** Authentication interface displaying positive confirmation alert after creating a `TinyDB` user record. 


**Incorrect Password State** , **Image 4** Authentication error prompt displayed when password validation fails against stored data.


**Home Screen Dashboard** , **Image 5**  Static dashboard view displaying user avatar, search bar, and saved question paper content card.

### Visual Documentation

**Splash & Loading View**

**Unregistered User Validation**

**Account Registration Confirmation**

**Authentication Failure View**

**Home Dashboard Screen**

## Additional Notes

* **Platform Takeaways:** While low-code platforms like MIT App Inventor offer rapid prototyping speed for standard application flows, fine-grained UI customization (margins, custom image masking, fluid animations) requires workaround structures compared to native Android development (Jetpack Compose / XML).
* **Future Scope: ** Potential enhancements include introducing dynamic dynamic list rendering for multiple course records, expanding `TinyDB` schemas to allow dynamic creation of new question papers, and implementing dynamic course syllabus uploads as designed in the initial Figma specification.
