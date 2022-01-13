package apitests;

import groovy.json.JsonParser;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.ConfigurationReader;
import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import static io.restassured.RestAssured.baseURI;

public class SpartanTestWithJsonPath {
    @BeforeClass
    public void beforeclass() {
        baseURI = ConfigurationReader.get("spartan_api_url");
    }

    /*    Given accept type is json
          And path param spartan id is 11
          When user sends a get request to /spartans/{id}
         Then status code is 200
         And content type is Json
         And   "id": 11,
               "name": "Nona",
              "gender": "Female",
              "phone": 7959094216    */

    @Test                   ////////BU ESKI YÖNTEM
    public void test1(){
        Response response = given().accept(ContentType.JSON)
                .and().pathParam("id", 11)
                .when().get("/api/spartans/{id}");
        assertEquals(response.statusCode(), 200);
        assertEquals(response.contentType(), "application/json");

        //verify id and name with path
        int id = response.path("id");            //postmanda sadece 1 tane id aliyoruz, listeye gerek yok
        String name = response.path("name");

        assertEquals(id, 11);
        assertEquals(name, "Nona");

        ///BURADA JSONPATH BASLIYOR
        //assign response to jsonpath
        JsonPath jsonPath = response.jsonPath();              //ikinci kisma dikkat, kücük j

        int idJson = jsonPath.getInt("id");
        String nameJson = jsonPath.getString("name");
        String gender = jsonPath.getString("gender");
        long phone =jsonPath.getLong("phone");

        System.out.println("idJson = " + idJson);
        System.out.println("nameJson = " + nameJson);
        System.out.println("gender = " + gender);
        System.out.println("phone = " + phone);

        assertEquals(idJson, 11);
        assertEquals(nameJson, "Nona");
        assertEquals(gender, "Female");
        assertEquals(phone, 7959094216l);   //sonuna l koy dikkat et lan biraz!

    }







}
