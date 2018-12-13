Petclinic setup instructions
=======================
 

## Steps to build
* Build cloud-config: mvn clean install (optionally add -DskipTests=true)
* Build platform.extension.client.petclinic: mvn clean install (optionally add -DskipTests=true)
* Build petclinic-gateway: mvn clean install (optionally add -DskipTests=true)
* Build petclinic.web: mvn clean install (optionally add -DskipTests=true)

## To start the spring boot applications
* Run cloud-config as a spring boot application
* Run petclinic-gateway as a spring boot application
* Run petclinic-web as a spring boot application


## To open the app in a browser
* Hit "http://localhost:8080/petclinic#/h/petclinicdashboard/vpDashboard"
