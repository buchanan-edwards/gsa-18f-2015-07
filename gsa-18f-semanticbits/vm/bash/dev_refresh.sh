#!/bin/bash

rm -rf LABEL
git clone -b develop https://github.com/semanticbits/label-priv.git LABEL
cd LABEL/code/LABEL
grails dev war LABEL.war
sudo service tomcat7 stop
sudo rm -rf /var/lib/tomcat7/webapps/LABEL*
sudo cp -f LABEL.war /var/lib/tomcat7/webapps
sudo service tomcat7 start