package opaqueTypes

opaque type Age = Int

object Age {
  def make(value: Int): Either[String, Age] = {
    if value < 0 then
      Left("Invalid age")
    else
      Right(value)
  }
}