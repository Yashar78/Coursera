package lectures

object testSheet {
  println("Welcome to the Scala worksheet")
  
  def mapReduce(f: Int => Int , combine: (Int , Int) => Int , zero:Int)(a: Int , b: Int): Int =
	if ( a > b ) zero
	else  combine(f(a), mapReduce(f, combine, zero)(a+1, b))
   
   
  def sum(f: Int => Int)(a:Int , b:Int): Int =
  	if (a > b ) 0 else f(a)+sum(f)(a+1,b)
	
	
	def cube(x:Int): Int =  x*x*x
  def id(x: Int): Int = x
	sum(cube)(10,15)
	
	def product(f: Int => Int)(a:Int , b:Int): Int = mapReduce(f, (x : Int , y: Int) => x*y, 1)(a, b)
  	
	product(id)(1, 5)
	product(x => x)( 1 , 10)
	
	def fact(n : Int): Int = product(x => x)(1,n)
	
	fact(10)
	
	
	
	
	
	
	
}