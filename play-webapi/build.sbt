name := """play-webapi"""

version := "1.0-SNAPSHOT"

libraryDependencies ++= Seq(
  "org.bitbucket.b_c" % "jose4j" % "0.4.1"
)

scalaVersion := "2.11.6"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

libraryDependencies ++= Seq(
  jdbc,
  cache,
  ws
)
