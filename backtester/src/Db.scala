package backtester

import slick.jdbc.PostgresProfile.api._
import scala.concurrent._, duration.Duration.Inf, java.util.concurrent.Executors
import com.typesafe.config.ConfigFactory
import java.io._
import java.sql.Timestamp

class BidAskTicks(tag: Tag)
  extends Table[(Timestamp, Double, Double, Int, Int, Boolean, Boolean, String)](
    tag, _tableName="test", _schemaName=Some("ticks")
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
  
object Db {
  implicit val ec = ExecutionContext.fromExecutorService(Executors.newFixedThreadPool(8))
  
  val fileConfig = ConfigFactory.parseFile(new File("application.conf"))
  val config = ConfigFactory.load(fileConfig)
  
  val db = Database.forConfig(path="postgres", config=config)

  def query() = {
    val future = db.run(TableQuery[BidAskTicks].filter(_.time > Timestamp.valueOf("2020-09-18 15:29:52.0")).result)
    future
  }
} 
