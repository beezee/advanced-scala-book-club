package main

import org.joda.time.DateTime

trait Doubleable[A] {
  def double(a: A): A
}

object DoubleableInstances {

  implicit val dblInt = new Doubleable[Int] {
    def double(a: Int) = a+a
  }

  implicit val dblString = new Doubleable[String] {
    def double(a: String) = a ++ a
  }

}

object DoubleDT {

  val dblDt = new Doubleable[DateTime] {
    def double(a: DateTime) = new DateTime(a.getMillis + a.getMillis)
  }

  // alternative definition of double for DateTime, double hours of day
  // can be selected at callsite by importing and assigning implicit
  val dblDt2 = new Doubleable[DateTime] {
    def double(a: DateTime) = {
      val d = new DateTime(a.getMillis)
      d.plusHours(d.getHourOfDay)
    }
  }
}

object DoubleableInterface {

  def double[A: Doubleable](a: A): A =
    implicitly[Doubleable[A]].double(a)
}

object DoubleableSyntax {

  implicit class DoubleableOps[A: Doubleable](a: A) {
    def double: A = DoubleableInterface.double(a)
  }
}

object DoubleableProgram {
  import DoubleableInstances._
  import DoubleableSyntax._

  def originalAndDouble[A: Doubleable](a: A): Unit =
    println((a, a.double))

  def originalsAndDoubles(i: Int, s: String, dt: DateTime)(
                          implicit ev: Doubleable[DateTime]): Unit = {
    originalAndDouble(i)
    originalAndDouble(s)
    originalAndDouble(dt)
  }
}
