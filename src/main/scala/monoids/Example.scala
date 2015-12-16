package main

import scalaz.std.option._
import scalaz.syntax.monoid._
import scalaz.std.anyVal._
import scalaz.Monoid

object MonoidExample {

  def greedyOptionAppendInt(o1: Option[Int], o2: Option[Int]): Option[Int] =
    (o1, o2) match {
      case (Some(i1), Some(i2)) => Some(i1 + i2)
      case (Some(i1), None) => Some(i1)
      case (None, Some(i2)) => Some(i2)
      case (None, None) => None
    }

  def greedyOptionAppendIntM(o1: Option[Int], o2: Option[Int]): Option[Int] =
    o1 |+| o2

  def greedyOptionAppendM[A: Monoid](o1: Option[A], o2: Option[A]): Option[A] =
    o1 |+| o2
}
