const jwt = require("jsonwebtoken");

const JWT_SECRET = process.env.JWT_SECRET || "dev-secret-change-me";
// Demo credentials for the admin account (would live in env vars / a hashed
// users table in a real deployment — kept simple for this internship task).
const ADMIN_USER = process.env.ADMIN_USER || "nour";
const ADMIN_PASS = process.env.ADMIN_PASS || "changeme123";

function login(req, res) {
  const { username, password } = req.body || {};

  if (!username || !password) {
    return res.status(400).json({ success: false, error: "username and password are required" });
  }

  if (username !== ADMIN_USER || password !== ADMIN_PASS) {
    return res.status(401).json({ success: false, error: "Invalid credentials" });
  }

  const token = jwt.sign({ sub: username, role: "admin" }, JWT_SECRET, { expiresIn: "2h" });
  res.status(200).json({ success: true, token, expiresIn: "2h" });
}

// Middleware: require a valid "Authorization: Bearer <token>" header
function requireAuth(req, res, next) {
  const header = req.headers.authorization || "";
  const [scheme, token] = header.split(" ");

  if (scheme !== "Bearer" || !token) {
    return res.status(401).json({ success: false, error: "Missing or malformed Authorization header" });
  }

  try {
    req.user = jwt.verify(token, JWT_SECRET);
    next();
  } catch (err) {
    return res.status(401).json({ success: false, error: "Invalid or expired token" });
  }
}

module.exports = { login, requireAuth };
