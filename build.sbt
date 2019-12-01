import sbt.Keys.scalaVersion


val ScalatraVersion = "2.7.0-RC1"
val ScalaVersion = "2.13.0"

lazy val root = (project in file("."))
  .settings(
    organization := "com.leocesario",
    name := "scalatra-app",
    version := "1.0.0-SNAPSHOT",
    scalaVersion := ScalaVersion,
    mainClass in assembly := Some("com.leocesario.AppLauncher"),
    resolvers += Classpaths.typesafeReleases,
    resolvers ++= Seq(
      "Maven Central" at "http://central.maven.org/maven2/",
      "Big Bee Consultants" at "http://dl.bintray.com/rick-beton/maven/",
      "Sbt Plugins" at "http://repo.scala-sbt.org/scalasbt/sbt-plugin-releases",
      "Scalaz Bintray Repo" at "http://dl.bintray.com/scalaz/releases",
      "Typesafe repository releases" at "http://repo.typesafe.com/typesafe/releases/",
      "Sonatype repo" at "https://oss.sonatype.org/content/groups/scala-tools/",
      "Java.net Maven2 Repository" at "http://download.java.net/maven/2/",
      "Maven mail" at "https://mvnrepository.com/"
    ),
    //scalacOptions += "-Ypartial-unification",

    libraryDependencies ++= Seq(
      "org.scalatra"      %%  "scalatra"            % ScalatraVersion,
      "org.scalatra"      %%  "scalatra-json"       % ScalatraVersion,
      "org.scalatra"      %%  "scalatra-scalatest"  % ScalatraVersion % "test",
      "ch.qos.logback"    %   "logback-classic"     % "1.2.3" % "runtime",
      "javax.servlet"     %   "javax.servlet-api"   % "3.1.0" % "provided",
      "org.json4s"        %%  "json4s-jackson"      % "3.6.7",
      "org.eclipse.jetty" %   "jetty-webapp"        % "9.4.24.v20191120" % "container;compile"
    )
  )
  .enablePlugins(ScalatraPlugin)
  .enablePlugins(JettyPlugin)
  /*.enablePlugins(DockerPlugin)
    .settings(
      dockerfile in docker := {
        // The assembly task generates a fat JAR file
        val artifact: File = assembly.value
        val artifactTargetPath = s"/app/${artifact.name}"

        new Dockerfile {
          from("openjdk:8-jre")
          add(artifact, artifactTargetPath)
          entryPoint("java", "-jar", artifactTargetPath)
        }
      }
    )*/

//For debug....(remote)
/*
javaOptions ++= Seq(
  "-Xdebug",
  "-Xrunjdwp:transport=dt_socket,server=y,suspend=n,address=5005"
),
 */