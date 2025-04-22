/*package com.zio_bdd.validators.UserValidator

import validators.{CreateUserService, User, UserValidator}
import zio.*
import zio.bdd.core.step.{TableExtractor, ZIOSteps}
import zio.bdd.core.{Assertions, Suite}
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
object CreateUserFailureExampleSpec extends ZIOSteps[CreateUserService, Context] {
  Given("a user is created with invalid data") { () =>
    val tbl: TableExtractor[ExampleTable] = TableExtractor[ExampleTable](ExampleTable.schema)
    for {
      _ <- tbl.extract().map { (list, _) =>
        list.foreach {
          x =>
            Console.printLine(x.age)
        }
      }
    } yield ()

    for  {
      ctx <- ScenarioContext.get
      example <- ctx.
    } yield()
    var age = ageString.toInt;
    ScenarioContext.update(_.copy(name = name, email = email, age = age))
  }

  When("there is an attempt to create the user") {
    for {
      ctx <- ScenarioContext.get
      service <- ZIO.service[CreateUserService]
      createResult <- service.create(ctx.name, ctx.email, ctx.age).either
      errm <- ZIO.succeed(createResult.left.get)
      _ <- ScenarioContext.update(_.copy(errorMessage = errm.getMessage))
    } yield ()
  }

  Then("the user creation must fail with error") { (expectedErrorMessage: String) =>
    for {
      actualErrorMessage <- ScenarioContext.get.map(_.errorMessage)
      _ <- Assertions.assertEquals(actualErrorMessage, expectedErrorMessage, s"Expected '$expectedErrorMessage', got '$actualErrorMessage'")
    } yield ()

  }

  override def environment: ZLayer[Any, Any, CreateUserService] =
    CreateUserService.live
}*/