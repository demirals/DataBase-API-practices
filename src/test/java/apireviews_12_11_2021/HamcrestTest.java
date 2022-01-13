package apireviews_12_11_2021;

import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import java.awt.geom.RectangularShape;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.testng.Assert.*;

public class HamcrestTest {

    String spurl = "http://3.239.148.14:8000";
    String zipurl = "https://www.zippopotam.us/";      //zipurl + "US/45414"

    @Test
    public void oneSpartanTest() {

        given().log().all().accept(ContentType.JSON)
                .and().pathParam("id", 7)
                .when().get(spurl + "/api/spartans/{id}")
                .then().assertThat().statusCode(200).and().contentType("application/json")

                .log().all()
                .body("id", equalTo(7), "name", equalTo("Hershel"),
                        "gender", equalTo("Male"), "phone", equalTo(5278678322l));
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
        }
    ]
}*/

    @Test
    public void oneSpartanTest2() {
        given().log().all().accept(ContentType.JSON)
                .and().pathParam("zipCode", 45414 )
                .when().get(zipurl + "US/{zipCode}")
                .then().assertThat().statusCode(200)
                .body("country", equalTo("United States"),
                        "'country abbreviation'", equalTo("US"),  //buradaki '   ' unutma
                        "places.state[0]", equalTo("Ohio"),
                        "'post code'", equalTo("45414"),
                        "places.'place name'[0]", equalTo("Dayton"));
    }
}