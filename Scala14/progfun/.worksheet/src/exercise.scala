import math.abs

object exercise {;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(58); 

val tolerance = 0.0001;System.out.println("""tolerance  : Double = """ + $show(tolerance ));$skip(68); 
def isCloseEnough(x: Double, y:Double)=
	abs((x-y)/x)/x < tolerance;System.out.println("""isCloseEnough: (x: Double, y: Double)Boolean""");$skip(234); 

def fixedPoint(f: Double => Double)(firstGuess: Double)={
	def iterate(guess: Double): Double ={
		val next = f(guess)
		println("guess="+guess)
		if (isCloseEnough(guess, next)) next
		else
		iterate(next)
	}
	iterate(firstGuess)
};System.out.println("""fixedPoint: (f: Double => Double)(firstGuess: Double)Double""");$skip(26); val res$0 = 
fixedPoint(x => 1+x/2)(1);System.out.println("""res0: Double = """ + $show(res$0));$skip(65); 



def averageDamp(f: Double => Double)(x: Double) = (x+f(x)) /2;System.out.println("""averageDamp: (f: Double => Double)(x: Double)Double""");$skip(26); val res$1 = 

averageDamp(x => x+4)(5);System.out.println("""res1: Double = """ + $show(res$1));$skip(60); 
def sqrt(x: Double) =
 fixedPoint(averageDamp(y => x/y))(1);System.out.println("""sqrt: (x: Double)Double""");$skip(10); val res$2 = 


sqrt(2);System.out.println("""res2: Double = """ + $show(res$2))}
}
