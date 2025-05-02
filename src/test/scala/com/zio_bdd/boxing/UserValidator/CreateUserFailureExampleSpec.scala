package com.zio_bdd.boxing.UserValidator

import boxing.CreateUserService
import com.zio_bdd.Context
import zio.*
import zio.bdd.core.step.ZIOSteps
import zio.bdd.core.{Assertions, Suite}

// Test specification
@Suite(
  featureDir = "features/",
  reporters = Array("pretty", "junitxml"),
  parallelism = 2,
  includeTags = Array("negative"),
  logLevel = "debug"
)
object CreateUserFailureExampleSpec extends ZIOSteps[CreateUserService, Context] {
  Given("a user is created with invalid " / string / " " / string / " and " / string) { (email: String, name: String, age: String) =>
    ScenarioContext.update(_.copy(name = name, email = email, age = age.toInt))
  }

  When("there is an attempt to create the user") {
    for {
      ctx <- ScenarioContext.get
      service <- ZIO.service[CreateUserService]
      createResult <- service.create(ctx.name, ctx.email, ctx.age).either
      _ <- ScenarioContext.update(_.copy(errorMessage = createResult.left.get.getMessage))
    } yield ()
  }

  Then("the user creation must fail with " / string) { (expectedErrorMessage: String) =>
    for {
      actualErrorMessage <- ScenarioContext.get.map(_.errorMessage)
      _ <- Assertions.assertEquals(actualErrorMessage, expectedErrorMessage, s"Expected '$expectedErrorMessage', got '$actualErrorMessage'")
    } yield ()

  }

  override def environment: ZLayer[Any, Any, CreateUserService] =
    CreateUserService.live
}