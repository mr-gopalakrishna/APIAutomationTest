package api.endpoints;

import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class Products {
    public static Response getAllProducts()
    {

        return given()
                .when()
                .get(Url.getAllProductsurl);
    }

    public static Response getaProduct(int productId)
    {
        Response response=given()
                .pathParam("productId",productId)
                .when()
                .get(Url.getaProducturl);

        return response;
    }


}
