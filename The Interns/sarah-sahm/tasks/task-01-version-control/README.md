# Task 01: Version Control

Prepared by: Sarah Sahm
Internship: 1001
Date: 2026 August 30th 



## Summary

The objective of this task was to learn how to use Git and GitHub for daily development work and understand the process of working with repositories, branches, commits, pushes, and pull requests.

((preparing myself before starting))

I had used Git and GitHub before, but it had been a long time since I last worked with them. The last time I used them was for a course I took around two years ago, so I needed to refresh my memory before starting the task.

To do so I went through YouTube videos and Google searches, watched tutorials, and read through different Git commands to remind myself what they did and how the workflow worked.

Although this helped me remember some of the terminology and commands, I still found it hard to understand everything until I started using Git and GitHub again. Going through the process myself, with some guidance at first, helped the concepts come back much more clearly than simply watching tutorials.

Even then, I still found myself getting stuck and making mistakes, but working through those mistakes helped me understand the process better.



((What i needed to do/Task objective))


1-I needed to learn how to use Git and GitHub 
2-clone the repository
3-create my own branch
4-make a change: add all the folders and files that were required for my submission
6-commit it the change
5-push the branch to GitHub
6-create a pull request
7-submit my weekly report


How i did it:.

I first tried using GitHub directly, but I personally found that I did not like working that way, so I moved to using VS Code instead. I downloaded VS Code and cloned the internship repository to my computer so I could work on the files locally.

After cloning the repo, I followed the steps and requirements on GitHub, but before making any changes, I used "git pull origin main" to make sure my local copy of the repository was up to date with the latest version of main.

I created my own branch so i would not be making changes directly to the main. This was important because I could work on my own files without affecting the main repository.

After creating my branch, I added the folders and files that were required for my submission making sure to follow the name format which was pretty simple and started putting my work into the correct places.

then I used
"git status" to check what had changed and make sure i was working with the correct files.
"git add" to select the files I wanted to include in the commit.
"git commit -m" to save them with a commit message.
"git checkout -b" to create a new branch and switch to it
"git show" to check the contents of a file from an older branch without switching to it

after committing my changes I used "git push" to upload my branch to GitHub.
Once it was on GitHub I created a pull request from my branch to main so my work could be reviewed before merging.



((Challenges faced))

-One issue I ran into was when I first tried to push my work to GitHub. It would not let me continue because my Git setup on the computer was not properly connected/configured with my GitHub account.

I had to set up my Git identity and make sure GitHub recognized my account before I could successfully push my branch.

At the time, this was confusing because I did not know whether the problem was with my files, my branch, or GitHub itself. Fixing it helped me understand that Git on my computer and my GitHub account both need to be set up correctly before I can push changes.

-Another challenge came from my first submission. At first, I thought the 1001 platform review was supposed to be Task 01, so I created a Task 01 folder, added the review there, committed the changes, pushed the branch, and created a pull request.

After a later meeting, it was clarified that Week 1 did not actually have a Task 01 and that the 1001 platform review was supposed to be included inside the Week 1 report instead.

So i canceled the PR to not be merged with main. And the I updated my local main branch, created a new branch for the Week 01 report, moved the platform review into "weekly-reports/week-01.md", and removed the old Task 01 folder.

I then used "git status" to make sure I was on the correct branch and that only the correct file was being included. After that, I committed the Week 1 report, pushed the branch to GitHub, and created a new pull request.

-I also ran into a problem when trying to delete the old Task 01 folder from my local files. It would not delete normally because of a permission issue, so I learned how to use the "-Force" option to remove it.

Even though these problems were frustrating at first, they helped me understand the Git and GitHub workflow better because I had to figure out what went wrong and correct it instead of simply starting over.
