#!/bin/bash
#Author: Narendra Potluri
#Version: 1.0
#Date: 06/26/2015
#Description: This script creates a (ubuntu 14.04.1 LTS with nodejs 0.12.4) docker image using docker file
#Then creates a container and mounts host folder in the container
#Then starts nodejs application using the application deployment in the the mounted folder.

#update the following variable if you want to change the application folder
AppDir="/vencore/docker/vfdademo-master"

#update the following variable if you want to launch application on a different port
PMap="80"

echo "Creating Docker Image "vencore-nodejs-demo" ...."
#execute the following command to build (ubuntu 14.04.1 LTS with nodejs 0.12.4) docker image 
sudo docker build -t vencore-nodejs-demo .

echo "Removing old Docker container "vencore-demo" "
#execute the following commands to stop and delete the demo container if it exists
sudo docker stop vencore-demo
sudo docker rm vencore-demo

echo "Clean-up application folder $AppDir if it exists"
#Create application folder
sudo rm -rf $AppDir
sudo mkdir -p $AppDir

echo "extract archive.tar to application folder $AppDir"
#extract application archive using the following command.
sudo tar -xzf ./archive.tar.gz -C $AppDir

echo "copy config.js to applicaton "$AppDir/static/js/" folder. If required update values of "host_ip_address" and "host_port_number" with docker machine DNS or IP and available port number for application port"
sudo cp -f config.js $AppDir/static/js/

echo "Create Docker container "vencore-demo" and start application"
#execute the following command to build the container from the image created in previous steps and then start vencore demo application.
sudo docker run --name vencore-demo -it -d -P -p $PMap:$PMap -v $AppDir:$AppDir vencore-nodejs-demo /bin/bash -c "PORT=80 pm2 start $AppDir/app.js && tail -f /dev/null"

echo "Check if application started properly by using application url http://<DockerHostIP or DNS>:<application port> eg: http://192.162.1.1:80"




