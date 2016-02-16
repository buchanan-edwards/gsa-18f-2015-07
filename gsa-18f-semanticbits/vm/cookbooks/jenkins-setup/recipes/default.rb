#
# Cookbook Name:: jenkins-setup
# Recipe:: default
#
# Copyright 2015, YOUR_COMPANY_NAME
#
# All rights reserved - Do Not Redistribute
#

include_recipe 'jenkins::master'

# Create private key credentials to connect from master to slaves.
jenkins_private_key_credentials 'ubuntu' do
  id 'fa3aab48-4edc-446d-b1e2-1d89d86f4458'
  description 'Private key credentials to connect from master to Linux slaves.'
  private_key node['jenkins-setup']['private_key']
end

# Add a Linux slave.
jenkins_ssh_slave 'linux' do
  description 'Runs Selenium tests on Linux'
  executors       1
  remote_fs   '/home/ubuntu'
  labels      ['ubuntu']

  # SSH specific attributes
  host        node['jenkins-setup']['linux_slave_host']
  user        'ubuntu'
  credentials 'fa3aab48-4edc-446d-b1e2-1d89d86f4458'
end

# Windows JNLP slave
jenkins_script 'add_jnlp_slave' do
  command <<-'EOH'
            import hudson.model.*
            import hudson.slaves.*
            import jenkins.model.*
            import jenkins.slaves.*           
            // Launcher
            launcher = new hudson.slaves.JNLPLauncher()
            // Build the slave object
            slave = new DumbSlave(
              'windows',
              'Runs Selenium tests on Windows',
              'c:\\Jenkins',
              '1',
              Node.Mode.EXCLUSIVE,
              'win',
              launcher,
              new RetentionStrategy.Always(),
              []
            )
            // Create or update the slave in the Jenkins instance
            nodes = new ArrayList(Jenkins.instance.getNodes())
            ix = nodes.indexOf(slave)
            (ix >= 0) ? nodes.set(ix, slave) : nodes.add(slave)
            Jenkins.instance.setNodes(nodes)

  EOH
end


jenkins_plugin 'grails'
jenkins_plugin 'xvfb'
jenkins_plugin 'violations'
jenkins_plugin 'cobertura'
jenkins_plugin 'publish-over-ssh'
jenkins_plugin 'github-oauth' do 
	notifies :restart, 'service[jenkins]', :immediately 
end


# Create Jenkins jobs
jenkins_job 'LABEL-unit' do
	config "/vagrant/jenkins/LABEL-unit/config.xml"
	action :create
end
jenkins_job 'LABEL-integration' do
	config "/vagrant/jenkins/LABEL-integration/config.xml"
	action :create
end
jenkins_job 'LABEL-functional' do
	config "/vagrant/jenkins/LABEL-functional/config.xml"
	action :create
end
jenkins_job 'LABEL-dev' do
	config "/vagrant/jenkins/LABEL-dev/config.xml"
	action :create
end
jenkins_job 'LABEL-uat' do
	config "/vagrant/jenkins/LABEL-uat/config.xml"
	action :create
end



# Add Publish Over SSH Dev server config.
jenkins_script 'add_ssh_dev_qa_config' do
  command <<-EOH.gsub(/^ {4}/, '')
    import jenkins.model.*
    import hudson.security.*
    import org.jenkinsci.plugins.*
	import jenkins.plugins.publish_over_ssh.descriptor.*
	import jenkins.plugins.publish_over_ssh.*
  
    plugin = Jenkins.getInstance().getExtensionList(BapSshPublisherPluginDescriptor.class)[0];
	
	BapSshCommonConfiguration conf = new BapSshCommonConfiguration("", """#{node['jenkins-setup']['private_key']}""", "", false);
	plugin.setCommonConfig(conf);

	// DEV.
	plugin.addHostConfiguration(new BapSshHostConfiguration("DEV", "#{node['jenkins-setup']['dev_host_name']}", "ubuntu", "", "/home/ubuntu", 22, 30000, false, "", "", false));
	
	// QA.
	plugin.addHostConfiguration(new BapSshHostConfiguration("QA", "#{node['jenkins-setup']['qa_host_name']}", "ubuntu", "", "/home/ubuntu", 22, 30000, false, "", "", false));

	plugin.save();
  EOH
end


# Add xvfb installation
jenkins_script 'add_xvfb_installation' do
  command <<-EOH.gsub(/^ {4}/, '')
    import jenkins.model.*
    import hudson.security.*
    import org.jenkinsci.plugins.*
  
    a = Jenkins.getInstance().getExtensionList(org.jenkinsci.plugins.xvfb.XvfbInstallation.DescriptorImpl.class)[0];
	b = (a.installations as List);
	b.add(new org.jenkinsci.plugins.xvfb.XvfbInstallation("xvfb", "", []));
	a.installations = b
	a.save()
  EOH
end

#
jenkins_script 'add_authentication' do
  command <<-EOH.gsub(/^ {4}/, '')
    import jenkins.model.*
    import hudson.security.*
    import org.jenkinsci.plugins.*	
	import hudson.model.*

    def instance = Jenkins.getInstance()

    def githubRealm = new GithubSecurityRealm(
      'https://github.com',
      'https://api.github.com',
      '#{node['jenkins-setup']['github_client_id']}',
      '#{node['jenkins-setup']['github_client_secret']}'
    )
    instance.setSecurityRealm(githubRealm)

    def strategy = new GlobalMatrixAuthorizationStrategy()
	strategy.add(Jenkins.ADMINISTER, "semanticbits")
	strategy.add(Jenkins.READ, "semanticbits")
	strategy.add(Jenkins.RUN_SCRIPTS, "semanticbits")
	
	strategy.add(Jenkins.READ, "anonymous")
	strategy.add(Computer.CONNECT, "anonymous")
	instance.setAuthorizationStrategy(strategy)

    instance.save()
  EOH
end