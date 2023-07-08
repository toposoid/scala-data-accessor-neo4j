import Dependencies._

ThisBuild / scalaVersion     := "2.13.11"
ThisBuild / version          := "0.5-SNAPSHOT"
ThisBuild / organization     := "com.ideal.linked"

lazy val root = (project in file("."))
  .settings(
    name := "scala-data-accessor-neo4j",
    libraryDependencies += "org.neo4j.driver" % "neo4j-java-driver" % "4.2.5",
    libraryDependencies += "com.ideal.linked" %% "scala-common" % "0.5-SNAPSHOT",
    libraryDependencies += "org.scalacheck" %% "scalacheck" % "1.14.0" % Test,
      libraryDependencies += "org.scalatest" %% "scalatest-flatspec" % "3.2.11" % Test,
    libraryDependencies += scalaTest % Test
  )
  .enablePlugins(AutomateHeaderPlugin)

organizationName := "Linked Ideal LLC.[https://linked-ideal.com/]"
startYear := Some(2021)
licenses += ("Apache-2.0", new URL("https://www.apache.org/licenses/LICENSE-2.0.txt"))

