package apiReviews.w12_04_21;

// import io.restassured.RestAssured;                static import
import static io.restassured.RestAssured.*;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
//import org.testng.Assert;
import static org.testng.Assert.*;
import org.testng.annotations.Test;

public class R01_API_Test {

    //restassured dependency koymayi unutma pom'a

    //swaggerdan try it diyerek curl alip, postmanda file-import .. yapabiliriz.

    String hrurl = "http://54.92.248.102:1000/ords/hr";
    String zipUrl = "http://api.zippopotam.us/";
    String spartanUrl = "http://54.92.248.102:8000/";

    @Test
    public void testOne() {
        Response response = RestAssured.given().accept(ContentType.JSON)
                .when().get(hrurl + "/employees");

        //   System.out.println(response.prettyPrint());
        assertEquals(response.statusCode(), 200);
        assertEquals(response.contentType(), "application/json");
    }

    @Test
    public void testTwo() {
        Response response = RestAssured.given().accept(ContentType.JSON)
                .when().get(hrurl + "/regions");

        // System.out.println(response.prettyPrint());
        assertEquals(response.statusCode(), 200);
        assertEquals(response.contentType(), "application/json");
        assertTrue(response.body().asString().contains("Europe"));
        assertTrue(response.headers().hasHeaderWithName("Date"));
        assertEquals(response.header("Transfer-Encoding"), "chunked");
    }

    @Test
    public void testThree() {
        Response response = RestAssured.given().accept(ContentType.JSON)
                .when().get(zipUrl + "US/45414");

     //   System.out.println(response.prettyPrint());
        assertEquals(response.statusCode(), 200);
        assertEquals(response.contentType(), "application/json");
        assertTrue(response.body().asString().contains("Dayton"));
    }
    @Test
    public void testFour() {              //chaning method response olmadan, hersey tek satirda
        given().accept(ContentType.JSON).when().get(zipUrl + "US/45414")
                .then().assertThat().contentType("application/json").and().statusCode(200);

    }



}



