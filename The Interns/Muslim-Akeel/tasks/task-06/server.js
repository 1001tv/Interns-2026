// AUIB 3D Printing Club Backend API - Week 6 Task
const express = require('express');
const app = express();
const PORT = process.env.PORT || 3000;

app.use(express.json());

let inventory = [
    { id: 1, material: 'PLA', color: 'Matte Black', stock_grams: 850, price_per_gram: 0.05 },
    { id: 2, material: 'PETG', color: 'Transparent Blue', stock_grams: 500, price_per_gram: 0.07 },
    { id: 3, material: 'Resin', color: 'Standard Grey', stock_grams: 1200, price_per_gram: 0.10 }
];

let printRequests = [
    { id: 1, student_email: 'student@auib.edu.iq', material: 'PLA', quantity: 2, status: 'Pending' }
];

app.get('/', (req, res) => {
    res.json({ message: 'Welcome to the AUIB 3D Printing Club API!' });
});

app.get('/api/inventory', (req, res) => {
    res.status(200).json({ success: true, count: inventory.length, data: inventory });
});

app.get('/api/print-requests', (req, res) => {
    res.status(200).json({ success: true, count: printRequests.length, data: printRequests });
});

app.post('/api/print-requests', (req, res) => {
    const { student_email, material, quantity, file_link } = req.body;

    if (!student_email || !material || !quantity) {
        return res.status(400).json({ 
            success: false, 
            error: 'Validation Error: student_email, material, and quantity are required fields.' 
        });
    }

    if (quantity <= 0) {
        return res.status(400).json({ 
            success: false, 
            error: 'Validation Error: Quantity must be at least 1.' 
        });
    }

    const newRequest = {
        id: printRequests.length + 1,
        student_email,
        material,
        quantity,
        file_link: file_link || 'Not provided',
        status: 'Queued',
        created_at: new Date().toISOString()
    };

    printRequests.push(newRequest);

    res.status(201).json({
        success: true,
        message: 'Print request successfully created!',
        data: newRequest
    });
});

app.listen(PORT, () => {
    console.log(`Server is running on http://localhost:${PORT}`);
});
