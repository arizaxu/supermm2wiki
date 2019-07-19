package com.goahead

import akka.actor.ActorSystem
import com.goahead.actors.{ MainActor, RoutesActor }
/**
  * @project supermm2wiki
  * @author GitHCorrado
  * @date 2019-07-19
  */
object Boot extends App{
  implicit val system = ActorSystem("SUPERMM2WIKI")

  system.actorOf(MainActor.props(RoutesActor.props _))

}
