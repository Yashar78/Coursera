	package week3

object rationals {;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(78); 
  println("Welcome to the Scala worksheet");$skip(27); 
 var x = new Rational(1,3);System.out.println("""x  : week3.Rational = """ + $show(x ));$skip(27); 
 var y = new Rational(5,7);System.out.println("""y  : week3.Rational = """ + $show(y ));$skip(27); 
 var z = new Rational(3,2);System.out.println("""z  : week3.Rational = """ + $show(z ));$skip(11); val res$0 = 
 x - y - z;System.out.println("""res0: week3.Rational = """ + $show(res$0));$skip(7); val res$1 = 
 y + y;System.out.println("""res1: week3.Rational = """ + $show(res$1));$skip(7); val res$2 = 
 x < y;System.out.println("""res2: Boolean = """ + $show(res$2));$skip(9); val res$3 = 
 x max y;System.out.println("""res3: week3.Rational = """ + $show(res$3))}

}

class  Rational(x : Int, y:Int){
	require(y != 0, "The denom argument should be non-zero!")
	
	private def gcd(a: Int, b: Int):Int = if (b==0) a else gcd(b, a%b)
	private val g = gcd(x,y)
	def numer = x / g
	def denom = y /g

	def this(x : Int) = this(x,1)
	
	def + (that: Rational) =
	new Rational(
	numer * that.denom + that.numer * denom, denom*that.denom )
	
	def unary_- : Rational = new Rational(-numer, denom)

	def - (that: Rational) = this + -that
		
	override def toString = numer+"/"+denom
	
	def < (that:Rational) =
		this.numer * that.denom < that.numer * this.denom
		
	def max(that:Rational) =
		if (this < that) that else this
	

}
