#

class proxy_config {
  require base_config

  File {
    require => Package[ 'nginx' ],
    owner => root,
    group => root,
    mode  => '640',
    notify  => Service['nginx'],
  }

  file { '/etc/nginx/nginx.conf': 
    ensure  => present,
    content => template("proxy_config/nginx.conf.erb"),
  }

  file { '/etc/nginx/conf.d/default.conf': 
    ensure  => present,
    content => template("proxy_config/default.conf.erb"),
  }

  service { 'nginx':
    ensure => running,
    enable => true,
  }

  selboolean { 'selinux allow proxying': 
    name => 'httpd_can_network_connect',
    value => on,
    persistent => true,
    require => Class[selinux],
  }

}