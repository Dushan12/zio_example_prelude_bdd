package boxing

import eu.timepit.refined
import eu.timepit.refined.api.Refined
import eu.timepit.refined.boolean.Not
import eu.timepit.refined.numeric.NonNegative


class Age(age: Int Refined NonNegative)

object Age {
  def make(value:Int): Either[String, Age] = refined.refineV[NonNegative](value).map(Age(_))
}