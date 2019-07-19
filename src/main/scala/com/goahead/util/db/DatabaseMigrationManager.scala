package com.goahead.util.db

import org.flywaydb.core.Flyway
/**
  * @project supermm2wiki
  * @author GitHCorrado
  * @date 2019-07-19
  */

class DatabaseMigrationManager(jdbcUrl: String, dbUser: String, dbPassword: String) {

  private val flyway = new Flyway()
  flyway.setDataSource(jdbcUrl, dbUser, dbPassword)
  flyway.setBaselineOnMigrate(true)

  def migrateDatabaseSchema(): Unit = flyway.migrate()

  def dropDatabase(): Unit = flyway.clean()

}
