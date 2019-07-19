package com.goahead.models.course

/**
  * @project supermm2wiki
  * @author GitHCorrado
  * @date 2019-07-19
  */
final case class Course(
  course_name: String,
  course_difficulty: Int,
  is_trending: Option[Int]
)
