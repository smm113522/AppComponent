git branch -a 

git checkout -b kotlinApp origin/kotlinApp

git status

git add .

git commit -m "log"

git push 


git pull origin/kotlinApp
git fetch --all
git reset --hard origin/master
