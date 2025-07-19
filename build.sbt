import Dependencies._
import de.heikoseeberger.sbtheader.License

ThisBuild / scalaVersion     := "2.13.11"
ThisBuild / version          := "0.6"
ThisBuild / organization     := "com.ideal.linked"

lazy val root = (project in file("."))
  .settings(
    name := "scala-data-accessor-neo4j",
    libraryDependencies += "org.neo4j.driver" % "neo4j-java-driver" % "4.4.10",
    libraryDependencies += "com.ideal.linked" %% "scala-common" % "0.6",
    libraryDependencies += "org.scalacheck" %% "scalacheck" % "1.17.0" % Test,
    libraryDependencies += "org.scalatest" %% "scalatest-flatspec" % "3.2.16" % Test,
    libraryDependencies += scalaTest % Test
  )
  .enablePlugins(AutomateHeaderPlugin)

organizationName := "Linked Ideal LLC.[https://linked-ideal.com/]"
startYear := Some(2021)
licenses += ("AGPL-3.0-or-later", new URL("http://www.gnu.org/licenses/agpl-3.0.en.html"))
headerLicense := Some(License.AGPLv3("2025", organizationName.value))
