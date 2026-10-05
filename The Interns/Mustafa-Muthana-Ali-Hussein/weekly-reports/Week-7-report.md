Week 7 Report — Code Quality and Refactoring

Introduction

This week, I dove into what it really means to write good code—code that's easy to read, maintain, test, and looks professional. Most of my work centered around
looking at existing code, figuring out where it could get better, and then refactoring it without messing up how it works.

For the hands-on part, I took a simple sign-up and sign-in system built with HTML and JavaScript. I focused on making the structure clearer, 
picking better names for things, cleaning up the formatting, and improving how reliable it is.

Main Activities

Using Better Names

The first big improvement was renaming variables and functions so you can actually tell what they're for. 
The original code used short names that didn’t say much—you'd have to read through the whole function just to figure it out. I switched them to
straightforward names tied to sign-ups, sign-ins, and account checking. Now, it's much easier to understand what happens where.

Making the Code Safer

I noticed some variables weren’t declared properly, and that can cause unwanted global variables. So, I cleaned this up with
the right declarations and made sure each variable had a clear scope.

I also added logic for when no users exist yet, so the app doesn’t throw errors if someone tries to sign in before anyone creates an account.

Clearer Functions

In the original, some functions tried to do too much, or their names didn't match what they actually did. I broke things up so
each function handles its own job—creating an account, signing in, or checking if an account exists. This makes it much simpler to figure 
out where things happen and it’s easier to keep up with changes.

Separation of Concerns

I looked at the overall organization, too. HTML handles the page structure and input fields. JavaScript takes care of logic and functionality.
CSS is all about the look and feel. Keeping these separate means making a change in one place doesn’t mess up everything else.

Following Coding Conventions

The last round of improvements was about formatting and consistency. I used the same indentation everywhere, spaced things out to make it readable, 
and kept names clear. Variables got properly declared, and I chose more descriptive HTML input types. I also stuck with consistent operators and dropped
in comments where it really mattered. All this helps make the project look professional and way easier for others to pick up.

Before-and-After

The first version of the code worked, but it was hard to read and not very tidy. Names were confusing, declarations were sloppy, and comments were almost nowhere.

After refactoring, the application still had the same features, but with a way better structure and readability.

Here’s what got better:
- Names actually make sense now
- Variables are managed right
- Functions have clear jobs
- Error handling is stronger
- Formatting is consistent
- Comments explain what’s going on
- The HTML’s cleaner
- Coding style is more unified

Code Review

While reviewing, I found a few things that needed work. First off, function and variable names didn’t tell you much. Also, 
undeclared variables could create surprising bugs. The login process wasn’t safe if there weren’t any users in storage yet,
so I made sure the code handles an empty list without crashing. Formatting was messy, too, so I cleaned up indentation and spacing.
Lastly, I added comments that spell out what’s happening where, which should help the next developer.

Testing

After tweaking everything, I tested different scenarios to make sure nothing broke.
- Created a new account
- Signed in with both correct and incorrect info
- Tried signing in before any accounts existed
- Made several accounts at once
- Refreshed the page to check if saved data stuck around

The main goal was just making sure the app still worked after all these changes.

What I Learned

This week made it clear that good coding isn’t just about making something work. Code needs to be organized, easy for others to understand, 
and just pleasant to maintain. I saw why meaningful names matter, why you want tight, focused functions, why it’s important to declare variables right,
and why you have to think about handling unexpected situations. Formatting matters, too, and testing your changes is essential.

I also figured out that refactoring doesn’t mean starting over. Even small tweaks to messy code can make a huge difference in how easy it is to read
and work with later.

Conclusion

Week 7 really put me in touch with what code quality and refactoring look like in practice. I got to look at a real piece of code, pick it apart,
and rebuild it without losing the original function.

The biggest thing I’ll take away is that professional-quality code isn’t just about getting results. It needs to make sense to other people, be easy to keep up,
and fit in with a team. These lessons will become even more important whenever I’m working on bigger projects or collaborating with others.
