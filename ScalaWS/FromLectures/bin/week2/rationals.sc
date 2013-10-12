package week2

object rationals {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet
 var x = new Rational(1,3)                        //> x  : week2.Rational = 1/3
 var y = new Rational(5,7)                        //> y  : week2.Rational = 5/7
 var z = new Rational(3,2)                        //> z  : week2.Rational = 3/2
 x - y - z                                        //> res0: week2.Rational = -79/42
 y + y                                            //> res1: week2.Rational = 10/7
 x < y                                            //> res2: Boolean = true
 x max y                                          //> res3: week2.Rational = 5/7
 val vv = 1                                       //> vv  : Int = 1
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