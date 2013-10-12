package lectures

import math.abs
object exercise {;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(95); 
  println("Welcome to the Scala worksheet");$skip(28); 
  
  val tolerance = 0.0001;System.out.println("""tolerance  : Double = """ + $show(tolerance ));$skip(76); 
  def isCloseEnough(x:Double , y:Double) =
  	abs((x-y) /x ) /x < tolerance;System.out.println("""isCloseEnough: (x: Double, y: Double)Boolean""");$skip(257); 
  
  def fixedPoint(f: Double => Double)(firstGuess: Double) = {
  	def iterate(guess: Double): Double = {
  		println(guess)
	  	val next = f(guess)
	  	if ( isCloseEnough(guess, next)) next
	  		else
	  		iterate(next)
	  	}
	  
  iterate(firstGuess)
  };System.out.println("""fixedPoint: (f: Double => Double)(firstGuess: Double)Double""");$skip(68); 
  
  
  def averageDamp(f: Double => Double)(x:Double) = (x+f(x))/2;System.out.println("""averageDamp: (f: Double => Double)(x: Double)Double""");$skip(33); val res$0 = 
  
  fixedPoint(x => 1+x/2)(1.0);System.out.println("""res0: Double = """ + $show(res$0));$skip(71); 
	def sqrt(x : Double): Double = fixedPoint(averageDamp(y => x/y))(1.0);System.out.println("""sqrt: (x: Double)Double""");$skip(10); val res$1 = 
	sqrt(20);System.out.println("""res1: Double = """ + $show(res$1))}
	

}
