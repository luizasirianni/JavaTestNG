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
    String username = "xingsling";
    String password = "985password";
    String token = "";

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
                .body("code", is(200))
                .body("message", is(Integer.toString(idUser)))
                .body("type", stringContainsInOrder("unknown"))
                ;
    }
    @Test(priority = 1, dependsOnMethods = {"createUser"})
    public void getUser() {
        given()
            .contentType("application/json")
            .log().all()
        .when()
            .get(uri + "/" + username)
        .then()
            .log().all()
            .statusCode(200)
            .body("id", is(idUser))
            .body("username", is(username))
            .body("email", is("xingsling@gmail.com"))
        ;
    }
    @Test(priority = 2, dependsOnMethods = {"getUser"})
    public void updateUser() throws IOException{
        String jsonBody = readJson("src/test/resources/data/user.json");
        given()
            .contentType("application/json")
            .log().all()
            .body(jsonBody)
        .when()
            .put(uri + "/" + username)
        .then()
            .log().all()
            .statusCode(200)
            .body("code", is(200))
            .body("message", is("666"))
            .body("type", is("unknown"))
            ;
    }

    @Test(priority = 2, dependsOnMethods = {"getUser"})
    public void loginUser() {
        String message = "logged in user session:1684405386864";
        given()
                .contentType("application/json")
                .log().all()
        .when()
                .get(uri+ "/login?username=" + username + "&password="+ password)
        .then()
                .log().all()
                .statusCode(200)
                .body("code", is(200))
                .body("type", is("unknown"))
        .extract()
                .path("message")
                ;
        System.out.println("=============================");
        System.out.println("Response message: " + message);
        token = message.substring(23);
        System.out.println("Token: " + token);
        System.out.println("=============================");
    }


    @Test(priority = 3, dependsOnMethods = {"loginUser"})
    public void logoutUser(){
        given()
                .contentType("application/json")
                .log().all()
        .when()
                .get(uri + "/logout")
        .then()
                .log().all()
                .statusCode(200)
                .body("code", is(200))
                .body("type", is("unknown"))
                .body("message", is("ok"))
                ;
    }




//    @Test(priority = 3, dependsOnMethods = {"updateUser"})
//    public void deleteUser() throws IOException{
//        given()
//            .contentType("application/json")
//            .log().all()
//        .when()
//            .delete(uri + "/" + username)
//        .then()
//            .log().all()
//            .body("code", is(200))
//            .body("type", is("unknown"))
//            .body("message", is(username))
//            ;
//    }


}
