package week3

object scratch {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet
  
  def error(msg: String) = throw new Error(msg)   //> error: (msg: String)Nothing
  
  //error("hello")
  val x= null                                     //> x  : Null = null
  
  //val z: Int = null
  if (true) null else scala.None                  //> res0: None.type = null
}