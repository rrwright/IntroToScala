import java.util.concurrent.Executors
import scala.concurrent.{ExecutionContext, Future}
import Collections._
import scala.annotation.implicitNotFound


object Implicits {

  // Implicit parameters
  {
    import scala.concurrent.Future
    import scala.concurrent.ExecutionContext.Implicits.global

    Future(???)(global)
  }


  implicit val ec = new ExecutionContext {
    val threadPool = Executors.newFixedThreadPool(8)
    def execute(runnable: Runnable) = threadPool.submit(runnable)
    def reportFailure(t: Throwable): Unit = println(t.toString)
  }
  Future(slow)(ec)


  // Implicit method extension
  implicit class BlingString(s: String) {
    def bling = s"*** $s ***"
  }

  "hi".bling



  // Type Classes

  // Goal:  def makeItRain(b: Blingable)


  @implicitNotFound("No Blingable type defined for {T}")
  trait Blingable[T] {
    def bling(t: T): T
  }
  object Blingable {
    implicit object BlingString extends Blingable[String] {
      def bling(t: String): String = s"*** $t ***"
    }
    implicit object BlingInt extends Blingable[Int] {
      def bling(t: Int): Int = t * 10
    }
    implicit object BlingLong extends Blingable[Long] {
      override def bling(t: Long): Long = t * 1000000
    }
  }
  import Blingable._

  def makeItRain[T](b: T)(implicit ev: Blingable[T]) = ev.bling(b)

  makeItRain[Long](2000L)(Blingable.BlingLong)

  makeItRain(2000L)

//  makeItRain(2000F)  // Does not compile
}
