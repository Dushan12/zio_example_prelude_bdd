package validators

import validators.{User, UserValidator}
import zio.{ZIO, ZLayer}

// Service
trait CreateUserService {
  def create(name: String, email: String, age: Int): ZIO[Any, Throwable, User]
}
case class CreateUserServiceImpl(prefix: String) extends CreateUserService {
  def create(name: String, email: String, age: Int): ZIO[Any, Throwable, User] =
    UserValidator.validate(
      User(
        id = "id1",
        name = name,
        email = email,
        age = age
      )
    )
}

object CreateUserService  {
  val live: ZLayer[String, Nothing, CreateUserService] =
    ZLayer.fromFunction(CreateUserServiceImpl(_))
}