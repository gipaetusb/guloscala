trait StrWriter[T]{ def write(t: T): Str }
object StrWriter{
  implicit object WriteInt extends StrWriter[Int] {
    def write(t: Int) = t.toString
  }
  implicit object WriteBool extends StrWriter[Bool] {
    def write(t: Bool) = t.toString
  }
  implicit object WriteDouble extends StrWriter[Double] {
    def write(t: Double) = t.toString
  }

  implicit def ParseSeq[T](implicit w: StrWriter[T]) = new StrWriter[Seq[T]] {
    def write(t: Seq[T]) = t.map(w.write).mkString("[", ",", "]")
  }

  implicit def ParseTuple[T, V](implicit w1: StrWriter[T], w2: StrWriter[V]) = new StrWriter[(T, V)] {
      def write(t: T)
    }

}
