package com.kuzdowicz.scala.examples.db

import scalikejdbc.SQLSyntaxSupport
import scalikejdbc.WrappedResultSet
import scalikejdbc._

case class User(id: Long, name: String, age: Int)

object User extends SQLSyntaxSupport[User] {
  override val tableName = "users"
  def apply(rs: WrappedResultSet) = new User(
    rs.long("id"),
    rs.string("name"),
    rs.int("age")
  )
}

object DBScalikeJDBCApp extends App {

  implicit val session = AutoSession

  val dbUrl = "jdbc:postgresql://127.0.0.1:5432/users_db"
  val dbUser = "master"
  val dbPassword = "pass"

  Class.forName("org.postgresql.Driver")
  ConnectionPool.singleton(dbUrl, dbUser, dbPassword)

  val users: List[User] =
    sql"select * from users".map(rs => User(rs)).list.apply()

  println(users)

}
