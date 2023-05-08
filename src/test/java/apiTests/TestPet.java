package apiTests;

import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.stringContainsInOrder;

public class TestPet {

    String uri = "https://petstore.swagger.io/v2/pet";
    int petID = 1000;
    public String readJson(String jsonPath) throws IOException {
        return new String(Files.readAllBytes(Paths.get(jsonPath)));
    }
  @Test(priority = 0)
    public void createPet() throws IOException { // create a pet (POST)
        String jsonBody = readJson("src/test/resources/data/pet.json");

        given()
                .contentType("application/json")
                // "text/xml" para webservices síncronos (ex: correios)
                // "application/json" para webservices assíncronos (ex: ifood, uber)
                .log().all() //registrar tudo q vai ser enviado
                .body(jsonBody)
        .when()
                .post(uri) // comando + endpoint
        .then()
                .log().all() //registrar tudo que voltou
                .statusCode(200) //validação do código de estado nativo
                //.body("code", is(200)) //validação do código de estado no JSON
                .body("id", is(petID))
                .body("name", is("Snoopy"))
                .body("category.name", is("dog"))
                .body("tags.name[0]", stringContainsInOrder("vermifugado"))
                .body("tags.id[0]", is(3))
                .body("tags.name[1]", stringContainsInOrder("vacina"))
                .body("tags.id[1]", is(4));
  }
  @Test (priority = 1, dependsOnMethods = {"createPet"})
    public void getPet(){ //nao necessita json
        //String petId = "1000";

        given()
                .contentType("application/json")
                .log().all()
        .when()
                .get(uri + "/" + petID)
        .then()
                .log().all()
                .statusCode(200)
                .body("name", is("Snoopy"))
                .body("status", is("available"));


  }

    @Test (priority = 2, dependsOnMethods = {"getPet"})
    public void updatePet() throws IOException { // PUT
        String jsonBody = readJson("src/test/resources/data/newPet.json");

        given()
                .contentType("application/json")
                .log().all()
                .body(jsonBody) //json a ser transmitido para alteraçao
        .when()
                .put(uri)
        .then()
                .log().all()
                .statusCode(200)
                .body("status", is("sold"));
    }

    @Test (priority = 1, dependsOnMethods = {"updatePet"})
    public void deletePet() throws IOException {

    }
}
