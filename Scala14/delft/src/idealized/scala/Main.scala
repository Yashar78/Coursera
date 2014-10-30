package idealized.scala

object Main extends App{
	import Boolean._
	val x = True
	if ((True || False)==True)
	  println("true")
	else
	  println("false")
	  
	val n = Zero
	n.successor.successor.successor 
}