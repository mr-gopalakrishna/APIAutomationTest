package api.test;

import api.endpoints.Status;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.annotations.Test;

public class StatusTests {
    @Test
    public void testStatusCheck()
    {
        Response response = Status.getStatus();
        response.then().log().all().time(Matchers.lessThanOrEqualTo(3000L));

        String responseBody = response.getBody().asString();

        Assert.assertEquals(response.statusCode(), 200);
        Assert.assertEquals(response.contentType(), "application/json; charset=utf-8");
        Assert.assertTrue(responseBody.contains("UP"));
    }
}
