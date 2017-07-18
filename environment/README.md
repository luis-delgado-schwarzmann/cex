# ENVIRONMENT MODULE

## Bringing it up!
[TBD] Explanation of how it's done
### Building images
[TBD] Explanation of script
`$> ./build-images.sh`
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



## Docker container
[TBD] Contents of container
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

## Flyway from SBT
[TBD] Flyway from SBT (once docker's up)
