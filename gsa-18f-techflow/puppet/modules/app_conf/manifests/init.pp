# set up places for for the app to run

class app_conf {
  include stdlib

  group{ 'tf-ads-bpa':
    ensure => present,
  }->
  user{ 'tf-ads-bpa':
    ensure     => present,
    groups     => [ 'tf-ads-bpa', 'docker' ],
    managehome => true,
    require    => Group['docker'],
  }->
  file { [ '/home/tf-ads-bpa/app', '/home/tf-ads-bpa/logs' ]:
    ensure => directory,
    owner  => 'tf-ads-bpa',
    group  => 'tf-ads-bpa',
  }
  file { '/etc/init/tf-ads-bpa.conf':
    content => template("app_conf/tf-ads-bpa.conf.erb"),
  }

  file_line { 'tf-ads-bpa sudo':
    path   => '/etc/sudoers',
    ensure => present,
    line   => 'tf-ads-bpa ALL=(ALL) NOPASSWD: /sbin/start tf-ads-bpa, /sbin/restart tf-ads-bpa, /sbin/stop tf-ads-bpa, /sbin/initctl list',
    match  => '^tf-ads-bpa .*',
  }
}
