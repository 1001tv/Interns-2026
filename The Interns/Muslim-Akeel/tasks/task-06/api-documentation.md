# AUIB 3D Printing Club API Documentation

## Base URL
`http://localhost:3000/api`

---

## Endpoints

### 1. Get Inventory
* **URL:** `/inventory`
* **Method:** `GET`
* **Description:** Retrieves a list of available 3D printing filaments and stock levels.

### 2. Submit Print Request
* **URL:** `/print-requests`
* **Method:** `POST`
* **Description:** Submits a new print job to the club queue.
* **Request Body (JSON):**
```json
{
  "student_email": "m.akeel@auib.edu.iq",
  "material": "PLA",
  "quantity": 1,
  "file_link": "[https://drive.google.com/example-file](https://drive.google.com/example-file)"
}
