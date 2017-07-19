import com.typesafe.config.{Config, ConfigFactory}

/*----------------*/
/* BUILDS SECTION */
/*----------------*/

lazy val root = (project in file("."))
  .disablePlugins(Settings.disabledPlugins: _*)
  .settings(Settings.moduleSettings: _*)
  .settings(name := Dependencies.name(Dependencies.projectName, name.value))
  .aggregate(environment, command_controller)

lazy val environment = (project in file("environment"))
  .enablePlugins(EnvironmentSettings.enabledPlugins: _*)
  .settings(PostgresSettings.moduleSettings: _*)
  .settings(name := Dependencies.name(Dependencies.projectName, name.value))
  .settings(
    FlywayConf.conf := {
      val flywayFilePath: String = "db/flyway/flyway.properties"
      val flywayConfFile: Config =  ConfigFactory.parseFile((resourceDirectory in Compile).value / flywayFilePath)
      val prefix = "flyway"
      FlywayConf.parse(flywayConfFile, prefix, Seq("url", "user", "password"))
    },

    flywayUrl       := FlywayConf.conf.value("url"),
    flywayUser      := FlywayConf.conf.value("user"),
    flywayPassword  := FlywayConf.conf.value("password"),
    flywayLocations := Seq("classpath:db/migration")
  )

lazy val command_controller = (project in file("command-controller"))
  .disablePlugins(Settings.disabledPlugins: _*)
  .settings(SpringBootSettings.moduleSettings: _*)
  .settings(name in ThisScope := Dependencies.name(Dependencies.projectName, name.value))

