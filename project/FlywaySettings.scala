import com.typesafe.config.Config
import sbt._

object FlywaySettings {
  val flywayConf = settingKey[Map[String, String]]("Flyway configuration")

  def parse(file: Config, prefix: String, values: Seq[String]): Map[String, String] = {
    values.foldLeft(Map[String, String]())((acc, v) => acc ++ Map(v -> file.getString(s"${prefix}.${v}")))
  }
}
