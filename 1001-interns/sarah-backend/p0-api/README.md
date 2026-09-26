# [Backend][P0] Learn TypeScript, Express and HTTP fundamentals

Prepared by: Sarah Sahm

## user API

This is a simple in-memory no database Users API built with TypeScript, Node.js, and Express.

The goal of this project is to learn and practice working with:

-routes 
-requests
-responses
-status codes
-middleware
-error handling.

## to run the project

npm run dev

* the server runs on http://localhost:3000 

### Endpoints

## GET /health 

checks if the server is running.

the response should say the status is ok and the server is runing

## GET /users

returns a full list of all the users.

the response should be each users name and their id

## GET/ users/:id

returns one user using the id of your choosing.

the response should be similar to this example:

GET /users/1:

if the user exists: (id : 1, name: sarah) status code '200'
if the user does not exist: (message : user not found) satus code '404'

## POST /users

creates a new user by sending a name in the request body.

example:

name: amnah

the server will automatically give the new user an ID based on the current number of users, so each new user gets the next id in order. (users.length + 1) 

the response should be similar to:

(id: 4, name: amnah) status code '201' means created successfully.

if the name is missing:

(message: name is required) status code '400'

## Error Handling

if an unexpected error happens in the server, the response should be:

(error: Internal Server Error) status code '500'

the full error can be seen in the terminal for debugging, but the stack trace is not sent to the client.






