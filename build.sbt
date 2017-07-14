val mainName = "dock-cex"
def projectName(s: String) = s"${mainName}-${s}"

val commonSettings = Seq(
  organization := "com.stratio",
  version := "0.1.0-SNAPSHOT",
  scalaVersion := "2.11.11",
  crossPaths := false
)

/*----------------------*/
/* DEPENDENCIES SECTION */
/*----------------------*/
lazy val springBoot: ModuleID  = "org.springframework.boot" % "spring-boot" % "1.5.4.RELEASE"


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
    libraryDependencies += springBoot
  )

