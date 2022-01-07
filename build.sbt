import Dependencies._

ThisBuild / scalaVersion     := "2.12.12"
ThisBuild / version          := "0.2"
ThisBuild / organization     := "com.ideal.linked"

lazy val root = (project in file("."))
  .settings(
    name := "scala-data-accessor-neo4j",
    libraryDependencies += "org.neo4j.driver" % "neo4j-java-driver" % "4.2.5",
    libraryDependencies += "com.ideal.linked" %% "scala-common" % "0.1.0",
    libraryDependencies += "org.scalacheck" %% "scalacheck" % "1.13.5" % Test,
    libraryDependencies += scalaTest % Test
  )
  .enablePlugins(AutomateHeaderPlugin)

organizationName := "Linked Ideal LLC.[https://linked-ideal.com/]"
startYear := Some(2021)
licenses += ("Apache-2.0", new URL("https://www.apache.org/licenses/LICENSE-2.0.txt"))

