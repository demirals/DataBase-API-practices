package apireviews_12_11_2021;

import static org.hamcrest.Matchers.*;
//import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;

import Day6_POJO.Spartan;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import javax.swing.*;

public class CreateSpartan {

    @BeforeClass
    public void setUp(){
      //  RestAssured.baseURI
        baseURI = "http://3.239.148.14:8000";
        basePath = "/api/spartans";                   //güzel birsey bu
    }
    @Test
    public void postSpartan (){
        Spartan mySpartan = new Spartan();
        mySpartan.setName("Spartan301");
        mySpartan.setGender("Male");
        mySpartan.setPhone(1236547895l);

        Response response = given()
                .accept(ContentType.JSON)  // I want json response
                .and()
                .contentType(ContentType.JSON)  //I am sending JSON as a value (****this is needed for POST)
        .and().body(mySpartan)                //serialization from java to json
                .when().post();   //since I provided URI and path as a base parameter

        int idFromPost = response.path("data.id");
        System.out.println("response.body() = " + response.body().asString());

        //simdi get request yap bakalim, hamcrest kullan burada

        given().log().all().accept(ContentType.JSON)
                .pathParam("id", idFromPost)
                .when().get("/{id}")
                .then().statusCode(200);







    }



}
