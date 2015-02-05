import sbt._
import Keys._
import spray.revolver.RevolverPlugin._
import org.scalastyle.sbt.ScalastylePlugin
 
 
object Dependencies {
   val appDependencies = Seq(
      "org.specs2" %% "specs2" % "2.4.15" % "test"
   )
}
 
object BuildSettings {
 
   val buildOrganization = "johnmurray.io"
   val appName = "CHANGE_ME"
   val buildVersion = "0.0.1-SNAPSHOT"
   val buildScalaVersion = "2.11.4"
   val buildScalaOptions = Seq("-unchecked", "-deprecation", "-encoding", "utf8")
   val buildResolvers = Seq[sbt.Resolver](
    "Typesafe Repo"             at "http://repo.typesafe.com/typesafe/releases/",
    "Sonatype Snapshots"        at "http://oss.sonatype.org/content/repositories/snapshots",
    "Sonatype Releases"         at "http://oss.sonatype.org/content/repositories/releases",
    "Scalaz Bintray Repo" at "http://dl.bintray.com/scalaz/releases"
  )

   import Dependencies._
 
   val buildSettings = Defaults.defaultSettings ++ Seq(
      organization         := buildOrganization,
      version              := buildVersion,
      scalaVersion         := buildScalaVersion,
      resolvers            := buildResolvers,
      libraryDependencies ++= appDependencies,
      scalacOptions        := buildScalaOptions
   ) ++ Revolver.settings ++ ScalastylePlugin.Settings
}
 
object ApplicationBuild extends Build {
 
   import BuildSettings._
 
   lazy val main = Project(
      appName,
      file("."),
      settings = buildSettings)
}
