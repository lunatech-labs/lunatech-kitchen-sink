name := """lunatech-kitchensink"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.8"

libraryDependencies ++= Seq(
  ws,
  "com.lunatech" %% "play-googleopenconnect" % "1.3",
  "com.adrianhurt" %% "play-bootstrap" % "1.1-P25-B3"
)

