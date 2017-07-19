import org.flywaydb.sbt.FlywayPlugin

object Settings extends Dependencies {
  override val disabledPlugins = Seq(FlywayPlugin)
}

object SpringBootSettings extends Dependencies {
  override val mainDeps = Seq(Dependencies.springBoot, Dependencies.springBootStarterWeb)
  override val testDeps = Seq(Dependencies.springBootStarterTest, Dependencies.jaywayJsonPath)
  override val disabledPlugins = Settings.disabledPlugins
}


object PostgresSettings extends Dependencies {
  override val mainDeps = Seq(Dependencies.postgresJDBC)
}

object EnvironmentSettings extends Dependencies {
  override val enabledPlugins = Seq(FlywayPlugin)
}