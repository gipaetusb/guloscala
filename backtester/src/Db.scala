package backtester

import slick.jdbc.PostgresProfile.api._
import scala.concurrent.ExecutionContext.Implicits.global
import com.typesafe.config.ConfigFactory
import java.io.File
import java.sql.Timestamp

abstract class BidAskTicks(tag: Tag, tableName: String, schemaName: Option[String])
  extends Table[(Timestamp, Double, Double, Int, Int, Boolean, Boolean, String)](
    tag, _tableName=tableName, _schemaName=schemaName
  ) {
  def time = column[Timestamp]("time")
  def bid = column[Double]("bid")
  def ask = column[Double]("ask")
  def bid_size = column[Int]("bid_size")
  def ask_size = column[Int]("ask_size")
  def bid_decrease = column[Boolean]("bid_decrease")
  def ask_increase = column[Boolean]("ask_increase")
  def pk = column[String]("pk", O.PrimaryKey)
  def * = (time, bid, ask, bid_size, ask_size, bid_decrease, ask_increase, pk)
}

abstract class TradeTicks(tag: Tag, tableName: String, schemaName: Option[String])
  extends Table[(Timestamp, Double, Int,/*String ,*/ String)](
    tag, _tableName=tableName, _schemaName=schemaName
  ) {
  def time = column[Timestamp]("time")
  def price = column[Double]("price")
  def size = column[Int]("size")
  def exchange = column[String]("exchange")
  def pk = column[String]("pk", O.PrimaryKey)
  def * = (time, price, size,/*exchange,*/ pk)
}

class BidAskTicksNQ20200918(tag: Tag)
  extends BidAskTicks(tag, "nq20200918_371749745_bid_ask", Some("ticks"))

class TradeTicksNQ20200918(tag: Tag)
  extends TradeTicks(tag, "nq20200918_371749745_trades", Some("ticks"))

class BidAskTicksGrpNQ20200918(tag: Tag)
  extends Table[(Timestamp, Double, Double)](
    tag, _tableName="nq20200918_371749745_bid_ask_grp", _schemaName=Some("ticks")
  ) {
  def time = column[Timestamp]("time")
  def bid = column[Double]("bid")
  def ask = column[Double]("ask")
  def * = (time, bid, ask)
}

object Db {
  val fileConfig = ConfigFactory.parseFile(new File("application.conf"))
  val config = ConfigFactory.load(fileConfig)
  
  val db = Database.forConfig(path="postgres", config=config)
} 
