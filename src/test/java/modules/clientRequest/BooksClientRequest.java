package modules.clientRequest;

import PojoClasses.create.AddBookRequestBody;
import PojoClasses.update.UpDateBookRequestBody;
import Constants.Constants;
import io.restassured.http.ContentType;
import io.restassured.response.Response;



import static io.restassured.RestAssured.given;

public class BooksClientRequest extends BaseClientHelper{


    public Response addNewBook(AddBookRequestBody addBookRequestBody,String token) {

        Response response = 
                given().spec(BaseClientHelper.requestSpec)
                        .header("Authorization", "Bearer " + token)
                .body(addBookRequestBody)
                .log().all(true)
                .when()
                .post( Constants.ADD_NEW_BOOK);
        return getResponse(response);
    }

    public Response duplicateBook(AddBookRequestBody addBookRequestBody,String token) {

        Response response =
                given().spec(BaseClientHelper.requestSpec)
                        .header("Authorization", "Bearer " + token)
                        .body(addBookRequestBody)
                        .log().all(true)
                        .when()
                        .post( Constants.ADD_NEW_BOOK);
        return getErrorResponse(response);
    }

    public Response editTheBook(UpDateBookRequestBody upDateBookRequestBody,int bookId, String token) {

        Response response = given()
                . given().spec(BaseClientHelper.requestSpec)
                .header("Authorization", "Bearer " + token)
                .pathParam("book_id", bookId)
                .body(upDateBookRequestBody)
                .log().all(true)
                .when()
                .put(Constants.BY_BOOK_ID + "/{book_id}");

        return getResponse(response);
    }

    public Response getBookDetailsById(int bookId, String token) {

        Response response = given()
                . given().spec(BaseClientHelper.requestSpec)
                .header("Authorization", "Bearer " + token)
                .pathParam("book_id", bookId)
                .log().uri()
                .when()
                .get( Constants.BY_BOOK_ID + "/{book_id}");

        return getResponse(response);
    }

    public Response getAllBooks(String token) {

        Response response = given()
                . given().spec(BaseClientHelper.requestSpec)
                .header("Authorization", "Bearer " + token)
                .contentType(ContentType.JSON)
                .log().uri()
                .when()
                .get(Constants.GET_BOOK_ALL);


        return getResponse(response);

    }

    public Response deleteTheBookById(int bookId,String token) {

        Response response = given()
                .spec(BaseClientHelper.requestSpec)
                . header("Authorization", "Bearer " + token)
                .pathParam("book_id", bookId)
                .log().uri()
                .when()
                .delete(Constants.BY_BOOK_ID +"/{book_id}");

        return getResponse(response);
    }


}
