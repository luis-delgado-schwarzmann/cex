

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



/*----------------*/
/* BUILDS SECTION */
/*----------------*/

lazy val environment = (project in file("environment"))
  .settings(
    name := projectName("environment"),
    commonSettings
  )

lazy val command_controller = (project in file("command-controller"))
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

