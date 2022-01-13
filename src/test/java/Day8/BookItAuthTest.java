package Day8;

import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.*;
import static org.testng.Assert.*;

public class BookItAuthTest {
/* GEREKLI LINKLER

Bookit API Doc: https://cybertek-reservation-api-docs.herokuapp.com/#introduction
Bookit Credentials : https://docs.google.com/spreadsheets/d/1X7NHmrm-XaAaEnTBAFiXBlomAYigyZHzzHtm909_WUs/edit#gid=0
 */
    @BeforeClass
    public void before(){
        baseURI = "https://cybertek-reservation-api-qa3.herokuapp.com";
    }

    String accessToken = "eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiI5MyIsImF1ZCI6InN0dWRlbnQtdGVhbS1sZWFkZXIifQ.fKdbQlE8o5LHaA7xoWciG3HV7J8REk2YlJO1-9n-fKs";

    @Test
    public void getAllCampuses(){
        Response response = given().header("Authorization", accessToken).

                when().get("/api/campuses");

        response.prettyPrint();
        System.out.println("response.statusCode() = " + response.statusCode());
    }
}
