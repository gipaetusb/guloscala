package backtester

import backtester.Db._
import slick.jdbc.PostgresProfile.api._
import java.sql.Timestamp
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent._, duration.Duration.Inf, java.util.concurrent.Executors
import scala.util.{Success, Failure}

object BackTester extends App {
  println("You called a BackTester, Here I am")

  val tradeTicks = TableQuery[TradeTicksNQ20200918]
  val bidaskTicks = TableQuery[BidAskTicksNQ20200918]
  val bidaskTicksGrp = TableQuery[BidAskTicksGrpNQ20200918]
  
  val largeTradeTicks = tradeTicks.filter(tick => tick.size > 100)
  // calling .result transforms the query into an action, which I can transform into a future using db.run
  db run largeTradeTicks.result onComplete {
    case Success(values) => println(values)
    case Failure(e) => println(e)
  }

  trait TickTrai
  case class Tick(time: java.sql.Timestamp, price: Double, size: Int, bid: Double, ask: Double) extends TickTrai
  
  val largeTrades = for {
    (trade, bidAsk) <- largeTradeTicks join bidaskTicksGrp on (_.time === _.time)
  } yield (trade.time, trade.price, trade.size, bidAsk.bid, bidAsk.ask)
  val f = db run largeTrades.result
  f.filter(t => 
    t match {
      case Seq(_, price, _, _, _) => price > 0
      case _ => false
      //case (time, price, size, bid, ask): Seq(, Double, Int, Double, Double) => price <= bid || price >= ask})
    }
  ) 

  //largeTrades.filter(t => t match {case Some(size) => size > 1 case None => false})
  
}
// NOTE: tick => tick.time > Timestamp.valueOf("2020-09-18 15:29:52.0")
