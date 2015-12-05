package main

import org.joda.time.DateTime

trait DoubleableI[A] {
  val v: A // This guarantees access to an A, but not THE A.
  def double(): A
}

object DoubleableInherits {

  class IntDouble(i: Int) extends DoubleableI[Int] {
    val v = i+2 // This is broken but it still compiles!
    def double(): Int = i+i
  }

  class StringDouble(s: String) extends DoubleableI[String] {
    val v = s
    def double() = s ++ s
  }

  class DTDouble(dt: DateTime) extends DoubleableI[DateTime] {
    val v = dt
    def double() = new DateTime(dt.getMillis + dt.getMillis)
  }
}

object DoubleInheritsProgram {
  import DoubleableInherits._

  // helper for console
  def makeParams(i: Int, s: String, dt: DateTime):
  (IntDouble, StringDouble, DTDouble) =
    (new IntDouble(i), new StringDouble(s), new DTDouble(dt))

  // concise but has runtime logic bugs
  def badOriginalsAndDoubles(i: IntDouble, s: StringDouble, dt: DTDouble):
  Unit = {
    println((i.v, i.double))
    println((s.v, s.double))
    println((dt.v, dt.double))
  }

  // correct but verbose, verbosity at callsite is verbosity all over
  // also no support for injecting alternate doubler!
  def originalsAndDoubles(i: Int, s: String, dt: DateTime): Unit = {
    println((i, new IntDouble(i).double))
    println((s, new StringDouble(s).double))
    println((dt, new DTDouble(dt).double))
  }
}
