package commons

object intsets {;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(82); 

val t1 = new NonEmpty( 3, new Empty, new Empty);System.out.println("""t1  : commons.NonEmpty = """ + $show(t1 ));$skip(19); 
val t2 = t1 incl 4;System.out.println("""t2  : commons.IntSet = """ + $show(t2 ));$skip(48); 
val t3 = new NonEmpty(45, new Empty, new Empty);System.out.println("""t3  : commons.NonEmpty = """ + $show(t3 ));$skip(22); val res$0 = 

t1 union t3 union t2;System.out.println("""res0: commons.IntSet = """ + $show(res$0))}
}

abstract class IntSet{
	def incl(x: Int): IntSet
	def contains(x: Int): Boolean
	def union(other: IntSet): IntSet
}

class Empty extends IntSet{
def incl(x: Int): IntSet = new NonEmpty(x, new Empty, new Empty)
def contains(x: Int): Boolean = false
override def toString ="."
def union(other: IntSet): IntSet = other
}

class NonEmpty(elem: Int, left: IntSet, right: IntSet) extends IntSet{

	def contains(x: Int): Boolean =
		if (x < elem)
			left contains x
		else if ( x > elem)
			right contains x
		else
			true
	
	def incl(x: Int): IntSet =
		if ( x < elem)
			new NonEmpty(elem, left incl x , right)
		else if ( x > elem)
			new NonEmpty(elem, left, right incl x)
		else
			this
			
	def union(other: IntSet): IntSet =
	((left union right) union other) incl elem
	
	
	
	
	
	
		
	override def toString= "{" + left + elem + right + "}"
}
