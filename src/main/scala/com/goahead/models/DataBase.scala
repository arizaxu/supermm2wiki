package com.goahead.models

import com.goahead.Tables
import com.goahead.util.db.DatabaseConnector

import scala.concurrent.ExecutionContext

/**
  * @project supermm2wiki
  * @author GitHCorrado
  * @date 2019-07-19
  */
final class DataBase(val databaseConnector: DatabaseConnector, val executionContext: ExecutionContext) extends Tables {

}
