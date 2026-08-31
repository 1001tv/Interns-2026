# Weekly Report — Week 02

## Summary

This week I focused on learning the Git and GitHub workflow used for daily
development work on this repository. I cloned the repository, created my
personal branch (intern/Nour/task-02), made a test change, staged and
committed it following the agreed commit message format, and pushed the
branch to GitHub. I also opened a pull request and am now addressing review
feedback to complete the workflow end to end.

## Completed Tasks

- Cloned the repository and created my personal branch
- Made a test change and committed it with a properly formatted message
- Pushed the branch to the remote repository
- Opened a pull request (PR #13) for review
- Responded to review feedback by adding my weekly report to the PR

## Skills Learned

- Technical skills: core Git workflow (clone, branch, add, commit, push),
  resolving GitHub CLI authentication issues, updating an existing PR by
  pushing additional commits to the same branch
- Tools or frameworks: Git, GitHub, GitHub CLI (gh)
- Communication or teamwork skills: responding to reviewer feedback and
  clarifying repository structure conventions with the team

## Challenges and Solutions

**Challenge:** GitHub CLI (`gh`) authentication returned a "Bad credentials"
error when trying to create a pull request, despite being logged in.
**Solution:** Logged out and re-authenticated (`gh auth logout` /
`gh auth login`) to refresh the cached token, which resolved the issue.

**Challenge:** The repository had ambiguous top-level files/folders
(`interns`, `resources`, `shared`) that didn't match the structure
described in the README.
**Solution:** Reviewed the README.md directly to confirm the exact required
folder structure (`interns/first-name-last-name/weekly-reports/`) and
followed it precisely.

## Feedback Received

Reviewer noted that the pull request was open but did not yet contain any
report files. Added the weekly report to the branch and pushed the update
to address this.

## Overall Progress

- Progress: 45%