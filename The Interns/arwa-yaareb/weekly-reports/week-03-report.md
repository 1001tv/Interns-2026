# Weekly Report — Week 03

## Summary

This week was focused on Android Development, participating in team engineering discussions, and completing the task of creating a low/no-code Android Application. 

A significant portion of my time was dedicated to researching Kotlin setup requirements and configuring the development environment to prepare for native workflows. For the primary deliverable, I was tasked with using **MIT App Inventor**. The platform presented severe UI constraints—including a total lack of native padding, margin controls, and image border-radius attributes. Rather than scaling back, I took on the challenge, worked through the friction, and applied web layout principles (Tailwind/CSS structural paradigms that i am familiar with) to engineer a functional, stateful application with local persistence.


## Completed Tasks

### Task 1: Environment Research & Setup (Kotlin)

* Researched setup prerequisites, environment configurations, and dependency management for Kotlin/Android development.
* Spent more time preparing and understanding the Kotlin ecosystem than on the App Inventor build itself, laying a solid foundation for upcoming native Android tasks.

### Task 2: AIExamGen Application Development

* **Loading Screen:** Implemented an automated 150ms timer trigger using a `Clock` component for seamless screen transitions.
* **Authentication & Database Integration:** Built a stateful Login and Registration system backed by local `TinyDB` storage for key-value pair credential management.
* **Logic Architecture:** Fixed nested conditional block bugs to create smooth validation flows for successful logins, unregistered accounts, and invalid password alerts.
* **Home Dashboard:** Structured a static layout displaying saved course question paper cards.

### Task 3: UI Architecture & Cross-Platform Research

* Identified visual and structural indicators to distinguish native apps from cross-platform builds via UI alone:
* Non-standard system gestures, font-weight variations, and list scrolling physics.
* Custom floating selection menus replacing native OS handles.
* Generic visual spinners instead of OS-native loading indicators.
* Margin and element distortion across varied screen aspect ratios.



### Task 4: Team Collaboration & Tech Syncs

* Attended the Thursday Tech Talk and daily standup syncs.
* Documented QA workflows, release cycles, and Jira ticketing best practices (attaching detailed steps, app versions, and device models).


## Skills Learned

### Technical Skills

* **Stateful Logic Design:** Programmed conditional execution pathways for user authentication and error-handling matrices using local database storage (`TinyDB`).
* **Workaround Engineering:** Adapted web UI concepts (CSS flexbox/Tailwind box-model logic) to simulate margins and padding using dynamic percentage sizing (`Fill Parent`) and invisible container layouts.
* **Native vs. Cross-Platform Auditing:** Learned to inspect mobile interfaces for non-standard gestures, menu behaviors, and rendering differences.

### Tools or Frameworks

* **MIT App Inventor & AI2 Mobile App Companion:** Real-time live testing, APK compilation, and device-level QA. (helped termendousily when live testing the compnents).

* **Figma:** Source design evaluation and layout structure mapping.

* **Jira & Bug Tracking:** Formulating detailed bug reports and understanding acceptance criteria validation.
* **Kotlin Prerequisites:** Dependency setup and environment configurations for native mobile builds.

### Communication & Teamwork Skills

* Learned the importance of clear communication in QA-to-Engineering pipelines, specifically how minor criteria mismatches lead to rejected features.
* Expanded internal networking strategies focused on knowledge exchange ("giving and receiving") within team meetings.

## Challenges and Solutions

* **Challenge: Platform Customization Limitations**
* *Issue:* MIT App Inventor lacks built-in padding, margin properties, and border-radius settings for images, making clean layouts difficult to achieve.
* *Solution:* Spent nearly 3 hours researching platform capabilities and packages. Overcame the limitation by pre-processing cropped design elements and utilizing hidden nested `HorizontalArrangement` components to act as dynamic spacers and alignment boundaries.


* **Challenge: Authentication Conditional Logic Bug**
* *Issue:* The login sequence repeatedly threw "Incorrect Password" errors despite correct user input due to faulty nested logic.
* *Solution:* Re-architected the block logic by stripping away complex `else-if` chains and isolating single condition checks, resulting in a fully working, stateful authentication flow.


* **Challenge: Device Testing Setup**
* *Issue:* Lack of a personal test device for instant builds.
* *Solution:* Set up live testing using the MIT AI2 Companion app paired with a borrowed Android device to verify layout responsiveness across display sizes.



---

## Feedback Received

* **QA & Acceptance Standards:** Team lead feedback emphasized that any minor deviation from acceptance criteria results in ticket rejection. Bug reports must always include complete descriptions, reproduction steps, app versions, and specific device models.
* **Career Development:** Advice from senior engineers and mentor highlighted the importance of adaptability across all departments (QA automation, backend, and frontend along side suggesting me to start IOS development) as a CS student, alongside proactive self-directed research when encountering new tech topics.


## Overall Progress

**Progress: 60%**

## Next Steps & Future Scope

* Transitioning research from MIT App Inventor workarounds to native Kotlin/Android development environment execution.
* Expanding database models to support dynamic list rendering for course management.
* Integrating course syllabus upload capabilities into future project iterations.
* IOS Development suggested by my mentor Amnah. She sees me excelling in this feild, and she offered to help and support my journey going forward.