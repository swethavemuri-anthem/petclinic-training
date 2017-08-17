Petclinic setup instructions
=======================
   

## Steps to follow
* Pre-requisites: Please follow the link to get all docker images from AWS
    https://confluence.anthem.com/display/NIM/Connect+to+AWS+CLI+to+pull+nimbus+docker+images
    Run : sh script-docker-pull-all-aws.yml
* cd into petclinic-training in the terminal
* Run :: sh dev-script-p-dep-staticcontent-setup-petclinic.sh
* The above command will clone the petclinic-static-content repo
* Run :: sh dev-script-p-dep-all-start-petclininc.sh to create and run the dependent docker containers for running the petclinic application.


## To restart the spring boot applications
* Run :: sh dev-script-p-core-all-start.sh (Please run this command whenever you make changes to the petclinic configuration project)
* The above command will start 3 spring boot applications (platform.web, security-oauth, edgeapi-gateway)
