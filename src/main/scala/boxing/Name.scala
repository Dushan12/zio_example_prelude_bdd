package boxing

import eu.timepit.refined.api.Refined
import eu.timepit.refined.predicates.all.NonEmpty
import eu.timepit.refined.refineV

class Name(name: String Refined NonEmpty)

object Name {
  def make(value: String): Either[String, Name] = {
    refineV[NonEmpty](value).map(Name(_))
  }
}