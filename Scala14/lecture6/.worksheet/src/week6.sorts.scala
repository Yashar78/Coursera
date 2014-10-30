package week6

object sorts {;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(103); 
  
  val fruit = List("apple", "orange", "cucumber", "pear", "pineapple");System.out.println("""fruit  : List[String] = """ + $show(fruit ));$skip(42); val res$0 = 
  
  fruit sortWith (_.length < _.length);System.out.println("""res0: List[String] = """ + $show(res$0));$skip(14); val res$1 = 
	fruit.sorted;System.out.println("""res1: List[String] = """ + $show(res$1));$skip(25); val res$2 = 
  fruit groupBy (_.head);System.out.println("""res2: scala.collection.immutable.Map[Char,List[String]] = """ + $show(res$2))}

}
