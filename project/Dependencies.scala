import sbt._
import sbt.Keys._


object Dependencies {
  lazy val organization = "com.stratio"
  lazy val version      = "0.1.0-SNAPSHOT"
  lazy val projectName  = "stratio-cex"
  lazy val scalaVersion = "2.11.7"
  lazy val resolvers    = Seq.empty[Resolver]
  lazy val crossPaths  = false

  /* Scala */
  lazy val scalaTest: ModuleID              = "org.scalatest" %% "scalatest" % "3.0.1"
  lazy val scalaXML: ModuleID               = "org.scala-lang.modules" %% "scala-xml" % "1.0.4"

  /* SpringBoot stuff */
  lazy val springBoot: ModuleID             = "org.springframework.boot" % "spring-boot" % "1.5.4.RELEASE"
  lazy val springBootStarterWeb: ModuleID   = "org.springframework.boot" % "spring-boot-starter-web" % "1.5.4.RELEASE"
  /* SpringBoot test stuff */
  lazy val springBootStarterTest: ModuleID  = "org.springframework.boot" % "spring-boot-starter-test" % "1.5.4.RELEASE"
  lazy val jaywayJsonPath: ModuleID         = "com.jayway.jsonpath" % "json-path" % "2.4.0"

  /* Postgresql */
  lazy val postgresJDBC: ModuleID           = "org.postgresql" % "postgresql" % "42.1.3"

  def name(prefix: String, name: String): String = s"${prefix}-${name}"

}

trait Dependencies {
  def moduleSettings: Seq[SettingsDefinition] = Seq (

      organization in ThisBuild := Dependencies.organization,
      version      in ThisBuild := Dependencies.version,
      scalaVersion in ThisBuild := scalaVersionUsed,
      crossPaths   in ThisBuild := Dependencies.crossPaths,
      resolvers                 := commonResolvers,
      libraryDependencies       ++= mainDeps,
      libraryDependencies       ++= testDeps map (_ % "test")

  )

  val scalaVersionUsed = Dependencies.scalaVersion

  def commonResolvers = Dependencies.resolvers

  def mainDeps = Seq.empty[ModuleID]
  def testDeps = Seq.empty[ModuleID]

  def enabledPlugins = Seq.empty[AutoPlugin]
}