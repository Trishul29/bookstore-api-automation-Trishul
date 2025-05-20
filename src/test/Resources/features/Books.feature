Feature: Validate the book store flow of add , update , list and delete books by user and verification of error handling

  Background:
    Given A registered user with email and password
    And the user is logged in successfully


  Scenario: Successfully Login up and add a new book after login

    When the user adds a new book to the store
    And  Response status code is 200
    And ALl Book Data is Present


  Scenario Outline: Add an existing book to the store

    And the user adds a  book with id <id> name "<BookName>", author "<Author>", summary "<Summary>", and year <Year>
    When the user tries to add the same book again
    Then The response Body for Duplicate Book Addition should be "<ResponseBody>"
    And  Response status code is 500


    Examples:
      | id  | BookName      | Author       | Summary           | Year | ResponseBody          |
      | 879 | The Alchemist | Paulo Coelho | A journey of self | 1988 | Internal Server Error |


  Scenario Outline: Add an existing book to the store with Invalid Token
    When the user adds a new book to the store with invalid token
    Then The response Body for Invalid token should be "<ResponseBody>"
    And  Response status code is 403

    Examples:
      | ResponseBody                   |
      | Invalid token or expired token |


  Scenario Outline: Add an existing book to the store with Missing Data

    And the user adds a  book with id <id> name "<BookName>", author "<Author>", summary "<Summary>", and year <Year>
    When the user tries to add the same book again
    Then The response Body for Duplicate Book Addition should be "<ResponseBody>"
    And Response status code is 500


    Examples:
      | id | BookName      | Author       | Summary | Year | ResponseBody          |
      | -1 | The Alchemist | Paulo Coelho |         | 1988 | Internal Server Error |


  Scenario Outline: Add an existing book to the store with Missing  Book ID

   # And the user adds a  book with id <id> name "<BookName>", author "<Author>", summary "<Summary>", and year <Year>
    When the user adds  book to the store with missing Id <id> name "<BookName>", author "<Author>", summary "<Summary>", and year <Year>
    And  Response status code is 200
    And ALl Book Data is Present


    Examples:
      | id | BookName      | Author       | Summary           | Year |
      | -1 | The Alchemist | Paulo Coelho | A journey of self | 1988 |

  Scenario: Get the details of a specific book using its ID

    And the user adds a new book to the store
    When the user fetches the book details using the book ID
    And  Response status code is 200
    And the fetched book details should match the created book


  Scenario: Get the details of a ALl book for user
    When the user fetches the book details
    Then  Response status code for GetAll book is 200
 Then the book list should not be empty

  Scenario Outline: Get the details of a ALl book for user with Expired or Invalid Token
    When the user fetches the book details using Expired or invalid Token
    Then  Response status code for GetAll book is 403
    And the response should contain error message "<error_message>"

    Examples:
      |error_message|
      |Invalid token or expired toke|



  Scenario Outline: Negative Scenario Get the details of a  book using Invalid ID

    And the user adds a new book to the store
    When the user fetches the book details using the book ID
    And  Response status code is  404
    And the response should contain error message "<error_message>"

Examples:
  |error_message|
  |Book not found|


  Scenario:E2E  Edit the name of an existing book and verify the updated Data
    And the user adds a new book to the store
    When the user fetches the book details using the book ID
    Given the user updates the book name to "Updated Book Name"
    When the user update the existing book with bookId
    Then the response status code for book update should be 200
    And the response should contain the updated book data

  Scenario Outline:E2E  Edit the name of an existing book with invalid Access Token
    And the user adds a new book to the store
    When the user fetches the book details using the book ID
    Given the user updates the book name to "Updated Book Name"
    When the user update the existing book with bookId for invalid token
    Then The response Body for update book with Invalid token should be "<ResponseBody>"
    And  Response status code is 403

    Examples:
      | ResponseBody                   |
      | Invalid token or expired token |





  Scenario: Delete a book by its ID E2E flow

    And the user adds a new book to the store
    When the user fetches the book details using the book ID
    When I delete the book by its ID
    Then Response status code is 200
    And The book should no longer exist

  Scenario: Delete Non Existent book

    And the user adds a new book to the store
    When the user fetches the book details using the book ID
    When I delete the book by invalid ID
    Then Response status code is  404
    And The book should not be deleted

