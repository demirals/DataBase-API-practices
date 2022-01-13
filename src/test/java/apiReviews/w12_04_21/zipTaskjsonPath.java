package apiReviews.w12_04_21;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.baseURI;
import static org.testng.Assert.*;
import static io.restassured.RestAssured.*;

public class zipTaskjsonPath {
//    Given Accept application/json
//    And path zipcode is 22031
//    When I send a GET request to /us endpoint
//    Then status code must be 200
//    And content type must be application/json
//    And Server header is cloudflare
//    And Report-To header exists
//    And body should contains following information
//    post code is 22031
//    country  is United States
//    country abbreviation is US
//    place name is Fairfax
//    state is Virginia
//    latitude is 38.8604

    @BeforeClass
    public void setUp(){baseURI="http://api.zippopotam.us/";}

    @Test
    public void test1(){

        Response response = given().accept(ContentType.JSON).when().get("US/22031");

        assertEquals(response.statusCode(), 200);
//    And content type must be application/json
//    And Server header is cloudflare
//    And Report-To header exists
//    And body should contains following information
//    post code is 22031
//    country  is United States
//    country abbreviation is US
//    place name is Fairfax
//    state is Virginia
//    latitude is 38.8604
assertEquals(response.contentType(), "application/json");
assertTrue(response.header("Server").equalsIgnoreCase("cloudflare"));
assertTrue(response.headers().hasHeaderWithName("Report-To"));

        JsonPath jsonPath = response.jsonPath();

        String postCode = jsonPath.getString("\'post code\'");   /////////////ENTERESAN BIRSIH, ARADA BOSLUK OLDUGU ICIN
        System.out.println("postCode = " + postCode);
        assertEquals(postCode, "22031");

        assertEquals(jsonPath.getString("country"), "United States");
        assertEquals(jsonPath.getString("\'country abbreviation\'"), "US");
        assertEquals(jsonPath.getString("places[0].\'place name\'"), "Fairfax");
        assertEquals(jsonPath.getString("places[0].state"), "Virginia");
        assertEquals(jsonPath.getString("places[0].latitude"), "38.8604");




    }


}
