package opaqueTypes

opaque type Name = String

object Name {
  def make(value: String): Either[String, Name] = {
    if value.nonEmpty then Right(value)
    else Left("Invalid name")
  }
}