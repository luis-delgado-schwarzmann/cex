val mainName = "dock-cex"
def projectName(s: String) = s"${mainName}-${s}"

val commonSettings = Seq(
  organization := "com.stratio",
  version := "0.1.0-SNAPSHOT",
  scalaVersion := "2.11.11",
  crossPaths := false
)


lazy val environment = (project in file("environment"))
  .settings(
    name := projectName("environment"),
    commonSettings
  )