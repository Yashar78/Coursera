package recfun

object assign2 {;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(76); 
  println("Welcome to the Scala worksheet");$skip(421); 
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
	
	};System.out.println("""balance: (chars: List[Char])Boolean""");$skip(34); val res$0 = 

	balance("(()())hjh()()".toList);System.out.println("""res0: Boolean = """ + $show(res$0));$skip(23); 
	val c= "hello".toList;System.out.println("""c  : List[Char] = """ + $show(c ));$skip(13); val res$1 = 
	c.head=='h';System.out.println("""res1: Boolean = """ + $show(res$1));$skip(225); 
	
	
	def countChange(money: Int, coins: List[Int]): Int =
		if (money==0)  1
		else
			if (money < 0) 0
				else
					if (coins.isEmpty) 0
						else
							countChange(money, coins.tail)+countChange(money-coins.head, coins);System.out.println("""countChange: (money: Int, coins: List[Int])Int""");$skip(43); val res$2 = 
					
	

	
	countChange(10, List(6,2,5,3));System.out.println("""res2: Int = """ + $show(res$2))}
	
	
	

}
