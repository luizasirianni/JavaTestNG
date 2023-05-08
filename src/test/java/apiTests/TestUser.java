package apiTests;

import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.stringContainsInOrder;

public class TestUser {

    String uri = "https://petstore.swagger.io/v2/user";
    int idUser = 666;

    public String readJson(String jsonPath) throws IOException {
        return new String(Files.readAllBytes(Paths.get(jsonPath)));
    }

    @Test(priority = 0)
    public void createUser() throws IOException {
        String jsonBody = readJson("src/test/resources/data/user.json");

        given()
                .contentType("application/json")
                .log().all()
                .body(jsonBody)
        .when()
                .post(uri)
        .then()
                .log().all()
                .statusCode(200)
                .body("message", is("666"))
                .body("type", stringContainsInOrder("unknown"))
                ;
    }


}
