package week6

object maps {
  val capitalOfCountry = Map("US" -> "WDC", "Iran" -> "Tehran")
                                                  //> capitalOfCountry  : scala.collection.immutable.Map[String,String] = Map(US ->
                                                  //|  WDC, Iran -> Tehran)
 // capitalOfCountry("Hasan")
  capitalOfCountry get "hasan"                    //> res0: Option[String] = None
  capitalOfCountry get "US"                       //> res1: Option[String] = Some(WDC)
  
  def showCapital( country: String ) = capitalOfCountry.get(country) match {
  	case Some(capital) => capital
  	case None => "No Country"
  
  }                                               //> showCapital: (country: String)String
  
  showCapital("Hasan")                            //> res2: String = No Country
  showCapital("US")                               //> res3: String = WDC


}

