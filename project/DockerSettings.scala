import com.typesafe.config.Config
import com.typesafe.sbt.packager.archetypes.JavaAppPackaging
import sbt._

object DockerSettings extends Dependencies {
  val dockerConf = settingKey[Config]("Docker configuration from file")
  override val enabledPlugins  = Seq(JavaAppPackaging)
}