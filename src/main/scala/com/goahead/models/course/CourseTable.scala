package com.goahead.models.course

import com.goahead.util.db.DatabaseConnector

/**
  *final case class Course(
  *course_name: String,
  *course_difficulty: Int
*)
 */
private[course] trait CourseTable {
  protected val databaseConnector: DatabaseConnector
  import databaseConnector.profile.api._
  class Coursetables(tag: Tag) extends Table[Course](tag, Some("supermm2_wiki"),"courses") {
    def coursename = column[String]("course_name", O.PrimaryKey)
    def coursedifficulty = column[Int]("course_difficulty")
    def istrending = column[Option[Int]]("is_trending")
    def * = (coursename, coursedifficulty, istrending) <> (Course.tupled, Course.unapply)
  }
  protected val courseTable = TableQuery[Coursetables]
}
