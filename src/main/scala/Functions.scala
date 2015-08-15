object Functions {

  // Functions as values
  // Elephant in the room: Functions are objects (well, traits, but they are objects as values)
  def f(i: Int): Int = i
  f(1)
  def g: Int => Int = x => x * 2
  g(1)
  g.apply(1)

  val o: Function1[Int,Int] = x => x * 2


  // Partially applied function
  val p: (Int, Int, Int, Int) => Int = _ + _ + _ + _
  val q = p(1, 2 ,_: Int, _: Int)
  q(3,4)


  // Eta expansion: methods into functions
  val h = f _


  // Higher order functions
  def higher(f: Int => Int, i: Int): Int = f(i)  // that's "apply"


  // Call by name
  def normal(i: Int): Int = {
    println("executing function")
    i
  }

  def byName(i: => Int): Int = {
    println("executing function")
    i
  }

  def five: Int = {
    println("returning 5")
    5
  }

  normal(five)
  byName(five)


  // Partial functions
  val wholeNums: PartialFunction[Int,String] = {
    case x if x == 0 => s"$x is zero"
    case x if x > 0  => s"$x is positive"
  }

  wholeNums.isDefinedAt(5)
  wholeNums.isDefinedAt(-2)

  (-3 to 3) collect wholeNums

  val negatives: PartialFunction[Int,String] = {
    case x if x < 0  => s"$x must be negative"
  }

  val integers = wholeNums orElse negatives


  // Pattern matching
  import Types._
  def whichType(t: SimpleTrait): String = t match {
    case AlgebraicDataType(x,y)    => s"AlgebraicDataType with $x and $y"
    case x: DeferredImplementation => s"someone must have defined foo by now. It's value is: ${x.foo}"
    case _                         => s"It must be something else..."
  }


  // apply and unapply
  case class Example(a: Int, b: Int)
  val firstOne = Example.apply(1,2)
  val params = Example.unapply(firstOne).get
  val recreated = Example(params._1, params._2)
  firstOne == recreated
  
//  val construtor = Example.apply _
//  val newOne = construtor.tupled(params)
//  newOne
}
