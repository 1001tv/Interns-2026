/**
 * AUIB 3D Printing Club API - Week 7 (Refactored for Code Quality)
 * Improvements: Separation of concerns, descriptive naming, clean error handling.
 */

const express = require('express');
const app = express();
const PORT = process.env.PORT || 3000;

app.use(express.json());

const filamentInventory = [
    { id: 1, material: 'PLA', color: 'Matte Black', stockGrams: 850, pricePerGram: 0.05 },
    { id: 2, material: 'PETG', color: 'Transparent Blue', stockGrams: 500, pricePerGram: 0.07 },
    { id: 3, material: 'Resin', color: 'Standard Grey', stockGrams: 1200, pricePerGram: 0.10 }
];

const printJobQueue = [
    { id: 1, studentEmail: 'student@auib.edu.iq', material: 'PLA', quantity: 2, status: 'Pending' }
];

function validatePrintRequest(body) {
    const { studentEmail, material, quantity } = body;
    
    if (!studentEmail || !material || !quantity) {
        return 'Validation Error: studentEmail, material, and quantity are mandatory fields.';
    }
    
    if (typeof quantity !== 'number' || quantity <= 0) {
        return 'Validation Error: Quantity must be a positive number.';
    }
    
    return null;
}

app.get('/', (req, res) => {
    res.status(200).json({ status: 'success', message: 'AUIB 3D Printing Club Clean API is running.' });
});

app.get('/api/inventory', (req, res) => {
    res.status(200).json({
        success: true,
        count: filamentInventory.length,
        data: filamentInventory
    });
});

app.get('/api/print-requests', (req, res) => {
    res.status(200).json({
        success: true,
        count: printJobQueue.length,
        data: printJobQueue
    });
});

app.post('/api/print-requests', (req, res) => {
    const validationError = validatePrintRequest(req.body);
    
    if (validationError) {
        return res.status(400).json({
            success: false,
            error: validationError
        });
    }

    const { studentEmail, material, quantity, fileLink } = req.body;

    const newJob = {
        id: printJobQueue.length + 1,
        studentEmail,
        material,
        quantity,
        fileLink: fileLink || 'No link provided',
        status: 'Queued',
        createdAt: new Date().toISOString()
    };

    printJobQueue.push(newJob);

    res.status(201).json({
        success: true,
        message: 'Print request successfully added to queue.',
        data: newJob
    });
});

app.listen(PORT, () => {
    console.log(`Clean server running on http://localhost:${PORT}`);
});
