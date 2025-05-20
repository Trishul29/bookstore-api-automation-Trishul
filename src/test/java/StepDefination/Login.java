package StepDefination;

import PojoClasses.create.LoginRequestBody;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import modules.clientRequest.BaseClientHelper;
import modules.clientService.SignUpService;
import org.testng.Assert;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class Login extends BaseClientHelper {

    TestContext testContext;
    SignUpService signUpService = new SignUpService();

    public Login(TestContext context) {
        this.testContext = context;
    }


    @Given("A registered user with email and password")
    public void a_registered_user_with_email_and_password() {
        String email = properties1.getProperty("email");
        String password = properties1.getProperty("password");
        int id = Integer.parseInt(properties1.getProperty("id"));

        testContext.loginRequestBody = LoginRequestBody.builder()
                .email(email)
                .password(password)
                .id(id)
                .build();
    }

    @When("the client sends a POST request to Endpoint with the valid credentials")
    public void the_client_sends_post_request_to_endpoint_with_credentials() {
        testContext.loginResponse = signUpService.LogInService(testContext.loginRequestBody);
        System.out.println("Reached here before loginResponse print");

        if (testContext.loginResponse != null) {
            System.out.println("######## ####" + testContext.loginResponse.getStatusCode());
        } else {
            System.out.println("######## #### loginResponse is null");
        }

        System.out.println("Reached here after loginResponse print");
    }

    @Then("the response status code for login should be 200")
    public void the_response_status_code_should_be_200() {
        assertEquals(testContext.loginResponse.getStatusCode(), 200);
    }

    @Then("the response should contain an access token")
    public void the_response_should_contain_an_access_token() {
        Assert.assertNotNull(testContext.loginResponse.getAccess_token(), "Access token is missing");
    }

    @Given("User login with unregistered {string} and {string}")
    public void user_login_with_unregistered_fakeuser1_test_com_and_wrongpass1(String invalidEmail, String invalidPassword) {
        int id = Integer.parseInt(properties1.getProperty("id"));

        testContext.loginRequestBody = LoginRequestBody.builder()
                .email(invalidEmail)
                .password(invalidPassword)
                .id(id)
                .build();
    }

    @When("the client sends a POST request to Endpoint with invalid credentials credentials")
    public void the_client_sends_a_post_request_to_endpoint_with_invalid_credentials_credentials() {
        testContext.loginErrorReponse = signUpService.LogInServiceWithInvalidData(testContext.loginRequestBody);
    }

    @Then("the response status code for Invalidlogin should be {int}")
    public void the_response_status_code_for_login_should_be(int expectedStatusCode) {
        Assert.assertEquals(testContext.loginErrorReponse.getStatusCode(), expectedStatusCode);
    }

    @Then("the response should contain {string}")
    public void the_response_should_contain_invalid_email_or_password(String errorMessage) {
        Assert.assertEquals(testContext.loginErrorReponse.getDetail(), errorMessage);
    }

    @Then("the response should contain error message {string}")
    public void the_response_should_contain_(String errorMessage) {
        String actualErrorMessage;
        if(testContext.getAllBookResponse!=null)
        {
            actualErrorMessage=testContext.getAllBookResponse.getDetail();
            assertTrue(actualErrorMessage.contains(errorMessage));
        }
        else if(testContext.getBookByIdResponse!=null)
        {
            actualErrorMessage=testContext.getBookByIdResponse.getErrorMessage();
            assertEquals(actualErrorMessage,errorMessage);
        }
        else if(testContext.signUpResponse!=null)
        {
            actualErrorMessage=testContext.signUpResponse.getDetail();
            assertEquals(actualErrorMessage,errorMessage);
        }
        else
        {
            actualErrorMessage=testContext.deleteBookResponseBody.getDetail();
            assertEquals(actualErrorMessage,errorMessage);
        }
    }
}