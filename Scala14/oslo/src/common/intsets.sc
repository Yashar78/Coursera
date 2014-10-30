package common
import berlin._
object intsets {

val t1 = new NonEmpty( 3, new Empty, new Empty)   //> t1  : common.NonEmpty = {.3.}
val t2 = t1 incl 4                                //> t2  : common.IntSet = {.3{.4.}}
val t3 = new NonEmpty(45, new Empty, new Empty)   //> t3  : common.NonEmpty = {.45.}

t1 union t3 union t2                              //> res0: common.IntSet = {.3{.4{.45.}}}


//lecture 4
val a: Array[NonEmpty] = Array(new NonEmpty(1, new  Empty, new Empty))
                                                  //> a  : Array[common.NonEmpty] = Array({.1.})
//val b: Array[IntSet] = a
//b(0) = new Empty
//val s: NonEmpty = a(0)

def f(xs: List[NonEmpty], x: Empty) = xs prepend x//> f: (xs: berlin.List[common.NonEmpty], x: common.Empty)berlin.List[common.Int
                                                  //| Set]

}

abstract class IntSet{
	def incl(x: Int): IntSet
	def contains(x: Int): Boolean
	def union(other: IntSet): IntSet
}

class Empty extends IntSet{
def incl(x: Int): IntSet = new NonEmpty(x, new Empty, new Empty)
def contains(x: Int): Boolean = false
override def toString ="."
def union(other: IntSet): IntSet = other
}

class NonEmpty(elem: Int, left: IntSet, right: IntSet) extends IntSet{

	def contains(x: Int): Boolean =
		if (x < elem)
			left contains x
		else if ( x > elem)
			right contains x
		else
			true
	
	def incl(x: Int): IntSet =
		if ( x < elem)
			new NonEmpty(elem, left incl x , right)
		else if ( x > elem)
			new NonEmpty(elem, left, right incl x)
		else
			this
			
	def union(other: IntSet): IntSet =
	((left union right) union other) incl elem
	override def toString= "{" + left + elem + right + "}"
	
}