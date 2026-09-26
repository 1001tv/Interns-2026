# Backend Development Internship Project

## Overview

I built this project during my software development internship to get some real 
hands-on experience with backend development. The main focus was to learn how 
to work with server-side applications, create APIs, 
handle HTTP requests, process data, and set up basic authentication.

At its core, the project is a simple sign-up and login system. The frontend 
collects user info and sends it over to a Node.js + Express backend using API
endpoints. The backend then takes care of processing these requests and saves 
the user data in a JSON file.

## Features

* User sign-up
* User login
* REST-style API endpoints
* HTTP POST requests
* Handling JSON requests and responses
* Basic input validation
* User data stored in a JSON file
* Simple frontend interface
* Communication between backend and frontend
* Returns errors for invalid requests
* Local server for development

## Project Structure

backend/
├── public/
│   └── index.html
├── server.js
├── users.json
├── package.json
├── package-lock.json
└── README.md

### File Descriptions

**server.js**  
Holds the logic for the Express server, API endpoints, request validation, and user data processing.

**public/index.html**  
The actual sign-up and login screens.

**users.json**  
Where all the test user accounts get saved.

**package.json**  
Lists dependencies and project setup.

## API Endpoints

### POST `/signup`

Creates a new user account.

**Request:**
{
  "email": "test@example.com",
  "password": "123456"
}

**Success Response:**
{
  "message": "Account created!"
}

**Example Error:**
{
  "message": "Email and password are required"
}

### POST `/login`

Checks if the provided email and password match any stored user.

**Request:**
{
  "email": "test@example.com",
  "password": "123456"
}

**Success:**
{
  "message": "Welcome back!"
}

**If Not Found:**
{
  "message": "Account not found"
}

## How It Works

The frontend gathers the user’s email and password, then sends it to the backend 
using the fetch() function in JavaScript.

The backend gets the request at the matching API endpoint.

Here’s what the flow looks like:

User  
↓  
HTML Form  
↓  
JavaScript fetch()  
↓  
POST /signup  
↓  
Express Server  
↓  
Request Validation  
↓  
users.json  
↓  
JSON Response  
↓  
Frontend

The backend uses express.json() to work with JSON request bodies and serves the
public directory through Express.

## Technologies Used

* Node.js — Runs the backend  
* Express.js — Web framework for the backend  
* JavaScript — Both frontend and backend logic  
* HTML — Frontend layout  
* JSON — For storing and passing data  
* Git/GitHub — Version control

## Installation

First, clone the repo and switch to the project directory:

git clone <repository-url>  
cd backend

Install dependencies:

npm install

## Running the Project

To start the server:

npm start

By default, the server runs at:  
http://localhost:3000

Open that link in a browser to try out the app.

## Testing

You can test the API through the frontend or with something like Postman.

Key endpoints to try:

POST /signup  
POST /login

Testing checks that the server receives requests, works with the JSON file,
and returns the right responses.

## Learning Outcomes

Working on this project, I learned how to:

* Set up backend servers
* Build servers with Node.js and Express
* Handle communication between client and server
* Use HTTP POST requests
* Make and use API endpoints
* Work with request and response objects
* Use req.body to read request data
* Process JSON
* Add simple validation
* Read and write files on the server
* Connect frontend and backend with APIs
* Test server behavior
* Document endpoints
* Use npm for dependency management
* Use Git and GitHub for version control

## Project Summary

This project covers a simple full-stack flow where the frontend talks to the 
backend through APIs. The backend accepts user info, processes it, stores it, 
then sends a response back.

Even though it’s simple by design, this is the foundation of most web apps: 
frontend forms, backend logic, APIs, data storage, and HTTP, all working together.

## Future Improvements

For a production-ready app, I’d want to:

* Use a real database (like MySQL or MongoDB)
* Hash passwords instead of saving them as plain text
* Add stronger authentication
* Validate emails properly
* Block duplicate sign-ups
* Use authentication tokens or sessions
* Make error handling better
* Add more endpoints and features
* Deploy to a cloud server

## Note

This project was built just for learning and as part of my internship. 
The way it handles storage and authentication is basic—using a JSON file 
just to keep things simple. It’s not meant for live, production use.
