package apiTests;

import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.is;
import static io.restassured.RestAssured.given;

public class TestPet {

    public String readJson(String jsonPath) throws IOException {
        return new String(Files.readAllBytes(Paths.get(jsonPath)));
    }
  @Test
    public void createPet() throws IOException { // create a pet (POST)
        String jsonBody = readJson("src/test/resources/data/pet.json");

        given()
                .contentType("application/json")
                // "text/xml" para webservices síncronos (ex: correios)
                // "application/json" para webservices assíncronos (ex: ifood, uber)
                .log().all() //registrar tudo q vai ser enviado
                .body(jsonBody)
        .when()
                .post("https://petstore.swagger.io/v2/pet") // comando + endpoint
        .then()
                .log().all() //registrar tudo que voltou
                .statusCode(200) //validação do código de estado nativo
                //.body("code", is(200)) //validação do código de estado no JSON
                .body("id", is(1000))
                .body("name", is("Snoopy"))
                .body("category.name", is("dog"))
                .body("tags.name", contains("Animal vermifugado e com todas as vacinas"));
                //.body("tags.name", contains("vacinas"));
  }

  @Test
    public void getPet(){ //nao necessita json
        String petId = "1000";

        given()
                .contentType("application/json")
                .log().all()
        .when()
                .get("https://petstore.swagger.io/v2/pet/" + petId)
        .then()
                .log().all()
                .statusCode(200)
                .body("name", is("Snoopy"))
                .body("status", is("available"));


  }
}
