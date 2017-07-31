#!/usr/bin/env bash

DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"
SCRIPT_NAME="$(basename ${0})"

if [ -z "$COMPOSE_INTERNAL_FOLDER" ]; then COMPOSE_INTERNAL_FOLDER="${DIR}/docker/compose"; fi
if [ -z "$ENVIRONMENT_BASE" ]; then ENVIRONMENT_BASE=("postgresql"); fi
if [ -z "$ENVIRONMENT_MS_BASE" ]; then ENVIRONMENT_MS_BASE=("${ENVIRONMENT_BASE[@]}" "docker-registry"); fi
if [ -z "$ENVIRONMENT_COMMAND_CONTROLLER" ]; then ENVIRONMENT_COMMAND_CONTROLLER=("${ENVIRONMENT_MS_BASE[@]}" "command-controller"); fi
if [ -z "$ENVIRONMENT_DAAS_APPOINTMENT" ]; then ENVIRONMENT_DAAS_APPOINTMENT=("${ENVIRONMENT_MS_BASE[@]}" "daas-appointment"); fi

show_usage() {
    cat << EndOfUsage
SYNOPSIS
    ${SCRIPT_NAME} <command> <type> <name>

DESCRIPTION
    Handles the environment based on docker and docker-compose.

OPTIONS
    <command>     start | stop | restart | clean
                  Note: clean does not accept type or name.

    <type>        container | environment


    <name>        Name of the container or environment.
                  Known environments (docker-compose without .yml extension
                                      under \$COMPOSE_INTERNAL_FOLDER [$COMPOSE_INTERNAL_FOLDER]):
                      - base               -> defined on \$ENVIRONMENT_BASE
                                              (default: ${ENVIRONMENT_BASE[@]}).
                      - ms-base            -> defined on \$ENVIRONMENT_MS_BASE
                                              (default: ${ENVIRONMENT_MS_BASE[@]}).
                      - command-controller -> defined on \$ENVIRONMENT_COMMAND_CONTROLLER
                                              (default: ${ENVIRONMENT_COMMAND_CONTROLLER[@]}).
                      - daas-appointment   -> defined on \$ENVIRONMENT_DAAS_APPOINTMENT
                                              (default: ${ENVIRONMENT_DAAS_APPOINTMENT[@]}).
                  Note: In order to change an environment on runtime, overwrite the associated variable.

IMPORTANT
    No error will be shown after a command if an environment does not exists. It is simply not found on the folder
    and doing nothing.

EXAMPLES
    Cleaning the whole environment:
    - ${SCRIPT_NAME} clean

    Starting a single container (already created on docker):
    - ${SCRIPT_NAME} start container command-controller

    Stopping an environment:
    - ${SCRIPT_NAME} stop environment ms-base

    Overwriting an environment:
    - ENVIRONMENT_BASE=("several" "files") ${SCRIPT_NAME} start environment base
EndOfUsage
}

test_docker() {
  #   --------------------------------------------------------------
  #   Checks whether docker-compose command is installed
  #     Accepts no arguments
  #   ----------------------------------------------------------------
   if [ ! -e /usr/bin/docker ] && [ ! -e /usr/local/bin/docker ]; then
        echo "docker not found!!! [checking /usr/bin/docker, /usr/local/bin/docker]"
        exit 1
   fi

   if [ ! -e /usr/bin/docker-compose ] && [ ! -e /usr/local/bin/docker-compose ]; then
        echo "docker-composed not found!!! [checking /usr/bin/docker-compose, /usr/local/bin/docker-compose]"
        exit 1
    fi
}

expand_environment() {
    local EXPANDED_ENVIRONMENT=()
    for i in $@; do
        EXPANDED_ENVIRONMENT+=("$COMPOSE_INTERNAL_FOLDER/$i.yml")
    done
    echo `expand_yml_files "${EXPANDED_ENVIRONMENT[*]}"`
}

expand_yml_files() {
    local EXPANDED_ENVIRONMENT=()
    for i in $@; do
        EXPANDED_ENVIRONMENT+=("-f $i")
    done
    echo "${EXPANDED_ENVIRONMENT[*]}"
}

clean() {
    search_for_yml_files() {
        #   ----------------------------------------------------------------
        #   Look for all .yml files within $COMPOSE_INTERNAL_FOLDER
        #     Accepts no arguments
        #   ----------------------------------------------------------------
        for f in "$COMPOSE_INTERNAL_FOLDER"/*".yml"; do
            YML_FILES+=("$f")
        done
        echo "${YML_FILES[@]}"
    }
    docker-compose $( expand_yml_files `search_for_yml_files` ) kill
    docker-compose $( expand_yml_files `search_for_yml_files` ) rm -f
}

start_container() {
    docker start "$1"
}

start_environment() {
    docker-compose "$@" up -d --build
}

stop_container() {
    docker stop "$1"
}

stop_environment() {
    docker-compose "$@" stop
}

restart_container() {
    stop_container $1
    start_container $1
}

restart_environment() {
    stop_environment $1
    start_environment $1
}

run() {
    case "$2" in
        container)
            case "$3" in
                postgresql|docker-registry|command-controller)
                    $1_container $3
                ;;
                *)
                    show_usage
                    exit 1
                    ;;
            esac
            ;;
        environment)
            case "$3" in
                base)
                    $1_environment `expand_environment ${ENVIRONMENT_BASE[@]}`
                ;;
                ms-base)
                    $1_environment `expand_environment ${ENVIRONMENT_MS_BASE[@]}`
                ;;
                command-controller)
                    $1_environment `expand_environment ${ENVIRONMENT_COMMAND_CONTROLLER[@]}`
                ;;
                daas-appointment)
                    $1_environment `expand_environment ${ENVIRONMENT_DAAS_APPOINTMENT[@]}`
                ;;
            esac
            ;;
        *)
            show_usage
            exit 1
            ;;
    esac
}


##################
# Execution area #
##################

test_docker

if [ $# -gt 3 ]; then
    show_usage
    exit 1
fi

case "$1" in
    start|stop|restart)
        COMMAND=$1
        ;;
     clean)
        clean
        exit 0
        ;;
    *)
        show_usage
        exit 1
        ;;
esac
shift

case "$1" in
    container|environment)
        OPTION=$1
        ;;
    *)
        show_usage
        exit 1
        ;;
esac
shift

case "$COMMAND" in
    start)
        run "start" $OPTION $1
        ;;

    stop)
        run "stop" $OPTION $1
        ;;
     *)
        show_usage
        exit 1
        ;;
esac

exit 0