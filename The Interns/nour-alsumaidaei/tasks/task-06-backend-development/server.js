const express = require("express");
const cors = require("cors");
const portfolioRoutes = require("./routes/portfolio");
const { login } = require("./auth");
require("./db"); // initializes + seeds the SQLite database on startup

const app = express();
const PORT = process.env.PORT || 3000;

app.use(cors());
app.use(express.json());

// Simple request logger (useful for testing/debugging during the internship task)
app.use((req, res, next) => {
  console.log(`${new Date().toISOString()} ${req.method} ${req.path}`);
  next();
});

app.get("/", (req, res) => {
  res.status(200).json({
    success: true,
    message: "Nour's Portfolio API is running",
    endpoints: [
      "GET /api/profile",
      "GET /api/skills?category=",
      "GET /api/experience",
      "GET /api/projects",
      "GET /api/projects/:id",
      "POST /api/contact",
      "GET /api/contact (admin, requires Bearer token)",
      "POST /api/auth/login",
    ],
  });
});

app.post("/api/auth/login", login);
app.use("/api", portfolioRoutes);

// 404 handler
app.use((req, res) => {
  res.status(404).json({ success: false, error: "Endpoint not found" });
});

// Centralized error handler
app.use((err, req, res, next) => {
  console.error(err.stack);
  res.status(500).json({ success: false, error: "Internal server error" });
});

app.listen(PORT, () => {
  console.log(`Portfolio API listening on http://localhost:${PORT}`);
});

module.exports = app;
