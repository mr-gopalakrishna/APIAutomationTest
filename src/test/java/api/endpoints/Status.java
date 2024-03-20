package api.endpoints;

import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class Status {

    public static Response getStatus()
    {

        return given()
                .when()
                .get(Url.getStatusurl);
    }
}
