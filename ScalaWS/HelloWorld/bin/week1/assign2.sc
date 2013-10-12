package week1

object assign2 {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet
  
  def pascal(c: Int, r: Int): Int =
  
  	if (c >= r) pascal(c-r,r) else
  	if (c ==0 || r == 0 ) 1
  	else pascal(c-1, r-1)+pascal(c,r-1)       //> pascal: (c: Int, r: Int)Int/
  
  pascal(0,0)
  
}