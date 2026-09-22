# Task 05 - Web Application Development

**Intern:** Nour Al-Sumaidaei
**Week:** 5
**Live preview:** https://nourcv1.netlify.app/
**Source file:** [`index.html`](./index.html)

## Objective

Learn the fundamentals of web application development: frontend structure, user interfaces, browser behavior, and API communication.

## What I Built

A personal **CV Dashboard**: a one-page website that presents my CV as a monitoring dashboard, inspired by the Grafana dashboards I built during my internship. Each CV section is a "panel", and key results (25% data integrity improvement, 20+ customer patterns, 40% less setup time, 100,000+ rows cleaned) appear as KPI boxes.

The whole site is one `index.html` file with the CSS and JavaScript inside it, using no libraries or frameworks.

## Main Activities

| Activity | What I did |
|---|---|
| Set up the web development environment | Used a code editor and a browser with developer tools. Deployed the page to Netlify to get a public preview link. |
| Understand HTML, CSS, and JavaScript | Structured the page with HTML, styled it with CSS, and added behavior with JavaScript. |
| Explore the web project structure | Kept the project as a single `index.html` with three parts: `<style>`, the page content in `<body>`, and `<script>`. |
| Build a responsive user interface | Added the viewport meta tag, a centered container with `max-width`, and a flexbox row with `flex-wrap` for the KPI boxes so they wrap on small screens. |
| Add forms and user interactions | Built a contact form and control-panel buttons (see below). |
| Connect the interface to a simple API | Not done in this version. See "Limitations and Next Steps". |
| Test on different screen sizes | See "Testing". |
| Document the implementation | This README and the Week 5 report. |

## Technical Explanation

### HTML (structure)
- Semantic sections with `id`s (`about`, `experience`, `projects`, `skills`, `education`, `contact`) and a navigation bar that uses internal links (`href="#projects"`).
- Lists (`ul`, `ol`) for experience and certifications.
- Tables with `rowspan` (skills) and `colspan` (education).
- A form with `fieldset`, `legend`, text, textarea, radio, checkbox, select, date, and color inputs, plus submit and reset buttons.
- External links with `target="_blank"` and a `mailto:` link.

### CSS (presentation)
- Dark dashboard theme with amber and teal accents.
- Class selectors for panels, KPI boxes, buttons, and skill items.
- Combinator selectors: descendant (`#experience p`), child (`#experience > p`), adjacent sibling (`#projects h3 + p`), and general sibling (`#projects h3 ~ ul`).
- Link states: `a:link`, `a:visited`, `a:hover`, `a:active`.
- Flexbox for the KPI row.

### JavaScript (behavior)
The script uses the DOM (`getElementById`, `createElement`, `appendChild`, `removeChild`) and `onclick` handlers.

| Feature | How it works |
|---|---|
| Say Your Name | `prompt()` asks for a name, then updates the greeting text and the tab title. |
| Change Background Color | Cycles through a list of colors and sets `document.body.style.backgroundColor`. |
| Hide / Show Projects | Switches the projects panel between `display: none` and `display: block`. |
| Search a Word | `searchWord(text, word)` splits the summary into words and counts matches with a loop. |
| Add Skill | Creates a new `<li>` and appends it to the skill list. |
| Delete Last Skill | Removes the last `<li>`, or shows an alert if the list is empty. |
| Send button | Checks the name field and shows an alert. It does not send data anywhere yet. |

## Testing

- Verified that the deployed page loads at the preview link and that all sections render.
- Screenshots go in the [`screenshots/`](./screenshots) folder:
  - `desktop.png` - full page on a desktop screen
  - `mobile.png` - page at a phone-sized width (browser DevTools device mode)
  - `interactions.png` - the page after using the control-panel buttons

## Limitations and Next Steps

- **No API connection yet.** The contact form only shows an alert, and all content is written directly in the HTML. A next step is to call a public API with `fetch()`, for example the GitHub API to list my repositories, and show the result on the page.
- **Only basic responsiveness.** The layout wraps using flexbox, but there are no `@media` rules yet. A next step is to adjust font sizes and spacing for small screens.
- Form data is not validated beyond the name check.

## Expected Outcome

I can build a basic responsive web page with forms and interactions, and I understand how the browser turns HTML, CSS, and JavaScript into a working page. The next step is learning frontend-backend communication through API calls.
