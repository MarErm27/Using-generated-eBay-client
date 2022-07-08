ThisBuild / scalaVersion := "2.13.8"

ThisBuild / version := "1.0-SNAPSHOT"

lazy val root = (project in file("."))
  .enablePlugins(PlayScala)
  .settings(
    name := """myPlayProject""",
    resolvers:= Seq("Reposilite" at "http://127.0.0.1:8181/releases/"),
    libraryDependencies ++= Seq(
      guice,
      "org.scalatestplus.play" %% "scalatestplus-play" % "5.1.0" % Test,
      "ebay" % "client_2.13" % "0.1.0-SNAPSHOT"
    )
  )