# Weekly Report - Week 5

**Intern:** Nour Al-Sumaidaei
**Focus:** Web Application Development
**Task:** [Task 05](../tasks/task-05-web-application-development/README.md)
**Live preview:** https://nourcv1.netlify.app/

## Summary

This week I learned the fundamentals of web application development and applied them by building and publishing a personal CV website. I designed it as a "CV Dashboard", where each section is a dashboard panel and my key results are shown as KPI boxes, based on my earlier Grafana work.

## What I Learned

### 1. Frontend structure with HTML
- How to organize a page into sections with `id`s and link between them using internal links.
- How to present information with lists, tables (`rowspan` and `colspan`), and forms with different input types.
- Why the viewport meta tag matters: without it, phones show the page zoomed out.

### 2. Styling with CSS
- How class and id selectors work, and how combinators (descendant, child, adjacent sibling, general sibling) target exactly the elements I want.
- How to style link states (`link`, `visited`, `hover`, `active`).
- How flexbox with `flex-wrap` lets boxes rearrange on smaller screens.

### 3. Browser behavior with JavaScript
- The browser builds a DOM tree from the HTML, and JavaScript can read and change it.
- How to respond to user actions with `onclick` handlers.
- How to create and remove elements (`createElement`, `appendChild`, `removeChild`) and change styles from code.
- How to reuse logic in a function, such as counting how often a word appears in a text.

### 4. Publishing a web page
- How to deploy a static page to Netlify and share it with a public link.
- The difference between running a file locally and viewing a hosted page.

### 5. Documentation
- How to describe a technical implementation clearly so someone else can understand and reproduce it.

## Work Completed

- Built a one-page CV website with a navigation bar, a control panel, KPI boxes, experience, projects, skills, education, and a contact form.
- Added interactions: greeting by name, background color changes, hide/show projects, word search, and adding and deleting skills.
- Deployed the site to Netlify: https://nourcv1.netlify.app/
- Wrote the task README with a technical explanation.

## Challenges

- Planning the structure of the page so that it stayed simple and readable while still showing a variety of HTML, CSS, and JavaScript features.
- Making the KPI boxes fit on different screen widths, which I solved with flexbox and `flex-wrap`.

## What Is Still Missing

- **API communication:** the page does not call an API yet. I plan to add a `fetch()` request to a public API, such as the GitHub API, to display data on the page.
- **Deeper responsive design:** adding `@media` rules for small screens.

## Plan for Next Week

- Learn how `fetch()` and JSON work and connect the page to a simple API.
- Improve responsiveness with media queries and test on more screen sizes.
- Add validation to the contact form.

## Deliverables

| Deliverable | Status |
|---|---|
| Working web page | Done - https://nourcv1.netlify.app/ |
| Preview link | Done |
| Screenshots | In `tasks/task-05-web-application-development/screenshots/` |
| Technical explanation | Done - task README |
| Weekly report | Done - this file |
