#week 08 report: overview of my week

Prepared by: Sarah Sahm
Internship: 1001
Date: 2026 October 11th

##summary

This week, I continued working on the P1 backend and focused on building the rest of the features needed for the teams version of the 1001.tv application.

Since registration, login, PostgreSQL, and the basic authentication setup had already been started, I moved on to the features that depend on knowing which user is currently logged in.

I worked on JWT token validation and created authentication middleware that checks the token sent by the frontend before allowing access to protected routes. I also added an endpoint that allows the frontend to check whether a saved token is still valid. This is important for keeping a user logged in when they close and reopen the application.

After that, I worked on the profile section. I created endpoints that allow the logged-in user to view their own profile and update information such as their name, age, and favorite genres. I also made sure that private information such as the password hash and security answer hash is never returned to the frontend.

I also continued working on the forgot-password flow. At first, users were able to create their own security question, but after discussing the application with the team, we decided to use a fixed list of security questions instead. The user now chooses one of the available questions during registration and provides an answer. The security answer is hashed before being stored in the database.

For the password reset process, the backend retrieves the user's security question, checks their answer using bcrypt, and allows the password to be changed if the answer is correct. I also added password length validation.

Another major feature I worked on was favorites. Favorite genres in a user's profile and actual saved movies or TV shows are two different things, so I created a separate favorites table in the database. Each favorite is connected to the user who saved it.

I created endpoints that allow users to add a movie or TV show to their favorites, view their saved content, and remove something from their favorites. I tested this using multiple accounts to make sure each user only receives their own favorites.

I then started working with the TMDB API so the application could use real movie and TV information. I created backend endpoints for trending content, searching for movies and TV shows, and retrieving details about a specific movie or series.

I also connected TMDB to the favorites system. Instead of the favorites endpoint only returning an ID from our database, it can now use the TMDB ID to return information such as the title, poster, overview, release date, and rating.

Because the frontend is being developed separately, we also needed a way for the other team members to access my backend while it was running on my laptop. I set up ZeroTier and worked through some network and Windows firewall issues. After fixing the settings, Bashar was able to successfully connect to the backend from another device and test it.

I also communicated with Arwa about the backend endpoints, required request information, authentication token, and the data the frontend should expect from the backend.

Toward the end, I tested the main flows using different users, checked the database migrations, cleaned up old code that was no longer needed, updated the README, checked the environment variable setup, and made sure private values such as database passwords, JWT secrets, and the TMDB access token were not included in the repository.

After finishing the main P1 work, I committed the project, pushed the branch to GitHub, and opened the pull request for review.

##completed tasks

-Created JWT authentication middleware for protected endpoints.
-Created a token validation endpoint.
-Added the backend functionality needed for users to remain logged in using a saved valid token.
-Created an endpoint for viewing the logged-in user's profile.
-Created an endpoint for updating profile information.
-Made sure private password and security answer hashes are not returned to the frontend.
-Continued developing the forgot-password flow.
-Changed the security questions from user-created questions to a fixed list.
-Created an endpoint that returns the available security questions.
-Used bcrypt to verify security answers.
-Added password reset functionality.
-Added minimum password length validation.
-Created a separate Favorite model and favorites table.
-Connected favorites to users using the user ID.
-Created an endpoint for adding favorites.
-Created an endpoint for viewing favorites.
-Created an endpoint for removing favorites.
-Made favorites user-specific.
-Tested the favorites system using different user accounts.
-Confirmed that users only receive their own profile information and favorites.
-Connected the backend to the TMDB API.
-Created an endpoint for trending movies and TV shows.
-Created an endpoint for searching TMDB.
-Created endpoints for retrieving movie and TV details.
-Connected TMDB information to the favorites response.
-Tested the TMDB endpoints.
-Set up ZeroTier so other team members could access the backend.
-Fixed Windows firewall and network settings that were blocking the connection.
-Confirmed that Bashar could connect to and test the backend successfully.
-Communicated the main backend endpoints and requirements to Arwa for frontend integration.
-Tested registration, login, token validation, profiles, password reset, favorites, and TMDB functionality.
-Tested the backend using multiple users.
-Checked and fixed the database migration setup.
-Created a simple API endpoint reference for Arwa that listed the endpoint names, paths, required information, and authentication requirements so it would be easier for her to connect the frontend to the backend.
-Cleaned up old P0 code that was no longer being used.
-Updated the README with the backend setup and available functionality.
-Checked that private environment variables were not included in Git.
-Committed and pushed the P1 backend.
-Opened the P1 pull request for review.

##skills learned

-Learned more about JWT authentication and protected routes.
-Learned how authentication middleware can identify the currently logged-in user.
-Learned how token validation can be used to keep a user logged in.
-Learned how to create user-specific API functionality.
-Learned more about handling user profile information safely.
-Learned how password recovery can work using security questions and hashed answers.
-Learned how bcrypt can be used for information other than passwords.
-Learned more about database relationships and connecting saved data to a user.
-Learned how to create and use another Sequelize model and migration.
-Learned how a backend can communicate with an external API.
-Learned how to work with TMDB from the backend.
-Learned why API access tokens should stay on the backend instead of being exposed to the frontend.
-Learned how to combine information stored in PostgreSQL with information returned from an external API.
-Practiced testing the same backend functionality using multiple user accounts.
-Learned more about networking and allowing another device to access a locally running backend.
-Practiced communicating API requirements with the frontend developer.
-Gained more experience preparing a larger project for GitHub submission and review.

##challenges and solutions

One of the main challenges this week was that many of the features depended on each other.

The profile and favorites systems needed to know which user was logged in, so the authentication middleware and token validation had to work properly first. I worked through the features in order instead of trying to build everything at once.

The forgot-password flow also changed while we were developing the application. Originally, the security question could be entered by the user, but we decided to change this to a fixed list so the frontend and backend could follow the same structure. I updated the registration and password reset logic and tested the new flow again.

Another challenge was understanding how to connect our own database information with information from TMDB. The favorites table only needs to store information such as the user ID, TMDB ID, and media type, while the rest of the movie or TV information can be requested from TMDB when it is needed. This helped me understand why we do not always need to duplicate external data inside our own database.

Networking was also a challenge. The backend worked correctly on my own laptop, but at first the other team members could not reach it. I checked that the server was listening on the correct port, tested the ZeroTier connection, added a Windows firewall rule, and adjusted the network settings. After that, Bashar was able to connect successfully.

Testing with more than one account was also important because I needed to make sure that information belonging to one user was not being returned to another user. I created and tested separate users and confirmed that profiles and favorites remained separate.

##Feedback Received

This week, communication with the team helped me understand more clearly what information the frontend needed from the backend and how the different features should work together.

Testing the backend from another device was also useful because it showed me that something working locally does not always mean that it is ready for another part of the application to use.

I continued focusing on understanding the reason behind each feature, especially authentication, database relationships, and TMDB integration, instead of only focusing on getting the endpoints to return a successful response.

Preparing the pull request also gave me the opportunity to review the project again, clean up unnecessary files and code, and make sure the documentation and environment setup were understandable.

##Overall Progress

This week, most of the main backend functionality for our version of the 1001.tv application came together.

The backend now supports PostgreSQL-based user accounts, registration and login, JWT authentication, token validation, protected user profiles, password recovery using security questions, user-specific favorites, and TMDB integration for trending content, search, and movie or TV details.

I also connected the favorites system with TMDB, tested the backend with multiple users, and made the server accessible to other team members for testing.

By the end of the week, I had organized and documented the project, pushed the P1 backend to GitHub, and opened the pull request for review.

Compared to the beginning of P1, I now understand much more clearly how the database, authentication, protected routes, user-specific data, external APIs, networking, and frontend-backend communication all work together inside one application.