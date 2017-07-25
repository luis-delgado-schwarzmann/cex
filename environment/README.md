# ENVIRONMENT MODULE

## Bringing it up!
[TBD] Explanation of how it's done
### Handling environment
[TBD] Explanation of script
#### Start
[TBD] Explanation of what it does
`$> ./handle-environment.sh start`
#### Stop
[TBD] Explanation of what it does
`$> ./handle-environment.sh stop`
#### Clean
[TBD] Explanation of what it does
`$> ./handle-environment.sh clean`

## Docker containers
[TBD] Contents
### Postgres (9.6.3)
### Flyway
[TBD] Explanation of Flyway
[TBD] Table of environment settings
#### Working with migrations
[TBD] Explanation of how to work with migration script (within the docker)
`flyway -configFile=$FLYWAY_CONF/$FLYWAY_FILENAME -locations=filesystem:$FLYWAY_MIGRATION <command>`
##### Info
[TBD] Explanation of how to work with migration script (within the docker)
`flyway -configFile=$FLYWAY_CONF/$FLYWAY_FILENAME -locations=filesystem:$FLYWAY_MIGRATION info`
##### Clean
[TBD] Explanation of how to work with migration script (within the docker)
`flyway -configFile=$FLYWAY_CONF/$FLYWAY_FILENAME -locations=filesystem:$FLYWAY_MIGRATION clean`
##### Migrate
[TBD] Explanation of how to work with migration script (within the docker)
`flyway -configFile=$FLYWAY_CONF/$FLYWAY_FILENAME -locations=filesystem:$FLYWAY_MIGRATION migrate`

### Command Controller
[TBD] Explanation of what it does

## Flyway from SBT
[TBD] Flyway from SBT (once docker's up)

## Dockerizing from SBT
[TBD] Explaining how to publish (locally or not) a docker image