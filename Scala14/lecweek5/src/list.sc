object list {
  def init[T](xs: List[T]): List[T] = xs match {
    case List() => throw new Error("init of empty list")
    case List(x) => List()
    case y :: ys => y :: init(ys)
  }                                               //> init: [T](xs: List[T])List[T]
  
  val l = List(10,23,1,432,3,4,5)                 //> l  : List[Int] = List(10, 23, 1, 432, 3, 4, 5)
  init(l)                                         //> res0: List[Int] = List(10, 23, 1, 432, 3, 4)
  
  def element[T](n: Int, xs: List[T]):T =
  	if (n==0) xs.head
  	else if (n < 0) throw new Error ("out of bound")
  	else
  	element(n-1,xs.tail)                      //> element: [T](n: Int, xs: List[T])T
  
  element(1,l)                                    //> res1: Int = 23
  
  def removeAt[T](n: Int, xs: List[T]):List[T] = {
  	def loop(nn:Int, lxs:List[T], acc: List[T]): List[T] ={
  		if (nn==0) if (lxs.length>0) acc ::: lxs.tail else throw new Error ("out of bound")
  			else if (nn < 0) throw new Error ("out of bound")
  		else
  			 loop(nn-1,lxs.tail,acc:::List(lxs.head))
  	  }
  	loop(n,xs,List())
  }                                               //> removeAt: [T](n: Int, xs: List[T])List[T]
  removeAt(3, l)                                  //> res2: List[Int] = List(10, 23, 1, 3, 4, 5)
  
  l take 2                                        //> res3: List[Int] = List(10, 23)
  l drop 3                                        //> res4: List[Int] = List(432, 3, 4, 5)
  
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
	}                                         //> msort: [T](xs: List[T])(lt: (T, T) => Boolean)List[T]
  msort(l)((x:Int, y:Int)=> x < y)                //> res5: List[Int] = List(1, 3, 4, 5, 10, 23, 432)
  
  
  /////
  import math.Ordering
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
	}                                         //> msortOrd: [T](xs: List[T])(implicit ord: scala.math.Ordering[T])List[T]
  msortOrd(l)                                     //> res6: List[Int] = List(1, 3, 4, 5, 10, 23, 432)
  


  def squareList(xs: List[Int]): List[Int] =
    xs map ???                                    //> squareList: (xs: List[Int])List[Int]
    
  
  
}