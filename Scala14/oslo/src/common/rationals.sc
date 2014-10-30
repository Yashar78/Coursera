package common

object  rationals {
  val x = new Rational(1,3)                       //> x  : common.Rational = 1/3
  x.denom                                         //> res0: Int = 3
  -x                                              //> res1: common.Rational = 1/-3
  val y = new Rational(5,7)                       //> y  : common.Rational = 5/7
  val z = new Rational(323232321,600000000)       //> z  : common.Rational = 107744107/200000000
  
  
  x- y- z                                         //> res2: common.Rational = 432341049/-94967296
  x < y                                           //> res3: Boolean = true
  x max y                                         //> res4: common.Rational = 5/7
  new Rational(1,1)                               //> res5: common.Rational = 1/1
  new Rational(4)                                 //> res6: common.Rational = 4/1

	x * y                                     //> res7: common.Rational = 5/21
	type Set = Int => Boolean
  def contains(s: Set, elem: Int): Boolean = s(elem)
                                                  //> contains: (s: Int => Boolean, elem: Int)Boolean
	true && false                             //> res8: Boolean(false) = false
	
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