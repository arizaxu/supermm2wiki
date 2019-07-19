package com.goahead.models.course

import com.goahead.util.db.DatabaseConnector

import scala.concurrent.{ExecutionContext, Future}
/**
  * @project supermm2wiki
  * @author GitHCorrado
  * @date 2019-07-19
  */
sealed trait CourseStorage {
  def getTrendingCourse(): Future[Seq[Course]]
}

final class JdbcCourseStorage(val databaseConnector: DatabaseConnector)(implicit executionContext: ExecutionContext) extends CourseTable with CourseStorage {
  import databaseConnector._
  import databaseConnector.profile.api._

  def getTrendingCourse(): Future[Seq[Course]] = {
    db.run(courseTable.result)
  }
}