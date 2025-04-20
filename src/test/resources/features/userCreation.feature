@core @createUser
Feature: User Creation

  @positive
  Scenario: Create a user
    Given a user named Dushan Gajikj with email dushan.gajikj@rldatix.com and age 37
    When the user is created
    Then the user must be successfully created with an email dushan.gajikj@rldatix.com and age 37

  @negative
  Scenario: Fail to create user with invalid email
    Given a user named Dushan Gajikj with email dushan.gajikjrldatix.com and age 37
    When the user is created
    Then the user creation must fail with error Invalid email

  @negative
  Scenario: Fail to create user with invalid age
    Given a user named Dushan Gajikj with email dushan.gajikjrldatix.com and age 0
    When the user is created
    Then the user creation must fail with error Invalid age