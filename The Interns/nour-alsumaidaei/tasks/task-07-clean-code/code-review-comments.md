# Code Review — CV Dashboard (`nour_cv_dashboard.html`)

Reviewed as a peer review pass before refactoring, in the style of PR comments. Each item references the original single-file version.

---

**File: `nour_cv_dashboard.html`, inline `<style>` block**
> Consider pulling this into its own `.css` file. Right now a reviewer has to scroll past 170 lines of CSS before reaching any markup, and the stylesheet can't be cached separately from the page.
**Severity:** Minor — Resolved in refactor (`styles.css`).

---

**File: `nour_cv_dashboard.html`, `<style>`, multiple rules**
> `#1b2233`, `#f5a623`, and `#3ccfcf` each appear 5+ times. If the brand color changes, every rule needs a manual find-and-replace, which is error-prone. Suggest CSS custom properties.
**Severity:** Minor — Resolved (`:root` variables in `styles.css`).

---

**File: `nour_cv_dashboard.html`, line ~424**
```js
document.getElementById('greet-button').onclick = function() {
```
> Using `.onclick =` means only one handler can ever be attached to this element — a second assignment elsewhere would silently replace this one. `addEventListener` doesn't have that failure mode and is the convention going forward.
**Severity:** Minor — Resolved.

---

**File: `nour_cv_dashboard.html`, line ~406**
```js
function searchWord(text, wordToSearch) {
    text = text.toLowerCase().replace(/[.,]/g, "");
    wordToSearch = wordToSearch.toLowerCase();
```
> Reassigning function parameters inside the function body makes the function harder to reason about — a reader can no longer trust that `text` means what the caller passed in. Prefer new `const` variables for the normalized values. Also: this function does three things (normalize, split, count) with no name that says so — the current name just says "search."
**Severity:** Minor — Resolved, renamed to `countWordOccurrences`, no parameter mutation.

---

**File: `nour_cv_dashboard.html`, line ~436**
```js
var colorIndex = 0;
document.getElementById('color-button').onclick = function() {
    colorIndex = colorIndex + 1;
    if (colorIndex == colors.length) {
        colorIndex = 0;
    }
```
> `colorIndex` is a global `var` that anything on the page could read or overwrite. It's also using `==` rather than `===`. A closure (or a small class) would encapsulate the state, and `% colors.length` removes the manual reset branch.
**Severity:** Minor — Resolved via `createBackgroundCycler`.

---

**File: `nour_cv_dashboard.html`, line ~479**
```js
document.getElementById('delete-skill-button').onclick = function() {
    var skillItems = document.getElementsByClassName('skill-item');
    if (skillItems.length > 0) {
        document.getElementById('skill-list').removeChild(skillItems[skillItems.length - 1]);
    } else {
        alert("No skills to delete.");
    }
};
```
> Logic reads back-to-front — the "normal" path is buried inside the `if`, and the edge case is the `else`. A guard clause (`if (empty) { alert; return; }` then the normal path) reads top-to-bottom in the order a user would expect.
**Severity:** Nit — Resolved.

---

**File: `nour_cv_dashboard.html`, whole file**
> No comments distinguish "this is a reusable helper" from "this is wiring for one specific button." Once the file is split, it would help to also separate function *definitions* from the code that *attaches* them to the DOM, so each can be read independently.
**Severity:** Suggestion — Resolved via `initCvDashboard()`.

---

## Not changed (flagged, left as-is)

- **Inline `style="text-align:center;"` on the "Back to top" link** — minor, low-impact, left alone to keep this refactor focused on the JS/CSS issues above rather than rewriting every inline style in the markup.
- **Repeated `<div class="panel">` structure per section** — this is content repetition (each CV section legitimately needs its own panel), not logic duplication, so no refactor was needed here.
