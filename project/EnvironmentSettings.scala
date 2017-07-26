import org.flywaydb.sbt.FlywayPlugin

object EnvironmentSettings extends Dependencies {
  override def enabledPlugins = super.enabledPlugins ++ Seq(FlywayPlugin)
}