package Constants;

import java.util.Random;
import java.util.UUID;

public class Constants {

static Random random=new Random();
    public static final String SING_UP = "/signup";
    public static final String LOG_IN="login";
    public static final String ADD_NEW_BOOK="/books/";
    public static final String BY_BOOK_ID="/books";

    public static final String GET_BOOK_ALL="/books/";


    public static final String INVALID_TOKEN = "bearer4576457 " + UUID.randomUUID().toString().substring(0, 7);
    public static final String DUPLICATE_BOOK_ERROR = "Internal Server Error";

    public static final int INVALID_BOOK_ID=random.nextInt(500000) + 1;

    public static  final   int id=6;
    public static  final String email="tram56@gmail.com";
    public static  final String password="shiva";
}
