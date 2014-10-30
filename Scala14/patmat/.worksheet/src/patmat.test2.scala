package patmat
import Huffman._
object test2 {

  //val text = ("scala.NotImplementedError: an implementation is missing exception was thrown]"+
  // "detailed error message in debug output section below[Lost Points] 15").toList
type bit = Int;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(288); 



val aTable = createCodeTree("aaa".toList);System.out.println("""aTable  : patmat.Huffman.CodeTree = """ + $show(aTable ));$skip(39); 
 
val input: List[Bit] = List(1,0,0,1);System.out.println("""input  : List[patmat.Huffman.Bit] = """ + $show(input ));$skip(35); 
val tree1: CodeTree = Leaf('a', 1);System.out.println("""tree1  : patmat.Huffman.CodeTree = """ + $show(tree1 ));$skip(78); 
val tree2: CodeTree = Fork(Leaf('a', 1), Leaf('b', 1), string2Chars("ab"), 2);System.out.println("""tree2  : patmat.Huffman.CodeTree = """ + $show(tree2 ));$skip(147); val res$0 = 
//decode(createCodeTree("baab".toList), input)
//decode(tree2, input)
//decode(Leaf('a', 1), List(1, 1, 1,  1))
decode(frenchCode,secret).toString;System.out.println("""res0: String = """ + $show(res$0));$skip(540); 
 
 
def encode2(tree: CodeTree)(text: List[Char]): List[Bit] = {
	  def loop(loopTree: CodeTree,loopText:List[Char], acc:List[Bit]): List[Bit] = loopText match {
	    case List() => acc
	    case x :: xs =>  loopTree match {
	      case Leaf(c,w) => if (c==x) acc ::: loop(tree,xs,List()) else throw new Error("wrong tree")
	      case Fork(lt,rt,cx,w) => if(chars(lt).contains(x)) loop(lt,loopText, acc ::: List(0))
	      							else
	      							  loop(rt, loopText, acc ::: List(1))
	      
	    }
	  }
    loop(tree,text, List())
  };System.out.println("""encode2: (tree: patmat.Huffman.CodeTree)(text: List[Char])List[patmat.Huffman.Bit]""");$skip(83); val res$1 = 


encode2(createCodeTree("baabdgjsjhdgjsgdsgdjsgdjsdjgc".toList))("baabcd".toList);System.out.println("""res1: List[patmat.Huffman.Bit] = """ + $show(res$1));$skip(56); val res$2 = 
encode2(createCodeTree("baabc".toList))("baabc".toList);System.out.println("""res2: List[patmat.Huffman.Bit] = """ + $show(res$2));$skip(32); val res$3 = 

createCodeTree("baabc".toList);System.out.println("""res3: patmat.Huffman.CodeTree = """ + $show(res$3));$skip(64); 
     
val t1 = Fork(Leaf('a',2), Leaf('b',3), List('a','b'), 5);System.out.println("""t1  : patmat.Huffman.Fork = """ + $show(t1 ));$skip(25); val res$4 = 
encode2(t1)("ab".toList);System.out.println("""res4: List[patmat.Huffman.Bit] = """ + $show(res$4));$skip(37); val res$5 = 
decode(t1, encode2(t1)("ab".toList));System.out.println("""res5: List[Char] = """ + $show(res$5));$skip(61); 


//=== s.toList)
      
 val ttt = List(('a', 2), ('b', 1));System.out.println("""ttt  : List[(Char, Int)] = """ + $show(ttt ));$skip(19); 
 val m = ttt.toMap;System.out.println("""m  : scala.collection.immutable.Map[Char,Int] = """ + $show(m ));$skip(8); val res$6 = 
 m('a');System.out.println("""res6: Int = """ + $show(res$6))}


  /**
   * This function returns the bit sequence that represents the character `char` in
   * the code table `table`.
   */

    
}
