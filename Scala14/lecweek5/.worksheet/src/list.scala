object list {;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(184); 
  def init[T](xs: List[T]): List[T] = xs match {
    case List() => throw new Error("init of empty list")
    case List(x) => List()
    case y :: ys => y :: init(ys)
  };System.out.println("""init: [T](xs: List[T])List[T]""");$skip(37); 
  
  val l = List(10,23,1,432,3,4,5);System.out.println("""l  : List[Int] = """ + $show(l ));$skip(10); val res$0 = 
  init(l);System.out.println("""res0: List[Int] = """ + $show(res$0));$skip(150); 
  
  def element[T](n: Int, xs: List[T]):T =
  	if (n==0) xs.head
  	else if (n < 0) throw new Error ("out of bound")
  	else
  	element(n-1,xs.tail);System.out.println("""element: [T](n: Int, xs: List[T])T""");$skip(18); val res$1 = 
  
  element(1,l);System.out.println("""res1: Int = """ + $show(res$1));$skip(344); 
  
  def removeAt[T](n: Int, xs: List[T]):List[T] = {
  	def loop(nn:Int, lxs:List[T], acc: List[T]): List[T] ={
  		if (nn==0) if (lxs.length>0) acc ::: lxs.tail else throw new Error ("out of bound")
  			else if (nn < 0) throw new Error ("out of bound")
  		else
  			 loop(nn-1,lxs.tail,acc:::List(lxs.head))
  	  }
  	loop(n,xs,List())
  };System.out.println("""removeAt: [T](n: Int, xs: List[T])List[T]""");$skip(17); val res$2 = 
  removeAt(3, l);System.out.println("""res2: List[Int] = """ + $show(res$2));$skip(14); val res$3 = 
  
  l take 2;System.out.println("""res3: List[Int] = """ + $show(res$3));$skip(11); val res$4 = 
  l drop 3;System.out.println("""res4: List[Int] = """ + $show(res$4));$skip(463); 
  
  def msort[T](xs:List[T])(lt: (T,T)=> Boolean):List[T]= {
			val n=xs.length/2
			if(n == 0) xs
			else {
			def merge(xs: List[T], ys: List[T]): List[T] =
			    (xs, ys) match {
			      case (Nil,ys) => ys
			      case (ys,Nil) => xs
			      case (x::xs1,y::ys1) => if(lt(x , y)) x :: merge(xs1, ys)
																	else
																	y :: merge(xs, ys1)
			    }
				
			val (fst, snd)= xs splitAt n
			merge(msort(fst)(lt), msort(snd)(lt))
			}
	};System.out.println("""msort: [T](xs: List[T])(lt: (T, T) => Boolean)List[T]""");$skip(35); val res$5 = 
  msort(l)((x:Int, y:Int)=> x < y)
  
  
  /////
  import math.Ordering;System.out.println("""res5: List[Int] = """ + $show(res$5));$skip(509); 
    def msortOrd[T](xs:List[T])(implicit ord:Ordering[T]):List[T]= {
			val n=xs.length/2
			if(n == 0) xs
			else {
			def merge(xs: List[T], ys: List[T]): List[T] =
			    (xs, ys) match {
			      case (Nil,ys) => ys
			      case (ys,Nil) => xs
			      case (x::xs1,y::ys1) => if(ord.lt(x , y)) x :: merge(xs1, ys)
																	else
																	y :: merge(xs, ys1)
			    }
				
			val (fst, snd)= xs splitAt n
			merge(msortOrd(fst), msortOrd(snd))
			}
	};System.out.println("""msortOrd: [T](xs: List[T])(implicit ord: scala.math.Ordering[T])List[T]""");$skip(14); val res$6 = 
  msortOrd(l);System.out.println("""res6: List[Int] = """ + $show(res$6));$skip(65); 
  


  def squareList(xs: List[Int]): List[Int] =
    xs map ???;System.out.println("""squareList: (xs: List[Int])List[Int]""")}
    
  
  
}
