package modules.clientService;

import PojoClasses.create.AddBookRequestBody;
import PojoClasses.create.AddBookResponse;
import PojoClasses.delete.DeleteBookResponseBody;
import PojoClasses.get.Book;
import PojoClasses.get.GetAllBookResponse;
import PojoClasses.get.GetBookByIdResponse;
import PojoClasses.update.UpDateBookRequestBody;
import PojoClasses.update.UpDateBookResponseBody;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.response.Response;
import modules.clientRequest.BooksClientRequest;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class BooksService {


    public AddBookResponse addNewBookService(AddBookRequestBody addBookRequestBody, String token) {
        Response response = new BooksClientRequest().addNewBook(addBookRequestBody, token);
        AddBookResponse addBookResponse = response.as(AddBookResponse.class);
        addBookResponse.setResponseTime(response.timeIn(TimeUnit.MILLISECONDS));
        addBookResponse.setStatusCode(response.statusCode());
        return addBookResponse;
    }

    public UpDateBookResponseBody editTheBookService(UpDateBookRequestBody upDateBookRequestBody, int bookId, String token) {
        Response response = new BooksClientRequest().editTheBook(upDateBookRequestBody, bookId, token);
        UpDateBookResponseBody upDateBookResponseBody = response.as(UpDateBookResponseBody.class);
        upDateBookResponseBody.setResponseTime(response.timeIn(TimeUnit.MILLISECONDS));
        upDateBookResponseBody.setStatusCode(response.statusCode());
        return upDateBookResponseBody;
    }

    public Response editTheBookServiceWithExpiredToken(UpDateBookRequestBody upDateBookRequestBody, int bookId, String token) {
        new BooksClientRequest().editTheBook(upDateBookRequestBody, bookId, token);


        return new BooksClientRequest().editTheBook(upDateBookRequestBody, bookId, token);
    }

    public GetBookByIdResponse getBookDetailsByIdService(int id, String token) {
        Response response = new BooksClientRequest().getBookDetailsById(id, token);
        GetBookByIdResponse getBookByIdResponse = response.as(GetBookByIdResponse.class);
        getBookByIdResponse.setResponseTime(response.timeIn(TimeUnit.MILLISECONDS));
        getBookByIdResponse.setStatusCode(response.statusCode());
        return getBookByIdResponse;
    }

    public GetAllBookResponse getAllBooksService(String token) {
        Response response = new BooksClientRequest().getAllBooks(token);
        String ErrorResponse = response.getBody().asString();
        GetAllBookResponse getAllBookResponse = new GetAllBookResponse();
        long responseTime = response.timeIn(TimeUnit.MILLISECONDS);
        int statusCode = response.getStatusCode();
        getAllBookResponse.setStatusCode(statusCode);
        getAllBookResponse.setResponseTime(responseTime);
        if (ErrorResponse.contains("Invalid token or expired token")) {
            getAllBookResponse.setDetail(ErrorResponse);
        } else {

            try {
                ObjectMapper mapper = new ObjectMapper();
                List<Book> books = mapper.readValue(response.asString(), new TypeReference<List<Book>>() {
                });
                getAllBookResponse.setBooks(books);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return getAllBookResponse;
    }


    public DeleteBookResponseBody deleteTheBookByIdService(int id,String token) {
        Response response = new BooksClientRequest().deleteTheBookById(id,token);
        DeleteBookResponseBody deleteBookResponseBody = response.as(DeleteBookResponseBody.class);
        deleteBookResponseBody.setResponseTime(response.timeIn(TimeUnit.MILLISECONDS));
        deleteBookResponseBody.setStatusCode(response.statusCode());
        return deleteBookResponseBody;
    }

    public Response addDuplicateBookService(AddBookRequestBody addBookRequestBody, String token) {
        return new BooksClientRequest().duplicateBook(addBookRequestBody, token);
    }
}
