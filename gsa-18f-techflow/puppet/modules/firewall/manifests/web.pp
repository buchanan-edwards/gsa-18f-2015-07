# Firewall for the build machine

class firewall::web {
  file { '/etc/sysconfig/iptables':
    content => template("firewall/web_iptables.erb"),
  }~>
  exec { 'service iptables restart':
    path        => [ '/bin', '/usr/bin', '/sbin' ],
    refreshonly => true,
  }
}