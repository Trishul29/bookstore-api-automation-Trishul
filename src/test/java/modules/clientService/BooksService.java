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

import java.util.List;
import java.util.concurrent.TimeUnit;

public class BooksService {

    private final BooksClientRequest client = new BooksClientRequest();

    private long getResponseTime(Response response) {
        return response.timeIn(TimeUnit.MILLISECONDS);
    }

    private int getStatusCode(Response response) {
        return response.statusCode();
    }

    public AddBookResponse addNewBookService(AddBookRequestBody requestBody, String token) {
        Response response = client.addNewBook(requestBody, token);
        AddBookResponse addBookResponse = response.as(AddBookResponse.class);
        addBookResponse.setResponseTime(getResponseTime(response));
        addBookResponse.setStatusCode(getStatusCode(response));
        return addBookResponse;
    }

    public UpDateBookResponseBody editTheBookService(UpDateBookRequestBody requestBody, int bookId, String token) {
        Response response = client.editTheBook(requestBody, bookId, token);
        UpDateBookResponseBody responseBody = response.as(UpDateBookResponseBody.class);
        responseBody.setResponseTime(getResponseTime(response));
        responseBody.setStatusCode(getStatusCode(response));
        return responseBody;
    }

    public Response editTheBookServiceWithExpiredToken(UpDateBookRequestBody requestBody, int bookId, String token) {
        return client.editTheBook(requestBody, bookId, token);
    }

    public GetBookByIdResponse getBookDetailsByIdService(int id, String token) {
        Response response = client.getBookDetailsById(id, token);
        GetBookByIdResponse responseBody = response.as(GetBookByIdResponse.class);
        responseBody.setResponseTime(getResponseTime(response));
        responseBody.setStatusCode(getStatusCode(response));
        return responseBody;
    }

    public GetAllBookResponse getAllBooksService(String token) {
        Response response = client.getAllBooks(token);
        GetAllBookResponse result = new GetAllBookResponse();
        result.setResponseTime(getResponseTime(response));
        result.setStatusCode(getStatusCode(response));

        String body = response.getBody().asString();
        if (body.contains("Invalid token or expired token")) {
            result.setDetail(body);
        } else {
            try {
                ObjectMapper mapper = new ObjectMapper();
                List<Book> books = mapper.readValue(body, new TypeReference<List<Book>>() {});
                result.setBooks(books);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return result;
    }

    public DeleteBookResponseBody deleteTheBookByIdService(int id, String token) {
        Response response = client.deleteTheBookById(id, token);
        DeleteBookResponseBody responseBody = response.as(DeleteBookResponseBody.class);
        responseBody.setResponseTime(getResponseTime(response));
        responseBody.setStatusCode(getStatusCode(response));
        return responseBody;
    }

    public Response addDuplicateBookService(AddBookRequestBody requestBody, String token) {
        return client.duplicateBook(requestBody, token);
    }
}
