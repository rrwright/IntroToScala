import scala.util.Random

object Types {

  // Type inference
  val a = 5L

  // Optional type annotations
  val b: Int = 5


  // Sum types -- Encoded by subclassing
  sealed abstract class ParentType
  class SumTypeExample(i: Int) extends ParentType
  class OtherSumTypeExample(i: Int) extends ParentType

  val sumType: ParentType = new SumTypeExample(5)


  // Product types
  case class ProductTypeExample(a: Int, b: Int)

  ProductTypeExample(1,5)


  // Traits
  trait SimpleTrait

  trait DeferredImplementation {
    def foo: Int
  }

  trait HasImplementation {
    def foo: Int = 5
  }


  // Mix-ins
  case class AlgebraicDataType(a: Int, b: Int) extends SimpleTrait with DeferredImplementation {
    def foo: Int = a + b
  }


  // Variance
  trait Fruit
  object Apple extends Fruit
  object Orange extends Fruit

  val apples = List(Apple, Apple)
  val oranges = List(Orange, Orange)
  val fruit = List(Apple, Orange)


  // Type parameters
  def id[T](it: T): T = it


  // Path-dependent types
  case class ComicUniverse(name: String) {
    case class Hero(name: String)
    case class Villain(name: String)

    def battle(h: Hero, v: Villain) =
      s"${if (Random.nextBoolean) h.name else v.name} Wins!"
  }

  val dc = ComicUniverse("DC")
  val batman = dc.Hero("Batman")
  val joker = dc.Villain("Joker")

  val marvel = ComicUniverse("Marvel")
  val spidey = marvel.Hero("Spider Man")
  val goblin = marvel.Villain("Green Goblin")

  dc.battle(batman, joker)
//  marvel.battle(spidey, joker) // Doesn't compile

}
