package api.test;

import api.endpoints.Carts;
import api.endpoints.Orders;
import api.payload.Cart;
import api.payload.Order;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Random;

public class OrdersTests {

    Order getAccessTokenPayload=new Order();
    Order createNewOrderPayload=new Order();
    Order updateOrderPayload=new Order();

    @BeforeClass
    public void setupData(ITestContext context)
    {
        int number,number1;
        Random random=new Random();
        number=random.nextInt(1000);
        number1=number+random.nextInt(1000);
        String testEmail= "testPayload"+number+number1+"@test.com";
        System.out.println(testEmail);
        getAccessTokenPayload.setClientName("testPayload");
        getAccessTokenPayload.setClientEmail(testEmail);

        String cartId=context.getAttribute("cartId").toString();
        createNewOrderPayload.setCartId(cartId);
        createNewOrderPayload.setCustomerName("testCustomerOrder");

        updateOrderPayload.setCustomerName("testCustomer1");
    }


    @Test(priority = 1)
    public void testgetAccessToken(ITestContext context)
    {
        Response response= Orders.getAccessToken(getAccessTokenPayload);
        response.then().log().all().time(Matchers.lessThanOrEqualTo(3000L));
        String accessToken= response.jsonPath().getString("accessToken");
        context.setAttribute("accessToken",accessToken);

        String responseBody = response.getBody().asString();

        Assert.assertEquals(response.statusCode(),201);
        Assert.assertEquals(response.contentType(), "application/json; charset=utf-8");
        Assert.assertTrue(responseBody.contains("accessToken"));
    }

    @Test(priority = 2)
    public void testCreateNewOrder(ITestContext context)
    {
        String accessToken=context.getAttribute("accessToken").toString();
        Response response= Orders.createNewOrder(accessToken,createNewOrderPayload);
        response.then().log().all().time(Matchers.lessThanOrEqualTo(3000L));

        String orderId= response.jsonPath().getString("orderId");
        context.setAttribute("orderId",orderId);

        Assert.assertEquals(response.statusCode(),201);
        Assert.assertEquals(response.contentType(), "application/json; charset=utf-8");
        Assert.assertEquals(response.jsonPath().getString("created"), "true");
    }

    @Test(priority = 3)
    public void testUpdateOrder(ITestContext context)
    {
        String accessToken=context.getAttribute("accessToken").toString();
        String orderId=context.getAttribute("orderId").toString();

        Response response= Orders.UpdateOrder(accessToken,orderId,updateOrderPayload);
        response.then().log().all().time(Matchers.lessThanOrEqualTo(3000L));

        String responseBody = response.getBody().asString();

        Assert.assertEquals(response.statusCode(), 204);
        Assert.assertEquals(response.contentType(), "");
        Assert.assertEquals(responseBody, "");
    }

    @Test (priority = 4)
    public void testgetAllOrders(ITestContext context)
    {
        String accessToken=context.getAttribute("accessToken").toString();
        Response response = Orders.getAllOrders(accessToken);
        response.then().log().all().time(Matchers.lessThanOrEqualTo(3000L));

        String responseBody = response.getBody().asString();

        Assert.assertEquals(response.statusCode(), 200);
        Assert.assertEquals(response.contentType(), "application/json; charset=utf-8");
        Assert.assertTrue(responseBody.contains("items"));
        Assert.assertTrue(responseBody.contains("id"));
        Assert.assertTrue(responseBody.contains("productId"));
        Assert.assertTrue(responseBody.contains("quantity"));
    }

    @Test (priority = 5)
    public void testDeleteOrder(ITestContext context)
    {
        String accessToken=context.getAttribute("accessToken").toString();
        String orderId=context.getAttribute("orderId").toString();

        Response response = Orders.DeleteOrder(accessToken,orderId);
        response.then().log().all().time(Matchers.lessThanOrEqualTo(3000L));

        String responseBody = response.getBody().asString();

        Assert.assertEquals(response.statusCode(), 204);
        Assert.assertEquals(response.contentType(), "");
        Assert.assertEquals(responseBody, "");
    }


}
