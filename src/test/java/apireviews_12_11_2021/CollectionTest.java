package apireviews_12_11_2021;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import static io.restassured.RestAssured.*;
import static org.testng.Assert.*;

public class CollectionTest {
    String spurl = "http://3.239.148.14:8000";
    String zipurl = "https://www.zippopotam.us/";      //zipurl + "US/45414"

    @Test
    public void oneSpartanWithMap(){
        Response response = given().accept(ContentType.JSON)
                .and().pathParam("id", 7)
                .when().get(spurl + "/api/spartans/{id}");

        assertEquals(response.statusCode(), 200);

        Map<String, Object> spartan7 = response.body().as(Map.class);
        System.out.println(spartan7);

        assertEquals(spartan7.get("id"), 7.0);
        assertEquals(spartan7.get("name"), "Hershel");
        assertEquals(spartan7.get("gender"), "Male");
        assertEquals(spartan7.get("phone"), 5.278678322E9);
    }
/*{
    "post code": "45414",
    "country": "United States",
    "country abbreviation": "US",
    "places": [
        {
            "place name": "Dayton",
            "longitude": "-84.2024",
            "state": "Ohio",
            "state abbreviation": "OH",
            "latitude": "39.8285"
        }     ] }*/
    @Test
    public void zipTestWithMap(){
        Response response = given().log().all().accept(ContentType.JSON)
                .and().pathParam("zipCode", 45414)
                .when().get(zipurl + "US/{zipCode}");

        assertEquals(response.statusCode(), 200);
        Map<String, Object> pc45414 = response.body().as(Map.class);  //de-serialization
        System.out.println("pc45414 = " + pc45414);

        assertEquals(pc45414.get("country"), "United States");
        assertEquals(pc45414.get("post code"), "45414");

        //places altinda baska bir array var, list of map yapmak lazim
        //pc45414 = {post code=45414, country=United States, country abbreviation=US, places=[{place name=Dayton, longitude=-84.2024, state=Ohio, state abbreviation=OH, latitude=39.8285}]}

        // I am getting places key from my map and putting the value in a list<map>
        List<Map<String, Object>> placesFor45414 = (List<Map<String, Object>>) pc45414.get("places");  //casting
        assertEquals(placesFor45414.get(0).get("state"), "Ohio");
        assertEquals(placesFor45414.get(0).get("state abbreviation"), "OH");

    }





}
