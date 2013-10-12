

object funsetSheet {;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(66); 
  println("Welcome to the Scala worksheet")
  type Set = Int => Boolean;$skip(149); 

  /**
   * Indicates whether a set contains a given element.
   */
  def contains(s: Set, elem: Int): Boolean = s(elem);System.out.println("""contains: (s: Int => Boolean, elem: Int)Boolean""");$skip(50); 
  def singletonSet(elem: Int): Set = x => x==elem;System.out.println("""singletonSet: (elem: Int)Int => Boolean""");$skip(30); 
  
  val s = singletonSet(11);System.out.println("""s  : Int => Boolean = """ + $show(s ));$skip(17); val res$0 = 
	contains(s, 10);System.out.println("""res0: Boolean = """ + $show(res$0));$skip(75); 
	
  def union(s: Set, t: Set): Set = x => contains(s, x) || contains(t, x);System.out.println("""union: (s: Int => Boolean, t: Int => Boolean)Int => Boolean""");$skip(27); 
  val t = singletonSet(12);System.out.println("""t  : Int => Boolean = """ + $show(t ));$skip(30); val res$1 = 
  
  contains(union(s, t),21);System.out.println("""res1: Boolean = """ + $show(res$1));$skip(78); 
	
	def intersect(s: Set, t: Set): Set = x => contains(s, x) && contains(t, x);System.out.println("""intersect: (s: Int => Boolean, t: Int => Boolean)Int => Boolean""");$skip(33); val res$2 = 
	
	contains(intersect(s, t), 10);System.out.println("""res2: Boolean = """ + $show(res$2));$skip(76); 
	
	def filter(s: Set, p: Int => Boolean): Set = x => contains(s, x) && p(x);System.out.println("""filter: (s: Int => Boolean, p: Int => Boolean)Int => Boolean""");$skip(37); val res$3 = 
	contains(filter(s, x => x > 0), 10);System.out.println("""res3: Boolean = """ + $show(res$3));$skip(18); 
	val bound = 1000;System.out.println("""bound  : Int = """ + $show(bound ));$skip(270); 
	
	def forall(s: Set, p: Int => Boolean): Boolean = {
	    def iter(a: Int): Boolean = {
	      if ( a > bound) true
	      else if (contains(s, a) && !contains(filter(s, p),a)) false//!contains(filter(s, p),a)) false
	      else iter(a+1)
	    }
	    iter(-bound)
	  };System.out.println("""forall: (s: Int => Boolean, p: Int => Boolean)Boolean""");$skip(17); val res$4 = 
	contains(s, 10);System.out.println("""res4: Boolean = """ + $show(res$4));$skip(37); val res$5 = 
  forall(union(s,t),x => x%10 == 0 );System.out.println("""res5: Boolean = """ + $show(res$5));$skip(77); 
  
  def exists(s: Set, p: Int => Boolean): Boolean = !forall(s, x => !p(x));System.out.println("""exists: (s: Int => Boolean, p: Int => Boolean)Boolean""");$skip(36); val res$6 = 
	exists(union(s,t),x => x%10 == 0 );System.out.println("""res6: Boolean = """ + $show(res$6));$skip(84); 
	
	def map(s: Set, f: Int => Int): Set = (elem:Int) => exists(s, x => f(x)==elem  );System.out.println("""map: (s: Int => Boolean, f: Int => Int)Int => Boolean""");$skip(38); val res$7 = 
	
	
	
	contains(map(s, x => 2*x), 22);System.out.println("""res7: Boolean = """ + $show(res$7))}
}
