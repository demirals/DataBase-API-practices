package Day8;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.ConfigurationReader;

import java.util.Random;

import static io.restassured.RestAssured.*;

public class DELETERequestDemo {

    @BeforeClass
    public void setUp() {
        baseURI = ConfigurationReader.get("spartan_api_url");
        basePath = "/api/spartans";
    }

    @Test
    public void test1() {

        Random rd = new Random();                               //RANDOM
        int idToDelete = rd.nextInt(200)+1;
        System.out.println("idToDelete = " + idToDelete);

        given().
                pathParam("id", idToDelete)
        .when()
                .delete("/{id}")
        .then()
                .statusCode(204).log().all();
    }

}
