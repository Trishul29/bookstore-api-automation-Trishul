package StepDefination;



import PojoClasses.create.*;
import PojoClasses.delete.DeleteBookResponseBody;
import PojoClasses.get.Book;
import PojoClasses.get.GetAllBookResponse;
import PojoClasses.get.GetBookByIdResponse;
import PojoClasses.update.UpDateBookRequestBody;
import PojoClasses.update.UpDateBookResponseBody;
import io.restassured.response.Response;

import java.util.List;

public class TestContext {

    public Response response;
    public LoginRequestBody loginRequestBody;
    public SignUpRequestBody signUpRequestBody;
    public SignUpResponse signUpResponse;
    public LoginResponse loginResponse;
    public LoginErrorReponse loginErrorReponse;

    public AddBookRequestBody addBookRequestBody;
    public  AddDuplicateBookResponse addDuplicateBookResponse;
    public AddBookResponse addBookResponse;

    public UpDateBookRequestBody upDateBookRequestBody;
    public UpDateBookResponseBody upDateBookResponseBody;

    public GetBookByIdResponse getBookByIdResponse;
   public GetAllBookResponse getAllBookResponse;

   public DeleteBookResponseBody deleteBookResponseBody;


   public  List<Book> getAllBooks;

}