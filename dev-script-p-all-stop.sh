echo "Stopping docker containers..."
#docker ps -a --filter "status=running" | awk '{print $2}'
docker stop $(docker ps -a --filter "status=running" | awk '{print $2}')
echo "Stopped docker containers..."
echo "Begin removing containers..."
docker rm $(docker ps -a --filter "status=exited" | awk '{print $2}')
echo "Removed docker containers..."
