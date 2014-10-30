package week6

import scala.io.Source

object x {;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(160); 
	val in = Source.fromURL("http://lamp.epfl.ch/files/content/sites/lamp/files/teaching/progfun/linuxwords.txt");System.out.println("""in  : scala.io.BufferedSource = """ + $show(in ));$skip(82); 
  val words = in.getLines.toList filter (word => word forall (ch => ch.isLetter));System.out.println("""words  : List[String] = """ + $show(words ));$skip(145); 
  
  val mnem = Map(
  '2' -> "ABC", '3' -> "DEF" , '4' -> "GHI" , '5' -> "JKL" ,
  '6' -> "MNO" , '7' -> "PQRS" , '8' -> "TUV" , '9' -> "WXYZ");System.out.println("""mnem  : scala.collection.immutable.Map[Char,String] = """ + $show(mnem ));$skip(97); 
  
  val charCode: Map[Char, Char] =
  	for((digit, str) <- mnem; ltr <- str) yield ltr -> digit;System.out.println("""charCode  : Map[Char,Char] = """ + $show(charCode ));$skip(113); 
  	
		def wordCode(word: String): String =
		//for(c <- word ) yield charCode(c)
		word.toUpperCase map charCode;System.out.println("""wordCode: (word: String)String""");$skip(96); 
	
	val wordsForNum: Map[String, Seq[String]] =
		words groupBy wordCode  withDefaultValue Seq();System.out.println("""wordsForNum  : Map[String,Seq[String]] = """ + $show(wordsForNum ));$skip(262); 
  def encode(number: String): Set[List[String]] =
  	if (number.isEmpty) Set(List())
  	else {
  		for {
  			split <- 1 to number.length
  			word <- wordsForNum(number take split)
  			rest <- encode(number drop split)
  			
  		} yield word::rest
  		}.toSet;System.out.println("""encode: (number: String)Set[List[String]]""");$skip(29); val res$0 = 
  		
  		
  		
  encode("3");System.out.println("""res0: Set[List[String]] = """ + $show(res$0));$skip(88); 
  
  def translate(number:String): Set[String] =
  	encode(number) map (_ mkString " ");System.out.println("""translate: (number: String)Set[String]""")}
  

}
