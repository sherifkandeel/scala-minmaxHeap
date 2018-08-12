name := "timeseries"

version := "0.1"

scalaVersion := "2.12.6"

libraryDependencies ++= Seq(
  "com.google.guava" % "guava"              % "24.0-jre",
  "org.specs2"       %% "specs2-core"       % "4.0.3" % "test",
  "org.specs2"       %% "specs2-scalacheck" % "4.0.3" % "test",
  "org.scalamock"    %% "scalamock"         % "4.1.0" % "test"
)
