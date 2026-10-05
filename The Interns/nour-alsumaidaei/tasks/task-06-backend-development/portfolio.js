const express = require("express");
const router = express.Router();
const db = require("../db");
const { requireAuth } = require("../auth");

/**
 * GET /api/profile
 */
router.get("/profile", (req, res) => {
  const row = db.prepare("SELECT * FROM profile WHERE id = 1").get();
  if (!row) return res.status(404).json({ success: false, error: "Profile not set" });

  res.status(200).json({
    success: true,
    data: { ...row, languages: JSON.parse(row.languages) },
  });
});

/**
 * GET /api/skills?category=
 */
router.get("/skills", (req, res) => {
  const { category } = req.query;

  const rows = category
    ? db.prepare("SELECT * FROM skills WHERE LOWER(category) = LOWER(?)").all(category)
    : db.prepare("SELECT * FROM skills").all();

  res.status(200).json({ success: true, count: rows.length, data: rows });
});

/**
 * GET /api/experience
 */
router.get("/experience", (req, res) => {
  const rows = db.prepare("SELECT * FROM experience").all();
  const data = rows.map((r) => ({ ...r, highlights: JSON.parse(r.highlights) }));
  res.status(200).json({ success: true, data });
});

/**
 * GET /api/projects
 */
router.get("/projects", (req, res) => {
  const rows = db.prepare("SELECT * FROM projects").all();
  const data = rows.map((r) => ({ ...r, stack: JSON.parse(r.stack) }));
  res.status(200).json({ success: true, count: data.length, data });
});

/**
 * GET /api/projects/:id
 */
router.get("/projects/:id", (req, res) => {
  const id = Number(req.params.id);
  if (!Number.isInteger(id)) {
    return res.status(400).json({ success: false, error: "Project id must be an integer" });
  }

  const row = db.prepare("SELECT * FROM projects WHERE id = ?").get(id);
  if (!row) {
    return res.status(404).json({ success: false, error: `Project with id ${id} not found` });
  }

  res.status(200).json({ success: true, data: { ...row, stack: JSON.parse(row.stack) } });
});

/**
 * POST /api/contact
 * Public. Validates and stores a contact message.
 */
router.post("/contact", (req, res) => {
  const { name, email, message } = req.body || {};
  const errors = [];

  if (!name || typeof name !== "string" || name.trim().length < 2) {
    errors.push("name is required and must be at least 2 characters");
  }
  const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
  if (!email || typeof email !== "string" || !emailRegex.test(email)) {
    errors.push("a valid email is required");
  }
  if (!message || typeof message !== "string" || message.trim().length < 5) {
    errors.push("message is required and must be at least 5 characters");
  }

  if (errors.length > 0) {
    return res.status(400).json({ success: false, errors });
  }

  const receivedAt = new Date().toISOString();
  const info = db
    .prepare("INSERT INTO contact_messages (name, email, message, received_at) VALUES (?, ?, ?, ?)")
    .run(name.trim(), email.trim(), message.trim(), receivedAt);

  res.status(201).json({
    success: true,
    message: "Message received. Thank you for reaching out.",
    data: { id: info.lastInsertRowid, name: name.trim(), email: email.trim(), message: message.trim(), receivedAt },
  });
});

/**
 * GET /api/contact
 * ADMIN ONLY — requires a valid Bearer token from POST /api/auth/login.
 */
router.get("/contact", requireAuth, (req, res) => {
  const rows = db.prepare("SELECT * FROM contact_messages ORDER BY id DESC").all();
  res.status(200).json({ success: true, count: rows.length, data: rows });
});

module.exports = router;
