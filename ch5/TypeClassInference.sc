trait StrParser[T]{ 
  def parse(s: String): T
}
object StrParser{
  implicit object ParseInt extends StrParser[Int] {
    def parse(s: String) = s.toInt
  }
  implicit object ParseBool extends StrParser[Boolean] {
    def parse(s: String) = s.toBoolean
  }
  implicit object ParseDouble extends StrParser[Double] {
    def parse(s: String) = s.toDouble
  }

  implicit def ParseSeq[T: StrParser] = {
    new StrParser[Seq[T]] {
      def parse(s: String): Seq[T] = {
        s.split(",").toSeq.map(implicitly[StrParser[T]].parse)
      }
    }
  }

  implicit def ParseTuple[T: StrParser, V: StrParser] =
    new StrParser[(T, V)] {
      def parse(s: String): (T, V) = {
        val Array(left, right) = s.split("=")
        (implicitly[StrParser[T]].parse(left), implicitly[StrParser[V]].parse(right))
      }
    }

}

def parseFromString[T: StrParser](s: String): T = {
  
  implicitly[StrParser[T]].parse(s)
}

def parseFromConsole[T: StrParser](s: String) = {
  implicitly[StrParser[T]].parse(scala.Console.in.readLine())
}
