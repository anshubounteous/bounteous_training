
## Setup & Configuration

```bash
git config --global user.name "Your Name"       # Set your global Git username
git config --global user.email "your.email@example.com"   # Set your global Git email
git config --list                               # List all current Git configurations
````

---

## Repository Initialization

```bash
git init                                         # Initialize a new Git repository
git clone <repo-url>                             # Clone a remote Git repository
```

---

## Basic Workflow

```bash
git status                                       # Show the working directory status
git add <filename>                               # Stage a specific file for commit
git add .                                        # Stage all changes in the current directory
git commit -m "Your message"                     # Commit staged changes with a message
git log                                          # View the commit history
```

---

## Remote Repositories

```bash
git remote -v                                    # Show current remote repository URLs
git remote add origin <repo-url>                 # Add a new remote repository
git push -u origin main                          # Push commits to the remote 'main' branch and track it
git pull                                         # Fetch and merge changes from the remote repo
```

---

## Branching

```bash
git branch                                       # List all local branches
git branch <branch-name>                         # Create a new branch
git checkout <branch-name>                       # Switch to the specified branch
git checkout -b <branch-name>                    # Create and switch to a new branch
git merge <branch-name>                          # Merge a branch into the current branch
```

---

## Undoing Changes

```bash
git reset <file>                                 # Unstage a file but keep the changes
git reset --hard                                 # Remove all changes and reset to the last commit
git revert <commit-hash>                         # Create a new commit that undoes the specified commit
```

---

## Stashing

```bash
git stash                                        # Save uncommitted changes temporarily
git stash pop                                    # Reapply the most recent stashed changes
```

---

## Tagging

```bash
git tag                                          # List all tags
git tag v1.0                                     # Create a lightweight tag named 'v1.0'
git tag -a v1.0 -m "version 1.0"                 # Create an annotated tag with a message
```

---

## Git Rebase (Advanced)

```bash
git rebase <branch>                              # Reapply commits on top of another base branch
git rebase -i HEAD~3                             # Interactively rebase the last 3 commits
git rebase --continue                            # Continue rebase after resolving conflicts
```

---

## Cleaning

```bash
git clean -fd                                    # Remove untracked files and directories
```

---

## Helpful Logs

```bash
git diff                                         # Show changes in unstaged files
git show <commit-hash>                           # Show details of a specific commit
```
