package utils.db

import slick.jdbc.PostgresProfile.api._
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Await
import scala.concurrent.duration._

object Db extends App {
  class BidAskTicks(tag: Tag)
    extends Table[(String, Double, Double, Int, Int, Boolean, Boolean, String)](
    tag, _tableName="test", _schemaName=Some("ticks")
  ) {
    def time = column[String]("time")
    def bid = column[Double]("bid")
    def ask = column[Double]("ask")
    def bid_size = column[Int]("bid_size")
    def ask_size = column[Int]("ask_size")
    def bid_decrease = column[Boolean]("bid_decrease")
    def ask_increase = column[Boolean]("ask_increase")
    def pk = column[String]("pk", O.PrimaryKey)
    def * = (time, bid, ask, bid_size, ask_size, bid_decrease, ask_increase, pk)
  }
  val bid_ask_ticks = TableQuery[BidAskTicks]

  val db = Database.forConfig("postgres")
  println("Hello world! This is slick 4 postgres")
  Await.result(
    awaitable=db.run(bid_ask_ticks.result).map(_.foreach{
      case (time, bid, ask, bid_size, ask_size, bid_decrease, ask_increase, pk) =>
        println(" " + time)
    }),
    atMost=2 seconds
  )

  /*val filtered = for {
    t <- bid_ask_ticks if t.time < "2019-06-05"
  } yield (t.bid)

  Await.result(
    awaitable=filtered,
    atMost=2 seconds
  )
  */
}

