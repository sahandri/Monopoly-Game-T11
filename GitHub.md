# GitHub
Here is the [link to project page](https://github.com/CSU-CS414-SP2018/T11) for the group project.

For windows system, down load [git bash v2.16.3](https://github.com/git-for-windows/git/releases/tag/v2.16.3.windows.1). Mac OS should work the same on terminal.

## Set up and cache of the GitHub
### If Using HTTP
Directly type the code in terminal:
```
git clone https://github.com/CSU-CS414-SP2018/T11
```
You should be able to clone the project into current directory.

In this case, the URL should be set as remote directory by default. If by any chance that the default directory is not the project link or has not yet been set, type code in terminal:
```
git remote add origin https://github.com/CSU-CS414-SP2018/T11
git branch --set-upstream-to=origin
```

In order to cache in password, you can use code:
```
git config --global credential.helper cache
git config --global credential.helper 'cache --timeout=36000'
```
The timeout is set in second, I generally just set it up to 36000 seconds.

### If Using SSH
Use SSH instead of HTTP links by replacing `https://github.com/CSU-CS414-SP2018/T11` with  `git@github.com:GoldenaArcher/ct310p2.git`. Regarding how to generate SSH key pairs and update it on the GitHub, you can check on [this GitHub article](https://help.github.com/articles/generating-a-new-ssh-key-and-adding-it-to-the-ssh-agent/), and [this article](https://help.github.com/articles/adding-a-new-ssh-key-to-your-github-account/) for adding SSH key to GitHub account.


## Commit Changes
If directly work on the master branch, each time before committing anything, use `git pull` to get newest update code. Then use `git add .` to add all the changes made, and use `git commit -m "some commit message"` Last use `git push` to push the code to master branch.

For the first time push/pull request, use `git push --set-upstream origin branch_name` instead of `git push` to set up an origin..

If working on a different branch, first `git branch "some branch name"` to create a new branch. The `git pull` command is still done on master branch, but later need to `git checkout "some branch name"` to checkout to the own branch, then use `git merge master` to get the newest update from master branch. Once done coding, use the same command as on the master branch, but need to go to the GitHub website and create pull request(at least that was how we did it in other class).

__NEVER WORK ON MASTER BRANCH DIRECTLY!!__
