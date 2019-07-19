/* package com.goahead.util

import pureconfig.loadConfig

/**
  * @project supermm2wiki
  * @author GitHCorrado
  * @date 2019-07-19
  */
case class Config(secretKey: String, http: HttpConfig, database: DatabaseConfig)

object Config {
  def load() =
    loadConfig[Config] match {
      case Right(config) => config
      case Left(error) =>
        throw new RuntimeException("Cannot read config file, errors:\n" + error.toList.mkString("\n"))
    }
}

private[util] case class HttpConfig(host: String, port: Int)
private[util] case class DatabaseConfig(jdbcUrl: String, username: String, password: String)

 */