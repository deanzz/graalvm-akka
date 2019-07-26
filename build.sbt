enablePlugins(GraalVMNativeImagePlugin)
//enablePlugins(UniversalPlugin)

name := "graalvm-akka"

version := "0.1"

scalaVersion := "2.12.8"

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-actor" % "2.5.21"/*,
  "org.graalvm.truffle" % "truffle-api" % "19.1.1",
  "com.oracle.truffle" % "truffle-dsl-processor" % "1.0.0-rc7",*/
)

graalVMNativeImageOptions ++= Seq(
  "--report-unsupported-elements-at-runtime",
  "-H:+ReportExceptionStackTraces",
  "--verbose",
  "--initialize-at-build-time",
  "--no-fallback",
)

mainClass in Compile := Some("graalvm.akka.Main")
mainClass in assembly := Some("graalvm.akka.Main")
assemblyJarName in assembly := "graalvm-akka.jar"