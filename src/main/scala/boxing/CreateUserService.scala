package boxing

import zio.{ZIO, ZLayer}

// Service
trait CreateUserService {
  def create(name: String, email: String, age: Int): ZIO[Any, Throwable, User]
}

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