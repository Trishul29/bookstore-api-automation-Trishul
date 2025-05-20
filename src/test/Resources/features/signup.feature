Feature: User Authentication

  Background:
    Given the API base URL is loaded from configuration

  Scenario: Successful user sign-up
    Given a new user with email and password
    When the client signs up the user with "valid"
    Then the response status code should be 200


  Scenario Outline:User SignUp With Already registered Email

    Given user with already registered data
    When the client signs up the user with "invalid"
    Then the response status code should be 400
    And the response should contain error message "<error_message>"

    Examples:
      |error_message|
      |Email already registered|



