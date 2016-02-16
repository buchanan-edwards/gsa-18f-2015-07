# Packages and other base config for the app machine

class base_config::app {
  require base_config
  package { [
    'java-1.8.0-openjdk',
    'java-1.8.0-openjdk-devel',
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
}