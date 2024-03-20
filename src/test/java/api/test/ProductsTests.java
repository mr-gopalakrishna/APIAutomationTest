package api.test;

import api.endpoints.Carts;
import api.endpoints.Products;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.Test;

public class ProductsTests {

    @Test (priority = 1)
    public void testGetAllProducts(ITestContext context)
    {
        Response response = Products.getAllProducts();
        response.then().log().all().time(Matchers.lessThanOrEqualTo(4000L));

        int productId= response.jsonPath().getInt("id[0]");
        context.setAttribute("productId",productId);

        String responseBody = response.getBody().asString();

        Assert.assertEquals(response.statusCode(), 200);
        Assert.assertEquals(response.contentType(), "application/json; charset=utf-8");
        Assert.assertTrue(responseBody.contains("id"));
        Assert.assertTrue(responseBody.contains("category"));
        Assert.assertTrue(responseBody.contains("name"));
        Assert.assertTrue(responseBody.contains("inStock"));
    }

    @Test (priority= 2)
    public void testGetAProduct(ITestContext context)
    {
        int productId= (Integer) context.getAttribute("productId");
        Response response = Products.getaProduct(productId);
        response.then().log().all().time(Matchers.lessThanOrEqualTo(3000L));

        String responseBody = response.getBody().asString();

        Assert.assertEquals(response.statusCode(), 200);
        Assert.assertEquals(response.contentType(), "application/json; charset=utf-8");
        Assert.assertTrue(responseBody.contains("id"));
        Assert.assertTrue(responseBody.contains("category"));
        Assert.assertTrue(responseBody.contains("name"));
        Assert.assertTrue(responseBody.contains("manufacturer"));
        Assert.assertTrue(responseBody.contains("price"));
        Assert.assertTrue(responseBody.contains("current-stock"));
        Assert.assertTrue(responseBody.contains("inStock"));
    }
}
