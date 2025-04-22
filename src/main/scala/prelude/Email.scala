package prelude

val emailRegex = """^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?(?:.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)*$""".r


import zio.prelude.*
import Assertion.*

type Email = Email.Type

object Email extends Newtype[String]:
  override inline def assertion: Assertion[String] = matches(emailRegex)
