object SpringBootSettings extends Dependencies {
  override val mainDeps = Seq(Dependencies.springBoot, Dependencies.springBootStarterWeb)
  override val testDeps = Seq(Dependencies.springBootStarterTest, Dependencies.jaywayJsonPath)
}