package modules.clientService;

import PojoClasses.create.*;
import io.restassured.response.Response;
import modules.clientRequest.SignUpClient;

import java.util.concurrent.TimeUnit;

public class SignUpService {

    public SignUpResponse SignupService(SignUpRequestBody signUpRequestBody) {
        Response response = new SignUpClient().SignUp(signUpRequestBody);
        SignUpResponse signUpResponse = response.as(SignUpResponse.class);
        signUpResponse.setResponseTime(response.timeIn(TimeUnit.MILLISECONDS));
        signUpResponse.setStatusCode(response.statusCode());

        return signUpResponse;
    }

    public SignUpResponse invalidSignUpService(SignUpRequestBody signUpRequestBody) {
        Response response = new SignUpClient().SignUpInvalid(signUpRequestBody);
        SignUpResponse signUpResponse = response.as(SignUpResponse.class);
        signUpResponse.setResponseTime(response.timeIn(TimeUnit.MILLISECONDS));
        signUpResponse.setStatusCode(response.statusCode());

        return signUpResponse;
    }


    public LoginResponse LogInService(LoginRequestBody loginRequestBody) {
        Response response = new SignUpClient().LogIn(loginRequestBody);
        LoginResponse loginResponse = response.as(LoginResponse.class);
        loginResponse.setResponseTime(response.timeIn(TimeUnit.MILLISECONDS));
        loginResponse.setStatusCode(response.statusCode());
        return loginResponse;
    }


    public LoginErrorReponse LogInServiceWithInvalidData(LoginRequestBody loginRequestBody)
    {
        Response response = new SignUpClient().LogIn(loginRequestBody);
        LoginErrorReponse loginErrorReponse = response.as(LoginErrorReponse.class);
        loginErrorReponse.setResponseTime(response.timeIn(TimeUnit.MILLISECONDS));
        loginErrorReponse.setStatusCode(response.statusCode());
        return loginErrorReponse;

    }

}
