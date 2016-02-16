# Packages and other base config for the app machine

class base_config {
  require epel

  class { selinux:
    mode => 'enforcing'
  }

  package { [
    'vim-common',
  ]:
    ensure => present,
  }

}