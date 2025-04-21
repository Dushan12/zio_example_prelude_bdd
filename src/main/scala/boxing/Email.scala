package boxing

import eu.timepit.refined.api.Refined
import eu.timepit.refined.predicates.all.MatchesRegex
import eu.timepit.refined.refineV

type emailRegex = MatchesRegex["""^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?(?:.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)*$"""]

class Email(email: String Refined emailRegex)

object Email {
  def make(value: String): Either[String, Email] = {
    refineV[emailRegex](value).map(Email(_))
  }
}
