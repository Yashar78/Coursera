package lectures

import math.abs
object exercise {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet
  
  val tolerance = 0.0001                          //> tolerance  : Double = 1.0E-4
  def isCloseEnough(x:Double , y:Double) =
  	abs((x-y) /x ) /x < tolerance             //> isCloseEnough: (x: Double, y: Double)Boolean
  
  def fixedPoint(f: Double => Double)(firstGuess: Double) = {
  	def iterate(guess: Double): Double = {
  		println(guess)
	  	val next = f(guess)
	  	if ( isCloseEnough(guess, next)) next
	  		else
	  		iterate(next)
	  	}
	  
  iterate(firstGuess)
  }                                               //> fixedPoint: (f: Double => Double)(firstGuess: Double)Double
  
  
  def averageDamp(f: Double => Double)(x:Double) = (x+f(x))/2
                                                  //> averageDamp: (f: Double => Double)(x: Double)Double
  
  fixedPoint(x => 1+x/2)(1.0)                     //> 1.0
                                                  //| 1.5
                                                  //| 1.75
                                                  //| 1.875
                                                  //| 1.9375
                                                  //| 1.96875
                                                  //| 1.984375
                                                  //| 1.9921875
                                                  //| 1.99609375
                                                  //| 1.998046875
                                                  //| 1.9990234375
                                                  //| 1.99951171875
                                                  //| res0: Double = 1.999755859375
	def sqrt(x : Double): Double = fixedPoint(averageDamp(y => x/y))(1.0)
                                                  //> sqrt: (x: Double)Double
	sqrt(20)                                  //> 1.0
                                                  //| 10.5
                                                  //| 6.2023809523809526
                                                  //| 4.713474545288365
                                                  //| 4.478314445474382
                                                  //| 4.4721402170657
                                                  //| res1: Double = 4.47213595500161
	

}