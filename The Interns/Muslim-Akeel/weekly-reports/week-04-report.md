# week 04 report: overview of my week

Prepared by: Muslim Akeel  
Internship: 1001  
Date: 2026 September 14th  

## Summary

This week focused on iOS application development and cross-platform mobile prototyping using **Thunkable**. Following our Android app work from last week, the goal of Task 04 was to recreate an intuitive, modern mobile application targeted at iOS/cross-platform deployment and test it directly on an iOS device.

I built **Habit Tracker**, a cross-platform mobile application designed to help users establish consistent daily routines, set custom reminders, track active completion streaks, and view daily progress metrics. 

I designed and built the app UI using Thunkable's component layout system and AI prompt assist tools, configured the visual block logic for dynamic habit creation, and tested the live build directly on an iPhone using the Thunkable Live app via QR code scanning.

Additionally, we attended our weekly Thursday Tech Talk, which provided deeper insights into cross-platform execution environments, iOS deployment workflows, and mobile UI/UX standards.

## Completed Tasks

- Designed and configured the UI layout for **Habit Tracker** on Thunkable.
- Built the primary app screens and modals, including:
  - **Today View Dashboard:** Displays the current date (Sep 14, 2026), daily completion counter ("1 of 1 habits complete"), active streaks, and habit cards.
  - **New Habit Modal:** Features input fields for Habit Name, Description (optional), Daily Reminder Time picker, and custom color category selectors (Blue, Pink, Green, Orange, Cyan, Purple).
  - **Bottom Navigation Bar:** Includes tab switches for *Today*, *Habits*, and *Stats*.
- Implemented state logic for dynamic habit addition, streak counter increments, and completion status toggles.
- Tested the live iOS build on a physical iPhone using the **Thunkable Live** app via QR code scanning.
- Recorded and uploaded a demonstration video to YouTube Shorts showing the layout, habit creation modal, interactive toggles, and physical device testing.
- Created and organized repository assets inside `Muslim-Akeel/tasks/task-04/` including app screenshots, README documentation, and the YouTube video demo link.

## Skills Learned

- **Thunkable Development:** Gained hands-on experience building cross-platform mobile apps with Thunkable's design canvas and drag-and-drop block logic.
- **iOS Live Testing:** Learned how to deploy and live-test development builds seamlessly on iOS devices using Thunkable Live without needing a full Xcode compilation pipeline.
- **Component State Management:** Learned how to capture modal form input (habit name, optional description, reminder times, color picks) and dynamically append interactive habit cards to the main view.
- **Mobile UX/UI Design:** Improved layout structuring for mobile forms, color-coded tag selectors, floating action buttons (`+`), and tab navigation bars.
- **Cross-Platform Concepts:** Understood the structural differences between native Android (MIT App Inventor/Kodular) and cross-platform visual app builders like Thunkable.

## Challenges and Solutions

### 1. Form Inputs and Dynamic Card Rendering
**Challenge:** Ensuring that user inputs from the "New Habit" modal (habit name, description, selected color) correctly passed back to the main "Today" dashboard to dynamically update the completion progress header.  
**Solution:** Used Thunkable's event blocks to store input parameters into local app variables upon tapping "Create Habit", automatically updating the list container and recalculating "X of Y habits complete".

### 2. Live iOS Device Testing Constraints
**Challenge:** Testing iOS builds usually requires a Mac with Xcode and Apple Developer provisioning profiles.  
**Solution:** Utilized Thunkable's live web preview and QR code pairing to stream the interactive application directly onto an iPhone via the Thunkable Live app, allowing full touch testing without compilation overhead.

### 3. Folder and Deliverable Organization
**Challenge:** Ensuring all Task 04 deliverables (screenshots, Markdown reports, video links) follow proper GitHub pathing without repeating past naming confusions.  
**Solution:** Structured all Task 04 assets under `Muslim-Akeel/tasks/task-04/` and placed the weekly summary in `Muslim-Akeel/weekly-reports/week-04-report.md`.



## Reflection

Building **Habit Tracker** in Thunkable gave me valuable experience in cross-platform development and real-time iOS testing. Seeing how quickly a functional prototype can be moved from a design builder to a physical smartphone made the development process fast and engaging.

Connecting user interface design directly to live event handling reinforced my understanding of state management and mobile usability standards.
