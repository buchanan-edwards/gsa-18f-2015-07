# Manage the git repo used by puppet

class puppet_git {
  require base_config::build
  
  Exec {
    user  => 'puppet',
    group => 'puppet',
  }
  
  file {'/srv/puppet':
    ensure => directory,
    owner  => puppet,
    group  => puppet,
    mode   => '0750',
  }->
  exec { 'Add SSH host':
    command => 'ssh-keyscan github.com >> /var/lib/puppet/.ssh/known_hosts',
    path    => [ '/usr/bin' , '/bin' ],
    creates => '/var/lib/puppet/.ssh/known_hosts',
  }->
  exec { 'check out git':
    command => 'git clone git@github.com:TFlowGit/tf-ads-bpa.git',
    cwd     => '/srv/puppet/',
    path    => [ '/usr/bin' , '/bin' ],
    creates => '/srv/puppet/tf-ads-bpa/',
  }->
  exec { 'update git':
    command => 'git pull origin master',
    cwd     => '/srv/puppet/tf-ads-bpa/',
    path    => [ '/usr/bin' , '/bin' ],
  }
}