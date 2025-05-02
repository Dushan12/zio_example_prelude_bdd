@core @createUser
Feature: User Creation

  @positive
  Scenario: Create a user
    Given a user named Dushan Gajikj with email dushan.gajikj@rldatix.com and age 37
    When the user is created
    Then the user Dushan Gajikj must be successfully created with an email dushan.gajikj@rldatix.com and age 37

  @negative
  Scenario Outline: Fail to create user with invalid data
    Given a user is created with invalid <email> <name> and <age>
    When there is an attempt to create the user
    Then the user creation must fail with <error>
    Examples:
    | email                         | name                   | age                  | error                    |
    | "dushan.gajikjrldatix.com"    | "Dushan Gajikj"        | 37                   | "Invalid email"          |
    | "dushan.gajikj@rldatix.com"   | ""                     | 37                   | "Invalid name"           |
    | "dushan.gajikj@rldatix.com"   | "Dushan Gajikj"        | -1                    | "Invalid age"            |