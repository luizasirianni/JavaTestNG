package apiTests;

import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

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
                .body("tags.name[0]", stringContainsInOrder("vermifugado"))
                .body("tags.id[0]", is(3))
                .body("tags.name[1]", stringContainsInOrder("vacina"))
                .body("tags.id[1]", is(4));
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

  @Test
    public void updatePet() throws IOException { //alterar pet
        String jsonBody = readJson("src/test/resources/data/newPet.json");

        given()
                .contentType("application/json")
                .log().all()
                .body(jsonBody) //json a ser transmitido para alteraçao
        .when()
                .put("https://petstore.swagger.io/v2/pet")
        .then()
                .log().all()
                .statusCode(200)
                .body("status", is("sold"));
    }
}
