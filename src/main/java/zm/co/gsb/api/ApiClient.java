package zm.co.gsb.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import zm.co.gsb.config.ConfigManager;

import java.io.IOException;

import static io.restassured.RestAssured.post;

public class ApiClient {
    private static final Logger logger = LogManager.getLogger(ApiClient.class);
    private static final String BASE_URL = ConfigManager.getProperty("apiBaseUrl");
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public ApiClient() {
        RestAssured.baseURI = BASE_URL;
        logger.info("REST Assured base URI: {}", BASE_URL);
    }

    @Step("GET request to endpoint: {0}")
    public Response get(String endpoint) {
        logger.info("Making GET request to endpoint: {}", endpoint);
        RequestSpecification request = RestAssured.given();
        Response response = request.get(endpoint);
        logger.info("GET request to {} executed with status code: {}", endpoint, response.getStatusCode());
        return response;
    }

    @Step("POST request to endpoint: {0} with body: {1} and response body class: {2}")
    public <T> T postAsObject(String endpoint, Object body, Class<T> responseClass) {
        logger.info("Making POST request to endpoint: {} with body: {} and deserializing response {}", endpoint, body, responseClass.getSimpleName());
        Response response = post(endpoint, body);
        try {
            T result = objectMapper.readValue(response.asString(), responseClass);
            logger.info("Deserialization successful: {}", result);
            return result;
        } catch (IOException e) {
            logger.error("Deserialization failed: {}", endpoint, e);
            throw new RuntimeException(e);
        }
    }
}

