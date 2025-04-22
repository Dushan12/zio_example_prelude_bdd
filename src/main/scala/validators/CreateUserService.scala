package validators

import validators.{User, UserValidator}
import zio.{ZIO, ZLayer}

// Service
trait CreateUserService {
  def create(name: String, email: String, age: Int): ZIO[Any, Throwable, User]
}


/**
 * The implementation allows you to create user without validation
 * also you can by mistake switch email with name, and it will not fail
 * if the validator is not called on it 
 */
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