package StepDefination;

import Constants.Constants;
import PojoClasses.create.SignUpRequestBody;
import PojoClasses.create.SignUpResponse;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import modules.clientRequest.BaseClientHelper;
import modules.clientService.SignUpService;

import java.util.Random;
import java.util.UUID;

import static org.testng.Assert.assertEquals;

public class SignUp extends BaseClientHelper {
   TestContext testContext;
    SignUpService signUpService=new SignUpService();


    public SignUp(TestContext context) {
        this.testContext = context;
    }

    @Given("the API base URL is loaded from configuration")
    public void the_api_base_url_is_loaded_from_configuration() {
        System.out.println("Base URL loaded from config: " + BaseClientHelper.baseUri);
    }


    @Given("a new user with email and password")
    public void a_new_user_with_email_and_password() {

        Random random = new Random();
        int id = random.nextInt(10000);
        String email = "user" + System.currentTimeMillis() + "@example.com";
        String password = UUID.randomUUID().toString().substring(0, 8);
        testContext.signUpRequestBody = SignUpRequestBody.builder()
                .id(id)
                .email(email)
                .password(password)
                .build();
        System.out.println("FAKER EMAIL: " + testContext.signUpRequestBody.getEmail());


    }
//    @When("the client signs up the user")
//    public void the_client_signs_up_the_user() {
//
//        testContext.signUpResponse = signUpService.SignupService(testContext.signUpRequestBody);
//
//    }
@When("the client signs up the user with {string}")
public void the_client_signs_up_the_user_with(String status) {
    if (status.equalsIgnoreCase("valid")) {

        testContext.signUpResponse = signUpService.SignupService(testContext.signUpRequestBody);
    } else if (status.equalsIgnoreCase("invalid")) {
        testContext.signUpResponse=signUpService.invalidSignUpService(testContext.signUpRequestBody);
    } else {
        throw new IllegalArgumentException("Unknown status: " + status);
    }



}

    @Then("the response status code should be {int}")
    public void the_response_status_code_should_be(Integer int1) {
        assertEquals(testContext.signUpResponse.getStatusCode(),int1);
    }

    @Given("user with already registered data")
    public void user_with_already_registered_data() {

        testContext.signUpRequestBody = SignUpRequestBody.builder()

                .id(Constants.id)
                .email(Constants.email)
                .password(Constants.password)
                .build();

    }
}
