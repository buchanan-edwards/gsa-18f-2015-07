# Firewall for the build machine

class firewall::app {
  file { '/etc/sysconfig/iptables':
    content => template("firewall/app_iptables.erb"),
  }~>
  exec { 'service iptables restart':
    path        => [ '/bin', '/usr/bin', '/sbin' ],
    refreshonly => true,
  }
}