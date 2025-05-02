package prelude

import zio.{NonEmptyChunk, ZIO, ZLayer}

// Service
trait CreateUserService {
  def create(name: String, email: String, age: Int): ZIO[Any, Nothing, Either[NonEmptyChunk[String], User]]
}

/**
 * No Runtime Overhead
 * No Compile Time Overhead
 * You cannot switch name and email because the attributes are typed and the code won't compile
 */
case class CreateUserServiceImpl() extends CreateUserService {
  def create(name: String, email: String, age: Int): ZIO[Any, Nothing, Either[NonEmptyChunk[String], User]] = {
    ZIO.succeed((for {
      name <- Name.make(name)
      email <- Email.make(email)
      age <- Age.make(age)
    } yield  {
      User(
        id = "id1",
        name = name,
        email = email,
        age = age
      )
    }).toEither)
  }
}

object CreateUserService  {
  val live: ZLayer[Any, Nothing, CreateUserService] =
    ZLayer.fromZIO(ZIO.succeed(CreateUserServiceImpl()))
}