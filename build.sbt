ThisBuild / scalaVersion     := "3.6.4"
ThisBuild / version          := "0.1.0-SNAPSHOT"
ThisBuild / organization     := "com.ziobdd"
ThisBuild / organizationName := "ziobdd"

lazy val root = (project in file("."))
  .settings(
    name := "zio_example_prelude_bdd",
    libraryDependencies ++= Seq(
      "dev.zio" %% "zio" % "2.1.17",
      "io.github.etacassiopeia" %% "zio-bdd" % "0.1.0" % Test,
      "dev.zio" %% "zio-test" % "2.1.17" % Test,
      "dev.zio" %% "zio-prelude" % "1.0.0-RC40",
      "dev.zio" %% "zio-test-sbt"       % "2.1.17" % Test,
        "eu.timepit" %% "refined"                 % "0.11.3",
        "eu.timepit" %% "refined-cats"            % "0.11.3", // optional
        "eu.timepit" %% "refined-jsonpath"        % "0.11.3", // optional, JVM-only
        "eu.timepit" %% "refined-pureconfig"      % "0.11.3", // optional, JVM-only
        "eu.timepit" %% "refined-scalacheck"      % "0.11.3", // optional
        "eu.timepit" %% "refined-scopt"           % "0.11.3", // optional
    ),
    Test / testFrameworks += new TestFramework("zio.bdd.ZIOBDDFramework")
  )
