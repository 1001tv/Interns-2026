# week 05 report: overview of my week

Prepared by: Sarah Sahm
Internship: 1001
Date: 2026 September 20th 


## summary

This week, I continued working on the P0 backend task and focused more on actually building the Users API. Since I already spent the previous week learning the basics and setting everything up, I was able to start applying what I learned directly in the code.

I created an in-memory list of users and worked on the main routes required for the task. I created a route to get all users, another route to get a specific user by ID, and a POST route to create a new user.

While working on these routes, I started understanding request parameters, request bodies, middleware, and HTTP status codes much better because I was actually using them in the code instead of only reading about them.

I did not have as much time as I wanted this week because I had several exams and some urgent family matters, so my progress was slower than planned. Even with that, I still managed to continue the main API functionality and understand the project better.


## completed tasks

-Continued working on the P0 backend task.
-Created GET /users to return all users.
-created GET /users/:id to return one user with his/her id.
-Added a 404 response when the user does not exist.
-created a POST /users to create new user
-Added basic validation for the user name.
-added a 400 response when the required name is missing.
-used 201 to show that a new user was created successfully.
-used 200 for successful GET request.
-connected all the routes to the main express app 
-started working on the 500 internal server error handling.


## skills learned

-learned the Difference between in memory data and database as well as how to use them.
-Learned how GET and POST routes work in Express.
-learned how req.params works and how it can be used to get an ID from the URL.
-Learned why the ID from the URL comes as a string and why I needed to convert it into a number.
-learned how .find() can be used to search for a specific user inside an array.
-learned how req.body works and how it is used to read data sent by the client.
-learned that express.json() is middleware and that it allows Express to read JSON data from the request body.
-Practiced using status codes such as 200, 201, 400, 404, 500.


## challenges and solutions

-One of the main challenges this week was not having enough time to work on the task because I had several important exams and urgent family matters. I had to take long breaks between working on the project, which made it harder to keep track of where I stopped.

-Every time I came back to the code, I usually understood what each part meant, but I still needed some time to remember what I had already done, what I was supposed to do next, and how the different parts connected together.

-To deal with that, I kept going back to the requirements and my notes before continuing. I also tried to work on one part at a time instead of thinking about the whole task at once, which made it easier to get back into the flow.

-Also ran into problems with my laptop and VS code that i still am trying to figure out I did talk to doctor for help but the problem was bigger than we thought so till now I'm still trying to fix it and hopefully it will be done soon so I can finish working on my tasks


## Feedback Received

-This week, I mostly focused on continuing the backend task and making sure I actually understood what I was writing instead of rushing just to finish it.

-I also kept in mind the feedback from the previous tech talk about being reliable and doing the work properly. Even though my week was busy, I did not want to submit something that I could not explain or understand.

## Overall Progress

I finished most of the main Users API functionality this week. I still need to finish the 500 error handling, test all of the endpoints properly, complete the documentation, and prepare the task for submission which i will do after I finish resetting up my laptop and VS code.

