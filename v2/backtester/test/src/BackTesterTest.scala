package backtester
import utest._

object BackTesterTest extends TestSuite{
  def foo[T](example: String): Unit = {
    println(example)
  }

  val tests: Tests = Tests {
    test("BackTester") - foo("success")
  }
}


