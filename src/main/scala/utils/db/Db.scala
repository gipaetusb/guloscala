package utils.db

import slick.jdbc.PostgresProfile.api._
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Await
import scala.concurrent.duration._


class Db(path: String = "postgres"){

  val db = Database.forConfig(path)
  println("Hello world! This is slick")

  def selectBidAskTicks() {
    val bid_ask_ticks = TableQuery[BidAskTicks]
    db.run(bid_ask_ticks.result)
  }
  
    def printBidAskTicks() {
    val bid_ask_ticks = TableQuery[BidAskTicks]
    Await.result(
      awaitable=db.run(bid_ask_ticks.result).map(_.foreach{
        case (time, bid, ask, bid_size, ask_size, bid_decrease, ask_increase, pk) =>
          println(time + " " + bid + " " + ask + " " + bid_size + " " + ask_size + " " + bid_decrease + " " + ask_increase + " " + pk)
      }),
      atMost=5 seconds
    )
  }

}

/*val filtered = for {
  t <- bid_ask_ticks if t.time < "2019-06-05"
} yield (t.bid)

Await.result(
  awaitable=filtered,
  atMost=2 seconds
)

