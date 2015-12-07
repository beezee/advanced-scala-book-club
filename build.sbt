name := """advanced-scala-book-club"""

version := "1.0"

scalaVersion := "2.11.7"

resolvers ++= Seq(
  "scalaz-bintray" at "http://dl.bintray.com/scalaz/releases"
)

libraryDependencies ++= Seq(
  "org.scalatest" %% "scalatest" % "2.2.4" % "test",
  "org.joda" % "joda-convert" % "1.2",
  "joda-time" % "joda-time" % "latest.release",
  "org.scalaz" %% "scalaz-core" % "7.1.5"
)

scalacOptions ++= Seq(
  "-deprecation",
  "-encoding", "UTF-8", // yes, this is 2 args
  "-feature",
  "-unchecked",
  "-Xfatal-warnings",
  "-Xlint",
  "-Yno-adapted-args",
  // "-Ywarn-dead-code", // N.B. doesn't work well with the ??? hole
  "-Ywarn-numeric-widen",
  "-Ywarn-value-discard",
  "-Xfuture")

initialCommands in console := """
  import main._
  import org.joda.time.DateTime
"""
