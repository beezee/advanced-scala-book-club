package main

import scalaz.\/
import scalaz.syntax.std.option._

object MonadExample {

  val getA: () => Integer = { () => 1 }
  val getB: Integer => Integer = { (a: Integer) => a+1 }

  def getAN: () => Integer = { () => null }
  def getBN: Integer => Integer = { (a: Integer) => null }

  def optionalNoOption(ga: () => Integer, gb: Integer => Integer) = {
    val a = ga()
    if (a != null) {
      val b = gb(a)
      if (b != null) {
        a + b
      }
    }
  }

  def optionalUsingOption(ga: () => Integer, gb: Integer => Integer) =
    for {
      a <- Option(ga())
      b <- Option(gb(a))
    } yield a + b

  def djNoDj(ga: () => Integer, gb: Integer => Integer) = {
    val a = ga()
    if (a != null) {
      val b = gb(a)
      if (b != null) {
        a + b
      } else { "no b" }
    } else { "no a" }
  }

  def djUsingDj(ga: () => Integer, gb: Integer => Integer) =
    for {
      a <- Option(ga()).toRightDisjunction("no a")
      b <- Option(gb(a)).toRightDisjunction("no b")
    } yield a + b
}
