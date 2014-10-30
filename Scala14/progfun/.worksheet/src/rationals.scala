

object  rationals {;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(49); 
  val x = new Rational(1,2);System.out.println("""x  : Rational = """ + $show(x ));$skip(10); val res$0 = 
  x.denom;System.out.println("""res0: Int = """ + $show(res$0))}
}

class Rational(x: Int, y: Int) {
def numer = x
def denom = y
}
