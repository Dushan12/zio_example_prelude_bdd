package com.zio_bdd.validators.UserValidator

import validators.{CreateUserService, User, UserValidator}
import zio.*
import zio.bdd.core.{Assertions, Suite}
import zio.bdd.core.step.ZIOSteps
import zio.schema.{DeriveSchema, Schema}
import zio.test.ZIOSpecDefault

/*
  Scenario: Create a user
    Given a user named Dushan Gajikj with email dushan.gajikjrldatix.com and age 37
    When the user is created
    Then the user createion must fail with error Invalid email
*/

// Test specification
@Suite(
  featureDir = "src/test/resources/features/",
  reporters = Array("pretty", "junitxml"),
  parallelism = 2,
  includeTags = Array("negative"),
  logLevel = "debug"
)
object CreateUserFailureSpec extends ZIOSteps[CreateUserService, Context] {
  Given("a user named " / string / " with email " / string / " and age " / int) { (name: String, email: String, age: Int) =>
    ScenarioContext.update(_.copy(name = name, email = email, age = age))
  }

  When("the user is created") {
    for {
      ctx <- ScenarioContext.get
      service <- ZIO.service[CreateUserService]
      createResult <- service.create(ctx.name, ctx.email, ctx.age).either
      errm <- ZIO.succeed(createResult.left.get)
      _ <- ScenarioContext.update(_.copy(errorMessage = errm.getMessage))
    } yield ()
  }

  Then("the user creation must fail with error " / string) { (expectedErrorMessage: String) =>
    for {
      actualErrorMessage <- ScenarioContext.get.map(_.errorMessage)
      _ <- Assertions.assertEquals(actualErrorMessage, expectedErrorMessage, s"Expected '$expectedErrorMessage', got '$actualErrorMessage'")
    } yield ()

  }

  override def environment: ZLayer[Any, Any, CreateUserService] =
    ZLayer.succeed("Hello") >>> CreateUserService.live
}