#week 07 report: overview of my week

Prepared by: Sarah Sahm
Internship: 1001
Date: 2026 October 4th

##summary

This week, I started by reading through the P1 backend overview and going over the concepts that I needed to understand before starting the actual implementation.

A big part of the task involved databases, PostgreSQL, Sequelize, models, migrations, environment variables, authentication, password hashing, and how data should be stored permanently instead of temporarily inside the code.

Since I have already taken a database course at university, some of the database concepts were familiar to me. I spent some time revising what I already knew about tables, records, keys, constraints, and relational databases, and then focused more on understanding how those concepts are actually used inside a backend application.

I also tried implementing and testing some small parts while learning so I could make sure that I actually understood what I was reading instead of only going through the theory.

After going through the P1 overview, I started working on the actual task. During one of the team meetings, however, I realized that what we were building was a little different from the written GitHub issue.

The main project was to recreate parts of the 1001.tv application. Because the application included more features than what was written directly in the original backend issue, we had to discuss what the final app should include and decide what each side needed from the other.

We tried to communicate as much as possible so that the backend and frontend would be working toward the same result and so we would not end up implementing things differently.

I had unfortunately missed a few meetings because of personal issues, so I had to spend some extra time catching up on decisions that had already been discussed. I used the notes I had, spoke with the team, and followed the requirements they agreed on so I could continue working without causing conflicts with the rest of the project.

After the requirements became clearer, I started setting up the P1 backend. I worked on connecting PostgreSQL and Sequelize, creating the user database structure, and starting the authentication system.

I also worked on registration and login. Registration allowed new users to be stored in the database instead of in memory, and I used bcrypt so passwords would be stored as hashes instead of plain text. For login, I checked the entered credentials against the stored user information and started using JWT so the backend could identify logged-in users.

We also had a tech talk this week with Aya Salam. She talked about different career paths in tech and how important it is to understand the basics and theory behind what we are doing instead of relying blindly on AI. One point that stood out to me was when she talked about how "full stack" can be misleading because most developers still have one area that they are stronger in, so it is important to discover which path and skills you actually want to focus on.

Another part I found useful was her advice about preparing for work before graduation. She talked about interviews, networking, LinkedIn, and how going to interviews can also be a learning experience. This made me realize that I had not really been paying attention to my LinkedIn profile, so I started working on it and learning how to use LinkedIn more professionally. And hopefully in the future I will be posting more on there.

She also discussed imposter syndrome and how it can be turned into something more positive by focusing on your own progress and finding the path that fits you instead of trying to learn everything at once. She also emphasized soft skills such as communication, teamwork, time management, ethics, handling pressure, and being proactive.

##completed tasks

-Read through the P1 backend overview and requirements.
-Reviewed the concepts I needed before starting implementation.
-Revised relational database concepts from my previous database course.
-Reviewed how database tables, fields, keys, and constraints are used in a backend project.
-Learned more about PostgreSQL and how it connects to an Express application.
-Learned how Sequelize is used to work with PostgreSQL.
-Learned the purpose of Sequelize models and migrations.
-Reviewed why environment variables are used for private configuration.
-Learned more about password hashing and authentication.
-Started learning how JWT is used for logged-in users.
-Took notes during the meeting about how the actual application requirements differed from the written P1 issue.
-Coordinated with the team about what features the frontend and backend would need.
-Started setting up the P1 backend.
-Connected the project to PostgreSQL using Sequelize.
-Created the user database structure.
-Started implementing the authentication system.
-Created the registration flow.
-Used bcrypt to hash passwords before storing them.
-Created the login flow.
-Started using JWT for authentication.
-Attended a tech talk with Aya Salam about career paths, AI, software engineering, interviews, networking, and soft skills.
-Started improving my LinkedIn profile and learning how to use it more professionally.

##skills learned

-Revised relational database concepts that I had previously studied at university.
-Learned how database concepts are applied in a real backend project instead of only in coursework.
-Learned how PostgreSQL works with Node.js and Express.
-Learned how Sequelize can be used to communicate with PostgreSQL.
-Learned the difference between a model, migration, and database table.
-Learned why environment variables are important for database credentials and other private information.
-Learned how bcrypt is used to protect passwords.
-Learned the basic flow of registration and login.
-Learned how JWT can be used to identify an authenticated user.
-Practiced planning backend endpoints based on what the frontend needs.
-Improved communication with the team while deciding how the different parts of the application should connect.
-Learned more about different career paths in technology and the importance of choosing an area to focus on.
-Learned why AI should be used as a tool while still understanding the code and concepts behind the work.
-Learned more about the importance of networking, interviews, and preparing for job opportunities before graduation.
-Learned about the importance of soft skills such as communication, teamwork, time management and being proactive.
-Started improving my LinkedIn profile and learning how to use it for professional networking.

##challenges and solutions

One of the main challenges this week was understanding the difference between the written P1 issue and what the team actually wanted to build for the final application.

At first, I was mainly following the GitHub issue, but during the meetings I realized that the project had developed into recreating more of the 1001.tv application and that there were additional features the team wanted to include.

To solve this, I took notes about the changes and communicated with the team about what they needed from the backend. I also asked for a clearer list of the additional features so that I could build the backend around the same plan that the frontend was following.

Another challenge was catching up after missing some meetings because of personal issues. Since some decisions had already been made while I was not there, I had to review the information again and ask questions when I was unsure about something.

The technical side also introduced several concepts together, especially PostgreSQL, Sequelize, migrations, password hashing, and authentication. Some of the database concepts were familiar because of my university course, which made that part easier, but applying them to a real backend project was still different.

I worked through the concepts one at a time and tested small parts as I learned them before moving on to the full implementation.

##Feedback Received

The meetings helped clarify how the frontend and backend were expected to work together and what additional features were needed for the final application.

I also continued trying to understand the concepts behind the code instead of only copying an implementation, especially when working with the database and authentication.

The tech talk also gave me useful advice about focusing on understanding what I am doing, choosing a path to develop further and starting career preparation early through networking, interviews, and LinkedIn.

##Overall Progress

This week was mainly focused on understanding the P1 task, reviewing the technical concepts needed for it, and making sure that my backend work matched what the team was actually building.

I revised the database concepts I already knew, learned how they are applied using PostgreSQL and Sequelize, and started implementing the main backend foundation.

By the end of the week, I had started the database setup and authentication flow, including registration, login, password hashing, and JWT.

I also had a much clearer understanding of what the team wanted from the final application and what I would need to continue implementing in the following week.

The tech talk also helped me think more seriously about my future path in technology, the skills I need to continue developing, and the importance of preparing for work and networking before graduation. 