
name := "supermm2wiki"

version := "0.1"

organization := "com.goahead"

lazy val akkaHttpVersion = "10.1.9"
lazy val akkaVersion    = "2.6.0-M3"
lazy val circeVersion = "0.11.1"
lazy val slickVersion = "3.2.3"
lazy val slickJodaMapperVersion = "2.3.0"
val akkaCors   = "0.1.11"

libraryDependencies ++= {
  Seq(
    "com.typesafe.akka" %% "akka-http"            % akkaHttpVersion,
    "com.typesafe.akka" %% "akka-http-spray-json" % akkaHttpVersion,
    "com.typesafe.akka" %% "akka-http-xml"        % akkaHttpVersion,
    "com.typesafe.akka" %% "akka-stream"          % akkaVersion,
    "org.joda"          % "joda-convert"          % "1.7",
    "joda-time"         %  "joda-time"            % "2.7",
    "org.joda"          % "joda-money"            % "0.10.0",
    "ch.megard"         %% "akka-http-cors"       % akkaCors,
    "de.heikoseeberger" %% "akka-http-circe"      % "1.12.0",
    "com.zaxxer"        % "HikariCP"              % "2.7.0",
    "com.typesafe.slick"%% "slick"                % slickVersion,
    "com.github.tototoshi" %% "slick-joda-mapper" % slickJodaMapperVersion,
    "com.github.pureconfig" %% "pureconfig"       % "0.11.1",
    "org.flywaydb"      % "flyway-core"           % "4.2.0",
    "mysql" % "mysql-connector-java" % "8.0.16",

    "com.typesafe.akka" %% "akka-http-testkit"    % akkaHttpVersion % Test,
    "com.typesafe.akka" %% "akka-testkit"         % akkaVersion     % Test,
    "com.typesafe.akka" %% "akka-stream-testkit"  % akkaVersion     % Test,
    "org.scalatest"     %% "scalatest"            % "3.0.5"         % Test
  )
}
libraryDependencies ++= Seq(
  "io.circe" %% "circe-core",
  "io.circe" %% "circe-generic",
  "io.circe" %% "circe-parser"
).map(_ % circeVersion)

// Assembly settings missing needs to be filled
