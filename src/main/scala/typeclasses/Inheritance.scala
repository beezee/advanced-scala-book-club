package main

import org.joda.time.DateTime

abstract class DoubleableI[A](a: A) {
  def v: A = a
  def double(): A
}

object DoubleableInherits {

  class IntDouble(i: Int) extends DoubleableI[Int](i) {
    override val v = i+2 // This is broken but it still compiles!
    def double(): Int = i+i
  }

  class StringDouble(s: String) extends DoubleableI[String](s) {
    def double() = s ++ s
  }

  class DTDouble(dt: DateTime) extends DoubleableI[DateTime](dt) {
    def double() = new DateTime(dt.getMillis + dt.getMillis)
  }
}

object DoubleInheritsProgram {
  import DoubleableInherits._

  // helper for console
  def makeParams(i: Int, s: String, dt: DateTime):
  (IntDouble, StringDouble, DTDouble) =
    (new IntDouble(i), new StringDouble(s), new DTDouble(dt))

  // has runtime logic bugs
  // also pushes construction of decorator to caller, error prone and verbose
  def badOriginalsAndDoubles(i: IntDouble, s: StringDouble, dt: DTDouble):
  Unit = {
    println((i.v, i.double))
    println((s.v, s.double))
    println((dt.v, dt.double))
  }

  // no support for injecting alternate doubler!
  def originalsAndDoubles(i: Int, s: String, dt: DateTime): Unit = {
    println((i, new IntDouble(i).double))
    println((s, new StringDouble(s).double))
    println((dt, new DTDouble(dt).double))
  }
}
