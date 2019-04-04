val Http4sVersion = "0.18.21"
val Specs2Version = "4.3.6"
val LogbackVersion = "1.2.3"
val CirisVersion = "0.12.1"

enablePlugins(JavaAppPackaging)

lazy val root = (project in file("."))
  .settings(
    organization := "se.brainiac",
    name := "e-learn",
    version := "0.0.1-SNAPSHOT",
    scalaVersion := "2.12.8",
    scalacOptions ++= Seq(
      "-Ypartial-unification",
      "-feature",
      "-language:higherKinds"
    ),
    libraryDependencies ++= Seq(
      "org.http4s"      %% "http4s-blaze-server"  % Http4sVersion,
      "org.http4s"      %% "http4s-circe"         % Http4sVersion,
      "org.http4s"      %% "http4s-dsl"           % Http4sVersion,
      "ch.qos.logback"  %  "logback-classic"      % LogbackVersion,
      "is.cir"          %% "ciris-core"           % CirisVersion,
      "org.specs2"      %% "specs2-core"          % Specs2Version % "test"
    ),
    addCompilerPlugin("org.spire-math" %% "kind-projector"     % "0.9.9"),
    addCompilerPlugin("com.olegpy"     %% "better-monadic-for" % "0.2.4"),
  )

