package api.test;

import api.endpoints.Carts;
import api.endpoints.Products;
import api.payload.Cart;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class CartTests {
    Cart createCartPayload = new Cart();
    Cart modifyCartPayLoad = new Cart();
    Cart replaceCartPayLoad = new Cart();
    Cart deleteCartPayLoad = new Cart();

    @BeforeClass
    public void setupData() {
        createCartPayload.setQuantity(1);
        createCartPayload.setProductId(4646);

        modifyCartPayLoad.setQuantity(2);

        replaceCartPayLoad.setProductId(1225);
        replaceCartPayLoad.setQuantity(2);

        deleteCartPayLoad.setProductId(1225);

    }

    @Test(priority = 1)
    public void testCreateCart(ITestContext context) {

        Response response = Carts.createNewCart();
        response.then().log().all().time(Matchers.lessThanOrEqualTo(3000L));
        String cartId = response.jsonPath().getString("cartId");
        context.setAttribute("cartId", cartId);

        Assert.assertEquals(response.statusCode(), 201);
        Assert.assertEquals(response.contentType(), "application/json; charset=utf-8");
        Assert.assertEquals(response.jsonPath().getString("created"), "true");
    }

    @Test(priority = 2)
    public void testAddItemtoCart(ITestContext context) {

        String cartId = context.getAttribute("cartId").toString();

        Response response = Carts.addIteminCart(cartId, createCartPayload);
        response.then().log().all().time(Matchers.lessThanOrEqualTo(3000L));
        int itemId = response.jsonPath().getInt("itemId");
        context.setAttribute("itemId", itemId);


        Assert.assertEquals(response.statusCode(), 201);
        Assert.assertEquals(response.contentType(), "application/json; charset=utf-8");
        Assert.assertEquals(response.jsonPath().getString("created"), "true");
    }

    @Test(priority = 3)
    public void testGetACart(ITestContext context) {
        String cartId = context.getAttribute("cartId").toString();
        Response response = Carts.getACart(cartId);
        response.then().log().all().time(Matchers.lessThanOrEqualTo(3000L));

        String responseBody = response.getBody().asString();

        Assert.assertEquals(response.statusCode(), 200);
        Assert.assertEquals(response.contentType(), "application/json; charset=utf-8");
        Assert.assertTrue(responseBody.contains("id"));
        Assert.assertTrue(responseBody.contains("productId"));
        Assert.assertTrue(responseBody.contains("quantity"));
    }

    @Test(priority = 4)
    public void testGetCartItem(ITestContext context) {
        String cartId = context.getAttribute("cartId").toString();
        Response response = Carts.getACartItem(cartId);
        response.then().log().all().time(Matchers.lessThanOrEqualTo(3000L));

        String responseBody = response.getBody().asString();

        Assert.assertEquals(response.statusCode(), 200);
        Assert.assertEquals(response.contentType(), "application/json; charset=utf-8");
        Assert.assertTrue(responseBody.contains("id"));
        Assert.assertTrue(responseBody.contains("productId"));
        Assert.assertTrue(responseBody.contains("quantity"));
    }

    @Test(priority = 5)
    public void testModifyCartItem(ITestContext context) {
        String cartId = context.getAttribute("cartId").toString();
        int itemId = (Integer) context.getAttribute("itemId");
        Response response = Carts.modifyCartItem(cartId, itemId, modifyCartPayLoad);
        response.then().log().all().time(Matchers.lessThanOrEqualTo(3000L));

        String responseBody = response.getBody().asString();

        Assert.assertEquals(response.statusCode(), 204);
        Assert.assertEquals(response.contentType(), "");
        Assert.assertEquals(responseBody, "");
    }

    @Test(priority = 6)
    public void testReplaceCartItem(ITestContext context) {
        String cartId = context.getAttribute("cartId").toString();
        int itemId = (Integer) context.getAttribute("itemId");


        Response response = Carts.replaceCartItem(cartId, itemId, replaceCartPayLoad);
        response.then().log().all().time(Matchers.lessThanOrEqualTo(3000L));

        String responseBody = response.getBody().asString();

        Assert.assertEquals(response.statusCode(), 204);
        Assert.assertEquals(response.contentType(), "");
        Assert.assertEquals(responseBody, "");
    }


    @Test(priority = 7)
    public void testDeleteCartItem(ITestContext context) {
        String cartId = context.getAttribute("cartId").toString();
        int itemId = (Integer) context.getAttribute("itemId");

        Response response = Carts.deleteCartItem(cartId, itemId, deleteCartPayLoad);
        response.then().log().all().time(Matchers.lessThanOrEqualTo(3000L));

        String responseBody = response.getBody().asString();

        Assert.assertEquals(response.statusCode(), 204);
        Assert.assertEquals(response.contentType(), "");
        Assert.assertEquals(responseBody, "");
    }
}
