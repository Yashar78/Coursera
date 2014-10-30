package berlin

object nth {;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(171); 
	def nth[T](n: Int, xs: List[T]): T =
	  if (xs.isEmpty) throw new IndexOutOfBoundsException
		else if (n==0) xs.head
		else nth(n-1, xs.tail);System.out.println("""nth: [T](n: Int, xs: berlin.List[T])T""");$skip(59); 
	val list = new Cons(1, new Cons(2, new Cons(3, new Nil)));System.out.println("""list  : berlin.Cons[Int] = """ + $show(list ));$skip(16); val res$0 = 
	
	nth(-1,list);System.out.println("""res0: Int = """ + $show(res$0))}
}
