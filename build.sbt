import com.typesafe.config.{Config, ConfigFactory}
import scala.collection.JavaConversions._

/*----------------*/
/* BUILDS SECTION */
/*----------------*/

lazy val root = (project in file("."))
  .enablePlugins(Settings.enabledPlugins: _*)
  .settings(Settings.moduleSettings: _*)
  .settings(name := Dependencies.name(Dependencies.projectName, name.value))
  .aggregate(environment, command_controller)

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

lazy val command_controller = (project in file("command-controller"))
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
    dockerRepository             := Some(DockerSettings.dockerConf.value.getString("repository")),
    dockerExposedPorts           := DockerSettings.dockerConf.value.getIntList("ports").map(_.toInt)
  )



lazy val daas_appointments = (project in file("daas-appointments"))
  .enablePlugins(JavaAppPackaging)
  .settings(SpringBootSettings.moduleSettings: _*)
  .settings(name := Dependencies.name(Dependencies.projectName, name.value))
