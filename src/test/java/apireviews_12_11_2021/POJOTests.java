package apireviews_12_11_2021;


import Day6_POJO.Spartan;
import com.google.gson.Gson;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.testng.Assert.*;

public class POJOTests {
    String spurl = "http://3.239.148.14:8000";
    String zipurl = "https://www.zippopotam.us/";      //zipurl + "US/45414"

    @Test
    public void spartanWithPojo() {
        Response response = given().accept(ContentType.JSON)
                .and().pathParam("id", 7)
                .when().get(spurl + "/api/spartans/{id}");

        assertEquals(response.statusCode(), 200);

        Spartan spartan7 = response.body().as(Spartan.class);   //de-serialization
        //as() method to map my json object to spartan object
        System.out.println("spartan7.getName() = " + spartan7.getName());

        assertEquals(spartan7.getName(), "Hershel");

        //jsonshema2pojo.org
    }
        @Test
    public void zipTestWithPojo(){

            Gson gson = new Gson();
            Response response = given().log().all().accept(ContentType.JSON)
                    .and().pathParam("zipCode", 45414)
                    .when().get(zipurl + "US/{zipCode}");

            assertEquals(response.statusCode(), 200);
            System.out.println(response.body().asString());

            //jsonshema2pojo.org
            //siteden classlari olusturduktan sonra ;
       //   PostalCode pc45414 = response.body().as(PostalCode.class);
       //   PostalCode pc45414 = gson.fromJson(response.body().asString(),PostalCode.class);
            //ileride problem yasarsan böyle string yapabilirsin ama cok anlamadim

            PostalCode pc45414 = response.body().as(PostalCode.class);   //bu da olur
            System.out.println(pc45414.getCountry());
            System.out.println(pc45414.getPlaces().get(0).getPlaceName());
    }





    }




