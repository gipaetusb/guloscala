package utils.db

import slick.jdbc.PostgresProfile.api._

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

