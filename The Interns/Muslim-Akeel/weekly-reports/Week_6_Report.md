# Internship Weekly Report — Week 6

**Name:** Muslim Akeel  
**Submission Date:** September 24, 2026  

---

## 1. Weekly Summary
During Week 6, I transitioned from frontend design to backend development, exploring server-side architecture, RESTful API design, data routing, input validation, and endpoint testing using Node.js and Express.

---

## 2. Tasks & Activities Completed
* **Backend Environment Setup:** Initialized a Node.js project environment and structured server files utilizing Express.js middleware.
* **RESTful API Development:** Created functional endpoints for managing mock database records (filament inventory retrieval and print job submissions).
* **Data Validation & Error Handling:** Implemented server-side conditional checks to validate incoming payload fields and return descriptive HTTP error codes (`400 Bad Request`, `201 Created`).
* **API Documentation:** Compiled structured documentation outlining endpoints, methods, and expected JSON structures.

---

## 3. Skills Learned
* **Server Architecture:** Configuring HTTP servers and parsing JSON payloads using Express middleware.
* **REST API Design Principles:** Structuring clean endpoint routes following standard conventions (`GET`, `POST`).
* **Robust Error Management:** Intercepting malformed user input to prevent application crashes and communicate clear status feedback.

---

## 4. Challenges & Solutions
* **Challenge:** Ensuring incoming POST request bodies contained valid data without crashing the server when optional or required fields were omitted.
* **Solution:** Implemented explicit validation middleware checking parameters before pushing new data to the mock queue array, responding cleanly with standard HTTP 400 error payloads.

---

## 5. Next Steps
* Integrate persistent database storage (such as MongoDB or SQLite) for data retention beyond runtime memory arrays.
