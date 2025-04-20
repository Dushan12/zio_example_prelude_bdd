import validators.{User, UserValidator}
import zio.*
import zio.Console.printLine

import java.io.IOException
import javax.xml.validation.Validator

object Main extends ZIOAppDefault:
  override def run: ZIO[Environment & ZIOAppArgs & Scope, Any, Any] = {
    printLine("Welcome to your first ZIO app!")
    val user1 = User(id = "id1", email = "dushan.gajikj@rldatix.com", name = "Dushan Gajikj", age = 37)
    printLine(user1.email)
    val user2 = User(id = "id1", email = "dushan.gajikjrldatix.com", name = "Dushan Gajikj", age = 37)
    val validatedUser1 = UserValidator.validate(user1).orDie
    val validatedUser2 = UserValidator.validate(user2).orDie
    printLine("END")
  }


  def exampleWithValidator: IO[IOException, Unit] = {
    printLine("Welcome to your first ZIO app!")
    val user1 = User(id = "id1", email = "dushan.gajikj@rldatix.com", name = "Dushan Gajikj", age = 37)
    printLine(user1.email)
    val user2 = User(id = "id1", email = "dushan.gajikjrldatix.com", name = "Dushan Gajikj", age = 37)
    val validatedUser1 = UserValidator.validate(user1).orDie
    val validatedUser2 = UserValidator.validate(user2).orDie
    printLine("END")
  }

  def exampleWithBoxing: IO[IOException, Unit] = {
    printLine("Welcome to your first ZIO app!")
    val user1 = User(id = "id1", email = "dushan.gajikj@rldatix.com", name = "Dushan Gajikj", age = 37)
    printLine(user1.email)
    val user2 = User(id = "id1", email = "dushan.gajikjrldatix.com", name = "Dushan Gajikj", age = 37)
    val validatedUser1 = UserValidator.validate(user1).orDie
    val validatedUser2 = UserValidator.validate(user2).orDie
    printLine("END")
  }

  def exampleWithOpaqueTypes: IO[IOException, Unit] = {
    printLine("Welcome to your first ZIO app!")
    val user1 = User(id = "id1", email = "dushan.gajikj@rldatix.com", name = "Dushan Gajikj", age = 37)
    printLine(user1.email)
    val user2 = User(id = "id1", email = "dushan.gajikjrldatix.com", name = "Dushan Gajikj", age = 37)
    val validatedUser1 = UserValidator.validate(user1).orDie
    val validatedUser2 = UserValidator.validate(user2).orDie
    printLine("END")
  }

  def exampleWithPrelude: IO[IOException, Unit] = {
    printLine("Welcome to your first ZIO app!")
    val user1 = User(id = "id1", email = "dushan.gajikj@rldatix.com", name = "Dushan Gajikj", age = 37)
    printLine(user1.email)
    val user2 = User(id = "id1", email = "dushan.gajikjrldatix.com", name = "Dushan Gajikj", age = 37)
    val validatedUser1 = UserValidator.validate(user1).orDie
    val validatedUser2 = UserValidator.validate(user2).orDie
    printLine("END")
  }