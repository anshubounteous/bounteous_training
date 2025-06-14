
````markdown
# 🚀 Git Commands Guide using Git Bash & Rebase

This guide covers essential Git commands using **Git Bash**, with detailed examples and instructions on how to use `git rebase` for a clean commit history.

---

## 📁 1. Setting Up Git & Git Bash

Git Bash is a command-line shell for using Git on Windows.  
👉 Download it from: [git-scm.com](https://git-scm.com/)

```bash
git --version                            # Check Git installation
git config --global user.name "Your Name"
git config --global user.email "you@example.com"
````

---

## 🔧 2. Initialize or Clone a Repository

```bash
git init                       # Start a new local repository
git clone <repository-url>    # Clone an existing remote repo
```

---

## 📄 3. Staging, Committing & Tracking Changes

```bash
git status                     # Check current changes
git add .                      # Stage all files
git commit -m "commit message" # Commit with message
git log                        # View commit history
```

---

## 🌐 4. Remote Repositories

```bash
git remote -v                                # Show remote URLs
git remote add origin <remote-url>           # Link to remote
git push -u origin main                      # First-time push
git pull origin main                         # Pull changes from remote
```

---

## 🌿 5. Branching & Merging

```bash
git branch                              # List branches
git checkout -b feature-branch          # Create & switch to new branch
git checkout main                       # Switch back to main
git merge feature-branch                # Merge feature into main
```

---

## ♻️ 6. Git Rebase – A Cleaner History

Use `git rebase` to move your feature work on top of the latest main branch:

```bash
git checkout feature-branch
git rebase main
```

### 🔁 Interactive Rebase

```bash
git rebase -i HEAD~3    # Rebase last 3 commits interactively
```

This allows you to:

* `pick` a commit
* `squash` commits
* `reword` messages
* `drop` unnecessary commits

---

## 🧽 7. Undoing Changes

```bash
git reset --soft HEAD~1     # Undo last commit, keep changes
git reset --hard HEAD~1     # Remove commit and changes
git checkout -- <file>      # Discard file changes
git stash                   # Temporarily save uncommitted work
git stash pop               # Restore stashed work
```

---

## 🧠 8. Git Tips and Power Moves

```bash
git log --oneline --graph      # Neat commit history
git branch -d feature-branch   # Delete branch locally
git remote prune origin        # Clean up deleted remote branches
git clean -fd                  # Remove untracked files/folders
```

---

## 🧪 9. Common Git Workflow (Feature Branch Model)

```bash
git clone <repo-url>
cd repo-folder
git checkout -b feature-x
# Make changes
git add .
git commit -m "added feature x"
git pull origin main --rebase   # Rebase onto latest main
git push origin feature-x
```

---

## ✅ Conclusion

This file covers **Git basics**, **Git Bash usage**, and **git rebase** to help maintain a clean, linear project history.

---