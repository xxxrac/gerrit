# Gerrit Code Review

[Gerrit](https://www.gerritcodereview.com) is a code review and project
management tool for Git based projects. For information about how to use Gerrit, refer to
[the official documentation](https://gerrit-review.googlesource.com/Documentation/index.html).

## Configuration

Let's start by installing Buck!

        git clone https://github.com/facebook/buck
        cd buck
        git checkout $(cat ../gerrit/.buckversion)
        ant
        mkdir ~/bin
        PATH=~/bin:$PATH
        ln -s `pwd`/bin/buck ~/bin/
        ln -s `pwd`/bin/buckd ~/bin/
        which buck

After _which buck_, if you see the directory of your buck installation, you may continue.

Clone (AND FORK) the repo to your home directory (Recursive means it will also synd the submodules directly from Google)

        git clone --recursive https://github.com/nicholaschum/gerrit
        cd gerrit && buck build release

Create a new script on your home so that you can easily perform upgrades based on this repo

        nano ~/upgrade_gerrit.sh (Linux)
        vim ~/upgrade_gerrit.sh (Mac)

Now add everything below into the nano window that has been opened:

        cd ~/gerrit
        git pull origin master
        git pull https://gerrit.googlesource.com/gerrit
        git push git@github.com:YOUR_PC_USERNAME/gerrit
        PATH=~/bin:$PATH && buck build release
        mv ~/gerrit/buck-out/gen/release/release.war
        ~/GERRIT_DIR/bin/gerrit.war
        bash ~/GERRIT_DIR/bin/gerrit.sh restart
        echo ""
        echo "Gerrit Upgrade Complete! Pushed all new changes directly to your repository."
        echo ""
        cd ~/

Then save the file!

Now, let's ensure that your computer has git access!

        git config --global user.name "YOUR NAME HERE"
        git config --global user.email "YOUR EMAIL HERE"
        ssh-keygen -t rsa -b 4096 -C "your_email@example.com"
        cat /home/user/YOUR_USERNAME/.ssh/id_rsa.pub

Head on over to https://github.com/settings/keys and copy and paste what you have received from the _cat_ output from the previous step, then let's authenticate from your Gerrit server!

        ssh -T git@github.com

If you see "Hi YOUR_USERNAME! You've successfully authenticated, but GitHub does not provide shell access.", then you have successfully finished upgrading!

If you set your script correctly, you can now call it directly from the shell to update from this Git repository, and also push to your own!

        chmod +x upgrade_gerrit.sh (ONLY ONE TIME)
        ./upgrade_gerrit.sh
        
NOTE: There are some cases where people may be trying to upgrade from a STABLE branch to _the_ master branch (which contains LOTS of fun!), so you guys need to perform these two commands WITHIN the Gerrit directory before starting the server - this ensures that your repository indexes are aligned properly, as well as reinitializing the Gerrit server to the new upgraded format:

        java -jar /home/user/GERRIT_DIR/bin/gerrit.war reindex
        java -jar bin/gerrit.war init

