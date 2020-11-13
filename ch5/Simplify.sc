{
sealed Trait Expr
case class BinOp(left: Expr, op: String, right: Expr) extends Expr
case class Literal(value: Int) extends Expr
case class Variable(name: String) extends Expr
}

def simplify(expr: Expr): String = expr match {
  case Literal(value) => value.toString
  case Variable(name) => name

  case BinOp(Literal(value), "*", 1) => value.toString
  case BinOp(Variable(name), "*", 1) => name
}
