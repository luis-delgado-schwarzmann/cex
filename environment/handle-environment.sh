#!/usr/bin/env bash

DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"
COMPOSE_INTERNAL_FOLDER="$DIR/docker/compose"

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
    docker-compose \
        -f $COMPOSE_INTERNAL_FOLDER/environment.yml \
        -f $COMPOSE_INTERNAL_FOLDER/command-controller.yml up \
        -d --remove-orphans --build
}


stop() {
    echo "Stopping enviroment..."
    docker-compose \
        -f $COMPOSE_INTERNAL_FOLDER/environment.yml \
        -f $COMPOSE_INTERNAL_FOLDER/command-controller.yml \
        stop
}

clean() {

    docker-compose \
        -f $COMPOSE_INTERNAL_FOLDER/environment.yml \
        -f $COMPOSE_INTERNAL_FOLDER/command-controller.yml \
        kill

    docker-compose \
        -f $COMPOSE_INTERNAL_FOLDER/environment.yml \
        -f $COMPOSE_INTERNAL_FOLDER/command-controller.yml \
        rm -f
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


