package com.goahead.routes.course

import akka.actor.{ActorRef, ActorSystem}
import com.goahead.models.DataBase
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import akka.http.scaladsl.server.directives.MethodDirectives.{get, post}
import akka.http.scaladsl.server.directives.PathDirectives.path
import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.server.directives.RouteDirectives.complete
import akka.util.Timeout
import akka.pattern.ask
import com.goahead.actors.Supermm2Actor
import de.heikoseeberger.akkahttpcirce.CirceSupport._
import io.circe.{Decoder, Encoder, Json}
import io.circe.syntax._
import com.goahead.actors.Supermm2Actor._
import com.goahead.models.course.{Course, Courses}

import scala.concurrent.ExecutionContext

/**
  * @project supermm2wiki
  * @author GitHCorrado
  * @date 2019-07-19
  */
class Supermm2Routes(system: ActorSystem, timeout: Timeout, db: DataBase)
      extends Supermm2Route {
  implicit val requestTimeout = timeout
  def createSupermm2Actor = system.actorOf(Supermm2Actor.props(db), "SuperMM2Actor")
}

trait Supermm2Route extends Supermm2api {
  import StatusCodes._
  import io.circe.generic.auto._
  def routes: Route =
    pathPrefix("wiki" / "supermm2" / "trendingtop10") {
      pathEndOrSingleSlash {
        get {
          onSuccess(getTrendingTop10Courses()) { courses =>
            complete(courses)
          }
        }
      }
    } ~
    pathPrefix("wiki") {
      pathEndOrSingleSlash {
        get {
          complete(OK)
        }
      }
    }
}

trait Supermm2api {
  import com.goahead.actors.Supermm2Actor._

  def createSupermm2Actor(): ActorRef
  //implicit def executionContext: ExecutionContext
  implicit def requestTimeout: Timeout

  lazy val supermm2actor = createSupermm2Actor()
  def getTrendingTop10Courses() =
    supermm2actor.ask(GetTrendingCourses).mapTo[Courses]
}
