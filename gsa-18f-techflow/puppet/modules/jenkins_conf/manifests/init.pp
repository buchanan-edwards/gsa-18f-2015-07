# Configure Jenkins build machine

class jenkins_conf {

  class { 'jenkins':
    executors => 1,
    install_java => false,
  }

  #Seems broken upstream. For now will set manually for now and debug later.
  #class { 'jenkins::security':
  #  security_model => 'full_control', 
  #}

  # Initial run only; causes problems after that:
  #jenkins::job { 'OpenFDA-app-build':
  #  config  => template("jenkins_conf/openfda-build-job.xml.erb"),
  #  require => File['/usr/lib/jenkins/puppet_helper.groovy'],
  #}

  jenkins::plugin {
    [
      'git',
      'cobertura',
      'findbugs',
      'pmd',
      'analysis-core',        # Required for pmd and findbugs
      'scm-api',              # Required for git
      'git-client',           # Required for git
      'ssh-agent',
      'sonar',
      'configurationslicing', # Required for sonar
      'maven-plugin',         # Required for sonar
    ]:
  }

  jenkins::user { 'admin':
    email     => 'nowhere@techflow.com',
    password  => 'changeme',
    full_name => 'Default Account',
    require   => Class['jenkins'],
  }

  user { 'jenkins':
    groups  => [ 'jenkins', 'docker' ],
    require => [Group['jenkins'], Group['docker']],
    notify  => Service['jenkins'],
  }

  #jenkins::credentials { 'openfda-deploy-key':
  #  password            => '',
  #  private_key_or_path => template('jenkins_conf/jenkins_key.erb'),
  #  description         => 'tf-ads-bpa repository deploy key',
  #  require             => Class['jenkins'],
  #}
}



