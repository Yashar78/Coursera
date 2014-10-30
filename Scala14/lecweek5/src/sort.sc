object sort {
 def isort(xs:List[Int]): List[Int] = xs match{
 	case List() => List()
 	case y :: ys => insert(y,isort(ys))
 }                                                //> isort: (xs: List[Int])List[Int]
 
 def insert(x: Int, xs:List[Int]):List[Int] = xs  match {
 	case List() =>  List(x)
 	//case y :: ys => if (y < x ) y :: insert(x, ys) else x :: y :: ys
 	case y :: ys => if ( x <= y ) x :: xs else y :: insert(x,ys)
 }                                                //> insert: (x: Int, xs: List[Int])List[Int]

isort(List(2,2,489,21))                           //> res0: List[Int] = List(2, 2, 21, 489)
}