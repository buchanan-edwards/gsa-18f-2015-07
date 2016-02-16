### Installation Instructions (Docker)

Following information will help to set up a node.js application running on a Docker container.
These instructions were tested on ubuntu 14.04.1 LTS operating system with node.js 0.12.4 and Docker version 1.2.0.

### Pre-Requisites:
1. Linux OS (Vencore used Ubuntu 14.04.1 LTS with nodejs version 0.12.4)
2. Docker client 1.2.0 is installed
3. Docker daemon 1.2.0 is installed
4. Git and gzip are installed
4. Execute the following command to check if Docker client and daemon are available    

               sudo docker version
               
5. Internet access for the Docker server to download Docker image from Docker Hub
6. Port 80 available on server where the Docker container is being created
7. sudo privileges to execute any command or can use root user account

### Application install instructions:
1. Connect to the Docker Machine and create a folder. eg: "vencore/docker"

2. Change to the folder created in step 1 and clone vencore git repository

		git clone https://github.com/vencoreinc/18FAGILEPROTOTYPE.git vencoredemo
		

3. Change to folder "vencoredemo/dist" and make sure the below files exist
                
                Dockerfile
                archive.tar.gz
                vencoreDemoApp.sh
             
4. Execute the following command to make the shell file executable

                sudo chmod +x vencoreDemoApp.sh

5. Execute the following command to build the Docker container and launch application on port 80  

	_If you need to change the application folder or application port, update the script file (vencoreDemoApp.sh) before 	executing the script file. Update "AppDir" variable to change application folder eg: 			
	AppDir="/vencore/docker/vfdademo-master". Update "PMap" variable to change application port eg: PMap="80"._

                ./vencoreDemoApp.sh > "VencoreDemoAppInstall-$(date +"%Y-%m-%d:%T").log"

6. Check if application started properly by using application URL

		http://<DockerHostIP or DNS>:<application port>  eg: http://192.162.1.1:80
		


### Installing the application on another machine (non-Docker):

### Pre-Requisites:
1. node.js (v.0.12.4)
2. npm
3. pm2 
4. grunt-cli (v.0.4.5)
5. git

### Install instructions
1. Clone Vencore git repository to local folder "vencoredemo"

		git clone https://github.com/vencoreinc/18FAGILEPROTOTYPE.git vencoredemo

2. Change to folder "vencoredemo/development/src" and execute the following command to start the application on port 8080.  
	_If port 8080 is not available update 8080 to available port in the following command before executing it._

		sudo PORT="8080" pm2 start app.js
		

3. Test the application using the below URL replacing the appropriate values

		http://<Host IP or DNS>:<application port>  eg: http://192.162.1.1:8080
		
		
