package myPkg

class MyRational(x: Int, y: Int) {

require( y != 0 , "denominator must be non-zero")

def this(x: Int)= this(x,1)

private def gcd(a: Int, b:Int): Int = if(b==0) a else gcd(b, a % b )
private val g = gcd(x,y)

def numer = x/g
def denom = y/g


def unary_- : MyRational = new MyRational(-numer, denom)
//override def toString = numer+"/"+denom
def + (that:MyRational) = new MyRational(numer*that.denom+denom*that.numer, denom*that.denom)
def - (that:MyRational) =  MyRational.this + -that
def < (that:MyRational) = numer * that.denom < denom * that.numer
def max(that:MyRational) = if (MyRational.this < that) that else MyRational.this
def * (that: MyRational) = new MyRational(numer * that.numer ,denom * that.denom)
override def toString = numer+"/"+denom


}