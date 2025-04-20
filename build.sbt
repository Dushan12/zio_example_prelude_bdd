ThisBuild / scalaVersion     := "3.6.4"
ThisBuild / version          := "0.1.0-SNAPSHOT"
ThisBuild / organization     := "com.ziobdd"
ThisBuild / organizationName := "ziobdd"

lazy val root = (project in file("."))
  .settings(
    name := "zio_bdd",
    libraryDependencies ++= Seq(
      "dev.zio" %% "zio" % "2.1.17",
      "io.github.etacassiopeia" %% "zio-bdd" % "0.1.0" % Test,
      "dev.zio" %% "zio-test" % "2.1.17" % Test,
      "dev.zio" %% "zio-test-sbt"       % "2.1.17" % Test
    ),
    Test / testFrameworks += new TestFramework("zio.bdd.ZIOBDDFramework")
  )
