package com.zio_bdd.boxing.UserValidator

import boxing.{CreateUserService, User}
import zio.*
import zio.bdd.core.step.ZIOSteps
import zio.bdd.core.{Assertions, Suite}
import zio.schema.{DeriveSchema, Schema}
import zio.test.ZIOSpecDefault

// Test specification
@Suite(
  featureDir = "features/",
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

  Then("the user " / string / " must be successfully created with an email" / string / " and age " / int) { (expectedName: String, expectedEmail: String, expectedAge: Int) =>

    for {
      actualEmail <- ScenarioContext.get.map(_.email)
      actualAge <- ScenarioContext.get.map(_.age)
      actualName <- ScenarioContext.get.map(_.name)
      _ <- Assertions.assertTrue(actualEmail == expectedEmail, s"Expected '$actualEmail', got '$expectedEmail'")
      _ <- Assertions.assertTrue(actualAge == expectedAge, s"Expected '$actualAge', got '$expectedAge'")
      _ <- Assertions.assertTrue(actualName == expectedName, s"Expected '$actualName', got '$expectedName'")
    } yield ()

  }

  override def environment: ZLayer[Any, Any, CreateUserService] =
    CreateUserService.live
}