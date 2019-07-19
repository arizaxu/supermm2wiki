package com.goahead
package actors

import akka.actor.{Actor, Props}
import com.goahead.models.DataBase
import com.goahead.models.course.Course

import scala.concurrent.Future

/**
  * @project supermm2wiki
  * @author GitHCorrado
  * @date 2019-07-19
  */
object Supermm2Actor {
  final case object GetTrendingCourses
  def props(db: DataBase): Props = Props(new Supermm2Actor(db))
}
final class Supermm2Actor(database: DataBase) extends Actor {
  import Supermm2Actor._

  def receive: Receive = {
    case GetTrendingCourses => {
      println("Supermm2Actor created!")
      getTrendingTop10()
    }
  }
  def getTrendingTop10(): Future[Seq[Course]] =
    database.CourseTables.getTrendingCourse()

}
