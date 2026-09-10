# week 03 report: overview of my week

Prepared by: Muslim Akeel  
Internship: 1001  
Date: 2026 September 8th  

## Summary

This week, my main focus was on Android development and building interactive mobile experiences using MIT App Inventor. 

Our core assignment for the week was to choose an existing mobile application design from platforms like Mobbin, Figma, or Dribbble and rebuild its user experience into a fully functional Android app. I selected a modern habit-tracking interface and built **StreakUp**, focusing on translating a polished UI concept into an interactive application.

While working on the task, I had to handle both the layout structure and the block-based visual logic. I spent significant time mapping out how screens communicate, how data moves between different view states, and how custom actions update the dashboard dynamically.

Outside of the app development task, I also spent time reviewing low-level mobile architecture patterns and learning more about how Android applications manage state, lifecycle events, and component trees.

We also had our weekly Tech Talk on Thursday focused on Quality Assurance (QA). I found this session insightful because it shifted my perspective on testing. I used to view QA primarily as manual verification, but the talk highlighted automated testing frameworks, structured bug tracking, and the role QA plays in release management pipelines.

## Completed Tasks

- Selected a minimalist habit tracker design from Mobbin and created **StreakUp** in MIT App Inventor.
- Built the primary app screens, including the main habit dashboard (`Screen1`) and the habit creation screen (`AddHabitScreen`).
- Implemented multi-screen navigation and back-stack communication using `close screen with value` and `Screen1.OtherScreenClosed`.
- Designed custom, reusable card UI structures (`HorizontalArrangement`) for habit items rather than relying on default system controls.
- Built interactive state toggles that switch habit statuses between pending (`O`) and completed (`✔`).
- Handled dynamic row display by using pre-configured hidden containers and controlling their visibility when data returns from the secondary screen.
- Compiled and deployed the standalone `StreakUp.apk` binary file.
- Tested and verified the final build on a physical Android smartphone to confirm UI stability and crash-free navigation.
- Recorded and published a 3-5 minute demonstration video on YouTube walking through the design reference, app architecture, blocks logic, and real device execution.
- Attended the Thursday Tech Talk covering QA methodologies, release cycles, and automated testing frameworks.

## Skills Learned

This week gave me a much stronger understanding of event-driven programming and state management in mobile applications. Before this task, I viewed app building mostly through layout structure, but working through the block logic showed me how much data flow management happens under the surface.

I became much more comfortable working in MIT App Inventor, specifically around screen communication and value passing (`close screen with value`). Learning to receive arguments upon returning to the main screen allowed me to keep the application flow smooth and responsive.

I also learned how to solve framework constraints creatively. Because MIT App Inventor does not allow creating new UI components at runtime through code, I had to think about state-driven layout manipulation—using hidden containers and toggling visibility based on returned data.

Finally, the QA Tech Talk gave me a clearer picture of production software delivery. Understanding test cases, regression testing, and build validation helped me connect individual developer tasks to the broader release cycle.

## Challenges and Solutions

The primary challenge I faced was managing dynamic components in MIT App Inventor. Unlike traditional Android code (like Java or Kotlin with RecyclerViews), MIT App Inventor cannot dynamically instantiate new UI elements while the app is running. 

To solve this, I designed a pre-configured, hidden container card on the main dashboard (`Screen1`). When the user completes the form on `AddHabitScreen` and submits a new habit name, the string passes back to `Screen1`, updates the label text, and sets the container's visibility to `true`. This provided a seamless user experience while staying within framework boundaries.

Another minor challenge was ensuring that the app performed well outside the development environment. I compiled the project into a standalone `StreakUp.apk` and installed it on a physical Android phone. Testing on an actual device confirmed that touch interactions, screen transitions, and font scaling behaved accurately on a real screen size.

Organizing repository deliverables also required attention to detail. I had to ensure that the repository paths (`Muslim-Akeel/tasks/task-03/`), media file names, and Markdown image links matched exactly so all verification screenshots and the APK link rendered correctly on GitHub.

## Feedback Received

Feedback throughout the week focused on maintaining repository organization, following exact directory conventions (`task-03`), and making sure every deliverable is cleanly documented. 

In addition, mentor discussions highlighted the importance of clear branch structures and using proper Pull Requests when submitting tasks, ensuring that the main branch remains stable while feature work is isolated.

The Thursday Tech Talk reinforced this by demonstrating how structured requirements directly drive both development and QA evaluation. Following team standards closely makes testing, reviewing, and merging code much more efficient for everyone on the team.

## Reflection

Building **StreakUp** end-to-end made me realize how much thought goes into smooth user interactions, even in a clean, minimalist application. Every button press, screen transition, and text field update requires explicit logic to feel natural to the user.

Working through the full pipeline—from analyzing an inspiration design on Mobbin to layout assembly, block programming, APK generation, and physical device testing—gave me confidence in handling complete mobile feature lifecycles.

Overall, it was a productive week. Solving technical constraints and seeing the finished app run smoothly on an actual phone made the process engaging and rewarding.
