# Intern Tasks and Reports Repository

Welcome to the Intern Tasks and Reports Repository.

This repository is used to organize, submit, and review interns’ assignments, progress updates, documentation, and final reports throughout the internship program.

## Repository Purpose

The repository helps interns and supervisors:

* Track assigned tasks and their progress
* Store completed work and supporting files
* Submit weekly reports
* Document challenges, solutions, and lessons learned
* Review feedback and improvements

## Repository Structure

Organize your files using the following structure:

```text
intern-tasks-reports/
│
├── README.md
│
├── interns/
│   ├── intern-name-1/
│   │   ├── tasks/
│   │   ├── daily-reports/
│   │   ├── weekly-reports/
│   │   └── final-report/
│   │
│   └── intern-name-2/
│       ├── tasks/
│       ├── daily-reports/
│       ├── weekly-reports/
│       └── final-report/
│
├── resources/
│   ├── templates/
│   ├── guidelines/
│   └── references/
│
└── shared/
    ├── meeting-notes/
    └── announcements/
```

Each intern should work only inside their own folder.

## Required Intern Folder

Create a folder using the following naming format:

```text
interns/first-name-last-name/
```

Example:

```text
interns/ahmed-ali/
```

Each intern folder should contain:

* `tasks/` — completed assignments and related files
* `weekly-reports/` — weekly summaries
* `final-report/` — final internship report and presentation

## Task Submission

For every task, create a separate folder inside the `tasks` directory.

Use this format:

```text
tasks/task-01-task-title/
```

Example:

```text
tasks/task-01-login-screen/
```

Each task folder should include:

```text
task-01-login-screen/
├── README.md
├── source-code/
├── screenshots/
└── documentation/
```

The task `README.md` should include:

* Task title
* Task objective
* Requirements
* Implementation details
* Challenges faced
* Solution
* Testing performed
* Final result
* Additional notes


## Weekly Report Format

Create one report for each week inside `weekly-reports`.

Use this format:

```text
weekly-reports/week-01.md
```

Each weekly report should include:

```markdown
# Weekly Report — Week 01

## Summary

Briefly describe the work completed during the week.

## Completed Tasks

- Task 1
- Task 2
- Task 3

## Skills Learned

- Technical skills
- Tools or frameworks
- Communication or teamwork skills

## Challenges and Solutions

Describe the main challenges and how they were handled.

## Feedback Received

Summarize feedback from the supervisor or team.

## Overall Progress

- Progress: 60%
```

## File Naming Rules

Use clear and consistent file names:

* Use lowercase letters
* Use hyphens instead of spaces
* Include dates in the format `YYYY-MM-DD`
* Use descriptive names

Good examples:

```text
task-02-api-integration/
login-screen-final.png
week-03-report.md
```

Avoid names such as:

```text
finalfinal2.pdf
new document.docx
file123.txt
```

## Git Workflow

Before starting work:

```bash
git pull origin main
```

Create a branch for your work:

```bash
git checkout -b intern/your-name/task-01
```

After completing your work:

```bash
git add .
git commit -m "Add task 01 report"
git push origin intern/your-name/task-01
```

Create a pull request for review if required by the supervisor.

Do not commit directly to the `main` branch unless you have permission.

## Submission Checklist

Before submitting your work, make sure that:

* Your files are inside your own intern folder
* The file names follow the naming rules
* The task description is complete
* Screenshots or supporting files are included when necessary
* The report explains challenges and solutions
* Your work has been tested
* No passwords, API keys, tokens, or private information are included
* The changes are committed and pushed to the correct branch

## Important Rules

* Keep your work organized and easy to review.
* Do not modify another intern’s folder without permission.
* Do not upload unnecessary or duplicated files.
* Do not commit sensitive information.
* Write clear and professional documentation.
* Update your reports regularly.
* Ask questions early when you are blocked.
* Review feedback and update your work when requested.

## Supervisor Review

Supervisors may review:

* Task completion
* Code or document quality
* Problem-solving approach
* Documentation quality
* Progress consistency
* Communication and responsiveness
* Ability to apply feedback

## Final Report

At the end of the internship, submit a final report inside:

```text
final-report/
```

The final report should include:

* Introduction
* Internship objectives
* Summary of completed tasks
* Skills and technologies learned
* Main challenges and solutions
* Important achievements
* Lessons learned
* Future improvement areas
* Conclusion

Thank you for contributing to the internship program. Keep your work clear, consistent, and professional throughout the internship.
