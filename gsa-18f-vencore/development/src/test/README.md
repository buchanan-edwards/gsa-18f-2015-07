# Setup browser headless intergration testing 

On Jenkins,

1.	Verified Firefox was installed.
2.	Added Firefox to PATH in Jenkins config.
3.	Verified xvfb (Xwindows Virtual Frame Buffer) was installed.
4.	Properly started xvfb, ran the integration tests via Grunt and stopped xvfb from within the Jeknins the Integration Test job:

```
#!/bin/bash
export PATH=$PATH;/usr/bin/firefox

# Instantiates an Xvfb session on display port 99
Xvfb :99 -ac &

# Environment variable to let firefox know where to run
export DISPLAY=:99

echo "## Perform the Functinal Testing..."
${GRUNT} integrationTest

# Pass or fail this script based on the final return value
set $STATUS = $?

# End the Xvfb session
killall Xvfb

# let Jenkins know how the tests went
exit $STATUS
```

