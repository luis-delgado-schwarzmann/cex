import com.typesafe.config.{Config, ConfigFactory}

/*----------------*/
/* BUILDS SECTION */
/*----------------*/

lazy val root = (project in file("."))
  .enablePlugins(Settings.enabledPlugins: _*)
  .settings(Settings.moduleSettings: _*)
  .settings(name := Dependencies.name(Dependencies.projectName, name.value))
  .aggregate(environment, `command-controller`)

lazy val environment = (project in file("environment"))
  .enablePlugins(EnvironmentSettings.enabledPlugins: _*)
  .settings(PostgresSettings.moduleSettings: _*)
  .settings(name := Dependencies.name(Dependencies.projectName, name.value))
  .settings(
    FlywaySettings.flywayConf := {
      val flywayFilePath: String = "db/flyway/flyway.json"
      val flywayConfFile: Config =  ConfigFactory.parseFile((resourceDirectory in Compile).value / flywayFilePath)
      val prefix = "flyway"
      FlywaySettings.parse(flywayConfFile, prefix, Seq("url", "user", "password"))
    },

    flywayUrl       := FlywaySettings.flywayConf.value("url"),
    flywayUser      := FlywaySettings.flywayConf.value("user"),
    flywayPassword  := FlywaySettings.flywayConf.value("password"),
    flywayLocations := Seq("classpath:db/migration")
  )

lazy val `common-libs` = (project in file("common-libs"))
  .settings(name := Dependencies.name(Dependencies.projectName, name.value))
  .settings(libraryDependencies += Dependencies.springBootConsul)

lazy val `command-controller` = (project in file("command-controller"))
  .dependsOn(`common-libs`)
  .enablePlugins(JavaAppPackaging)
  .settings(SpringBootSettings.moduleSettings: _*)
  .settings(name := Dependencies.name(Dependencies.projectName, name.value))
  .settings(
    DockerSettings.dockerConf := ConfigFactory.parseFile((resourceDirectory in Compile).value / "docker.json"),
    maintainer         in Docker := DockerSettings.dockerConf.value.getString("maintainer"),
    packageSummary     in Docker := DockerSettings.dockerConf.value.getString("summary"),
    packageDescription in Docker := DockerSettings.dockerConf.value.getString("description"),
    packageName        in Docker := DockerSettings.dockerConf.value.getString("name"),
    dockerUsername               := Some(DockerSettings.dockerConf.value.getString("username")),
    dockerRepository             := Some(DockerSettings.dockerConf.value.getString("repository"))
  )




lazy val `daas-appointment` = (project in file("daas-appointment"))
  .dependsOn(`common-libs`)
  .enablePlugins(JavaAppPackaging)
  .settings(SpringBootSettings.moduleSettings: _*)
  .settings(name := Dependencies.name(Dependencies.projectName, name.value))
  .settings(
    DockerSettings.dockerConf := ConfigFactory.parseFile((resourceDirectory in Compile).value / "docker.json"),
    maintainer         in Docker := DockerSettings.dockerConf.value.getString("maintainer"),
    packageSummary     in Docker := DockerSettings.dockerConf.value.getString("summary"),
    packageDescription in Docker := DockerSettings.dockerConf.value.getString("description"),
    packageName        in Docker := DockerSettings.dockerConf.value.getString("name"),
    dockerUsername               := Some(DockerSettings.dockerConf.value.getString("username")),
    dockerRepository             := Some(DockerSettings.dockerConf.value.getString("repository"))
  )
