package week6

object sorts {
  
  val fruit = List("apple", "orange", "cucumber", "pear", "pineapple")
                                                  //> fruit  : List[String] = List(apple, orange, cucumber, pear, pineapple)
  
  fruit sortWith (_.length < _.length)            //> res0: List[String] = List(pear, apple, orange, cucumber, pineapple)
	fruit.sorted                              //> res1: List[String] = List(apple, cucumber, orange, pear, pineapple)
  fruit groupBy (_.head)                          //> res2: scala.collection.immutable.Map[Char,List[String]] = Map(p -> List(pear
                                                  //| , pineapple), a -> List(apple), c -> List(cucumber), o -> List(orange))

}