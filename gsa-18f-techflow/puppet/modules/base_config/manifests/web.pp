# Packages and other base config for the web machine



class base_config::web {
  require base_config

  package { [
    'nginx',
    'squid',
  ]:
    ensure => present,
  }
  
  
}