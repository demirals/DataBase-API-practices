package apiReviews.w12_04_21;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import static io.restassured.response.Response.*;
import static org.testng.Assert.*;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.IResultMap;
import org.testng.annotations.Test;

public class ParametersTest {

    String hrurl = "http://54.92.248.102:1000/ords/hr";
    String zipUrl = "http://api.zippopotam.us/";
    String spartanUrl = "http://54.92.248.102:8000";

    @Test
    public void pathParamtest(){

        Response response = RestAssured.given().accept(ContentType.JSON)
                .and().pathParam("id", 15)
                .when().get(spartanUrl + "/api/spartans/{id}");

        System.out.println("response.prettyPrint() = " + response.prettyPrint());
        Assert.assertEquals(response.statusCode(), 200);
        Assert.assertEquals(response.contentType(), "application/json");
        Assert.assertTrue(response.body().asString().contains("Peter"));
    }

    @Test
    public void queryParamTest(){

        Response response = RestAssured.given().accept(ContentType.JSON)
                .and().queryParam("nameContains", "Peter")
                .and().queryParam("gender", "m")
                .when().get(spartanUrl + "/api/spartans/search");

//        System.out.println("response.prettyPrint() = " + response.prettyPrint());
        assertEquals(response.statusCode(), 200);

     //   Assert.assertEquals(response.contentType(), "application/json");
     //   Assert.assertTrue(response.body().asString().contains("Peter"));
    }

    @Test
    public void queryParamHRTest(){
        Response response = RestAssured.given().accept(ContentType.JSON)
                .and().queryParam("q", "{\"department_id\":80}")
                .when().get(hrurl + "/employees");

        response.prettyPrint();
        assertEquals(response.statusCode(), 200);
    }






}
