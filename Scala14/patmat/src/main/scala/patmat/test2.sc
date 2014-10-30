package patmat
import Huffman._
object test2 {

  //val text = ("scala.NotImplementedError: an implementation is missing exception was thrown]"+
  // "detailed error message in debug output section below[Lost Points] 15").toList
type bit = Int



val aTable = createCodeTree("aaa".toList)
 
val input: List[Bit] = List(1,0,0,1)
val tree1: CodeTree = Leaf('a', 1)
val tree2: CodeTree = Fork(Leaf('a', 1), Leaf('b', 1), string2Chars("ab"), 2)
//decode(createCodeTree("baab".toList), input)
//decode(tree2, input)
//decode(Leaf('a', 1), List(1, 1, 1,  1))
decode(frenchCode,secret).toString
 
 
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
  }


encode2(createCodeTree("baabdgjsjhdgjsgdsgdjsgdjsdjgc".toList))("baabcd".toList)
encode2(createCodeTree("baabc".toList))("baabc".toList)

createCodeTree("baabc".toList)
     
val t1 = Fork(Leaf('a',2), Leaf('b',3), List('a','b'), 5)
encode2(t1)("ab".toList)
decode(t1, encode2(t1)("ab".toList))


//=== s.toList)
      
 val ttt = List(('a', 2), ('b', 1))
 val m = ttt.toMap
 m('a')


  /**
   * This function returns the bit sequence that represents the character `char` in
   * the code table `table`.
   */

    
}