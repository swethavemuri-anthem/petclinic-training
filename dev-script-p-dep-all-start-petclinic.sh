#!/bin/sh

export NIMBUS_ENVIRONMENT=docker
export NIMBUS_LOG_DIR=/logs 
export CONFIG_PATH=ssh://git@bitbucket.anthem.com:7999/nim/petclinic-config-properties.git

export WEB_HOST_PORT=8080
export WEB_CONTAINER_PORT=8080
export EDGESERVER_HOST_PORT=9090
export EDGESERVER_CONTAINER_PORT=9090
export AUTHWEB_HOST_PORT=8890
export AUTHWEB_CONTAINER_PORT=8890
export PDEP_REDIS_PORT=6379
export PDEP_REDIS_HOST=redis
export PDEP_MONGODB_PORT=27017
export PDEP_MONGODB_HOST=mongo
export PDEP_MYSQL_PORT=3306
export PDEP_RABBITMQ_PORT=5672

export CLOUD_CONFIG_LABEL=master
export WEB_APP_NAME=platform-web
export EDGEAPI_APP_NAME=edge-apigateway
export SECURITY_APP_NAME=security-oauth



docker network rm pdep

docker network create pdep

docker-compose -f docker-compose-p-dep-rabbitmq-aws.yml up -d
SLEEP 5
docker-compose -f docker-compose-p-dep-redis-aws.yml up -d
SLEEP 5
docker-compose -f docker-compose-p-dep-consul-aws.yml up -d
# SLEEP 10
# docker-compose -f docker-compose-p-dep-registrator.yml up -d
SLEEP 10
docker-compose -f docker-compose-p-dep-configserver-aws.yml up -d
SLEEP 10
docker-compose -f docker-compose-p-dep-mongo-db-aws.yml up -d
SLEEP 15
docker-compose -f docker-compose-p-dep-mongo-db-aws.yml exec mongo bash -c 'source /platform/load-data.sh'
SLEEP 5
docker-compose -f docker-compose-p-dep-mysql-db-aws.yml up -d
SLEEP 15
docker-compose -f docker-compose-p-dep-mysql-db-aws.yml exec mysql bash -c 'source /platform/load-data.sh'
SLEEP 5
docker-compose -f docker-compose-staticcontent-aws.yml up -d
SLEEP 5
docker-compose -f docker-compose-p-core-all-petclinic.yml up -d
