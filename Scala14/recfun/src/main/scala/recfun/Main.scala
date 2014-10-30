package recfun
import common._

object Main {
  def main(args: Array[String]) {
    println("Pascal's Triangle")
    for (row <- 0 to 10) {
      for (col <- 0 to row)
        print(pascal(col, row) + " ")
      println()
    }
  }

  /**
   * Exercise 1
   */
  def pascal(c: Int, r: Int): Int = {
    if (c==0 || c==r )
      1
    else if(c > r/2.0)
      pascal(r-c,r)
    else
      pascal(c,r-1)+pascal(c-1,r-1)
  }
    

  /**
   * Exercise 2
   */
  def balance(chars: List[Char]): Boolean = {
    def loop(s:List[Char], counter: Int): Int={
      if (s.length == 0 || counter < 0 ) counter
      else 
        if(s.head=='(') loop(s.tail, counter+1)
      else
        if(s.head==')') loop(s.tail, counter-1)
      else 
        loop(s.tail, counter)
    }
    
    if (loop(chars, 0)==0) true
    else false
  }

  /**
   * Exercise 3
   */
  
  def countChange(money: Int, coins: List[Int]): Int = {
	if (money==0)  
	  1
    else if (money < 0) 
      0 
    else if (coins.isEmpty) 
      0
    else
      countChange(money, coins.tail)+countChange(money-coins.head, coins)
    }
}
