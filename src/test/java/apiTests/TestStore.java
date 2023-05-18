package apiTests;

import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;
public class TestStore {
    public String readJson(String jsonPath) throws IOException {
        return new String(Files.readAllBytes(Paths.get(jsonPath)));
    }

    @Test
    public void placeOrder() throws IOException {
        String jsonBody = readJson("src/test/resources/data/order.json");
        given()
                .contentType("application/json")
                .log().all()
                .body(jsonBody)
        .when()
                .post("https://petstore.swagger.io/v2/store/order")
        .then()
                .log().all()
                .statusCode(200)
                .body("id", is(10))
                .body("status", is("placed"))
                .body("complete", is(true))
        ;
    }
}
