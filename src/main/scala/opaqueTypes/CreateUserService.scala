package opaqueTypes

import zio.{ZIO, ZLayer}

// Service
trait CreateUserService {
  def create(name: String, email: String, age: Int): ZIO[Any, Throwable, User]
}

/**
 * At runtime behaves as string
 * Compile Time Overhead
 * The compiler should know that the data is valid
 * It does not know that the data is invalid at compile time
 * we need to use for comprehensions for data that we know is valid
 */
case class CreateUserServiceImpl() extends CreateUserService {
  def create(name: String, email: String, age: Int): ZIO[Any, Throwable, User] = {

    ZIO.attempt {
      val nameValidated = Name.make(name).getOrElse(throw new Exception("Invalid name"))
      val emailValidated = Email.make(email).getOrElse(throw new Exception("Invalid email"))
      val ageValidated = Age.make(age).getOrElse(throw new Exception("Invalid age"))

      User(
        id = "id1",
        name = nameValidated,
        email = emailValidated,
        age = ageValidated
      )
    }
  }
}

object CreateUserService  {
  val live: ZLayer[Any, Nothing, CreateUserService] =
    ZLayer.fromZIO(ZIO.succeed(CreateUserServiceImpl()))
}