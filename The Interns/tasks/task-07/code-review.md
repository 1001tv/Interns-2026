# Code Review & Refactoring Report — Week 7

## 1. Overview of Code Quality Issues Found in Initial Implementation
* **Monolithic Route Logic:** Validation rules were written directly inline inside the `POST` route, making the function long and hard to read.
* **Inconsistent Naming Conventions:** Mixed styles (using snake_case like `stock_grams` alongside camelCase) created confusion.
* **Lack of Helper Functions:** Code repetition for error handling could easily be cleaned up using modular helper functions.

---

## 2. Before-and-After Comparison

### Before (Messy Code Example)
```javascript
// Inline messy validation and mixed naming
app.post('/api/print-requests', (req, res) => {
    if (!req.body.student_email || !req.body.material || !req.body.quantity) {
        return res.status(400).json({ error: 'Missing fields' });
    }
    if (req.body.quantity <= 0) {
        return res.status(400).json({ error: 'Bad quantity' });
    }
    // ... saving logic mixed in ...
});

After (Clean Code Solution)
JavaScript
// Extracted into a clean validation helper with unified camelCase naming
function validatePrintRequest(body) {
    const { studentEmail, material, quantity } = body;
    if (!studentEmail || !material || !quantity) return 'Missing mandatory fields.';
    if (quantity <= 0) return 'Quantity must be positive.';
    return null;
}

app.post('/api/print-requests', (req, res) => {
    const error = validatePrintRequest(req.body);
    if (error) return res.status(400).json({ success: false, error });
    // Clean route execution
});
