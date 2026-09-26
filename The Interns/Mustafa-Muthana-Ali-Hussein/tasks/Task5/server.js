



const express = require("express");
const fs = require("fs");

const app = express();

app.use(express.json());
app.use(express.static("public"));

// SIGN UP
app.post("/signup", (req, res) => {

    const { email, password } = req.body;

    if (!email || !password) {
        return res.status(400).json({
            message: "Email and password are required"
        });
    }

    let users = JSON.parse(fs.readFileSync("users.json"));

    users.push({
        email: email,
        password: password
    });

    fs.writeFileSync("users.json", JSON.stringify(users, null, 2));

    res.json({
        message: "Account created!"
    });
});


// LOGIN
app.post("/login", (req, res) => {

    const { email, password } = req.body;

    let users = JSON.parse(fs.readFileSync("users.json"));

    const user = users.find(
        user =>
            user.email === email &&
            user.password === password
    );

    if (user) {
        res.json({
            message: "Welcome back!"
        });
    } else {
        res.status(401).json({
            message: "Account not found"
        });
    }
});


app.listen(3000, () => {
    console.log("Server running at http://localhost:3000");
});
