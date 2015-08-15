object Syntax {

  // Immutability
  val a = 5
//  a = 6   // doesn't compile


  var b = 6
  b = 5


  // How lazy can you be?
  lazy val c = {
    println("setting the lazy val!")
    7
  }
  c
  c
  c

  def d = {
    println("returning value from function")
    8
  }
  d
  d
  d


  // Statements and blocks
  val stringStatement = "literal"
  val stringInABlock = {
    if (5 + 5 > 0) ()
    "literal"   // no "return"
  }
  stringStatement == stringInABlock


  // Two Infix notations
  val aString = "firstPart" + "secondPart"
  val another = "firstPart".+("secondPart")
  aString == another


  // Postfix notation and semicolon inference
  import scala.language.postfixOps
  Set(1,2,3) toList


  // For comprehensions
  for {
    i <- 1 to 10
  } println(i)


  // everything returns a value
  val evens = for {
    i <- 1 to 10
  } yield i*2

  val u: Unit = ()


  // The underscore
//  val x = _
//  val _ = 6

}
