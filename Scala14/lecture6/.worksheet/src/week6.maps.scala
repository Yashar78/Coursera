package week6

object maps {;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(92); 
  val capitalOfCountry = Map("US" -> "WDC", "Iran" -> "Tehran");System.out.println("""capitalOfCountry  : scala.collection.immutable.Map[String,String] = """ + $show(capitalOfCountry ));$skip(61); val res$0 = 
 // capitalOfCountry("Hasan")
  capitalOfCountry get "hasan";System.out.println("""res0: Option[String] = """ + $show(res$0));$skip(28); val res$1 = 
  capitalOfCountry get "US";System.out.println("""res1: Option[String] = """ + $show(res$1));$skip(149); 
  
  def showCapital( country: String ) = capitalOfCountry.get(country) match {
  	case Some(capital) => capital
  	case None => "No Country"
  
  };System.out.println("""showCapital: (country: String)String""");$skip(26); val res$2 = 
  
  showCapital("Hasan");System.out.println("""res2: String = """ + $show(res$2));$skip(20); val res$3 = 
  showCapital("US");System.out.println("""res3: String = """ + $show(res$3))}


}
