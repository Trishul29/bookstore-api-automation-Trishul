Feature: Login

  Scenario: Successful user login

    Given A registered user with email and password
    When the client sends a POST request to Endpoint with the valid credentials
    Then the response status code should be 200
    And the response should contain an access token


  Scenario Outline:  user login with invalid username or password

    Given User login with unregistered "<email>" and "<password>"
    When the client sends a POST request to Endpoint with invalid credentials credentials
    Then the response status code for Invalid login should be 400
    And the response should contain "<error_message>"

    Examples:
      | email              | password   | error_message               |
      | fakeuser1@test.com | wrongpass1 | Incorrect email or password |
      | notfound@mail.com  | badpass2   | Incorrect email or password |


