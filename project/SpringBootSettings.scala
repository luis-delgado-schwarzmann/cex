object SpringBootSettings extends BaseSettings {
  override def mainDeps = super.mainDeps ++ Seq(
    Dependencies.springBoot,
    Dependencies.springBootStarterWeb,
    Dependencies.springBootConsul
  )
  override def testDeps = super.testDeps ++ Seq(Dependencies.springBootStarterTest, Dependencies.jaywayJsonPath)
}