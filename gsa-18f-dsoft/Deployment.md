#Deployment

###Overview

For both staging and production server, this project uses TeamCity to automatically build and deploy the website to Azure Linux host. For the staging server, deployments are automatically triggered by GitHub pushes. For the production server, deployments are manually triggered after Q&A has ran the integration tests.

###TeamCity configuration

The TeamCity project configuration consists of three build configurations

* GitHub Pull: 
  - Automatically triggered by GitHub pushes to https://github.com/DSoftTechnology/ADS-FDA-Pool3.git (queries every 60 seconds)
  - Runs NuGet to install / update packages defined in packages.config
  - Builds Xamarin solution
  - Runs NUnit and JetBrains dotCover (code coverage)
  - Publishes everything under the /Source/dsoft.ads/dsoft.ads.web as artifact (including the root dockerfile)

* Azure Staging Deploy
  - Automatically triggered by succesfull GitHub Pull
  - Uses the dockerfile to build a new docker image
  - Pushes the new docker image to staging Azure Linux host (staging-dsoft-ads.cloudapp.net)

* Azure Production Deploy
  - Manually triggered after Q&A integration tests
  - Uses the dockerfile to build a new docker image
  - Pushes the new docker image to production Azure Linux host (dsoft-ads.cloudapp.net)

###Azure configuration

Azure has two virtual Linux machines configured: dsoft-ads and staging-dsoft-ads. Each Linux VM server has the Azure Docker Extension installed and simply serves as a Docker host.

For development purposes, the source solution can be loaded in MonoDevelop / Xamarin Studio and run in the integrated XPS server.
