object Collections {

  // Basic collections
  val l = List(1,2,3)
  val set = Set(1,2,3)
  val m = Map(1 -> "one", 2 -> "two", 3 -> "three")


  // Higher order functions on collections
  l.map(_ * 2)
  l.flatMap(i => List(i*10+1, i * 10 + 2, i * 10 +3))
  l.filter(_ % 2 == 0)
  l.fold(0)(_ + _)


  // Optional values as a collection
  val o: Option[Int] = Some(5)
  val n: Option[Int] = None

  o.map(_ * 2)
  n.map(_ * 2)


  // Successful operations as a collection
  import scala.util.Try
  def divide(n: Int, d: Int): Try[Int] = Try { n / d }

  val s = divide(4, 2)
  val f = divide(4, 0)

  s.map(_ * 2)
  f.map(_ * 2)

  s.flatMap( n => Try { 2 / n } )


  // Concurrency. Parallel collections
  def wasteCpu(i: Int): Int = wasteCpu(i + 1)
  (1 to 8).map(wasteCpu)
  (1 to 8).par.map(wasteCpu)


  // Concurrency. Futures
  def longCompute[T](f: => T): T = {
    java.lang.Thread.sleep(5000)
    f
  }

  def slow = longCompute {
    println("finally done")
    5
  }

  slow


  import scala.concurrent.Future
  import scala.concurrent.ExecutionContext.Implicits.global

  val fut = Future { slow }
  fut.value
  fut.value.get.get

  fut.map(_ + 2)

  Future { slow } flatMap ( first =>
    Future{ slow * first }
  )
}
