name := "guloscala"

version := "0.1"

scalaVersion := "2.12.2"

libraryDependencies ++= Seq (
  "joda-time" % "joda-time" % "2.10.8",
  "com.typesafe.slick" %% "slick" % "3.3.0",
  "org.slf4j" % "slf4j-nop" % "1.6.4",
  "com.typesafe.slick" %% "slick-hikaricp" % "3.3.0",
  "org.postgresql" % "postgresql" % "9.4-1206-jdbc42",
)
