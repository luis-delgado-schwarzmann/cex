#!/usr/bin/env bash

###########
# Helpers #
###########

test_docker_compose() {
    if [ ! -e /usr/local/bin/docker-compose ]; then
        echo "docker-composed not found!!! [checking /usr/local/bin/docker-compose]"
        exit 2
    fi
}

start() {
    echo "Starting environment..."
    docker-compose -f docker-compose.yml up -d --remove-orphans
}


stop() {
    echo "Stopping enviroment..."
    docker-compose -f docker-compose.yml stop
}

clean() {
    docker-compose -f docker-compose.yml kill
    docker-compose -f docker-compose.yml rm -f
}


####################
# Script execution #
####################

test_docker_compose

case "$1" in
    start)
        start
        ;;
    stop)
        stop
        ;;
    restart)
        stop
        start
        ;;
    clean)
        clean
        ;;
    *)
        echo "Unkownn command ${1}. [start|stop|restart|clean]"
        exit 1
esac


