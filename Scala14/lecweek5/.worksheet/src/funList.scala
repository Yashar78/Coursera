object funList {;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(193); 
 def pack[T](xs: List[T]): List[List[T]] = xs match {
    case Nil      => Nil
    case x :: xs1 =>
      val (first, rest) = xs span (y => y== x)
      first :: pack(rest)
  };System.out.println("""pack: [T](xs: List[T])List[List[T]]""");$skip(49); 
 val l = List("a", "a", "a", "b", "c", "c", "a");System.out.println("""l  : List[String] = """ + $show(l ));$skip(23); val res$0 = 
 
l span (x => x!="b");System.out.println("""res0: (List[String], List[String]) = """ + $show(res$0));$skip(18); 
 
val p = pack(l);System.out.println("""p  : List[List[String]] = """ + $show(p ));$skip(22); val res$1 = 
p map (x => x.length);System.out.println("""res1: List[Int] = """ + $show(res$1));$skip(31); val res$2 = 

p.map(x => (x.head,x.length));System.out.println("""res2: List[(String, Int)] = """ + $show(res$2));$skip(84); 
 def encode[T](xs: List[T]): List[(T,Int)] =
 	pack(xs).map(x => (x.head,x.length));System.out.println("""encode: [T](xs: List[T])List[(T, Int)]""");$skip(11); val res$3 = 

encode(l);System.out.println("""res3: List[(String, Int)] = """ + $show(res$3));$skip(93); 

def mapFun[T, U](xs: List[T], f: T => U): List[U] =
    (xs foldRight List[U]())( f(_)::_ );System.out.println("""mapFun: [T, U](xs: List[T], f: T => U)List[U]""");$skip(27); 
    
   val ll = List(1,2);System.out.println("""ll  : List[Int] = """ + $show(ll ));$skip(29); val res$4 = 
 mapFun(ll, ( x =>  x == 3));System.out.println("""res4: List[Nothing] = """ + $show(res$4));$skip(70); 
   
 def mm[T](xs: List[T]): Int = {
    (xs foldRight 0)( _ + 1 )
 };System.out.println("""mm: [T](xs: List[T])Int""")}
}
