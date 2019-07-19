package com.goahead.actors

import akka.actor.{Actor, ActorRef, ActorSystem, Props}
import akka.http.scaladsl.Http
import akka.http.scaladsl.server.Directives._
import akka.stream.Materializer
import akka.util.Timeout
import com.goahead.models.DataBase
import com.goahead.routes.course.Supermm2Routes

import com.goahead.util.db.{DatabaseConnector, DatabaseMigrationManager}

import scala.concurrent.duration._
import scala.concurrent.{Await, ExecutionContext}
import com.typesafe.config.{ Config, ConfigFactory }
/**
  * @project supermm2wiki
  * @author GitHCorrado
  * @date 2019-07-19
  */
final class RoutesActor private (m: Materializer)
  extends Actor with RequestTimeout {

  implicit val actorSystem: ActorSystem = context.system
  implicit val executor: ExecutionContext = actorSystem.dispatcher
  implicit val materialiser: Materializer = m

  val config = ConfigFactory.load()
  new DatabaseMigrationManager(
    config.getString("database.jdbc-url"),
    config.getString("database.user"),
    config.getString("database.password")
  ).migrateDatabaseSchema()
  val databaseConnector = new DatabaseConnector(
    config.getString("database.jdbc-url"),
    config.getString("database.user"),
    config.getString("database.password"))
  val db = new DataBase(databaseConnector, executor)
  val api = new Supermm2Routes(actorSystem, requestTimeout(config), db)
  val bindingFuture = Http().bindAndHandle(api.routes, config.getString("http.interface"), config.getInt("http.port"))

  override def receive: Receive = { case _ => }

  override def postStop(): Unit = {
    Await.result(bindingFuture.flatMap(_.unbind()), Duration.Inf)
  }
}

object RoutesActor {

  def props(materialiser: Materializer): Props =
    Props(new RoutesActor(materialiser))
}

trait RequestTimeout {
  import scala.concurrent.duration._
  def requestTimeout(config: Config): Timeout = {
    val t = config.getString("akka.http.server.request-timeout")
    val d = Duration(t)
    FiniteDuration(d.length, d.unit)
  }
}