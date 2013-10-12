package week4

object listWS {
  
  def nth[T](n: Int, l:List[T]): T =
	  if (l.isEmpty) throw new IndexOutOfBoundsException
	  else
	  if (n==0) l.head
	  else nth(n-1, l.tail)                   //> nth: [T](n: Int, l: week4.List[T])T
 
 val list = new Cons(1, new Cons(2, new Cons(3, new Nil)))
                                                  //> list  : week4.Cons[Int] = week4.Cons@7f2ad19e
 nth(10,list)                                     //> java.lang.IndexOutOfBoundsException
                                                  //| 	at week4.listWS$$anonfun$main$1.nth$1(week4.listWS.scala:6)
                                                  //| 	at week4.listWS$$anonfun$main$1.apply$mcV$sp(week4.listWS.scala:12)
                                                  //| 	at org.scalaide.worksheet.runtime.library.WorksheetSupport$$anonfun$$exe
                                                  //| cute$1.apply$mcV$sp(WorksheetSupport.scala:76)
                                                  //| 	at org.scalaide.worksheet.runtime.library.WorksheetSupport$.redirected(W
                                                  //| orksheetSupport.scala:65)
                                                  //| 	at org.scalaide.worksheet.runtime.library.WorksheetSupport$.$execute(Wor
                                                  //| ksheetSupport.scala:75)
                                                  //| 	at week4.listWS$.main(week4.listWS.scala:3)
                                                  //| 	at week4.listWS.main(week4.listWS.scala)
          
}