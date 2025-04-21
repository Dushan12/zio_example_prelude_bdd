package validators

import validators.{User, UserValidator}
import zio.{ZIO, ZLayer}

// Service
trait CreateUserService {
  def create(name: String, email: String, age: Int): ZIO[Any, Throwable, User]
}
case class CreateUserServiceImpl() extends CreateUserService {
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

object CreateUserService {
  val live: ZLayer[Any, Nothing, CreateUserService] =
    ZLayer.fromZIO(ZIO.succeed(CreateUserServiceImpl()))
}