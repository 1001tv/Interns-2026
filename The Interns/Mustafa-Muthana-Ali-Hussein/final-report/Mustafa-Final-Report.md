1. Introduction

Spending seven weeks interning at 1001 TV opened up a new world for me. I was able to dive into software development, app design, web tech, version control, and what it really means to work 
in a actual professional setting. Each week I was able to get hands on experiace with real assignments and not just theoretical, just practical stuff that forced me to figure out how software gets 
planned, built, tested, and kept up.

It all started off with a basic tour getting to know the company, the team, and their app and website. After that, I went step by step learning Git and GitHub, building mobile apps with
MIT App Inventor and Thunkable, picking up the basics of frontend work with HTML, CSS, and JavaScript, and then moving into backend with Node.js and Express.js.

Towards the end, I spent time on refactoring and code quality and making old code better without breaking anything.

This report is my own recap of the whole internship, what I set out to learn, what I worked on, the tools I picked up, the roadblocks I hit, what I’m proud of, and the things I still want to 
get better at.

2. Internship Objectives

Here’s what I wanted to get out of my time at 1001 TV:

- Understand how the company works day to day, how the people, teams, and projects fit together.
- Learn version control, mainly using Git and GitHub, and figure out how developers team up on the same code.
- Build some real experience making mobile apps with low code platforms.
- Get the basics of frontend web development.
- See what JavaScript can actually do when it comes to user input, handling app logic, and saving data.
- Figure out backend basics and how servers and frontends talk.
- Build a simple API with Node.js and Express.js.
- Practice testing, debugging, data validation, and how to handle errors.
- Make my code easier to read, organize it better, and keep it maintainable by refactoring.
- Level up my independent problem solving, research, and communication skills.

These were my goals. The plan was to start simple and slowly take on more complex hands on programming work.

3. Summary of Completed Tasks

Week 1: Introduction to 1001 TV

I started off by joining the company and getting my work setup ready. Created my company email, joined standup meetings with Mrs. Amna and the other employees to get a sense of who does what and 
the company vibe. I checked out both the 1001 TV app and their website, messed around their features, and reported anything weird I noticed. I also set up my GitHub account since I’d need it for 
version control.

Tasks done:
- Got my company email.
- Joined team and intro meetings.
- Explored and tested the 1001 TV app & website.
- Signed up for GitHub.

Week 2: Version Control and GitHub

Second week meant getting comfortable with Git and GitHub. I installed some Git GUIs, but also practiced terminal basics like cd and ls. Most of my time went into cloning repos, changing files, 
pushing updates, pulling changes, and submitting stuff for review. I learned firsthand how developers sync their work and avoid getting in each other’s way.

Tasks done:
- Used key terminal commands.
- Set up and played around with Git tools.
- Cloned projects, edited files.
- Practiced pushing and pulling code.
- Tried out the pull request and review process.

Week 3: Mobile App Development with MIT App Inventor

This week, I dove into MIT App Inventor and built a simple Android app from scratch. The tool’s drag and drop setup took a bit of adjustment but I learned how to set up screens, add components,
keep track of variables, and wire up the app’s logic. I made a practice app to get the hang of it, then picked a user interface design from Dribbble as inspiration, built my main project off that, 
and documented the whole thing with screenshots before uploading it to GitHub.

Tasks done:
- Wrapped my head around MIT App Inventor basics.
- Built a prototype app.
- Developed the primary app for my assignment.
- Used a real world design as a reference.
- Created docs and screenshots.
- Uploaded everything to GitHub.

Week 4: Note App with Thunkable

Thunkable was next. I built Zen Note, a basic note taking app. Focused on making a simple, clean interface, connecting UI elements to the main logic with programming blocks, and debugging issues 
as I went. Testing every feature was key I kept fixing small bugs by tweaking connections and logic in the blocks editor.

Tasks done:
- Learned how Thunkable’s block workflow works.
- Made the Zen Note app.
- Structured the app’s screens and layout.
- Linked UI controls to functions.
- Tested features and ironed out issues.

Week 5: Frontend Web Development

In week five, I built a barebones signup/sign-in app with HTML, CSS, and JavaScript. HTML handled the structure, CSS made it look half decent , and JavaScript glued it all together,
handling events, logic, and working with local storage via JSON. Learned to dynamically update the page and store user data temporarily in the browser.

Tasks done:
- Marked up forms and pages with HTML.
- Designed and styled everything with CSS.
- Wrote JavaScript for user flow and validation.
- Used JSON to save and fetch user data.
- Tested to make sure data stuck around on reload.

Week 6: Backend Development with Node.js & Express.js

I spent this week diving into backend with Node.js, npm, and Express.js. I made simple API endpoints for signup and login, letting the front end talk to the server using fetch(). Stored user 
info in a JSON file, and learned about handling requests, responses, and validating data. I documented the API and tried different login attempts to check for problems.

Tasks done:
- Got Node.js, Express.js up and running.
- Built a simple backend server.
- Connected frontend logic via API calls.
- Managed “database” with a JSON file.
- Added basic checks and validation.
- Tested both success & failure cases.
- Wrote API docs and organized it all on GitHub.

Week 7: Code Quality and Refactoring

Final week: refactoring and cleanup. Looked back at my signup/sign in app, spotted things like unclear variable names, messy function structure, and better ways to deal with empty user
data. Standardized formatting, improved comments, and checked the logic for edge cases testing everything to guarantee nothing broke in the process.

Tasks done:
- Reviewed and cleaned up codebase.
- Fixed variable and function naming.
- Made functions more focused and clear.
- Handled situations like no accounts present.
- Improved code formatting and added comments.
- Ran tests for account creation, login, multiple users, data persistence.

4. Skills and Technologies Learned

Here’s a quick list of what I picked up:

- Version control: Git, GitHub, cloning, pushing, pulling
- Terminal: commands like cd and ls
- Mobile dev: MIT App Inventor, Thunkable, visual coding
- UI/UX: basic design, custom layouts, design inspiration from Dribbble
- Frontend: HTML, CSS, JavaScript, DOM, events, Flexbox
- Data: JSON, browser local storage, reading & writing files
- Backend: Node.js, npm, Express.js, APIs, fetch(), endpoints
- Code quality: refactoring, clean structure, modularity
- Testing: hands-on troubleshooting, validation, error handling
- Docs: screenshots, references, documentation for apps/APIs

Beyond coding, I got better at teaching myself new tech breaking big tasks into smaller parts, and working through problems on my own or by asking the right questions.

5. Main Challenges and Solutions

Learning unfamiliar tools

Early on, I was lost with GitHub, command-line basics, and visual app platforms.
What I did was just practice. Broke down the official guides into steps and Googled anything I didn’t get right away.

Converting ideas to working apps

Getting my app ideas to function in MIT App Inventor and Thunkable tripped me up.
My fix was to try out different components, experimented a lot, researched how others solved similar problems, and made small features work one at a time.

Frontend data handling

DOM tricks, event handlers, JSON, and local storage, none of that was obvious at first.
I learned each piece on its own, then put it together in the signup/sign-in app to see it in action.

Connecting frontend and backend

Getting APIs right, making fetch() work with Express, handling server responses, and using middleware was tough.
I Set up a tiny Express app, connected it, and tried different requests until things clicked.

Improving code reliability

While refactoring, I had to tweak organization without breaking existing features.
I Read through the code closely, picked better names, reorganized logic, fixed variables, and always tested after changes.

Working through these challenges gave me more confidence in debugging and working methodically.

6. Important Achievements

Here’s what stands out from the internship:

- Learned how 1001 TV operates.
- Used Git and GitHub to submit real code.
- Built mobile apps with MIT App Inventor and Thunkable.
- Made Zen Note, my own note taking app.
- Built a basic web sign up/sign in flow from scratch.
- Set up a backend login/register system using Node.js & Express.js.
- Linked frontend with backend APIs.
- Practiced saving data with JSON and browser storage.
- Documented my apps and APIs.
- Refactored and cleaned up messy code.
- Tested my work in many different situations and fixed errors as they came up.

It was a journey from “how does this even work?” to “I can build and fix real stuff now.”

7. Lessons Learned

The biggest thing I learned? Just because code works doesn’t mean it’s good. You need to make apps understandable, reliable, and easy to maintain.

Version control is crucial for keeping track and working with others. Frontend and backend are two sides of the same coin UI talks to the server, which does the heavy lifting with data.

Testing matters, trying out weird user inputs or situations can uncover bugs you’d never notice otherwise.

Clean variable names, small focused functions, comments, and organized code all add up when projects get bigger. Refactoring showed me you don’t need to start from scratch just keep improving what
you have.

Most of all, learning independently, being patient, documenting your moves, and asking questions are what get you through tough spots. Working with new technology gets easier as you keep at it.

8. Future Improvement Areas

I’ve still got a lot I want to learn..

Database management

Switching from JSON files to real databases is next on my list. I want to learn about data modeling, queries, and handling bigger data loads.

Authentication and security

It’s important I learn about things like password hashing, secure authentication, session management, input validation, and general API security.

Advanced backend work

I want to build better APIs, get serious about error handling, and structure backend projects for easier scaling and maintenance.

Frontend development

I plan to keep pushing my JavaScript skills further, learn more about responsive and accessible web design, and tackle more advanced UI challenges.

Testing and debugging

I want to add automated and systematic testing to my toolkit and make sure my projects become more reliable.

Collaboration and version control

I’m aiming to master branching, merging, conflict resolution, and advanced Git workflows to be a better team player.

Code quality

I’ll keep practicing things like smart refactoring, modular design, clear naming, and documenting my work for others and myself.

9. Conclusion

All in all, my eight weeks at 1001 TV taught me alot. I got a much better sense of how software development really works and which tools and practices matter most.

I started by just learning about the company and version control, then moved through a series of hands on projects, mobile apps, web frontends, backend APIs, and serious code refactoring. 
I learned how the parts fit together, worked with new tech like GitHub, App Inventor, Thunkable, Node.js, and Express.js, and got a lot better at troubleshooting, testing, and self-directed learning.

There were plenty of moments where I was unsure, but working through those problems made everything stick. Now I understand the value of clean code, thorough testing, version control, and
always getting better.

This experience gave me a solid start for my career as a developer. I’m grateful to 1001 TV and Mrs. Amna for giving me this chance and guiding me along the way. I’m eager to keep building on what I’ve 
learned and take on tougher, bigger projects as I move forward.
