package objsets



object testSheet {;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(81); 
  println("Welcome to the Scala worksheet");$skip(27); 
  
   val set1 = new Empty;System.out.println("""set1  : objsets.Empty = """ + $show(set1 ));$skip(55); 
    val set2 = set1.incl(new Tweet("a", "a body", 15));System.out.println("""set2  : objsets.TweetSet = """ + $show(set2 ));$skip(55); 
    val set3 = set2.incl(new Tweet("b", "b body", 25));System.out.println("""set3  : objsets.TweetSet = """ + $show(set3 ));$skip(45); 
    val c = new Tweet("rahim", "c body", 15);System.out.println("""c  : objsets.Tweet = """ + $show(c ));$skip(47); 
    val d = new Tweet("rahims", "d body", 12 );System.out.println("""d  : objsets.Tweet = """ + $show(d ));$skip(29); 
    val set4c = set3.incl(c);System.out.println("""set4c  : objsets.TweetSet = """ + $show(set4c ));$skip(29); 
    val set4d = set3.incl(d);System.out.println("""set4d  : objsets.TweetSet = """ + $show(set4d ));$skip(29); 
    val set5 = set4c.incl(d);System.out.println("""set5  : objsets.TweetSet = """ + $show(set5 ));$skip(121); 
    
    def asSet(tweets: TweetSet): Set[Tweet] = {
    var res = Set[Tweet]()
    tweets.foreach(res += _)
    res
  };System.out.println("""asSet: (tweets: objsets.TweetSet)Set[objsets.Tweet]""");$skip(50); 

  def size(set: TweetSet): Int = asSet(set).size;System.out.println("""size: (set: objsets.TweetSet)Int""");$skip(47); val res$0 = 
	
	size(set5.filter(tw => tw.user == "rahim"));System.out.println("""res0: Int = """ + $show(res$0));$skip(45); val res$1 = 
  size(set5.filter(tw => tw.retweets == 20));System.out.println("""res1: Int = """ + $show(res$1));$skip(32); val res$2 = 
  
  
	size(set4c.union(set4d));System.out.println("""res2: Int = """ + $show(res$2));$skip(26); val res$3 = 
  
   set4d.mostRetweeted;System.out.println("""res3: objsets.Tweet = """ + $show(res$3));$skip(95); 
                                                   
   set4c.foreach(x => println(x.retweets));$skip(24); val res$4 = 
   set4c.findMaxRetweet;System.out.println("""res4: Int = """ + $show(res$4));$skip(45); 
   
   
   val ll = set5.descendingByRetweet;System.out.println("""ll  : objsets.TweetList = """ + $show(ll ));$skip(32); 
   ll.foreach(x => println(x ));$skip(35); 
  
  val s = "hello andssroid mmm";System.out.println("""s  : String = """ + $show(s ));$skip(75); 
  val g = List("android", "Android", "galaxy", "Galaxy", "nexus", "Nexus");System.out.println("""g  : List[String] = """ + $show(g ));$skip(16); val res$5 = 
  s.contains(g);System.out.println("""res5: Boolean = """ + $show(res$5));$skip(86); val res$6 = 
  //s.contains(g.exists(p))
  //TweetReader.allTweets.
  g.exists(x => s.contains(x));System.out.println("""res6: Boolean = """ + $show(res$6));$skip(21); 
  val x1 = new Empty;System.out.println("""x1  : objsets.Empty = """ + $show(x1 ));$skip(33); val res$7 = 
  x1.incl(new Tweet("h","b", 1));System.out.println("""res7: objsets.TweetSet = """ + $show(res$7));$skip(21); 
  val x2 = new Empty;System.out.println("""x2  : objsets.Empty = """ + $show(x2 ));$skip(33); val res$8 = 
  x2.incl(new Tweet("h","a", 2));System.out.println("""res8: objsets.TweetSet = """ + $show(res$8));$skip(18); val res$9 = 
  
  x1.union(x2);System.out.println("""res9: objsets.TweetSet = """ + $show(res$9))}
  
  
}
