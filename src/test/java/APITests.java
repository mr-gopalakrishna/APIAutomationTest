import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class APITests {

    @Test
    void test1(){

        Response response=
                given().
                baseUri("https://reqres.in/").
                when().
                get("/api/users?page=2").
                then().extract().response();

        System.out.println("Response: "+response.asString());
        System.out.println("Status Code is :"+response.getStatusCode());


//        System.out.println("Status Code is :"+response.getStatusCode());
//        System.out.println("Body :"+response.getBody().asString());
//        System.out.println("Time Taken :"+response.getTime());
//        System.out.println("Time Taken :"+response.getTime());

    }
}
