package prelude

import zio.prelude.*
import Assertion.*

type Age = Age.Type

object Age extends Newtype[Int]:
  override inline def assertion: Assertion[Int] =
    greaterThanOrEqualTo(0)