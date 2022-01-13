package Day8;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.*;
import static org.testng.Assert.*;


////////////pom xml e dependency ekledik bu isler icin
///resources icinde schema dosyan var
//schemayi developerdan alacaksin

import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.ConfigurationReader;

import static io.restassured.RestAssured.basePath;
import static io.restassured.RestAssured.baseURI;

public class JsonSchemaValitationDemo {
    @BeforeClass
    public void setUp() {
        baseURI = ConfigurationReader.get("spartan_api_url");
    }

    @Test
    public void JsonSchemaValidationForSpartan(){

        given().accept(ContentType.JSON)
                .and()
                .pathParam("id", 13)
        .when()
                .get("/api/spartans/{id}")
        .then()
                .statusCode(200)
                .and()
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("SingleSpartanSchema.json"));
    }

}
