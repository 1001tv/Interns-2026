# Weekly Report — Week 8: Backend Development

**Intern:** Nour
**Focus area:** Backend development, server-side applications, APIs, databases, authentication, and data processing

## Summary

This week I got hands-on with backend development by building a small REST API in Node.js/Express that powers my personal data-analyst portfolio. The goal was to move past frontend-only work and understand how a server receives requests, validates data, talks to a data layer, and returns structured responses.

## Main Activities — what I did

- **Set up the backend environment:** Initialized a Node.js project (`package.json`), installed Express and CORS, and structured the project into `server.js` (entrypoint), `routes/` (endpoint logic), and `data/` (mock data layer).
- **Explored the backend project structure:** Separated concerns — routing, data access, and the server bootstrap each live in their own file, which mirrors how a larger backend (e.g. what I saw with dashboards/pipelines at Qi Card) is organized.
- **Understood HTTP methods and API endpoints:** Implemented `GET` endpoints for reading data (profile, skills, experience, projects) and a `POST` endpoint for creating a resource (contact messages), matching REST conventions and correct status codes (`200`, `201`, `400`, `404`, `500`).
- **Created/modified API endpoints:**
  - `GET /api/profile`
  - `GET /api/skills` (with optional `?category=` query filter)
  - `GET /api/experience`
  - `GET /api/projects` and `GET /api/projects/:id`
  - `POST /api/contact`
  - `GET /api/contact`
- **Validated request data and handled errors:** Server-side validation on `POST /api/contact` (required fields, minimum lengths, email format via regex), a centralized 404 handler for unknown routes, and a centralized error-handling middleware for unexpected failures.
- **Connected the API to data:** Endpoints read from a mock JSON "database" (`data/mockData.json`); the contact endpoint writes to an in-memory store, structured so it can be swapped for a real database (e.g. PostgreSQL) without changing the route logic.
- **Tested the endpoints:** Manually tested every endpoint with `curl`, covering both success and failure paths (valid/invalid contact submissions, valid/invalid/missing project ids).
- **Documented the API:** Wrote `API.md` with the request/response shape, status codes, and validation rules for every endpoint.

## Deliverables

- Working backend with 6 endpoints (`server.js`, `routes/portfolio.js`, `data/mockData.json`)
- `API.md` — full API documentation with example requests and responses
- This weekly report
- A 3D interactive portfolio front-end that consumes this API's data shape

## What I learned

- How Express structures routing, middleware, and error handling.
- Why validating input on the server matters even when the frontend already validates it.
- The difference between query params (`?category=`) and path params (`/:id`) and when to use each.
- How to design consistent JSON response shapes (`{ success, data }` / `{ success, error }`) so a frontend can rely on a predictable contract.

## Follow-up work (same week)

After the initial review, I addressed the two gaps I'd flagged as "next steps":

- **Replaced the mock JSON with a real database.** Swapped `data/mockData.json` for a SQLite database (`better-sqlite3`), with a proper schema (`profile`, `skills`, `experience`, `projects`, `contact_messages`) created and seeded automatically on first run. Route logic changed from reading a JSON object to running parameterized SQL queries — a closer approximation of how a production backend works.
- **Added authentication.** Built a `POST /api/auth/login` endpoint issuing JWTs, and a `requireAuth` middleware protecting `GET /api/contact` (the endpoint that exposes visitor messages) so only the portfolio owner can read submitted messages. Verified both the happy path (valid login → valid token → 200) and the failure paths (wrong credentials → 401, missing/invalid token → 401).

## Next steps

- Hash the admin password (e.g. bcrypt) instead of comparing plaintext, and move credentials fully into environment variables for deployment.
- Add automated tests (e.g. Jest + Supertest) instead of manual curl testing.
- Deploy the API publicly so the live portfolio's contact form can call it directly instead of only validating client-side.
