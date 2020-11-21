package backtest

import org.joda.time.DateTime

class BackTestTrade(
    val side: Int,
    val open_time: DateTime,
    val open_price: Float,
    var close_time: Option[DateTime]=None,
    var close_price: Option[Float]=None,
) {
  /*def this(side: Int, open_time: DateTime, open_price: Float) {
    this(side=side, open_time=open_time, open_price=open_price, close_time=None, close_price=None)
  }*/

}
