@core @createUser
Feature: User Creation

  @positive
  Scenario: Create a user
    Given a user named Dushan Gajikj with email dushan.gajikj@rldatix.com and age 37
    When the user is created
    Then the user Dushan Gajikj must be successfully created with an email dushan.gajikj@rldatix.com and age 37

  @negative
  Scenario: Fail to create user with invalid email
    Given a user named Dushan Gajikj with email dushan.gajikjrldatix.com and age 37
    When the user is created
    Then the user creation must fail with error Invalid email

  @negative
  Scenario: Fail to create user with invalid age
    Given a user named Dushan Gajikj with email dushan.gajikj@rldatix.com and age -1
    When the user is created
    Then the user creation must fail with error Invalid age

  @negative
  Scenario: Fail to create user with empty name
    Given a user named  with email dushan.gajikj@rldatix.com and age 37
    When the user is created
    Then the user creation must fail with error Invalid name
#
#  @negative
#  Scenario: Fail to create user with invalid data
#    Given a user is created with invalid data
#    When there is an attempt to create the user
#    Then the user creation must fail with error
#    Example
#    | email                       | name                   | age                  | error                   |
#    | dushan.gajikjrldatix.com    | Dushan Gajikj          | 37                   | Invalid email           |
#    | dushan.gajikj@rldatix.com   |                        | 37                   | Invalid name            |
#    | dushan.gajikj@rldatix.com   | Dushan Gajikj          | 0                    | Invalid age             |