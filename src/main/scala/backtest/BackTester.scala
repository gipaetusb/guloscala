package backtest

import backtest.BackTestTrade

class BackTester(
                  val contract: String,
                  val trigger: Int,
                  val sl: Double,
                  val tp: Double
                ) {

  // one-arg auxiliary constructor
  def this(contract: String) = {
    this(contract=contract, trigger=100, sl=0.25, tp=1)
  }

  val lots: Int = 1
  var signal: Int = 0
  var trade: Option[BackTestTrade] = None
  var trade_log: Array[BackTestTrade] = Array()

  def backtest {
    println("Run a backtest")
  }

}

object BackTester extends App {
  val bt = new BackTester(contract="nq")
}
