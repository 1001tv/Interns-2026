# Task 02 — Version Control

## Objective
Learn and practice the Git and GitHub workflow used for daily development
on this repository.

## Requirements
- Clone the repository
- Create a personal branch
- Make a test change
- Commit the change
- Push the branch
- Create a pull request
- Respond to review comments

## Implementation Details
- Cloned the repository locally and created branch `intern/Nour/task-02`.
- Made a test change and committed it with a properly formatted message.
- Pushed the branch to the remote repository.
- Opened pull request #13 for review.
- Resolved a `gh` CLI authentication issue (401 Bad credentials) by
  re-authenticating with `gh auth login`.
- Fixed a repository structure issue where `interns` existed as a file
  instead of a directory, replacing it with the correct folder structure
  per the README.
- Added weekly report and this task README in response to reviewer
  feedback requesting report content.

## Challenges Faced
- GitHub CLI authentication token was stale/invalid.
- The `interns` path existed as a placeholder file rather than a folder.

## Solution
- Re-authenticated `gh` with a fresh login, which resolved the PR
  creation error.
- Removed the placeholder `interns` file and created the correct
  `interns/nour-alsumaidaei/` folder structure as specified in the
  repository README.

## Testing Performed
- Verified `git status` showed a clean working tree after each commit.
- Confirmed the branch was up to date with origin after each push.
- Confirmed pull request #13 reflected the new commits after each push.

## Final Result
Branch created, committed, and pushed. Pull request #13 opened and
updated with weekly report and this task documentation.

## Additional Notes
None.