package backtester

import backtester.Db._
import slick.jdbc.PostgresProfile.api._
import java.sql.Timestamp
import scala.concurrent._, duration.Duration.Inf, java.util.concurrent.Executors

object BackTester extends App {
  println("You called a BackTester, Here I am")
  val future = Db run {
    TableQuery[BidAskTicksNQ20200918]
      .filter(_.time > Timestamp.valueOf("2020-09-18 15:29:52.0"))
      .result
  }
  Await.result(future, Inf).foreach(println(_))
}
