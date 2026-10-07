# Final Report — 1001 Internship

**Intern: Nour AlSabtin | Baghdad, Iraq**

## Overview

This report summarizes what I learned across the weeks of the internship, built around a single running project — my personal CV dashboard — which let each week's focus build on the one before it instead of starting from scratch each time.

## Week 5 — Frontend Fundamentals

**Focus:** HTML structure, CSS layout and responsiveness, JavaScript and the DOM, browser behavior, and basic frontend-backend thinking.

I built an interactive CV dashboard as a single-page responsive site: a fixed section layout (About, KPIs, Experience, Projects, Skills, Education, Contact), in-page anchor navigation, a flexbox-based KPI layout that reflows on narrow screens, and a control panel of JavaScript-driven interactions — changing a greeting and the tab title, cycling the background color, showing/hiding sections, searching text, and adding/removing list items. I deployed it live on Netlify and tested it across desktop, tablet, and mobile widths.

**Key takeaway:** the frontend manages its own state and responds to user events even before it talks to a server — understanding that first made it much easier to reason about adding real API calls later.

## Week 7 — Clean Code

**Focus:** meaningful names, reducing duplication, single-responsibility functions, separation of concerns, simplified logic, formatting conventions, refactoring, and peer code review.

I went back to the Week 5 dashboard and refactored it rather than rebuilding it — the same visible behavior, but better-structured code underneath:
- Split one `.html` file into `index.html`, `styles.css`, and `script.js`.
- Replaced 15+ repeated hex color values with CSS custom properties.
- Gave every button its own named handler instead of one large anonymous function per `onclick`.
- Replaced a manual index-reset with the modulo operator, and nested `if/else` with guard clauses.
- Wrote up a before-and-after comparison and a peer code review of my own earlier code before changing it.

**Key takeaway:** code review is most useful *before* refactoring, not after — writing down the problems first turned the refactor into a checklist instead of a vague "clean this up" task. I also learned that clean code is about restructuring, not redesigning — the goal was identical behavior with better-organized code, not new features.

## Skills Gained

| Area | What I Can Now Do |
|---|---|
| HTML/CSS | Build a responsive, multi-section page using flexbox, semantic structure, and CSS custom properties for theming |
| JavaScript | Write event-driven, DOM-based interactivity with pure, testable helper functions |
| Code quality | Spot duplication, poor naming, and tangled logic, and refactor them safely without changing behavior |
| Process | Deploy a static site, test across screen sizes, write a peer code review, and document the reasoning behind changes |
| Git/GitHub | Work on a feature branch, commit, push, and open a pull request following the team's workflow |

## Reflection

Reusing the same project across weeks made the learning cumulative — Week 7's refactor only made sense *because* Week 5 had working code to improve. Each week's report and brief also became a record I could look back on, which made writing this final report mostly a matter of pulling the throughline together rather than reconstructing what happened.

## Looking Ahead

- Connect the dashboard to a real external API, which Week 5 flagged as a planned next step.
- Carry the multi-file, Clean Code structure into any new feature from the start, instead of refactoring after the fact.
- Keep writing a short before-and-after comparison for any future refactor — it made the changes easier to explain and to justify.
