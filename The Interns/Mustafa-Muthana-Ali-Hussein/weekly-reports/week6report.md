Weekly Internship Report

Backend Development

Activities Completed

This week, I jumped into the basics of backend development and server-side apps. 
I set up my whole environment—Node.js, npm, and Express.js—and saw firsthand how
they power a simple server.

I figured out how backend apps talk to frontend apps. The backend listens for 
requests, processes them, and then sends back a response. I got my head around 
HTTP methods, setting up API endpoints, working with JSON, and handling both the
request and response objects. Middleware? That was brand new for me, but I 
started to see how it fits in.

For hands-on practice, I built a basic sign-up and login system. There are two 
main API endpoints: /signup and /login. These let the frontend interact with 
the backend. I connected my HTML and JavaScript frontend to these endpoints 
using fetch()—that was cool to see working.

I learned how to use req.body so the backend could handle the data sent from 
the frontend (like email and password), process it, and send a JSON response 
back.

I used a JSON file to store user info, which meant I had to practice reading
the file, adding users, and writing the updated data out with Node.js. I threw 
in some basic validation and error handling too—things like checking if a user
left out a required field or entered the wrong login info.

Testing

I ran everything locally. From my browser and the frontend app, I tried out
creating accounts, logging in, entering the wrong password, and even leaving
fields blank to see how the backend handled it. I watched the API requests
and responses, which helped me picture how data actually moves between
frontend and backend.

Challenges

Honestly, the hardest part was connecting all the dots between frontend and 
backend. Terms like API endpoints, request bodies, middleware, and status 
codes were all new territory for me.

On top of that, really understanding how the server receives, processes,
and stores data took some time. Building the sign-up and login system forced
me to work through these gaps, and it finally started to click.

Outcome

By the end of the week, I’d built a backend app with Node.js and Express.js
that actually talks to the frontend via API requests. It can handle basic user
data and send proper responses back.

I also put together API docs with sample requests, responses, and listed
all the endpoints, so anyone can follow along. I organized my project on 
GitHub using Git.

Skills Learned

This week, I picked up practical skills in:
- Node.js
- Express.js
- npm
- JavaScript
- Backend development
- Building APIs
- Handling HTTP requests and responses
- Working with JSON
- Using req.body
- Express middleware
- Simple validation and error handling
- File-based data storage
- Frontend-backend communication
- Git and GitHub

Next Steps

Next, I want to keep going deeper with backend work: proper databases, 
authentication, password security, API security, and making more advanced
APIs. Moving away from a JSON file to a real database is at the top of my list.

Summary

This week gave me a real, practical starting point in backend development.
Building a sign-up and login system showed me how a frontend app connects
with a server, how APIs handle requests, and how data gets processed and 
stored on the backend. Now I’ve got a foundation to keep building on and 
tackle more advanced concepts going forward.
