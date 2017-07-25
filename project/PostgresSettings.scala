object PostgresSettings extends Dependencies {
  override def mainDeps = super.mainDeps ++ Seq(Dependencies.postgresJDBC)
}
