package com.kuzdowicz.scala.examples

import java.sql.{Connection, DriverManager}

import scala.collection.mutable.ListBuffer

case class User(id: Long, name: String, age: Int)

object DBApp extends App {

  Class.forName("org.postgresql.Driver")

  val dbUrl = "jdbc:postgresql://127.0.0.1:5432/users_db"
  val dbUser = "master"
  val dbPassword = "pass"
  val users = ListBuffer.empty[User]
  var connection: Connection = _

  try {

    connection = DriverManager.getConnection(dbUrl, dbUser, dbPassword)
    val stm = connection.createStatement()
    val rs = stm.executeQuery("SELECT * FROM users")

    while (rs.next()) {
      val id = rs.getLong(1)
      val name= rs.getString(2)
      val age = rs.getInt(3)
      users += User(id, name, age)
    }

    users.foreach(println(_))

  } catch {
    case e: Exception => e.printStackTrace()
  } finally {
    if (connection != null) connection.close()
  }


}
