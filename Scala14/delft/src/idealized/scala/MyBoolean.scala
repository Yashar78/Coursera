package idealized.scala

abstract class MyBoolean {

  def ifThenElse[T](t: => T, e: =>T): T
  def && (x: => MyBoolean): MyBoolean = ifThenElse(x, False)
  def || (x: => MyBoolean): MyBoolean = ifThenElse(True, x)

  def unary_! : MyBoolean = ifThenElse(False, True)
  
  def == (x: MyBoolean): MyBoolean = ifThenElse(x, x.unary_!)
  def != (x: MyBoolean): MyBoolean = ifThenElse(x.unary_!, x)
  
  
  def < (x: MyBoolean): MyBoolean = ifThenElse(False,x)
}

object False extends MyBoolean{
  def ifThenElse[T](t: => T, e: => T) =  e
}

object True extends MyBoolean{
  def ifThenElse[T](t: => T , e: => T ) = t
}



object Assert{
  def apply(b: Boolean) = if (b == True) true else false
}

