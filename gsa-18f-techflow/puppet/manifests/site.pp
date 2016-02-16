# Turn off virtual packages unless overridden by Hiera:
if versioncmp($::puppetversion,'3.6.1') >= 0 {
  $allow_virtual_packages = hiera('allow_virtual_packages',false)

  Package {
    allow_virtual => $allow_virtual_packages,
  }
}

node default {
  include epel
}

node bastion {
  include epel
}

node puppetmaster {
  include base_config::build
  include firewall::build
  # Commenting out Jenkins to prevent spurious restarts.
  # include jenkins_conf
  include puppet_git
}

node /^app\d+$/ {
  include base_config::app
  include firewall::app
  include app_conf
}

node /^web\d+$/ {
  include base_config::web
  include firewall::web
  include proxy_config
}
