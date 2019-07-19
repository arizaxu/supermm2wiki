package com.goahead.actors

import akka.actor.{Actor, ActorSystem, Props}
import akka.pattern.{ BackoffOpts, BackoffSupervisor }
import akka.stream.{ActorMaterializer, Materializer}

import scala.concurrent.duration._
/**
  * @project supermm2wiki
  * @author GitHCorrado
  * @date 2019-07-19
  */
object MainActor {

  def props(worker: Materializer => Props): Props =
    Props(new MainActor(worker))
}

class MainActor private (build: Materializer => Props) extends Actor {

  override val receive: Receive = { case _ => }

  implicit val a: ActorSystem = context.system
  implicit val materialiser = ActorMaterializer()

  val workerProps: Props = build(materialiser)

  val supervisorProps: Props = BackoffSupervisor.props(
    BackoffOpts.onFailure(
      workerProps,
      "RoutesActor",
      3.seconds,
      30.seconds,
      0.2))
  context.actorOf(supervisorProps)

}

