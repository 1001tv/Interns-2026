# Internship Final Report

**Name:** Muslim Akeel 

---

## Introduction
This final report summarizes my experience, technical achievements, and personal growth during my backend software engineering internship. As a Computer Science senior at the American University of Iraq–Baghdad (AUIB), this program served as a pivotal bridge between my academic computer science foundation and real-world industrial software engineering. Over the course of the internship, I progressed from establishing core Express server architectures to designing RESTful endpoints, implementing defensive payload validation pipelines, refactoring legacy backend codebases, and maintaining disciplined Git workflows.

---

## Internship Objectives
* **Practical REST API Architecture:** Learn to design, structure, and document scalable RESTful API routes using Node.js and Express.js.
* **Defensive Data Validation:** Implement robust server-side input checking and HTTP status handling to ensure backend reliability.
* **Clean Code & Software Principles:** Refactor monolithic route functions into clean, modular helper modules adhering to the Single Responsibility Principle (SRP).
* **Professional Version Control:** Master real-world Git and GitHub workflows, including feature branching, atomic commits, Pull Requests (PRs), and peer code reviews.

---

## Summary of Completed Tasks

### Phase 1: Environment Configuration & Server Foundations
* Set up local backend runtime environments with Node.js and Express.js.
* Configured primary entry points (`server.js`) and routed standard HTTP request handlers.
* Maintained clean task directory structures and repository guidelines.

### Phase 2: RESTful API Engineering & Input Validation (Week 6)
* Engineered functional backend endpoints (such as `/api/print-requests`) to process incoming client payloads.
* Built server-side validation logic for required payload attributes (`studentEmail`, `material`, `quantity`).
* Handled HTTP response codes properly (returning `400 Bad Request` for invalid input and `200 OK` / `201 Created` for valid transactions).

### Phase 3: Code Refactoring, Clean Architecture & Peer Review (Week 7)
* **Monolithic Code Analysis:** Identified maintainability bottlenecks in legacy route handlers where request handling, validation, and execution were mixed together.
* **Single Responsibility Separation:** Extracted inline validation checks out of Express handlers into isolated, reusable helper functions (`validatePrintRequest()`).
* **Casing & Naming Uniformity:** Standardized variable declarations and JSON payload keys from mixed `snake_case` (e.g., `stock_grams`, `student_email`) to unified `camelCase`.
* **Peer Code Review:** Authored a comprehensive code review document (`code-review.md`) providing side-by-side "Before & After" comparisons to demonstrate structural improvements.

---

## Skills and Technologies Learned

* **Backend Frameworks & Runtimes:** Node.js, Express.js, JavaScript (ES6+), and RESTful API design.
* **Software Architecture & Clean Code:** Single Responsibility Principle (SRP), helper function abstractions, defensive programming, and uniform `camelCase` naming conventions.
* **Development Workflows:** Git feature branching (`interns/Muslim-Akeel/...`), GitHub Pull Request workflows, issue tracking, and Markdown technical documentation.

---

## Main Challenges and Solutions

* **Challenge 1: Bloated Monolithic Routes**
  * *Issue:* Early API handlers contained nested conditional statements, making the codebase hard to read and test.
  * *Solution:* Refactored validation rules into dedicated helper functions that return explicit error strings or `null`, keeping main Express routes clean and concise.

* **Challenge 2: Inconsistent Variable Naming**
  * *Issue:* Legacy scripts contained mixed naming styles, causing confusion across API payloads.
  * *Solution:* Standardized all variables, object keys, and JSON parameters across the codebase to follow uniform `camelCase`.

* **Challenge 3: Git Branch and Path Precision**
  * *Issue:* Ensuring files were pushed to designated repository directories (`tasks/task-07/`, `weekly-reports/`, `final-report/`) without folder duplication.
  * *Solution:* Mastered exact Git branch management and GitHub interface path navigation.

---

## Important Achievements
* Successfully refactored backend server files into clean, modular code scripts (`server-refactored.js`).
* Produced a detailed peer code review document (`code-review.md`) illustrating clean code refactoring principles.
* Maintained a 100% submission rate across all weekly reports and Pull Requests throughout the program.

---

## Lessons Learned & Mindset Transformation
* **Shift to Quality Engineering:** Moving beyond "code that just works" to writing clean, self-documenting code built for long-term maintainability.
* **Power of Modular Abstraction:** Offloading repetitive logic into modular helper functions simplifies debugging and reduces code duplication.
* **Professional Accountability:** Communicating progress clearly through weekly logs and Pull Request descriptions mirrors real-world software engineering practices.

---

## Future Improvement Areas & Career Outlook
* **Automated Testing Pipelines:** Integrate testing frameworks (such as Jest or Mocha) to programmatically test API endpoints.
* **Database Integration:** Connect backend endpoints to persistent database systems (such as PostgreSQL or MongoDB) using ORMs like Prisma or Mongoose.
* **CI/CD & Code Quality:** Implement ESLint and Prettier into continuous integration pipelines to enforce style rules automatically.

---

## Conclusion & What I Plan to Do in the Future

### Personal Reflection
This internship has been a transformative experience in my development as a software engineer. Working on backend systems, refactoring legacy scripts, and managing GitHub PR workflows gave me the practical confidence to tackle production-level software challenges.

### Future Goals
1. **Academic Completion:** Apply the software engineering practices learned here to my senior computer science coursework and capstone deliverables at AUIB.
2. **Career Pursuit in Backend Engineering:** Leverage these practical skills in Node.js, RESTful API design, and clean architecture to secure a full-time Backend Software Engineer role in the tech sector.
3. **Continuous Learning:** Expand my technical stack into microservices architecture, containerization (Docker), and cloud deployments (AWS/GCP) to build scalable, high-performance web systems.
