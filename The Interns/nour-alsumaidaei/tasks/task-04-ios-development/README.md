# Task 04 - iOS Development with MIT App Inventor

## Demo Video
https://youtu.be/IwF3F6pQ23U

## Summary

This task introduced iOS development through MIT App Inventor, a no-code, block-based builder, per my supervisor's direction to avoid native Xcode/Swift development this week.

## Walkthrough Summary (from the demo video)

- **Introduction**: Explained the tool choice - MIT App Inventor, no-code, block-based, targeting iOS specifically, tested via the Companion app rather than a compiled Xcode build.
- **Design overview**: Walked through the Designer view - a top control bar with Play/Reset buttons and Score/Lives/Game Over/Win labels, a Canvas containing multiple ImageSprite components (the ball, paddle, and colored blocks), and a non-visible OrientationSensor component for tilt input.
- **Block logic walkthrough**: Covered the event-driven logic - Play button resets state and starts the ball; OrientationSensor changes move the paddle based on tilt; collision events handle ball-paddle bouncing, ball-block destruction and scoring, and win/loss condition checks.
- **Live device test**: Demonstrated the app running live on a real iPhone via the App Inventor Companion app, showing tilt-based paddle control and real-time score updates.
- **Challenge and learning**: Covered the lack of a standalone iOS build/install option in App Inventor (requires Apple's Developer account and toolchain), solved via Companion-based live testing; and the key learning around event-driven, block-based logic as a visual equivalent to traditional coding concepts.

## Status
Complete - demo video recorded and testing performed on a real iOS device via the Companion app.
