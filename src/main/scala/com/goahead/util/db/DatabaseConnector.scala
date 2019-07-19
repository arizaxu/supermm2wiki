package com.goahead.util.db

import com.zaxxer.hikari.{ HikariConfig, HikariDataSource }
/**
  * @project supermm2wiki
  * @author GitHCorrado
  * @date 2019-07-19
  */

class DatabaseConnector(jdbcUrl: String, dbUser: String, dbPassword: String) {
  private val hikariDataSource = {
    val hikariConfig = new HikariConfig()
    hikariConfig.setJdbcUrl(jdbcUrl)
    hikariConfig.setUsername(dbUser)
    hikariConfig.setPassword(dbPassword)

    new HikariDataSource(hikariConfig)
  }
  val profile = slick.jdbc.MySQLProfile
  import profile.api._

  val db = Database.forDataSource(hikariDataSource, None)
  db.createSession()
}