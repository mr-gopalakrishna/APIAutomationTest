package api.endpoints;

import api.payload.Cart;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class Carts {

    public static Response createNewCart()
    {
        Response response =given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .when()
                .post(Url.createNewCarturl);


        return response;
    }

    public static Response addIteminCart(String cartId,Cart payload)
    {

        Response response=given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .pathParam("cartId",cartId)
                .body(payload)
                .when()
                .post(Url.AddNewCartItemurl);

        return response;
    }

    public static Response getACart(String cartId)
    {

        return given()
                .pathParam("cartId",cartId)
                .when()
                .get(Url.GetACarturl);
    }

    public static Response getACartItem(String cartId)
    {

        return given()
                .pathParam("cartId",cartId)
                .when()
                .get(Url.GetCartItemsurl);
    }

    public static Response modifyCartItem(String cartId,int itemId, Cart payload)
    {

        Response response=given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .pathParam("cartId",cartId)
                .pathParam("itemId",itemId)
                .body(payload)
                .when()
                .patch(Url.modifyCartItemsurl);

        return response;
    }

    public static Response replaceCartItem(String cartId,int itemId, Cart payload)
    {
        Response response=given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .pathParam("cartId",cartId)
                .pathParam("itemId",itemId)
                .body(payload)
                .when()
                .put(Url.replaceCartItemsurl);

        return response;
    }

    public static Response deleteCartItem(String cartId,int itemId, Cart payload)
    {
        Response response=given()
                .pathParam("cartId",cartId)
                .pathParam("itemId",itemId)
                .when()
                .delete(Url.deleteCartItemurl);

        return response;
    }

}
