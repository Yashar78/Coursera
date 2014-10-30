object books {
	case class Book(title: String, authors: List[String])
	val books:List[Book]=List(Book(title="Structure and Interpretation of Computer Programs",authors
=
List
(
"Abelson, Harald"
,
"Sussman, Gerald J."
)),
Book
(
title
=
"Introduction to Functional Programming"
,
authors
=
List
(
"Bird, Richard"
,
"Wadler, Phil"
)),
Book
(
title
=
"Effective Java"
,
authors
=
List
(
"Bloch, Joshua"
)),
Book
(
title
=
"Java Puzzlers"
,
authors
=
List
(
"Bloch, Joshua"
,
"Gafter, Neal"
)),
Book
(
title
=
"Programming in Scala"
,
authors
=
List
(
"Odersky, Martin"
,
"Spoon, Lex"
,
"Venners, Bill"
)))                                               //> books  : List[books.Book] = List(Book(Structure and Interpretation of Comput
                                                  //| er Programs,List(Abelson, Harald, Sussman, Gerald J.)), Book(Introduction to
                                                  //|  Functional Programming,List(Bird, Richard, Wadler, Phil)), Book(Effective J
                                                  //| ava,List(Bloch, Joshua)), Book(Java Puzzlers,List(Bloch, Joshua, Gafter, Nea
                                                  //| l)), Book(Programming in Scala,List(Odersky, Martin, Spoon, Lex, Venners, Bi
                                                  //| ll)))
for( b <- books; a <- b.authors if a startsWith "Bird,")
 	yield b.title                             //> res0: List[String] = List(Introduction to Functional Programming)
 	
 	
 	
(for(b <- books; a <- b.authors ) yield (a,b.title)).groupBy(w => w).mapValues(_.size)
                                                  //> res1: scala.collection.immutable.Map[(String, String),Int] = Map((Bloch, Jos
                                                  //| hua,Effective Java) -> 1, (Bloch, Joshua,Java Puzzlers) -> 1, (Venners, Bill
                                                  //| ,Programming in Scala) -> 1, (Abelson, Harald,Structure and Interpretation o
                                                  //| f Computer Programs) -> 1, (Gafter, Neal,Java Puzzlers) -> 1, (Spoon, Lex,Pr
                                                  //| ogramming in Scala) -> 1, (Wadler, Phil,Introduction to Functional Programmi
                                                  //| ng) -> 1, (Sussman, Gerald J.,Structure and Interpretation of Computer Progr
                                                  //| ams) -> 1, (Bird, Richard,Introduction to Functional Programming) -> 1, (Ode
                                                  //| rsky, Martin,Programming in Scala) -> 1)
for {

b1 <- books
b2 <- books
if b1.title < b2.title
a1 <- b1.authors
a2 <- b2.authors
if a1==a2
} yield a1                                        //> res2: List[String] = List(Bloch, Joshua)
 	
}