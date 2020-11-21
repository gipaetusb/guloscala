package backtester

import scala.concurrent._, duration.Duration.Inf, java.util.concurrent.Executors
import backtester.Db._

object BackTester extends App {
  println("You called a BackTester, Here I am")
  Await.result(query(), Inf).foreach(println(_))
}
