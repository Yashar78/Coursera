package berlin

object nth {
	def nth[T](n: Int, xs: List[T]): T =
	  if (xs.isEmpty) throw new IndexOutOfBoundsException
		else if (n==0) xs.head
		else nth(n-1, xs.tail)            //> nth: [T](n: Int, xs: berlin.List[T])T
	val list = new Cons(1, new Cons(2, new Cons(3, new Nil)))
                                                  //> list  : berlin.Cons[Int] = berlin.Cons@50c4fe76
	
	nth(-1,list)                              //> java.lang.IndexOutOfBoundsException
                                                  //| 	at berlin.nth$$anonfun$main$1.nth$1(berlin.nth.scala:5)
                                                  //| 	at berlin.nth$$anonfun$main$1.apply$mcV$sp(berlin.nth.scala:10)
                                                  //| 	at org.scalaide.worksheet.runtime.library.WorksheetSupport$$anonfun$$exe
                                                  //| cute$1.apply$mcV$sp(WorksheetSupport.scala:76)
                                                  //| 	at org.scalaide.worksheet.runtime.library.WorksheetSupport$.redirected(W
                                                  //| orksheetSupport.scala:65)
                                                  //| 	at org.scalaide.worksheet.runtime.library.WorksheetSupport$.$execute(Wor
                                                  //| ksheetSupport.scala:75)
                                                  //| 	at berlin.nth$.main(berlin.nth.scala:3)
                                                  //| 	at berlin.nth.main(berlin.nth.scala)
}