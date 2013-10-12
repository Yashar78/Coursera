package lectures

object testSheet {;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(80); 
  println("Welcome to the Scala worksheet");$skip(176); 
  
  def mapReduce(f: Int => Int , combine: (Int , Int) => Int , zero:Int)(a: Int , b: Int): Int =
	if ( a > b ) zero
	else  combine(f(a), mapReduce(f, combine, zero)(a+1, b));System.out.println("""mapReduce: (f: Int => Int, combine: (Int, Int) => Int, zero: Int)(a: Int, b: Int)Int""");$skip(96); 
   
   
  def sum(f: Int => Int)(a:Int , b:Int): Int =
  	if (a > b ) 0 else f(a)+sum(f)(a+1,b);System.out.println("""sum: (f: Int => Int)(a: Int, b: Int)Int""");$skip(35); 
	
	
	def cube(x:Int): Int =  x*x*x;System.out.println("""cube: (x: Int)Int""");$skip(26); 
  def id(x: Int): Int = x;System.out.println("""id: (x: Int)Int""");$skip(18); val res$0 = 
	sum(cube)(10,15);System.out.println("""res0: Int = """ + $show(res$0));$skip(101); 
	
	def product(f: Int => Int)(a:Int , b:Int): Int = mapReduce(f, (x : Int , y: Int) => x*y, 1)(a, b);System.out.println("""product: (f: Int => Int)(a: Int, b: Int)Int""");$skip(23); val res$1 = 
  	
	product(id)(1, 5);System.out.println("""res1: Int = """ + $show(res$1));$skip(26); val res$2 = 
	product(x => x)( 1 , 10);System.out.println("""res2: Int = """ + $show(res$2));$skip(49); 
	
	def fact(n : Int): Int = product(x => x)(1,n);System.out.println("""fact: (n: Int)Int""");$skip(12); val res$3 = 
	
	fact(10);System.out.println("""res3: Int = """ + $show(res$3))}
	
	
	
	
	
	
	
}
