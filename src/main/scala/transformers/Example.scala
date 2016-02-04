package main

import scala.language.implicitConversions
import scalaz.{\/, EitherT, Writer}
import scalaz.std.list._
import scalaz.syntax.either._
import scalaz.syntax.writer._
import scalaz.syntax.std.boolean._

object LoggingEither {

  type Logger[A] = Writer[List[String], A]
  type LE[A] = EitherT[Logger, String, A]

  implicit class ToLEOps[A](wd: Logger[\/[String, A]]) {
    def lift: LE[A] = EitherT(wd)
  }

  def allTrue(b1: Boolean, b2: Boolean): LE[Boolean] =
    for {
      r1 <- b1.fold(true.right[String], "b1 false".left[Boolean])
              .set(List("checking b1")).lift
      r2 <- b2.fold(true.right[String], "b2 false".left[Boolean])
              .set(List("checking b2")).lift
    } yield r1 && r2
}
