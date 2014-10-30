package week3

object  rational2 {;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(62); 
  val x = new Rational(1,3);System.out.println("""x  : week3.Rational = """ + $show(x ));$skip(10); val res$0 = 
  x.denom;System.out.println("""res0: Int = """ + $show(res$0));$skip(5); val res$1 = 
  -x;System.out.println("""res1: week3.Rational = """ + $show(res$1));$skip(28); 
  val y = new Rational(5,7);System.out.println("""y  : week3.Rational = """ + $show(y ));$skip(44); 
  val z = new Rational(323232321,600000000);System.out.println("""z  : week3.Rational = """ + $show(z ));$skip(16); val res$2 = 
  
  
  x- y- z;System.out.println("""res2: week3.Rational = """ + $show(res$2));$skip(8); val res$3 = 
  x < y;System.out.println("""res3: Boolean = """ + $show(res$3));$skip(10); val res$4 = 
  x max y;System.out.println("""res4: week3.Rational = """ + $show(res$4));$skip(20); val res$5 = 
  new Rational(1,1);System.out.println("""res5: week3.Rational = """ + $show(res$5));$skip(18); val res$6 = 
  new Rational(4);System.out.println("""res6: week3.Rational = """ + $show(res$6));$skip(8); val res$7 = 

	x * y
	type Set = Int => Boolean;System.out.println("""res7: week3.Rational = """ + $show(res$7));$skip(80); 
  def contains(s: Set, elem: Int): Boolean = s(elem);System.out.println("""contains: (s: Int => Boolean, elem: Int)Boolean""");$skip(15); val res$8 = 
	true && false;System.out.println("""res8: Boolean(false) = """ + $show(res$8))}
	
}

class Rational(x: Int, y: Int) {

require( y != 0 , "denominator must be non-zero")

def this(x: Int)= this(x,1)

private def gcd(a: Int, b:Int): Int = if(b==0) a else gcd(b, a % b )
private val g = gcd(x,y)

def numer = x/g
def denom = y/g


def unary_- : Rational = new Rational(-numer,denom)
//override def toString = numer+"/"+denom
def + (that:Rational) = new Rational(numer*that.denom+denom*that.numer, denom*that.denom)
def - (that:Rational) =  this + -that
def < (that:Rational) = numer * that.denom < denom * that.numer
def max(that:Rational) = if (this < that) that else this
def * (that: Rational) = new Rational(numer * that.numer , denom * that.denom)
override def toString = numer+"/"+denom


}
