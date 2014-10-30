package files
import myPkg.MyRational

object scratch {;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(85); 
	val r1 = new MyRational(1,2);System.out.println("""r1  : myPkg.MyRational = """ + $show(r1 ));$skip(4); val res$0 = 
	r1;System.out.println("""res0: myPkg.MyRational = """ + $show(res$0))}
}
