package zm.co.gsb.api;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.qameta.allure.Step;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import zm.co.gsb.config.ConfigManager;

public class ApiClient {
    private static final Logger logger = LogManager.getLogger(ApiClient.class);
    private static final String BASE_URL = ConfigManager.getProperty("apiBaseUrl");

    public ApiClient() {
        RestAssured.baseURI = BASE_URL;
        logger.info("REST Assured base URI set to: {}", BASE_URL);
    }

    @Step("Perform GET request to endpoint: {0}")
    public Response get(String endpoint) {
        logger.info("Performing GET request to endpoint: {}", endpoint);
        RequestSpecification request = RestAssured.given();
        Response response = request.get(endpoint);
        logger.info("GET request to {} returned status code: {}", endpoint, response.getStatusCode());
        return response;
    }

    @Step("Perform POST request to endpoint: {0} with body: {1}")
    public Response post(String endpoint, Object body) {
        logger.info("Performing POST request to endpoint: {} with body: {}", endpoint, body);
        RequestSpecification request = RestAssured.given()
                .header("Content-Type", "application/json")
                .body(body);
        Response response = request.post(endpoint);
        logger.info("POST request to {} returned status code: {}", endpoint, response.getStatusCode());
        return response;
    }
}

