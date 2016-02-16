# Turn off virtual packages unless overridden by Hiera:
if versioncmp($::puppetversion,'3.6.1') >= 0 {
  $allow_virtual_packages = hiera('allow_virtual_packages',false)

  Package {
    allow_virtual => $allow_virtual_packages,
  }
}

# Modules to use:
include base_config::build
include jenkins_conf
include firewall::build
include puppet_git
