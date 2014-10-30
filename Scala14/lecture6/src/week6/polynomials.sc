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
  }
   
  val m1 = Map("a"->1, "b"->3)                    //> m1  : scala.collection.immutable.Map[String,Int] = Map(a -> 1, b -> 3)
  val m2 = Map("c"->1, "b"->2)                    //> m2  : scala.collection.immutable.Map[String,Int] = Map(c -> 1, b -> 2)
  
  m1 ++ m2                                        //> res0: scala.collection.immutable.Map[String,Int] = Map(a -> 1, b -> 2, c ->
                                                  //|  1)
  val v1 = new Poly(1->2, 3 -> 4)                 //> v1  : week6.polynomials.Poly = 4.0X^3+2.0X^1
  val v2 = new Poly(1->2, 2 -> 4)                 //> v2  : week6.polynomials.Poly = 4.0X^2+2.0X^1
  
  
  v1 + v2                                         //> res1: week6.polynomials.Poly = 4.0X^3+4.0X^2+4.0X^1
  v1.toString                                     //> res2: String = 4.0X^3+2.0X^1
  (for((e,t) <- m1) yield  t+"X^"+e) mkString "+" //> res3: String = 1X^a+3X^b
}