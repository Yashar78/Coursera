package recfun

object assign2 {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet
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
	
	}                                         //> balance: (chars: List[Char])Boolean

	balance("(()())hjh()()".toList)           //> res0: Boolean = true
	val c= "hello".toList                     //> c  : List[Char] = List(h, e, l, l, o)
	c.head=='h'                               //> res1: Boolean = true
	
	
	def countChange(money: Int, coins: List[Int]): Int =
		if (money==0)  1
		else
			if (money < 0) 0
				else
					if (coins.isEmpty) 0
						else
							countChange(money, coins.tail)+countChange(money-coins.head, coins)
                                                  //> countChange: (money: Int, coins: List[Int])Int
					
	

	
	countChange(10, List(6,2,5,3))            //> res2: Int = 5
	
	
	

}