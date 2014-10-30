object currying {
def mapReduce(f: Int => Int, combine:(Int, Int)=> Int, zero:Int)(a:Int, b:Int): Int=
	if (a > b) zero
	else combine(f(a), mapReduce(f,combine,zero)(a+1,b))
  def product(f: Int => Int)(a: Int, b:Int): Int = mapReduce(x => x, (x,y)=> x* y , 1)(a,b)
  product(x=>x)(1, 5)
  def fact(n: Int): Int =
  	product(x =>x)(1,n)
  	
	fact(5)
}