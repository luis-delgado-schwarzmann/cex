logLevel := Level.Warn

// Native Packager
addSbtPlugin("com.typesafe.sbt" % "sbt-native-packager" % "1.2.0")

// Flyway stuff
addSbtPlugin("org.flywaydb" % "flyway-sbt" % "4.2.0")
resolvers += "Flyway" at "https://flywaydb.org/repo"

