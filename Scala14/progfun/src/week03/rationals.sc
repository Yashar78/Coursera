

object  rationals {
  val x = new Rational(1,2)
  x.denom
}

class Rational(x: Int, y: Int) {
def numer = x
def denom = y
}