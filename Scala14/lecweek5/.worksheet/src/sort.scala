object sort {;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(126); 
 def isort(xs:List[Int]): List[Int] = xs match{
 	case List() => List()
 	case y :: ys => insert(y,isort(ys))
 };System.out.println("""isort: (xs: List[Int])List[Int]""");$skip(221); 
 
 def insert(x: Int, xs:List[Int]):List[Int] = xs  match {
 	case List() =>  List(x)
 	//case y :: ys => if (y < x ) y :: insert(x, ys) else x :: y :: ys
 	case y :: ys => if ( x <= y ) x :: xs else y :: insert(x,ys)
 };System.out.println("""insert: (x: Int, xs: List[Int])List[Int]""");$skip(25); val res$0 = 

isort(List(2,2,489,21));System.out.println("""res0: List[Int] = """ + $show(res$0))}
}
