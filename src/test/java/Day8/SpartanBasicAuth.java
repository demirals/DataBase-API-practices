package Day8;

import io.restassured.http.ContentType;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.ConfigurationReader;

import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.*;
import static org.testng.Assert.*;

public class SpartanBasicAuth {
    @BeforeClass
    public void setUp() {
        baseURI = ConfigurationReader.get("spartan_api_url");
        basePath = "/api/spartans";
    }

    @Test
    public void test1(){
        given()
                .accept(ContentType.JSON)
                .and()
                .auth().basic("user","user")                                //auth().basic()
        .when()
                .get(baseURI + basePath)
                .then().log().all()
                .statusCode(200);
    }



}
