name := "scala-playground"

version := "0.1"

scalaVersion := "2.12.6"

libraryDependencies ++= Seq(
  "com.typesafe.slick" %% "slick" % "3.2.3",
  "com.typesafe.slick" %% "slick-hikaricp" % "3.2.3",
  "org.postgresql" % "postgresql" % "42.2.4",
  "org.scalikejdbc" %% "scalikejdbc" % "3.3.0",
  "com.h2database" % "h2" % "1.4.197"
)