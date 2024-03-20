package api.endpoints;

import api.payload.Order;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class Orders {
    public static Response getAccessToken(Order payload)
    {
        Response response=given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(payload)
                .when()
                .post(Url.getAccessTokenurl);

        return response;
    }

    public static Response createNewOrder(String accessToken,Order payload)
    {
        Response response=given()
                .headers("Authorization",accessToken)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(payload)
                .when()
                .post(Url.createNewOrderurl);

        return response;
    }

    public static Response UpdateOrder(String accessToken,String orderId,Order payload)
    {
        Response response=given()
                .headers("Authorization",accessToken)
                .pathParam("orderId",orderId)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(payload)
                .when()
                .patch(Url.UpdateOrderurl);

        return response;
    }

    public static Response getAllOrders(String accessToken)
    {
        Response response=given()
                .headers("Authorization",accessToken)
                .when()
                .get(Url.getAllOrdersurl);

        return response;
    }

    public static Response DeleteOrder(String accessToken,String orderId)
    {
        Response response=given()
                .headers("Authorization",accessToken)
                .pathParam("orderId",orderId)
                .when()
                .delete(Url.deleteOrderurl);

        return response;
    }

}
