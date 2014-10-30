object funList {
 def pack[T](xs: List[T]): List[List[T]] = xs match {
    case Nil      => Nil
    case x :: xs1 =>
      val (first, rest) = xs span (y => y== x)
      first :: pack(rest)
  }
 val l = List("a", "a", "a", "b", "c", "c", "a")
 
l span (x => x!="b")
 
val p = pack(l)
p map (x => x.length)

p.map(x => (x.head,x.length))
 def encode[T](xs: List[T]): List[(T,Int)] =
 	pack(xs).map(x => (x.head,x.length))

encode(l)

def mapFun[T, U](xs: List[T], f: T => U): List[U] =
    (xs foldRight List[U]())( f(_)::_ )
    
   val ll = List(1,2)
 mapFun(ll, ( x =>  x == 3))
   
 def mm[T](xs: List[T]): Int = {
    (xs foldRight 0)( _ + 1 )
 }
}