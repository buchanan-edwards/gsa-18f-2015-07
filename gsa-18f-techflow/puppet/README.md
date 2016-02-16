# Introduction

The scripts in here are for the creation and configuration of the
infrastructure used to run TechFlow's submission to the 18F Agile
Delivery Services BPA.

# Running with Vagrant

With Vagrant and VirtualBox installed, run `vagrant up` within this
directory. The web machine will be accessible at
http://localhost:8080.

The scripts contained herein will create and provision the necessary
machines but do not contain everything required to run the application
(including the code). Some setup of ssh keys and similar connections
is still required.

# Normal install

Bootstrapping Puppet:
* `yum install epel-release`
* `rpm -ivh http://yum.puppetlabs.com/puppetlabs-release-el-6.noarch.rpm`
* `yum install puppet-server git`
* Check repo out to `/srv/puppet` and configure `puppet.conf` to look
  at it.

# Post-Puppet steps

Configure [Jenkins](http://localhost:8081/):

- Within 'Configure Global Security', update to require user login for
  configuration.
- Under 'Configure System', add a Maven installation. Set the Jenkins
  URL (if needed).
- Under 'Credentials', add 'openfda-deploy-key' credentials.
- Configure the `jenkins` account with proper ssh keys to push to the
  app server.
- Run the job and verify it works.

