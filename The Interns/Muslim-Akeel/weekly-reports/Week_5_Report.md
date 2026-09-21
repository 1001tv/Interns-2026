# Internship Weekly Report — Week 5

**Name:** Muslim Akeel  
**Department:** Web Development / Engineering  
**Submission Date:** September 20, 2026  

---

## 1. Weekly Summary
During Week 5, I focused on frontend web development fundamentals, responsive interface creation, and third-party API integration. To apply these concepts practically, I designed and built a fully functional web portal for the **AUIB 3D Printing Club** (`index.html`), featuring a streamlined print request form and a modern dark theme layout.

---

## 2. Tasks & Activities Completed
* **Environment & Structure Setup:** Designed a clean single-page layout utilizing semantic HTML5 tags (`<header>`, `<nav>`, `<section>`, `<form>`, and `<footer>`).
* **UI Styling & Responsiveness:** Built a custom dark mode color scheme using CSS variables (`:root`), grid systems (`grid-3`), and responsive media queries to ensure compatibility across desktop and mobile devices.
* **Form & API Integration:** Added interactive form elements (inputs, select dropdowns, textareas) and connected the form to **Web3Forms API** via a POST request for seamless email dispatch without a complex backend server.
* **File Upload Optimization:** Replaced direct heavy binary file inputs with secure cloud-link fields (Google Drive, OneDrive, WeTransfer) to prevent payload limits and ensure reliable large-file sharing.

---

## 3. Skills Learned
* **Frontend Design Principles:** Structuring responsive layouts with modern CSS flexbox and grid properties.
* **Client-Server Communication:** Handling form submissions and routing form payloads via external API endpoints using HTML forms (`method="POST"`).
* **User Experience (UX) Enhancements:** Implementing sticky navigation, intuitive form placeholders, visual card grids, and clear validation notices.

---

## 4. Challenges & Solutions
* **Challenge:** Direct file uploading via standard form inputs caused size restriction errors and payload failures on external API endpoints.
* **Solution:** Switched the upload architecture to accept cloud-sharing URL links with clear instructions for users, successfully streamlining submissions while eliminating server-side storage overhead.

---

## 5. Next Steps
* Continue exploring advanced JavaScript DOM manipulation and asynchronous interactions for upcoming web applications.
