

val mainName = "dock-cex"
def projectName(s: String) = s"${mainName}-${s}"

val commonSettings = Seq(
  organization in ThisBuild := "com.stratio",
  version in ThisBuild := "0.1.0-SNAPSHOT",
  scalaVersion in ThisBuild := "2.11.11",
  crossPaths in ThisBuild := false
)

/*----------------------*/
/* DEPENDENCIES SECTION */
/*----------------------*/
lazy val springBoot: ModuleID  = "org.springframework.boot" % "spring-boot" % "1.5.4.RELEASE"
lazy val springBootStarterWeb: ModuleID  = "org.springframework.boot" % "spring-boot-starter-web" % "1.5.4.RELEASE"
lazy val springBootStarterTest: ModuleID  = "org.springframework.boot" % "spring-boot-starter-test" % "1.5.4.RELEASE" % "test"
lazy val jaywayJsonPath: ModuleID  = "com.jayway.jsonpath" % "json-path" % "2.4.0" % "test"
lazy val postgresJDBC: ModuleID = "org.postgresql" % "postgresql" % "42.1.3"


/*----------------*/
/* BUILDS SECTION */
/*----------------*/

lazy val root = (project in file("."))
  .disablePlugins(FlywayPlugin)

lazy val environment = (project in file("environment"))
  .settings(
    name := projectName("environment"),
    commonSettings,
    flywayUrl := "jdbc:postgresql://localhost:5432/",
    flywayUser := "postgres",
    flywayPassword := "",
    flywayLocations := Seq("classpath:db/migration"),
    libraryDependencies ++= Seq(
      postgresJDBC
    )
  )

lazy val command_controller = (project in file("command-controller"))
  .disablePlugins(FlywayPlugin)
  .settings(
    name := projectName("command-controller"),
    commonSettings,
    libraryDependencies ++= Seq(
      springBoot,
      springBootStarterWeb,
      springBootStarterTest,
      jaywayJsonPath
    )
  )

