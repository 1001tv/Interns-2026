# Weekly Report — Week 05

### Summary
Focused on foundational software development principles, environment setup, and iOS project delivery. Engaged with the introduction of Uncle Bob’s *Clean Code* alongside Agile sprint methodologies, practiced Swift logic via testing files on Windows, attended the weekly tech talk covering Clean Architecture and GitHub project management, and resolved multi-layered Git and macOS terminal configuration bottlenecks to successfully submit and push the `twoNavigationScreen` UIKit project to the remote repository.

### Completed Tasks
- **Clean Code & Agile Fundamentals:** Read the introduction to *Clean Code* by Robert C. Martin, analyzing the case study of the "Killer App" company to understand technical debt and the core thesis that "the only way to move fast is to keep the code clean"; reviewed Agile sprint workflows.
- **Swift Logic Prototyping:** Explored Swift code and validated language logic through standalone testing files on Windows.
- **iOS Project Structuring & Migration (`twoNavigationScreen`):** Organized the local folder directory under `1001-interns/arwa-yaareb-ios-path/ios-p0/` within the cloned repository `1001tv/Interns-2026` on the `interns/arwa-yaareb/ios-path-p0` branch.
- **Git Repository Troubleshooting:** Identified and removed the hidden nested `.git` directory generated automatically by Xcode, cleared the cached submodule gitlink (`git rm --cached`), and correctly staged all Xcode metadata, Storyboards, Assets, and Swift view controllers on the branch.
- **Shell Environment Configuration:** Fixed persistent terminal hook errors (`autoload:3: bad option: -u` and `precmd: command not found: vcs_info`) by configuring `autoload -Uz vcs_info` in `~/.zshrc` to achieve clean Git branch prompt rendering.
- **Authentication & Remote Synchronization:** Resolved GitHub password deprecation by generating and authenticating with a Classic Personal Access Token (`reposcope`); resolved remote history divergence using `git pull --rebase` and successfully pushed all project files to `origin interns/arwa-yaareb/ios-path-p0`.
- **Team Meeting Participation & Action Items:** Documented key takeaways from Thursday’s tech talk regarding GitHub Project Backlog management, layered Clean Architecture concepts, and planned SOLID principle 

### Skills Learned
- **Technical Skills:**
  - Git history management, submodule identification/cleanup, remote rebasing (`git pull --rebase`), and Personal Access Token (PAT) authentication.
  - Zsh shell environment troubleshooting and prompt customization via `vcs_info`.
  - UIKit project directory architecture, project bundling, and Xcode file tracking.
  - Core software design concepts: Clean Architecture data flow (outer layer to core), layer isolation for security, and SOLID design principles.
- **Tools & Frameworks:**
  - Git & GitHub (GitHub Projects, Backlog boards, branch management).
  - macOS Terminal & Zsh.
  - Xcode & UIKit (`twoNavigationScreen`, `ViewController`, `AppDelegate`, `SceneDelegate`, Storyboards).
- **Communication or Teamwork Skills:**
  - Aligning project status during weekly team meetings.
  - Sharing and discussing areas of improvement with team members to collaborate smoothly across different project paths.
  - Transparently communicating environment hurdles and hardware access limitations with teammates who provided guidance and 

### Challenges and Solutions

- **Challenge 1: Hardware Access and Time Constraints**
  - *Description:* Limited physical access to the shared iMac workstations at the American Space due to constrained campus free hours (restricted to 2–3 hours per session across only two days).
  - *Solution:* Prepared logic and reviewed Swift concepts in advance on a local Windows machine, reserving lab hours strictly for macOS-specific environment tasks, terminal debugging, and Xcode builds.

- **Challenge 2: macOS Terminal vs. VS Code Terminal Discrepancies & Shell Syntax Errors**
  - *Description:* Unfamiliarity with the native macOS Zsh environment compared to the integrated VS Code terminal, compounded by persistent startup errors (`precmd: command not found: vcs_info` and `autoload:3: bad option: -u`).
  - *Solution:* Diagnosed the `~/.zshrc` script, corrected the autoload definition to `autoload -Uz vcs_info`, and sourced the file to stabilize prompt rendering and command execution.

- **Challenge 3: Embedded Submodule Conflicts from Xcode Initialization**
  - *Description:* Xcode automatically initialized a hidden Git repository upon project creation, causing `git add .` to track the project as a detached submodule gitlink rather than individual source files.
  - *Solution:* Removed the nested `.git` folder using `rm -rf`, unstaged the cached submodule entry via `git rm --cached -f`, and re-staged the full directory tree.

- **Challenge 4: Remote History Divergence & Authentication Failures**
  - *Description:* GitHub rejected HTTPS password authentication, and subsequent pushes were blocked due to diverged commit history between the local branch and remote.
  - *Solution:* Configured a Classic GitHub Personal Access Token for HTTPS operations and rebased the local commits onto the remote branch (`git pull --rebase origin interns/arwa-yaareb/ios-path-p0`) to preserve linear history without unnecessary merge
### Feedback Received
- **Clean Code & Architecture Guidelines:** Emphasized the importance of Clean Architecture, specifically understanding how layer isolation protects the core and learning data flow mechanics from outer layers inward.
- **Self-Directed Research & Best Practices:** Encouraged to study Uncle Bob’s Clean Code principles, investigate industry architectures (including Google's recommended patterns), study SOLID principles, and learn effective technical documentation reading strategies.
- **Task Board Tracking:** Noted action item to follow up with Amnah regarding board permissions to independently update ticket statuses in the GitHub backlog.

### Overall Progress
**Progress: 62.5% (Week 5 of 8)**
