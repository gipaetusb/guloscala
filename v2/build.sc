import mill._, scalalib._

object backtester extends ScalaModule {
  def scalaVersion = "2.13.2"
  def ivyDeps = Agg(
    ivy"io.getquill::quill-jdbc:3.5.2",
    ivy"org.postgresql:postgresql:42.2.8",
    ivy"com.opentable.components:otj-pg-embedded:0.13.1",
    ivy"com.lihaoyi::scalatags:0.9.1",
    ivy"com.lihaoyi::cask:0.7.4",
    ivy"joda-time:joda-time:2.10.8",
    ivy"com.typesafe.slick::slick:3.3.2",
    ivy"org.slf4j:slf4j-nop:1.6.4",
    ivy"com.typesafe.slick::slick-hikaricp:3.3.2",
    ivy"org.postgresql:postgresql:9.4-1206-jdbc42"
  )
  object test extends Tests {
    def testFrameworks = Seq("utest.runner.Framework")
    def ivyDeps = Agg(ivy"com.lihaoyi::utest:0.7.4", ivy"com.lihaoyi::requests:0.6.5") 
  }
}
