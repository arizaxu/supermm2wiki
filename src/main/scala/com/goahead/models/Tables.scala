package com.goahead

import com.goahead.models.course.JdbcCourseStorage

import com.goahead.util.db.{DatabaseConnector, DatabaseMigrationManager}

import scala.concurrent.ExecutionContext

/**
  * @project supermm2wiki
  * @author GitHCorrado
  * @date 2019-07-19
  */
trait Tables {
  implicit def executionContext: ExecutionContext
  implicit def databaseConnector: DatabaseConnector
  lazy val CourseTables = new JdbcCourseStorage(databaseConnector)
}
