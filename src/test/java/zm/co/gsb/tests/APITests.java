package zm.co.gsb.tests;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import zm.co.gsb.api.ApiClient;

public class APITests {

    private ApiClient apiClient = new ApiClient();

    @Test
    public void testGetEndpoint() {
        Response response = apiClient.get("/status");
        Assert.assertEquals(response.getStatusCode(), 200, "Status code should be 200");
    }
}

