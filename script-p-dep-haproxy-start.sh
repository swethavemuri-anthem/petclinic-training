#docker network rm proxy-net
#docker network create proxy-net
SLEEP 5
docker-compose -f docker-compose-p-dep-haproxy.yml up -d
