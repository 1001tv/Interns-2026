# Portfolio API Documentation

Base URL (local): `http://localhost:3000`

A REST API serving the data behind Nour's data-analyst portfolio: profile info, skills, experience, projects, and a contact-message endpoint. Built with Node.js + Express, backed by a real **SQLite database** (`better-sqlite3`), with **JWT authentication** protecting the admin-only endpoint.

---

## POST /api/auth/login
Authenticates the portfolio owner and issues a JWT. Demo credentials (env-overridable): `ADMIN_USER` / `ADMIN_PASS`, default `nour` / `changeme123`.

**Request body**
```json
{ "username": "nour", "password": "changeme123" }
```

**Response `200`**
```json
{ "success": true, "token": "eyJhbGciOiJIUzI1NiIs...", "expiresIn": "2h" }
```

**Error `401`** (wrong credentials)
```json
{ "success": false, "error": "Invalid credentials" }
```

Use the token on protected routes as `Authorization: Bearer <token>`.

---

## GET /
Health check / index of available endpoints.

**Response `200`**
```json
{
  "success": true,
  "message": "Nour's Portfolio API is running",
  "endpoints": ["GET /api/profile", "..."]
}
```

---

## GET /api/profile
Returns the profile summary.

**Response `200`**
```json
{
  "success": true,
  "data": {
    "name": "Nour",
    "title": "Data Analyst",
    "location": "Baghdad, Iraq",
    "education": "B.Sc. Computer Science, American University of Iraq - Baghdad (AUIB)",
    "languages": ["Arabic", "English"],
    "bio": "Computer Science student and aspiring data analyst..."
  }
}
```

---

## GET /api/skills
Returns all skills. Optional query param `category` filters by category (`Language`, `Library`, `Tool`).

**Request**
```
GET /api/skills?category=Tool
```

**Response `200`**
```json
{
  "success": true,
  "count": 3,
  "data": [
    { "id": 4, "name": "Grafana", "category": "Tool", "level": 80 },
    { "id": 5, "name": "Power BI", "category": "Tool", "level": 75 },
    { "id": 6, "name": "Excel", "category": "Tool", "level": 85 }
  ]
}
```

---

## GET /api/experience
Returns work experience entries.

**Response `200`**
```json
{
  "success": true,
  "data": [
    {
      "id": 1,
      "company": "Qi Card Company",
      "role": "Data / Backend Intern",
      "highlights": ["Built data pipelines for internal analytics", "..."]
    }
  ]
}
```

---

## GET /api/projects
Returns all projects. Optional query param `id` returns a single project (same as the path-param variant below).

**Response `200`**
```json
{
  "success": true,
  "count": 3,
  "data": [
    {
      "id": 1,
      "name": "AI-Powered Real Estate Platform",
      "description": "Senior project for the Iraqi real estate market...",
      "stack": ["Next.js", "React Three Fiber", "FastAPI", "PostgreSQL", "AI APIs"]
    }
  ]
}
```

## GET /api/projects/:id
Returns a single project by id.

**Request**
```
GET /api/projects/1
```

**Response `200`**
```json
{
  "success": true,
  "data": {
    "id": 1,
    "name": "AI-Powered Real Estate Platform",
    "description": "Senior project for the Iraqi real estate market with PropTech commercial ambitions, built with a partner.",
    "stack": ["Next.js", "React Three Fiber", "FastAPI", "PostgreSQL", "AI APIs"]
  }
}
```

**Error `404`** (unknown id)
```json
{ "success": false, "error": "Project with id 99 not found" }
```

**Error `400`** (non-integer id)
```json
{ "success": false, "error": "Project id must be an integer" }
```

---

## POST /api/contact
Submits a contact message. Validates all fields server-side.

**Request body**
```json
{
  "name": "Test User",
  "email": "test@example.com",
  "message": "Hello there!"
}
```

**Response `201`**
```json
{
  "success": true,
  "message": "Message received. Thank you for reaching out.",
  "data": {
    "id": 1,
    "name": "Test User",
    "email": "test@example.com",
    "message": "Hello there!",
    "receivedAt": "2026-09-25T20:54:31.641Z"
  }
}
```

**Validation rules**
| Field   | Rule                                  |
|---------|----------------------------------------|
| name    | required, string, min 2 characters     |
| email   | required, must match a valid email format |
| message | required, string, min 5 characters     |

**Error `400`** (e.g. `{"name":"a","email":"bad","message":"hi"}`)
```json
{
  "success": false,
  "errors": [
    "name is required and must be at least 2 characters",
    "a valid email is required",
    "message is required and must be at least 5 characters"
  ]
}
```

---

## GET /api/contact
**Admin only.** Returns all submitted contact messages. Requires `Authorization: Bearer <token>` from `POST /api/auth/login`.

**Response `200`**
```json
{ "success": true, "count": 1, "data": [ { "id": 1, "name": "Test User", "...": "..." } ] }
```

**Error `401`** (missing/invalid token)
```json
{ "success": false, "error": "Missing or malformed Authorization header" }
```

---

## Running & testing locally

```bash
cd backend
npm install
npm start
# Server on http://localhost:3000 — creates & seeds portfolio.db on first run

# Example tests with curl:
curl http://localhost:3000/api/profile

curl -X POST http://localhost:3000/api/contact \
  -H "Content-Type: application/json" \
  -d '{"name":"Test User","email":"test@example.com","message":"Hello there!"}'

# Admin flow:
TOKEN=$(curl -s -X POST http://localhost:3000/api/auth/login \
  -H "Content-Type: application/json" \
  -d '{"username":"nour","password":"changeme123"}' | python3 -c "import sys,json;print(json.load(sys.stdin)['token'])")

curl http://localhost:3000/api/contact -H "Authorization: Bearer $TOKEN"
```

All endpoints above were tested manually with curl during development (see Weekly Report for results): every success path returns `200`/`201` with the expected JSON shape, and error paths (`400` invalid contact form, `400` non-integer project id, `404` unknown project id, `401` missing/invalid/wrong-credential auth) return the correct status codes and error messages.

## Data layer

Data lives in a SQLite database (`portfolio.db`, created automatically on first run) with tables for `profile`, `skills`, `experience`, `projects`, and `contact_messages`. `db.js` creates the schema and seeds it once if empty, so restarting the server doesn't duplicate data.

## Environment variables (optional)

| Variable      | Default              | Purpose                          |
|---------------|-----------------------|-----------------------------------|
| `PORT`        | `3000`                | Server port                      |
| `JWT_SECRET`  | `dev-secret-change-me`| Signing key for auth tokens       |
| `ADMIN_USER`  | `nour`                | Admin login username              |
| `ADMIN_PASS`  | `changeme123`         | Admin login password              |

In a real deployment these should be set via a `.env` file (already a dependency) rather than left as defaults.
