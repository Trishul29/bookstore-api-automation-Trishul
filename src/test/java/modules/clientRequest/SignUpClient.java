package modules.clientRequest;

import PojoClasses.create.LoginRequestBody;
import PojoClasses.create.SignUpRequestBody;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import Constants.Constants;

import static io.restassured.RestAssured.given;

public class SignUpClient extends BaseClientHelper {

    public Response SignUp(SignUpRequestBody signUpRequestBody) {

        Response response =
                given()
                        .baseUri(baseUri)
                        .contentType(ContentType.JSON)
                        .body(signUpRequestBody)
                        .log().all(true)
                        .when()
                        .post( Constants.SING_UP);
        return getResponse(response);
    }

    public Response SignUpInvalid(SignUpRequestBody signUpRequestBody) {

        Response response =
                given()
                        .baseUri(baseUri)
                        .contentType(ContentType.JSON)
                        .body(signUpRequestBody)
                        .log().all(true)
                        .when()
                        .post(Constants.SING_UP);
        return getErrorResponse(response);
    }


    public Response LogIn(LoginRequestBody loginRequestBody) {

        Response response =
                given().spec(BaseClientHelper.requestSpec)
                        .body(loginRequestBody)
                        .log().all()
                        .when()
                        .post( Constants.LOG_IN);
        return getResponse(response);
    }



}
