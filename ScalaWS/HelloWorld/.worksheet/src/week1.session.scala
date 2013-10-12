package week1





object session {;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(79); 
  println("Welcome to the Scala worksheet");$skip(47); 
  
  def abs(x: Double) = if (x < 0) -x else x;System.out.println("""abs: (x: Double)Double""");$skip(307); 
  def sqrt(x:Double) = {
		  def sqrtItr(guess: Double): Double =
		   if (isGoodEnough(guess)) guess
		   else sqrtItr(improve(guess))
		  
		  def isGoodEnough(guess: Double) =
		  abs(guess * guess - x )/x < 0.001
		  
		  def improve(guess: Double) =
		  	(guess + x/guess)/2
		  	
		  sqrtItr(1.0)
  };System.out.println("""sqrt: (x: Double)Double""");$skip(82); 
  

  def factorial(n: Int, m: Int): Int =
  if (n==1) m else factorial(n-1, m*n);System.out.println("""factorial: (n: Int, m: Int)Int""");$skip(20); val res$0 = 
  
  factorial(4,1);System.out.println("""res0: Int = """ + $show(res$0));$skip(188); 
  
  
  
  
  def sum(xs: List[Int]): Int = {
    
    def loop(acc: Int, xs:List[Int]):Int =
      if (xs.isEmpty) acc
      else  loop(xs.head+acc, xs.tail)
     loop(0,xs)
        
  };System.out.println("""sum: (xs: List[Int])Int""");$skip(129); 
  
    def max(xs: List[Int]): Int =
    if (xs.isEmpty) throw new NoSuchElementException()
    else findMaxValue(xs: List[Int]);System.out.println("""max: (xs: List[Int])Int""");$skip(235); 
    
    def findMaxValue(xs: List[Int]): Int = {
    	def loop(maxVal: Int, xs: List[Int]):Int =
    	if (xs.isEmpty) maxVal
    	else
    		loop(if (maxVal > xs.head) maxVal else xs.head , xs.tail)
      loop(xs.head, xs)
    
    };System.out.println("""findMaxValue: (xs: List[Int])Int""")}
    
      
  
}
