#week 06 report: overview of my week

Prepared by: Sarah Sahm
Internship: 1001
Date: 2026 September 27th

##summary

This week, I focused on finishing the P0 backend task and preparing it for submission. Since most of the main Users API routes were already done from the previous week, I mainly worked on finishing the remaining requirements, testing everything properly, adding documentation, and organizing the project inside the internship repository.

I finished the 500 internal server error handling and tested it by temporarily creating a route that would cause an error on purpose. This helped me make sure that the client only receives a simple Internal Server Error response while the full error still appears in the terminal for debugging.

I also tested the main API endpoints again, including GET /health, GET /users, GET /users/:id, and POST /users. I tested successful requests as well as invalid requests such as a missing user name and a user ID that does not exist.

After testing, I created a README file explaining the project, how to run it, the available endpoints, the expected responses, and the status codes. I also added a .gitignore file so unnecessary files such as node_modules would not be uploaded to the repository.

I then updated the internship repository, created a new branch for the backend P0 task, moved the project into the correct folder, installed the dependencies again, tested that the project still worked from inside the repository, and submitted the completed task.

After finishing P0, I also started working on the next backend task, P1, which introduces PostgreSQL and Sequelize. I installed PostgreSQL and started reviewing the new requirements for replacing the in-memory users with a real database. I also started learning the difference between a database table, a Sequelize model, and a migration, as well as why environment variables are used for database configuration and why passwords should never be stored as plain text.

##completed tasks

-Finished the 500 internal server error handling.
-Tested the 500 error response and made sure the stack trace is not sent to the client.
-Tested GET /health, GET /users, GET /users/:id with existing users, GET /users/:id with a user that does not exist and confirmed the 404 response, POST /users and successfully created a new user, POST /users without a name and confirmed the 400 response.
-Confirmed the use of status codes 200, 201, 400, 404, and 500.
-Created a README file for the project.
-Documented the endpoints and expected responses.
-Added a .gitignore file to ignore node_modules, dist, and .env.
-Updated the internship repository with the latest version of main.
-Created a new branch for the P0 backend task.

-Tested the server again after moving the project.
-Committed and submitted the completed P0 backend task.
-Started reviewing the P1 backend task.
-Installed PostgreSQL.
-Started learning how PostgreSQL and Sequelize will replace the in-memory users from P0.
-Reviewed the required user fields for the new database task, including id, name, email, passwordHash, createdAt, and updatedAt.
-Started learning about models, migrations, database tables, unique email constraints, and environment variables.

##skills learned

-Learned more about how Express error handling middleware works.
-Practiced testing different HTTP responses including 200, 201, 400, 404, and 500.
-Learned more about how POST requests send data through the request body.
-Practiced using Invoke-RestMethod in PowerShell to test POST requests.
-Started learning relational database basics and reviewing what I studied in database class.
-Started learning the difference between a database table, a Sequelize model, and a migration.
-Learned why environment variables are used for database connection information.
-Started learning why passwords should never be stored as plain text.
-Started learning why email should have a unique constraint in the users table.

##challenges and solutions

One of the main challenges this week was testing the POST request using curl in PowerShell. The JSON request body kept causing syntax errors because of the way PowerShell was reading the quotation marks. Instead of continuing to use curl, I used Invoke-RestMethod, which worked correctly and made it easier to test the request body.

I also had some problems with VS Code giving me errors or telling me something was missing, and with the way the repository folders were showing on the side. At one point, my laptop kept crashing and I couldn't work on it at all. I fixed this by troubleshooting the problems one at a time, resetting and reopening VS Code, making sure I was opening the correct project and repository folders, and reinstalling the project dependencies when needed as well as a lot of other complicated stuff. After that, I was able to get everything working again and continue the task

Starting P1 also introduced a lot of concepts at once, especially PostgreSQL, Sequelize, models, migrations, and database configuration. I started by installing PostgreSQL and going through the requirements one part at a time so I can understand the basics before changing the API.

##Feedback Received

-This week, I mainly focused on making sure the P0 task matched the acceptance criteria and was ready to submit.
-I kept focusing on understanding each part of the code instead of only making it work. While finishing the error handling and testing, I made sure I understood why each status code was being used and how the request moved through the application.
-For the next task, I am also trying to keep the same approach by understanding the database concepts first before starting to write the PostgreSQL and Sequelize code.

##Overall Progress

I finished and submitted the P0 backend Users API task this week.

The main API functionality, validation, status codes, error handling, testing, and documentation are now complete. I also added the project to the internship repository on its own branch and prepared it for submission.

After completing P0, I started moving on to P1. I installed PostgreSQL and began learning the database concepts needed to replace the in-memory users with PostgreSQL and Sequelize.