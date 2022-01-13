package apitests;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.*;

public class SpartanTestWithPath {
    @BeforeClass
    public void beforeclass() {
        baseURI = "http://54.92.248.102:8000";
    }
    /*  TASK
   Given accept type is json
   And path param id is 10
   When user sends a get request to "api/spartans/{id}"
   Then status code is 200
   And content-type is "application/json;charset=UTF-8"
   And response payload values match the following:
           id is 11,                                        //degerleri degistirdim
           name is "Nona",
           gender is "Female",
           phone is 7959094216     */
    @Test
    public void getOneSpartan_path(){
        Response response = given().accept(ContentType.JSON)
                .and().pathParam("id", 11)
                .when().get("api/spartans/{id}");
        assertEquals(response.statusCode(), 200);
     // assertEquals(response.contentType(), "application/json;charset=UTF-8");
     // response.prettyPrint();

        //printing each key value in the json body/payload
        System.out.println(response.path("id").toString());
        System.out.println(response.path("name").toString());
        System.out.println(response.body().path("gender").toString());   //buraya neden body() ekledi ki ?
        System.out.println(response.body().path("phone").toString());

        //save json keyvalues
        int id = response.path("id");
        String name = response.path("name");
        String gender = response.path("gender");
        long phone = response.path("phone");
        assertEquals(id, 11);
        assertEquals(name, "Nona");
        assertEquals(gender, "Female");
        assertEquals(phone, 7959094216l);               //long kabul etmedi sonuna l koydum
    }

    //BURADAN ITIBAREN JSON ICINE GIRIYORUZ
    @Test
    public void getAllSpartanWithPath() {
        Response response = given().accept(ContentType.JSON)
                .when().get("/api/spartans");

        assertEquals(response.statusCode(), 200);
        assertEquals(response.getHeader("Content-Type"), "application/json");

        System.out.println("response.path(\"id[0]\").toString() = " + response.path("id[0]").toString());

        int firstId = response.path("id[0]");
        System.out.println("firstId = " + firstId);

        String firstName = response.path("name[0]");
        System.out.println("firstName = " + firstName);

        //last FirstName
        String lastFirstName = response.path("name[-1]");
        System.out.println("lastFirstName = " + lastFirstName);

        int lastId = response.path("id[-1]");
        System.out.println("lastId = " + lastId);

        //get all firstnames (list olarak alir);
        
        List<String> names = response.path("name");
        System.out.println("names = " + names);

        List<Object> phones = response.path("phone");
        for (Object phone : phones) {
            System.out.println("phone = " + phone);
        }


    }


}
