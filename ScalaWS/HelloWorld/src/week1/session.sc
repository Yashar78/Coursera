package week1





object session {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet
  
  def abs(x: Double) = if (x < 0) -x else x       //> abs: (x: Double)Double
  def sqrt(x:Double) = {
		  def sqrtItr(guess: Double): Double =
		   if (isGoodEnough(guess)) guess
		   else sqrtItr(improve(guess))
		  
		  def isGoodEnough(guess: Double) =
		  abs(guess * guess - x )/x < 0.001
		  
		  def improve(guess: Double) =
		  	(guess + x/guess)/2
		  	
		  sqrtItr(1.0)
  }                                               //> sqrt: (x: Double)Double
  

  def factorial(n: Int, m: Int): Int =
  if (n==1) m else factorial(n-1, m*n)            //> factorial: (n: Int, m: Int)Int
  
  factorial(4,1)                                  //> res0: Int = 24
  
  
  
  
  def sum(xs: List[Int]): Int = {
    
    def loop(acc: Int, xs:List[Int]):Int =
      if (xs.isEmpty) acc
      else  loop(xs.head+acc, xs.tail)
     loop(0,xs)
        
  }                                               //> sum: (xs: List[Int])Int
  
    def max(xs: List[Int]): Int =
    if (xs.isEmpty) throw new NoSuchElementException()
    else findMaxValue(xs: List[Int])              //> max: (xs: List[Int])Int
    
    def findMaxValue(xs: List[Int]): Int = {
    	def loop(maxVal: Int, xs: List[Int]):Int =
    	if (xs.isEmpty) maxVal
    	else
    		loop(if (maxVal > xs.head) maxVal else xs.head , xs.tail)
      loop(xs.head, xs)
    
    }                                             //> findMaxValue: (xs: List[Int])Int
    
      
  
}