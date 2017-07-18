import sbt._

object FlywayConf {
  val conf = settingKey[Map[String, String]]("Flyway configuration")
}