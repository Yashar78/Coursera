package week6

object polynomials {
  class Poly2(val terms:Map[Int, Double]) {
  	def + (other: Poly) = new Poly((other.terms foldLeft terms )(addTerm))
  	
  	override def toString = (for((exp,coef) <- terms.toList.sorted.reverse) yield coef+"X^"+exp) mkString "+"
  	def addTerm(terms: Map[Int, Double], term:(Int,Double)): Map[Int, Double] = {
  		val (exp, coef) = term
  		terms + (exp -> (coef+terms(exp)))
  	}
  	def adjust(term: (Int, Double)): (Int, Double) = {
  		val (exp, coef) = term
  		terms get exp match {
  			case Some(coef1) => exp -> (coef1 + coef)
  			case None => exp -> coef
  		}
  	}
  }
  
   class Poly(terms0:Map[Int, Double]) {
   def this(bindings:(Int,Double)*) = this(bindings.toMap)
 
   val terms = terms0 withDefaultValue 0.0
  	def + (other: Poly) = new Poly( terms ++ (other.terms map adjust ))
  	override def toString = (for((exp,coef) <- terms.toList.sorted.reverse) yield coef+"X^"+exp) mkString "+"
  	
  	def adjust(term: (Int, Double)): (Int, Double) = {
  		val (exp, coef) = term
  		exp -> (coef + terms(exp))
  	}
  };import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(1105); 
   
  val m1 = Map("a"->1, "b"->3);System.out.println("""m1  : scala.collection.immutable.Map[String,Int] = """ + $show(m1 ));$skip(31); 
  val m2 = Map("c"->1, "b"->2);System.out.println("""m2  : scala.collection.immutable.Map[String,Int] = """ + $show(m2 ));$skip(14); val res$0 = 
  
  m1 ++ m2;System.out.println("""res0: scala.collection.immutable.Map[String,Int] = """ + $show(res$0));$skip(34); 
  val v1 = new Poly(1->2, 3 -> 4);System.out.println("""v1  : week6.polynomials.Poly = """ + $show(v1 ));$skip(34); 
  val v2 = new Poly(1->2, 2 -> 4);System.out.println("""v2  : week6.polynomials.Poly = """ + $show(v2 ));$skip(16); val res$1 = 
  
  
  v1 + v2;System.out.println("""res1: week6.polynomials.Poly = """ + $show(res$1));$skip(14); val res$2 = 
  v1.toString;System.out.println("""res2: String = """ + $show(res$2));$skip(50); val res$3 = 
  (for((e,t) <- m1) yield  t+"X^"+e) mkString "+";System.out.println("""res3: String = """ + $show(res$3))}
}
