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
  def pascal(c: Int, r: Int): Int =
  
  	if (c >= r && c!=0) pascal(c-r,r) else
  	if (c ==0 || r == 0 ) 1 else
  	pascal(c-1, r-1)+pascal(c,r-1)

  /**
   * Exercise 2
   */
  def balance(chars: List[Char]): Boolean = {
	
	def loop(counter : Int, chars: List[Char]): Boolean =
	if (counter < 0) false
	else {
		if (chars.isEmpty) (counter==0)
		else
		{
			if (chars.head.equals('(')) loop(counter+1, chars.tail)
				else
					if (chars.head.equals(')')) loop(counter-1, chars.tail)
					else
					loop(counter, chars.tail)
		}
	}
	
	loop(0,chars)
	
	//if (chars.isEmpty) true
	//else false
	
	}   

  /**
   * Exercise 3
   */
  def countChange(money: Int, coins: List[Int]): Int =
		if (money==0)  1
		else
			if (money < 0) 0
				else
					if (coins.isEmpty) 0
						else
							countChange(money, coins.tail)+countChange(money-coins.head, coins)

}
