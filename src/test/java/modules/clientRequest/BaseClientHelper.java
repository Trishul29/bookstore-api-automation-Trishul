package modules.clientRequest;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import Utilities.FileUtility;

import java.util.Properties;

import static org.hamcrest.core.AnyOf.anyOf;
import static org.hamcrest.core.StringContains.containsString;
import static org.hamcrest.text.MatchesPattern.matchesPattern;

public class BaseClientHelper {

    public static String propertyPath = System.getProperty("user.dir") + "/src/main/resources/env.properties";
    public static String propertyPath1 = System.getProperty("user.dir") + "/src/main/resources/data.properties";
    public static Properties properties = FileUtility.loadProperties(propertyPath);
    public static Properties properties1 = FileUtility.loadProperties(propertyPath1);
    public static String bearerToken = properties.getProperty("bearerToken");
    public static String baseUri = properties.getProperty("baseUri");


    public static RequestSpecification requestSpec = new RequestSpecBuilder()
            .setBaseUri(baseUri)
            .setContentType(ContentType.JSON)
            .build();


    protected static Response getResponse(Response response) {

        response
                .then()
                .contentType(ContentType.JSON)
                .log().all(true);
        return response;

    }


    protected static Response getErrorResponse(Response response) {

        response
                .then()
               // .header("Content-Type", containsString("application/json"))
                .log().all(true);
        return response;

    }
}
