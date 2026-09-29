# Before-and-After Code Comparison — CV Dashboard Refactor

This compares the original `nour_cv_dashboard.html` (one file, inline `<style>` and `<script>`) against the refactored version (`index.html` + `styles.css` + `script.js`), organized by the Clean Code principle each change applies.

## 1. Separation of concerns

**Before:** all markup, styling, and behavior lived in a single `.html` file — a ~500-line file mixing three different languages.

**After:** split into three files, each with one job:
- `index.html` — structure/content only
- `styles.css` — presentation only
- `script.js` — behavior only, loaded with `defer` so it runs after the DOM is parsed

```html
<!-- Before -->
<head>
  <style> /* 170+ lines of CSS */ </style>
</head>
...
<script> /* 90+ lines of JS */ </script>

<!-- After -->
<head>
  <link rel="stylesheet" href="styles.css">
</head>
...
<script src="script.js" defer></script>
```

## 2. Reduce duplicated code

**Before:** the same six hex colors were repeated 15+ times across the stylesheet (`#1b2233`, `#f5a623`, `#3ccfcf`, `#e6e9f0`, `#262f45`, `#b58cff`...). Changing the theme meant hunting down every occurrence.

```css
/* Before */
h1 { color: #f5a623; }
.kpi-number { color: #f5a623; }
.button { background-color: #f5a623; }
```

**After:** colors are defined once as CSS custom properties and referenced everywhere.

```css
/* After */
:root {
  --color-primary: #f5a623;
}
h1 { color: var(--color-primary); }
.kpi-number { color: var(--color-primary); }
.button { background-color: var(--color-primary); }
```

Duplication also showed up in JavaScript: `document.getElementById(...)` was repeated in every handler. A one-line helper removed that repetition:

```js
// After
function byId(id) {
  return document.getElementById(id);
}
```

## 3. Meaningful names

**Before:** `li`, `visitor`, `projects` for a style object, generic `var` names that don't say what they hold.

```js
var li = document.createElement('li');
var visitor = prompt("What is your name?");
```

**After:** names describe exactly what the value is.

```js
const skillItem = document.createElement("li");
const visitorName = prompt("What is your name?");
```

## 4. Function and class responsibility

**Before:** every button had one large anonymous function assigned directly to `.onclick`, mixing "read input," "compute," and "update the DOM" in one block.

**After:** each handler does one thing and has a name that states it. The pure calculation (`countWordOccurrences`) is separated from the DOM-reading code that calls it, so the logic itself could be unit-tested without a browser.

```js
// After — pure function, no DOM access, easy to test in isolation
function countWordOccurrences(text, targetWord) {
  const normalizedText = text.toLowerCase().replace(/[.,]/g, "");
  const normalizedTarget = targetWord.toLowerCase();
  return normalizedText.split(/\s+/).filter((w) => w === normalizedTarget).length;
}
```

## 5. Simplify complex logic

**Before:** the color cycler used a manual reset check, and the word counter used a `for` loop with an index variable and a mutated parameter.

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
```

**After:** the modulo operator removes the reset branch entirely, and the counter state is hidden inside a closure instead of sitting in global scope.

```js
// After
function createBackgroundCycler(colors) {
  let index = 0;
  return function cycleBackground() {
    index = (index + 1) % colors.length;
    document.body.style.backgroundColor = colors[index];
  };
}
```

Nested `if/else` blocks were also replaced with guard clauses:

```js
// Before
if (name) {
    alert("Thank you " + name + "! Your message was received.");
} else {
    alert("Please write your name first.");
}

// After
if (!visitorName) {
  alert("Please write your name first.");
  return;
}
alert(`Thank you ${visitorName}! Your message was received.`);
```

## 6. Formatting and coding conventions

**Before:** mixed `var`/no `var`, double quotes and single quotes used inconsistently, string concatenation with `+`, loose equality (`==`).

**After:** consistent `const`/`let`, template literals, strict equality (`===`), and `addEventListener` instead of overwriting `.onclick` (which allows only one handler per element and silently drops any previous one).

## Summary table

| Principle | Before | After |
|---|---|---|
| Files | 1 file, 3 languages mixed | 3 files, 1 language each |
| Repeated colors | 15+ literal hex values | 1 set of CSS variables |
| Global mutable state | `colors`, `colorIndex` as globals | enclosed in `createBackgroundCycler` |
| Equality checks | `==` | `===` |
| Event binding | `.onclick =` (overwrites) | `.addEventListener()` (additive) |
| Word counter | mutates its parameters, manual loop | pure function, `.filter()` |
| Conditionals | nested `if/else` | guard clauses |
