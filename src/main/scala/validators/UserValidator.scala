package validators

import zio.ZIO

object UserValidator {


  private val emailRegex = """^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?(?:.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)*$""".r


  private def check(e: String): Boolean = e match {
    case null => false
    case e if e.trim.isEmpty => false
    case e if emailRegex.findFirstMatchIn(e).isDefined => true
    case _ => false
  }

  def validate(user: User): ZIO[Any, Throwable, User] = {
    if(user.age < 0) {
      ZIO.fail(Exception("Invalid age"))
    } else {
      if(!check(user.email)) {
        ZIO.fail(Exception("Invalid email"))
      } else if (user.name.isEmpty) {
        ZIO.fail(Exception("Invalid name"))
      }
      else
        ZIO.succeed(user)
    }
  }

}
