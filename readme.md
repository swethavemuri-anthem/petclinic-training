Petclinic setup instructions
=======================
   

## Steps to follow
* Pre-requisites: Install mongo and start. Alternately, you can use docker to run an instance of mongo.

## To start the spring boot application
* Run petclinic-web project as a spring-boot app. The port is configured in application.yml as 8082
* Run in the browser : http://localhost:8082/petclinic/login

## Things to know
* The petclinic extension project which contains all the sample implementations of the framework is hosted in github along with a web project to run the application.
* Please follow the below link to know more.
	https://github.com/openanthem/petclinic-training 
* This repo's petclinic-web project is just a handle to deploy the petclinic sample application in a war deployment strategy for Anthem's servers.
* The dependency of the extension project is provided in the current repository's petclinic-web project (dependent extension jars will be downloaded from oss nexus during build time.
