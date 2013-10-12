package week3

object scratch {;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(75); 
  println("Welcome to the Scala worksheet");$skip(51); 
  
  def error(msg: String) = throw new Error(msg);System.out.println("""error: (msg: String)Nothing""");$skip(36); 
  
  //error("hello")
  val x= null;System.out.println("""x  : Null = """ + $show(x ));$skip(58); val res$0 = 
  
  //val z: Int = null
  if (true) null else scala.None;System.out.println("""res0: None.type = """ + $show(res$0))}
}
