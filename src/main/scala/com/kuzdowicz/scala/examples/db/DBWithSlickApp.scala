package com.kuzdowicz.scala.examples.db

import slick.driver.PostgresDriver.api._

import scala.concurrent.Await
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.Duration

class Users(tag: Tag) extends Table[User](tag, "users") {
  def id = column[Long]("id")

  def name = column[String]("name")

  def age = column[Int]("age")

  def * = (id, name, age) <> (User.tupled, User.unapply)
}

object DBWithSlickApp extends App {

  val query = TableQuery[Users]
  val dbUrl = "jdbc:postgresql://127.0.0.1:5432/users_db"
  val dbUser = "master"
  val dbPassword = "pass"
  val db = Database.forURL(dbUrl, dbUser, dbPassword)
  // from .conf file
  //  val db = Database.forConfig("pg-postgres")
  try {
    Await.result(db.run(DBIO.seq(
      query.result.map(println)
    )), Duration.Inf)
  } finally db.close

}
