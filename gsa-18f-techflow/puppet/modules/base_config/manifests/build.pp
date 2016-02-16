# Packages and other base config for the build machine


class base_config::build {
  require base_config
  package { [
      'java-1.8.0-openjdk',
      'java-1.8.0-openjdk-devel',
      'puppet-server',
      'git',
      'docker-io',
    ]:
    ensure => present,
  }->
  file { '/var/run/docker.sock':
    ensure => present,
    replace => no,
    group   => 'docker',
    require => Group['docker']
  }->
  service { 'docker':
    ensure => running,
    enable => true,
  }
  group { 'docker': 
  }
  
  # Make sure we use the correct JDK:
  package { [
      'java-1.7.0-openjdk-devel',
      'java-1.7.0-openjdk',
    ]:
    ensure => absent,
  }
}