# Weekly Report — Week 7

**Clean Code: Readable, Maintainable, Professional Code**
Intern: Nour AlSabtin | Baghdad, Iraq

## Summary

This week I applied Clean Code principles to refactor my CV dashboard from Week 5. The page still looks and behaves exactly the same, but the underlying HTML, CSS, and JavaScript are now organized, named, and structured in a way that's much easier to read, extend, and review.

## What I Did

I started by reviewing my own Week 5 code as if it were a teammate's pull request, and wrote down every code-quality issue I found — repeated color values, anonymous `onclick` functions, a function that mutated its own parameters, and nested `if/else` logic that was harder to follow than it needed to be. I then refactored against that list:

- **Separated concerns:** split the single `.html` file into `index.html` (structure), `styles.css` (presentation), and `script.js` (behavior).
- **Removed duplication:** replaced 15+ repeated hex color values with CSS custom properties (`--color-primary`, `--color-accent`, etc.), and added a `byId()` helper so `document.getElementById` logic isn't repeated in every handler.
- **Gave every function one job:** each button now has its own named handler (`handleAddSkillButtonClick`, `handleSendButtonClick`, ...) instead of one large anonymous function doing input-reading, computation, and DOM updates together.
- **Simplified logic:** replaced a manual reset check with the modulo operator for the background-color cycler, and replaced nested `if/else` blocks with guard clauses.
- **Followed consistent conventions:** `const`/`let` instead of `var`, `===` instead of `==`, template literals instead of string concatenation, and `addEventListener` instead of `.onclick =`.
- **Documented the reasoning:** added a short comment above each non-obvious function explaining *why* it's written that way, not just what it does.

## What I Learned

| Clean Code Idea | What It Means in Practice |
|---|---|
| Meaningful names | A variable called `visitorName` tells the next reader what it holds; `visitor` or `name` makes them guess. |
| DRY (Don't Repeat Yourself) | Repeated values aren't just extra typing — they're multiple places a future change has to remember to update. |
| Single responsibility | A function that reads input, computes a result, *and* updates the DOM is three functions pretending to be one. Splitting them makes each one testable on its own. |
| Guard clauses | Handling the exceptional case first and returning early keeps the "normal" path unindented and easy to follow. |
| Separation of concerns | Keeping markup, style, and behavior in separate files isn't just tidiness — it means a CSS change can't accidentally break JavaScript logic sitting in the same file. |

## Before-and-After Example

```js
// Before
var colorIndex = 0;
document.getElementById('color-button').onclick = function() {
    colorIndex = colorIndex + 1;
    if (colorIndex == colors.length) {
        colorIndex = 0;
    }
    document.body.style.backgroundColor = colors[colorIndex];
};

// After
function createBackgroundCycler(colors) {
  let index = 0;
  return function cycleBackground() {
    index = (index + 1) % colors.length;
    document.body.style.backgroundColor = colors[index];
  };
}
```

The behavior is identical, but the state is no longer a loose global variable, and the reset logic disappears entirely once the modulo operator does the same job.

## Peer Code Review

I wrote up my review comments as if submitting them on a teammate's pull request — one comment per issue, each with a severity label and a note on how it was resolved. Full list is in `code-review-comments.md`.

## Challenges

- Deciding which repetitions were real code duplication (worth fixing) versus content repetition that's just how a CV naturally reads (the repeated `panel` divs per section — left alone).
- Making sure the refactor didn't change any visible behavior — I re-checked every button against the original page after each change.
- Resisting the urge to redesign the page while refactoring; Clean Code is about the same behavior with better structure, not a redesign.

## Next Steps

- Add simple unit tests for `countWordOccurrences` now that it's a pure function with no DOM dependency.
- Apply the same `byId` / named-handler pattern consistently if I add new interactive features later.
- Carry the external-file structure (`index.html` / `styles.css` / `script.js`) into future weeks instead of starting new features back in a single file.
