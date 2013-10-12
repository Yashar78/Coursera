package week1

object assign2 {;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(75); 
  println("Welcome to the Scala worksheet");$skip(142); 
  
  def pascal(c: Int, r: Int): Int =
  
  	if (c >= r) pascal(c-r,r) else
  	if (c ==0 || r == 0 ) 1
  	else pascal(c-1, r-1)+pascal(c,r-1);System.out.println("""pascal: (c: Int, r: Int)Int""");$skip(17); val res$0 = 
  
  pascal(0,0);System.out.println("""res0: Int = """ + $show(res$0))}
  
}
