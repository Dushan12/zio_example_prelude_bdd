package prelude

import zio.{ZIO, ZLayer}

// Service
trait CreateUserService {
  def create(name: String, email: String, age: Int): ZIO[Any, Throwable, User]
}

/**
 * No Runtime Overhead
 * No Compile Time Overhead
 * You cannot switch name and email because the attributes are typed and the code won't compile
 */
case class CreateUserServiceImpl() extends CreateUserService {
  def create(name: String, email: String, age: Int): ZIO[Any, Throwable, User] = {

    ZIO.attempt {
      (for {
      nameValidated <- Name.make(name)//.getOrElse(throw new Exception("Invalid name"))
      emailValidated <- Email.make(email)//.getOrElse(throw new Exception("Invalid email"))
      ageValidated <- Age.make(age)//.getOrElse(throw new Exception("Invalid age"))
      } yield {
        User(
          id = "id1",
          name = nameValidated,
          email = emailValidated,
          age = ageValidated
        )
      }).getOrElse(throw new Exception("Invalid Data"))
    }
  }
}

object CreateUserService  {
  val live: ZLayer[Any, Nothing, CreateUserService] =
    ZLayer.fromZIO(ZIO.succeed(CreateUserServiceImpl()))
}