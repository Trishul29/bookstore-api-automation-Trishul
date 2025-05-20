package StepDefination;
import PojoClasses.create.AddBookRequestBody;
import PojoClasses.get.GetBookByIdResponse;
import PojoClasses.update.UpDateBookRequestBody;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import modules.clientRequest.BaseClientHelper;
import modules.clientService.BooksService;
import modules.clientService.SignUpService;
import Constants.Constants;
import org.testng.Assert;
import java.util.Random;
import java.util.UUID;

import static org.testng.Assert.assertEquals;

public class BookStepD extends BaseClientHelper {

    TestContext testContext;
    SignUpService signUpService = new SignUpService();
    BooksService booksService = new BooksService();
    String randomName = "Book " + UUID.randomUUID().toString().substring(0, 5);
    String randomAuthor = "Author " + UUID.randomUUID().toString().substring(0, 5);
    String randomSummary = "Summary of " + randomName;

    Random random = new Random();
    int randomYear = 1950 + random.nextInt(75);
    int randomId = random.nextInt(500000) + 1;
    String accessToken;


    public BookStepD(TestContext context) {
        this.testContext = context;
    }

    @Given("the user is logged in successfully")
    public void the_user_is_logged_in_successfully() {
        testContext.loginResponse = signUpService.LogInService(testContext.loginRequestBody);
        Assert.assertNotNull(testContext.loginResponse.getAccess_token(), "Access token is missing");
        //accessToken=testContext.loginResponse.getAccess_token();


    }

    @When("the user adds a new book to the store")
    public void the_user_adds_a_new_book_to_the_store() {
        testContext.addBookRequestBody = AddBookRequestBody.builder()
                .name(randomName)
                .author(randomAuthor)
                .book_summary(randomSummary)
                .published_year(randomYear)
                .id(randomId)
                .build();
        accessToken = testContext.loginResponse.getAccess_token();
        testContext.addBookResponse = booksService.addNewBookService(testContext.addBookRequestBody, accessToken);
    }

    @When("the user adds a new book to the store with invalid token")
    public void the_user_adds_a_new_book_to_the_store_invalid_token() {
        testContext.addBookRequestBody = AddBookRequestBody.builder()
                .name(randomName)
                .author(randomAuthor)
                .book_summary(randomSummary)
                .published_year(randomYear)
                .id(randomId)
                .build();
        accessToken = Constants.INVALID_TOKEN;
        testContext.addBookResponse = booksService.addNewBookService(testContext.addBookRequestBody, accessToken);
    }


    @Then("the response status code for Sucessfull Book Addition should be {int}")
    public void the_response_status_code_for_successfull_book_addition_should_be(Integer int1) {
        assertEquals(testContext.addBookResponse.getStatusCode(), int1);

    }

    @Then("ALl Book Data is Present")
    public void a_ll_book_data_is_present() {
        Assert.assertNotNull(testContext.addBookResponse.getName(), "Book name is null");
        Assert.assertNotNull(testContext.addBookResponse.getAuthor(), "Book author is null");
        Assert.assertNotNull(testContext.addBookResponse.getBook_summary(), "Book summary is null");
        Assert.assertTrue(testContext.addBookResponse.getPublished_year() > 0, "Published year is invalid");
    }

    @Given("the user adds a  book with id {int} name {string}, author {string}, summary {string}, and year {int}")
    public void the_user_adds_a_new_book_with_details(int id, String bookName, String author, String summary, int year) {
        testContext.addBookRequestBody = AddBookRequestBody.builder()
                .id(id)
                .name(bookName)
                .author(author)
                .book_summary(summary)
                .published_year(year)
                .build();


    }

    @When("the user tries to add the same book again")
    public Response add_same_book_again() {
        accessToken = testContext.loginResponse.getAccess_token();
        testContext.response = booksService.addDuplicateBookService(testContext.addBookRequestBody, accessToken);

        return testContext.response;

    }

    @Then("The response Body for Duplicate Book Addition should be {string}")
    public void response_Error_message_is(String expectedBody) {

        assertEquals(testContext.response.getBody().asString(), expectedBody, "Error body message not matching");
    }


    @And("Response status code is {int}")
    public void response_status_code_is(int expectedStatusCode) {
        int actualStatusCode;

        if (testContext.response != null) {
            actualStatusCode = testContext.response.getStatusCode();
        } else if (testContext.addBookResponse != null) {
            actualStatusCode = testContext.addBookResponse.getStatusCode();
        } else if (testContext.getBookByIdResponse != null) {
            actualStatusCode = testContext.getBookByIdResponse.getStatusCode();
        } else if (testContext.deleteBookResponseBody != null) {
            actualStatusCode = testContext.deleteBookResponseBody.getStatusCode();

        } else {
            throw new IllegalStateException("No response object found to assert status code.");
        }

        assertEquals(actualStatusCode, expectedStatusCode, "Unexpected status code.");
    }


    @Then("The response Body for Invalid token should be {string}")
    public void the_response_body_for_invalid_token_should_be(String expectedErrorMessagew) {

        if (testContext.addBookResponse != null) {
            assertEquals(testContext.addBookResponse.getDetail(), expectedErrorMessagew);
        }
    }

    @Then("The response Body for update book with Invalid token should be {string}")
    public void the_response_body_for_invalid_token_with_update_book_should_be(String expectedErrorMessagew) {
        String actualDetail = testContext.response.jsonPath().getString("detail");
        assertEquals(actualDetail, expectedErrorMessagew, "Error message mismatch");
    }


    @When("the user adds a Duplicate book with Id {int} name {string}, author {string}, summary {string}, and year {int}")
    public void the_user_adds_a_new_book_to_the_store_missing_id() {
        testContext.addBookRequestBody = AddBookRequestBody.builder()
                .name(randomName)
                .author(randomAuthor)
                .book_summary(randomSummary)
                .published_year(randomYear)
                .id(randomId)
                .build();
        accessToken = testContext.loginResponse.getAccess_token();
        testContext.addBookResponse = booksService.addNewBookService(testContext.addBookRequestBody, accessToken);
    }


    @When("the user adds  book to the store with missing Id {int} name {string}, author {string}, summary {string}, and year {int}")
    public void theUserAddsANewBookToTheStoreWithMissingId( String name, String author, String summary, int year) {
        testContext.addBookRequestBody = AddBookRequestBody.builder()
                .id(null)
                .name(name)
                .author(author)
                .book_summary(summary)
                .published_year(year)
                .build();
        accessToken = testContext.loginResponse.getAccess_token();
        testContext.addBookResponse = booksService.addNewBookService(testContext.addBookRequestBody, accessToken);
    }


    @When("the user updates the book name to {string}")
    public void the_user_updates_the_book_name_to(String input) {
        String updateName = input + UUID.randomUUID().toString().substring(0, 5);
        testContext.upDateBookRequestBody = UpDateBookRequestBody.builder()
                .book_summary(testContext.getBookByIdResponse.getBook_summary()).author(testContext.getBookByIdResponse.getAuthor()).name(updateName).published_year(testContext.getBookByIdResponse.getPublished_year())
                .build();

    }

    @When("the user update the existing book with bookId")
    public void the_user_update_the_existing_book() {
        accessToken = testContext.loginResponse.getAccess_token();
        int bookId = testContext.getBookByIdResponse.getId();
        testContext.upDateBookResponseBody = booksService.editTheBookService(testContext.upDateBookRequestBody, bookId, accessToken);
    }

    @When("the user update the existing book with bookId for invalid token")
    public void the_user_update_the_existing_book_invalid_token() {
        accessToken = Constants.INVALID_TOKEN;
        int bookId = testContext.getBookByIdResponse.getId();
        testContext.response = booksService.editTheBookServiceWithExpiredToken(testContext.upDateBookRequestBody, bookId, accessToken);
    }


    @Then("the response status code for book update should be {int}")
    public void the_response_status_code_for_book_update_should_be(int statusCOde) {

        assertEquals(testContext.upDateBookResponseBody.getStatusCode(), statusCOde, "Status Code is not 200");


    }

    @Then("the response should contain the updated book data")
    public void the_response_should_contain_the_updated_book_data() {
        assertEquals(
                testContext.upDateBookResponseBody.getBook_summary(),
                testContext.upDateBookRequestBody.getBook_summary(),
                "Book summary mismatch: Expected summary from request does not match the response."
        );

        assertEquals(
                testContext.upDateBookResponseBody.getName(),
                testContext.upDateBookRequestBody.getName(),
                "Book name mismatch after update."
        );

        assertEquals(
                testContext.upDateBookResponseBody.getAuthor(),
                testContext.upDateBookRequestBody.getAuthor(),
                "Author mismatch after update."
        );

        assertEquals(
                testContext.upDateBookResponseBody.getPublished_year(),
                testContext.upDateBookRequestBody.getPublished_year(),
                "Published year mismatch after update."
        );

    }

    @When("the user fetches the book details using the book ID")
    public void the_user_fetches_the_book_details_using_the_book_id() {
        int bookId = testContext.addBookResponse.getId();
        accessToken = testContext.loginResponse.getAccess_token();
        testContext.getBookByIdResponse = booksService.getBookDetailsByIdService(bookId, accessToken);

    }

    @When("the user fetches the book details")
    public void the_user_fetches_the_book_details_() {
        accessToken = testContext.loginResponse.getAccess_token();
        testContext.getAllBookResponse = booksService.getAllBooksService(accessToken);
//
    }

    @When("the user fetches the book details using Expired or invalid Token")
    public void the_user_fetches_the_book_details_invalid_token() {
        accessToken = Constants.INVALID_TOKEN;
        testContext.getAllBookResponse = booksService.getAllBooksService(accessToken);
    }


    @Then("the book list should not be empty")
    public void the_book_list_should_not_be_empty() {
        Assert.assertNotNull(testContext.getAllBookResponse.getBooks(), "GetAllBookResponse is null");
        Assert.assertFalse(testContext.getAllBookResponse.getBooks().isEmpty(), "Books list is empty");
    }

    @When("the user fetches the book details using Invalid ID")
    public void the_user_fetches_the_book_details_using_invalid_id() {
        int bookId = Constants.INVALID_BOOK_ID;
        accessToken = testContext.loginResponse.getAccess_token();
        testContext.getBookByIdResponse = booksService.getBookDetailsByIdService(bookId, accessToken);


    }

    @When("the fetched book details should match the created book")
    public void the_fetched_book_details_should_match_the_created_book() {
        AddBookRequestBody expected = testContext.addBookRequestBody;
        GetBookByIdResponse actual = testContext.getBookByIdResponse;
        assertEquals(actual.getId(), expected.getId(), "Book ID mismatch");
        assertEquals(actual.getName(), expected.getName(), "Book name mismatch");
        assertEquals(actual.getAuthor(), expected.getAuthor(), "Author mismatch");
        assertEquals(actual.getBook_summary(), expected.getBook_summary(), "Book summary mismatch");
        assertEquals(actual.getPublished_year(), expected.getPublished_year(), "Published year mismatch");
    }

    @Then("Response status code for GetAll book is {int}")
    public void response_status_code_get_all_is(int expectedStatusCode) {

        assertEquals(testContext.getAllBookResponse.getStatusCode(), expectedStatusCode);
    }

    @When("I delete the book by its ID")
    public void i_delete_the_book_by_its_id() {
        int bookId = testContext.addBookResponse.getId();
        accessToken = testContext.loginResponse.getAccess_token();
        testContext.deleteBookResponseBody = booksService.deleteTheBookByIdService(bookId, accessToken);
    }

    @When("I delete the book by invalid ID")
    public void i_delete_the_book_by_its_invalid_id() {
        int bookId = Constants.INVALID_BOOK_ID;
        accessToken = testContext.loginResponse.getAccess_token();
        testContext.deleteBookResponseBody = booksService.deleteTheBookByIdService(bookId, accessToken);
    }


    @Then("The book should no longer exist")
    public void the_book_should_no_longer_exist() {
        String actualMessage = testContext.deleteBookResponseBody.getMessage();
        assertEquals(actualMessage, "Book deleted successfully", "Book not deleted");

    }

    @Then("The book should not be deleted")
    public void the_book_should_not_be_deleted() {
        String actualMessage = testContext.deleteBookResponseBody.getDetail();
        assertEquals(actualMessage, "Book not found", "Book not found message not displayed");

    }

    @And("Response status code is  404")
    public void response_status_code_is_404() {
        int actualStatusCode;

        if (testContext.deleteBookResponseBody != null) {
            actualStatusCode = testContext.deleteBookResponseBody.getStatusCode();
            assertEquals(actualStatusCode, 404);
        }
        else
        {
            actualStatusCode = testContext.getBookByIdResponse.getStatusCode();
            assertEquals(actualStatusCode, 404);
        }

    }
}



