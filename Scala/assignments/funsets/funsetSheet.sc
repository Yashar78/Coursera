

object funsetSheet {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet
  type Set = Int => Boolean

  /**
   * Indicates whether a set contains a given element.
   */
  def contains(s: Set, elem: Int): Boolean = s(elem)
                                                  //> contains: (s: Int => Boolean, elem: Int)Boolean
  def singletonSet(elem: Int): Set = x => x==elem //> singletonSet: (elem: Int)Int => Boolean
  
  val s = singletonSet(11)                        //> s  : Int => Boolean = <function1>
	contains(s, 10)                           //> res0: Boolean = false
	
  def union(s: Set, t: Set): Set = x => contains(s, x) || contains(t, x)
                                                  //> union: (s: Int => Boolean, t: Int => Boolean)Int => Boolean
  val t = singletonSet(12)                        //> t  : Int => Boolean = <function1>
  
  contains(union(s, t),21)                        //> res1: Boolean = false
	
	def intersect(s: Set, t: Set): Set = x => contains(s, x) && contains(t, x)
                                                  //> intersect: (s: Int => Boolean, t: Int => Boolean)Int => Boolean
	
	contains(intersect(s, t), 10)             //> res2: Boolean = false
	
	def filter(s: Set, p: Int => Boolean): Set = x => contains(s, x) && p(x)
                                                  //> filter: (s: Int => Boolean, p: Int => Boolean)Int => Boolean
	contains(filter(s, x => x > 0), 10)       //> res3: Boolean = false
	val bound = 1000                          //> bound  : Int = 1000
	
	def forall(s: Set, p: Int => Boolean): Boolean = {
	    def iter(a: Int): Boolean = {
	      if ( a > bound) true
	      else if (contains(s, a) && !contains(filter(s, p),a)) false//!contains(filter(s, p),a)) false
	      else iter(a+1)
	    }
	    iter(-bound)
	  }                                       //> forall: (s: Int => Boolean, p: Int => Boolean)Boolean
	contains(s, 10)                           //> res4: Boolean = false
  forall(union(s,t),x => x%10 == 0 )              //> res5: Boolean = false
  
  def exists(s: Set, p: Int => Boolean): Boolean = !forall(s, x => !p(x))
                                                  //> exists: (s: Int => Boolean, p: Int => Boolean)Boolean
	exists(union(s,t),x => x%10 == 0 )        //> res6: Boolean = false
	
	def map(s: Set, f: Int => Int): Set = (elem:Int) => exists(s, x => f(x)==elem  )
                                                  //> map: (s: Int => Boolean, f: Int => Int)Int => Boolean
	
	
	
	contains(map(s, x => 2*x), 22)            //> res7: Boolean = false
}