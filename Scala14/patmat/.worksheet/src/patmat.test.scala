package patmat
import Huffman._
import com.sun.tools.javac.util.Convert

object test {;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(441); 
 //times(List('h', 'e', 'l', 'l', 'o', ',', ' ', 'w', 'o', 'r', 'l', 'd'))
 

//  val engToDeu = Map(("d",1), ("c",2), ("r",2))
//engToDeu.contains("e")
//val numbers = collection.mutable.Map(1 -> 2)
//numbers.get(1)
//numbers.getOrElseUpdate(2, 3)
//numbers
//numbers += (4 -> 1)

val l = List('h', 'e', 'l', 'l', 'o', ',', ' ', 'w', 'o', 'r', 'l', 'd');System.out.println("""l  : List[Char] = """ + $show(l ));$skip(71); 


val words = List("one", "two", "one", "three", "four", "two", "one");System.out.println("""words  : List[String] = """ + $show(words ));$skip(129); 

def isort(xs:List[(Char, Int)]): List[(Char, Int)] = xs match{
 	case List() => List()
 	case y :: ys => insert(y,isort(ys))
 };System.out.println("""isort: (xs: List[(Char, Int)])List[(Char, Int)]""");$skip(252); 
 
 def insert(x: (Char, Int), xs:List[(Char, Int)]):List[(Char, Int)] = xs  match {
 	case List() =>  List(x)
 	//case y :: ys => if (y < x ) y :: insert(x, ys) else x :: y :: ys

 	case y :: ys => if ( x._2 <= y._2 ) x :: xs else y :: insert(x,ys)
 }

  abstract class CodeTree
  case class Fork(left: CodeTree, right: CodeTree, chars: List[Char], weight: Int) extends CodeTree
  case class Leaf(char: Char, weight: Int) extends CodeTree;System.out.println("""insert: (x: (Char, Int), xs: List[(Char, Int)])List[(Char, Int)]""");$skip(238); 
val l2 = isort(List(('t', 2), ('e', 1), ('x', 3)));System.out.println("""l2  : List[(Char, Int)] = """ + $show(l2 ));$skip(34); val res$0 = 
l2.map(x => new  Leaf(x._1,x._2));System.out.println("""res0: List[patmat.test.Leaf] = """ + $show(res$0));$skip(10); val res$1 = 
l2.length;System.out.println("""res1: Int = """ + $show(res$1));$skip(314); 



	def insertTree(x: Fork, xs:List[CodeTree]):List[CodeTree] = xs  match {
	 	case List() =>  List(x)
	 	case Leaf(c,w) :: ys => if ( x.weight <= w ) x :: xs else new Leaf(c,w) :: insertTree(x,ys)
	 	case Fork(lt,rt,cx,w) :: ys => if ( x.weight <= w ) x :: xs else new Fork(lt,rt,cx,w) :: insertTree(x,ys)
	 	
	};System.out.println("""insertTree: (x: patmat.test.Fork, xs: List[patmat.test.CodeTree])List[patmat.test.CodeTree]""");$skip(793); 
	

  def combine(trees: List[CodeTree]): List[CodeTree] = trees match {
    case List() => trees
    case x :: List() => trees
    //case x :: y :: xs =>   new Fork(x,y, x.chars ::: y.chars, x.weight+y.weight) :: xs
    case Leaf(cl,wl) :: Leaf(cr,wr) :: xs =>   insertTree(new Fork(new Leaf(cl,wl), new Leaf(cr,wr), List(cl,cr), wl+wr), xs)
    case Leaf(cl,wl) :: Fork(lt,rt,cx,wt) :: xs =>  insertTree( new Fork(new Leaf(cl,wl), new Fork(lt,rt,cx,wt), cl :: cx, wl+wt), xs)
    case Fork(lt,rt,cx,wt) :: Leaf(cr,wr) :: xs =>   insertTree(new Fork(new Fork(lt,rt,cx,wt), new Leaf(cr,wr), cr :: cx, wr+wt) , xs)
    case Fork(llt,lrt,lcx,lwt) :: Fork(rlt,rrt,rcx,rwt) :: xs => insertTree( new Fork(new Fork(llt,lrt,lcx,lwt), new Fork(rlt,rrt,rcx,rwt), lcx ::: rcx, lwt+rwt),xs)
    
    
  };System.out.println("""combine: (trees: List[patmat.test.CodeTree])List[patmat.test.CodeTree]""");$skip(76); 
  
  val ll = List(Leaf('a', 2), Leaf('b', 2), Leaf('c', 3), Leaf('d', 10));System.out.println("""ll  : List[patmat.test.Leaf] = """ + $show(ll ));$skip(24); 
  val l12 = combine(ll);System.out.println("""l12  : List[patmat.test.CodeTree] = """ + $show(l12 ));$skip(27); val res$2 = 
     combine(combine(l12));System.out.println("""res2: List[patmat.test.CodeTree] = """ + $show(res$2));$skip(135); 
  
    def until(sing: List[CodeTree] => Boolean, comb: List[CodeTree] => List[CodeTree])(trees: List[CodeTree]): List[CodeTree] = ???;System.out.println("""until: (sing: List[patmat.test.CodeTree] => Boolean, comb: List[patmat.test.CodeTree] => List[patmat.test.CodeTree])(trees: List[patmat.test.CodeTree])List[patmat.test.CodeTree]""");$skip(9); val res$3 = 
		  null;System.out.println("""res3: Null(null) = """ + $show(res$3));$skip(291); 
		 
/*
words.groupBy(w => w).mapValues(_.size).toList

  def makeOrderedLeafList(freqs: List[(Char, Int)]): List[Leaf] =
  	def loop(fs: List[(Char, Int)], acc List[Leaf]): List[Leaf] = fs match{
  		case List => acc
  		case x :: xs =>
  	}

  	
  	
  */
  val ct = List(('a',List(1,2,3)))
    type CodeTable = List[(Char, List[Bit])];System.out.println("""ct  : List[(Char, List[Int])] = """ + $show(ct ));$skip(92); 
 val s = List(('a',List(1,2,3))).toMap get 'a';System.out.println("""s  : Option[List[Int]] = """ + $show(s ));$skip(4); val res$4 = 
  s
  type Bit = Int;System.out.println("""res4: Option[List[Int]] = """ + $show(res$4));$skip(225); 
  def codeBits(table: CodeTable)(char: Char): List[Bit] = table match {
    case List() => throw new Error("chracter does not exist")
    case (x,y) :: xs => if (x == char) y else codeBits(xs)(char)
    
  };System.out.println("""codeBits: (table: patmat.test.CodeTable)(char: Char)List[patmat.test.Bit]""");$skip(20); val res$5 = 

 codeBits(ct)('a');System.out.println("""res5: List[patmat.test.Bit] = """ + $show(res$5));$skip(55); 
    
 val ttt = List(('a', List(2)), ('b', List(1,2)));System.out.println("""ttt  : List[(Char, List[Int])] = """ + $show(ttt ));$skip(19); 
 val m = ttt.toMap;System.out.println("""m  : scala.collection.immutable.Map[Char,List[Int]] = """ + $show(m ));$skip(8); val res$6 = 
 m('a');System.out.println("""res6: List[Int] = """ + $show(res$6));$skip(45); 
  
  val t1 = createCodeTree("baabc".toList);System.out.println("""t1  : patmat.Huffman.CodeTree = """ + $show(t1 ));$skip(14); val res$7 = 
  convert(t1);System.out.println("""res7: patmat.Huffman.CodeTable = """ + $show(res$7));$skip(42); val res$8 = 
  quickEncode(t1)("aaabbcccacaca".toList);System.out.println("""res8: List[patmat.Huffman.Bit] = """ + $show(res$8))}
  
                                                  
}
