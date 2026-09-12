# week 03 report: overview of my week

Prepared by: Nour AlSumaidaei
Internship: 1001
Date: 2026 September 8th

## Summary

This week, I focused on Android app development, learning how to go from a UI design concept to a working, installable Android application. My task was to choose a design inspiration and build a small multi-screen app around it. I designed DataPulse, a personal finance analytics app, inspired by a finance dashboard concept I found while researching mobile UI design references. I built it out as a 3-screen app — Dashboard, Reports, and Profile — with persistent local data and interactive features.

## Completed Tasks

- Selected a finance analytics dashboard design as visual inspiration for the app's layout and color palette.
- Built a Dashboard screen with KPI cards (Balance, Income, Expenses, Savings) and a trend chart.
- Built a Reports screen with a reusable report-card component and a review/toggle interaction.
- Built a Profile screen with an editable name field and a live summary of app activity.
- Implemented local data persistence so KPI values, report review states, and the profile name are saved across sessions.
- Added a bottom navigation bar connecting all three screens, with correct Android back-button behavior.
- Debugged a toggle-state bug where tapping a reviewed item a second time didn't revert it, by switching the logic to dedicated boolean variables instead of checking the item's visual state.
- Generated the Android build and tested it in an emulator preview before moving to real-device installation.

## Skills Learned

This week gave me a much clearer picture of the full pipeline behind a working app — not just designing screens, but wiring up navigation, state, and persistence so the app actually behaves correctly across sessions. I learned the difference between visual-state logic and variable-based logic, and why relying on an element's appearance to drive behavior can cause hard-to-catch bugs. I also learned how important it is to pick the correct build target (web vs. native Android) upfront, since I initially generated a web version by mistake before catching it and rebuilding for Android specifically.

## Challenges and Solutions

One of the main challenges was a toggle bug on the Reports screen — items could be marked "reviewed" but tapping them again didn't revert the state. I solved this by rebuilding the logic around explicit boolean variables per item instead of inferring state from the icon's color, which fixed the bug reliably in both directions.

Another challenge was realizing partway through that I'd generated a web app instead of a native Android build — the platform selection step is easy to miss. I had to go back, explicitly select Android, and regenerate, which cost some time but taught me to double-check build targets before investing time in testing.

I also had to think carefully about data persistence — making sure KPI values, review states, and the profile name all survived closing and reopening the app, rather than resetting each session, which required using local storage rather than just in-memory variables.

## Feedback Received

The main direction was to follow the design we chose and focus on making the app both visually similar and functional.

## Reflection

This week showed me how much can go wrong in small, easy-to-miss ways — a mis-set platform option, a state-logic bug that only shows up when you test both directions of an interaction — and how much of development is actually catching and fixing those small things methodically. It was satisfying to go from a design reference to an actual working, testable app, and I have a much better sense now of why testing thoroughly (not just "does it look right") matters so much.