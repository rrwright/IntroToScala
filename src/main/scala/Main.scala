
// Java Interop

object Main extends App {
  val j = new JavaInterop(5, "a string", java.util.UUID.randomUUID())
  println(j)
}
