import com.typesafe.sbt.packager.archetypes.JavaAppPackaging
import org.flywaydb.sbt.FlywayPlugin

object Settings extends Dependencies




object PostgresSettings extends Dependencies {
  override val mainDeps = Seq(Dependencies.postgresJDBC)
}

object EnvironmentSettings extends Dependencies {
  override val enabledPlugins = Seq(FlywayPlugin)
}