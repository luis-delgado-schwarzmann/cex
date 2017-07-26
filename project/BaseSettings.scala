trait BaseSettings extends Dependencies {
  override def testDeps = super.testDeps ++ Seq(Dependencies.scalaTest, Dependencies.scalaXML)
}

object Settings extends BaseSettings
