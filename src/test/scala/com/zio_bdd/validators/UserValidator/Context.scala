package com.zio_bdd.validators.UserValidator

import zio.schema.{DeriveSchema, Schema}

// Domain and state
case class Context(name: String, email: String, age: Int, errorMessage: String)
object Context {
  implicit val schema: Schema[Context] = DeriveSchema.gen[Context]
}

case class ExampleTable(email: String, name: String, age: Int, errorMessage: String)
object ExampleTable {
  implicit val schema: Schema[ExampleTable] = DeriveSchema.gen[ExampleTable]
}