#!/usr/bin/env bash

test_flyway() {
    FLYWAY_PATH="/usr/local/bin/flyway"
    if [ ! -e $FLYWAY_PATH ]; then
        echo "flyway found!!! [checking ${FLYWAY_PATH}]"
        exit 2
    fi
}

_run() {
    flyway -configFile=${FLYWAY_CONF}/${FLYWAY_FILENAME} -locations=filesystem:${FLYWAY_MIGRATION} $1
}


migrate() {
    _run migrate
}

info() {
    _run info
}

clean() {
    _run clean
}


####################
# Script execution #
####################

test_flyway

case "$1" in
    migrate)
        migrate
        ;;
    info)
        info
        ;;
    clean)
        clean
        ;;
    *)
        echo "Unkownn command ${1}. [migrate|info|clean]"
        exit 1
esac
