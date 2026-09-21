# week 04 report: overview of my week

Prepared by: Nour Al-Sumaidaei
Internship: 1001
Date: 2026 September 13th

## Summary

This week I was introduced to iOS development through MIT App Inventor, working with block-based, no-code logic instead of writing traditional Swift/Xcode code. My supervisor specifically directed me to use a no-code, block-based builder rather than native Xcode development, so I focused on understanding App Inventor's Designer and Blocks environments as applied to an iOS target.

## Completed Tasks

- Explored MIT App Inventor's iOS support, including the Companion app for live device testing.
- Investigated whether an AI-prompt-based building approach was available in App Inventor and confirmed it is not - the platform relies entirely on manual drag-and-drop components and visual block logic.
- Browsed the MIT App Inventor Gallery to see example community projects and understand common block-logic patterns before building.
- Built a tilt-controlled, breakout-style iOS game using App Inventor's Designer and Blocks editors.
- Set up UI components including a control bar (Play/Reset buttons, Score/Lives/Game Over/Win labels), a Canvas with multiple ImageSprite components, and a non-visible OrientationSensor.
- Implemented event-driven block logic for paddle movement (via tilt), ball-paddle and ball-block collisions, scoring, and win/loss conditions.
- Tested the app live on a real iPhone using the App Inventor Companion app.
- Recorded a 5-minute demo video walking through the design, the tool, the block logic, and a live device test.

## Skills Learned

This week deepened my understanding of event-driven programming as expressed through visual blocks rather than written syntax - collisions, sensor changes, and button clicks all trigger blocks automatically, which is conceptually the same as event listeners in traditional coding, just represented visually.

I also learned a clear distinction between "no-code" tools in general and specifically block-based tools like App Inventor, since not every no-code platform works the same way - some rely on AI-prompt generation instead of manual block assembly. Confirming this distinction with my supervisor helped me choose the right tool for what she actually wanted.

I learned how App Inventor accesses real device hardware, like the OrientationSensor for tilt input, entirely through visual blocks rather than platform-specific APIs.

## Challenges and Solutions

The main challenge was that MIT App Inventor's iOS support does not allow compiling a standalone installable file the way Android's APK export does - Apple's own toolchain and a paid Developer account would be required for that. I resolved this by using App Inventor's Companion app for live, real-device testing instead, which satisfied the assignment's practical testing requirement without needing Xcode or a Mac.

Another early challenge was clarifying exactly what "no-code, block-based" meant, since tools like Thunkable also market themselves as no-code but rely more on AI-prompt generation. Talking this through helped me settle on the right approach for this specific task.

## Feedback Received

The main direction was to build using a no-code, block-based platform rather than native Xcode/Swift development, given the team's current focus on exploring build tools rather than deep native iOS coding this week.

## Reflection

This week gave me a clearer sense of how block-based, no-code platforms work under the hood - event triggers, component-based UI, and sensor access are all still there, just expressed visually. It also reinforced how important it is to clarify tool expectations upfront, since "no-code" can mean different things depending on the platform. Testing live on a real iPhone through the Companion app was a nice way to see the project come together without needing a Mac or paid developer account.
