package week6



object test {;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(55); 
	val x = Array(1,2,3,44);System.out.println("""x  : Array[Int] = """ + $show(x ));$skip(21); val res$0 = 
	x map ( x => 2 *x );System.out.println("""res0: Array[Int] = """ + $show(res$0));$skip(23); 
	val s = "Hello World";System.out.println("""s  : String = """ + $show(s ));$skip(27); val res$1 = 
	s filter (c => c.isUpper);System.out.println("""res1: String = """ + $show(res$1));$skip(29); val res$2 = 
	
	s exists (c => c.isUpper);System.out.println("""res2: Boolean = """ + $show(res$2));$skip(27); val res$3 = 
	s forall (c => c.isUpper);System.out.println("""res3: Boolean = """ + $show(res$3));$skip(33); 
	
	val pairs = List(1,2,3) zip s;System.out.println("""pairs  : List[(Int, Char)] = """ + $show(pairs ));$skip(13); val res$4 = 
	pairs.unzip;System.out.println("""res4: (List[Int], List[Char]) = """ + $show(res$4));$skip(31); val res$5 = 
	
	s.flatMap(c => List('.',c));System.out.println("""res5: String = """ + $show(res$5));$skip(11); val res$6 = 
	(1 to 10);System.out.println("""res6: scala.collection.immutable.Range.Inclusive = """ + $show(res$6));$skip(11); 
	val M = 6;System.out.println("""M  : Int = """ + $show(M ));$skip(11); 
	val N = 5;System.out.println("""N  : Int = """ + $show(N ));$skip(47); val res$7 = 
  (1 to M) flatMap (x => List(x) zip (1 to N));System.out.println("""res7: scala.collection.immutable.IndexedSeq[(Int, Int)] = """ + $show(res$7));$skip(56); val res$8 = 
  
   (1 to M) flatMap (x => (1 to N) map (y => (x,y)));System.out.println("""res8: scala.collection.immutable.IndexedSeq[(Int, Int)] = """ + $show(res$8));$skip(112); 
	def scalarProduct(xs: Vector[Double], ys: Vector[Double]): Double =
		(xs zip ys).map(xy => xy._1 * xy._2).sum;System.out.println("""scalarProduct: (xs: Vector[Double], ys: Vector[Double])Double""");$skip(115); 
  def scalarProduct2(xs: Vector[Double], ys: Vector[Double]): Double =
		(xs zip ys).map{case (x,y)=> (x * y)}.sum;System.out.println("""scalarProduct2: (xs: Vector[Double], ys: Vector[Double])Double""");$skip(125); 
	
	def isPrime(n: Int): Boolean =
		//!((2 until n) map (x => n %x) exists (y => y==0))
    (2 until n) forall (x => n%x!=0);System.out.println("""isPrime: (n: Int)Boolean""");$skip(22); val res$9 = 
    
		
		isPrime(67);System.out.println("""res9: Boolean = """ + $show(res$9));$skip(56); val res$10 = 
		
		!(( 2 until 4) map(x => 4 % x) exists (y => y==0));System.out.println("""res10: Boolean = """ + $show(res$10))}
}
