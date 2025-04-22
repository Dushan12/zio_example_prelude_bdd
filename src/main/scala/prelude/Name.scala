package prelude

import zio.prelude.*
import Assertion.*

type Name = Name.Type

object Name extends Newtype[String]:
  override inline def assertion: Assertion[String] = !isEmptyString


