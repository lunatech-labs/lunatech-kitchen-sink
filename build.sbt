name := """lunatech-kitchensink"""

version := "1.1"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.8"

libraryDependencies ++= Seq(
  ws,
  "com.lunatech" %% "play-googleopenconnect" % "2.0",
  "com.adrianhurt" %% "play-bootstrap" % "1.1-P25-B3"
)
