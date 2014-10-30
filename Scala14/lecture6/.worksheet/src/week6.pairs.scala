package week6

object pairs {;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(153); 

	def isPrime(n: Int): Boolean =
		//!((2 until n) map (x => n %x) exists (y => y==0))
    (2 until n) forall (x => n%x!=0);System.out.println("""isPrime: (n: Int)Boolean""");$skip(10); 
val n = 7;System.out.println("""n  : Int = """ + $show(n ));$skip(68); val res$0 = 
 ((1 until n) map (i =>
 		(1 until i) map ( j  => (i,j)))).flatten;System.out.println("""res0: scala.collection.immutable.IndexedSeq[(Int, Int)] = """ + $show(res$0));$skip(111); val res$1 = 
   (1 until n) flatMap (i =>
 		(1 until i) map ( j  => (i,j))) filter (pair =>
 		isPrime(pair._1 + pair._2));System.out.println("""res1: scala.collection.immutable.IndexedSeq[(Int, Int)] = """ + $show(res$1));$skip(121); 
 		
	def scalarProduct(xs: Vector[Double], ys: Vector[Double]): Double =

			(for ( (x,y) <- xs zip ys) yield x * y).sum;System.out.println("""scalarProduct: (xs: Vector[Double], ys: Vector[Double])Double""")}
 
}
