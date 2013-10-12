package week4

object listWS {;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(177); 
  
  def nth[T](n: Int, l:List[T]): T =
	  if (l.isEmpty) throw new IndexOutOfBoundsException
	  else
	  if (n==0) l.head
	  else nth(n-1, l.tail);System.out.println("""nth: [T](n: Int, l: week4.List[T])T""");$skip(61); 
 
 val list = new Cons(1, new Cons(2, new Cons(3, new Nil)));System.out.println("""list  : week4.Cons[Int] = """ + $show(list ));$skip(14); val res$0 = 
 nth(10,list);System.out.println("""res0: Int = """ + $show(res$0))}
          
}
