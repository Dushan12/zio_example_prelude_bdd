package com.zio_bdd.validators.UserValidator

import validators.{CreateUserService, User, UserValidator}
import zio.*
import zio.bdd.core.{Assertions, Suite}
import zio.bdd.core.step.ZIOSteps
import zio.schema.{DeriveSchema, Schema}
import zio.test.ZIOSpecDefault

// Test specification
@Suite(
  featureDir = "src/test/resources/features/",
  reporters = Array("pretty", "junitxml"),
  parallelism = 2,
  includeTags = Array("positive"),
  logLevel = "debug"
)
object CreateUserSpec extends ZIOSteps[CreateUserService, Context] {
  Given("a user named " / string / " with email " / string / " and age " / int) { (name: String, email: String, age: Int) =>
    ScenarioContext.update(_.copy(name = name, email = email, age = age))
  }

  When("the user is created") {
    for {
      ctx <- ScenarioContext.get
      service <- ZIO.service[CreateUserService]
      user <- service.create(ctx.name, ctx.email, ctx.age)
    } yield ()
  }

  Then("the user must be successfully created with an email" / string / " and age " / int) { (expectedEmail: String, expectedAge: Int) =>

    for {
      actualEmail <- ScenarioContext.get.map(_.email)
      actualAge <- ScenarioContext.get.map(_.age)
      _ <- Assertions.assertTrue(actualEmail == expectedEmail, s"Expected '$actualEmail', got '$expectedEmail'")
      _ <- Assertions.assertTrue(actualAge == expectedAge, s"Expected '$actualAge', got '$expectedAge'")
    } yield ()

  }

  override def environment: ZLayer[Any, Any, CreateUserService] =
    ZLayer.succeed("Hello") >>> CreateUserService.live
}