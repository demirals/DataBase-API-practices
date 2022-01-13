package apitests;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.ConfigurationReader;

import javax.xml.bind.util.JAXBSource;

import static io.restassured.RestAssured.*;
import static org.testng.Assert.*;

public class CBTrainingWithJsonPath {
    @BeforeClass
    public void beforeclass() {
        baseURI = ConfigurationReader.get("cbt_api_url");
    }

    @Test
    public void test1(){
        Response response = given().accept(ContentType.JSON)
                .and().pathParam("id", 10774)
                .when().get("/teacher/{id}");            //burada kaldim , cok zorlandim

        assertEquals(response.statusCode(), 200);

        /////////////////////////////////
        //assign response to jsonpath

        JsonPath jsonPath = response.jsonPath();

        //get values from jsonpath

        String firstName = jsonPath.getString("teachers.firstName[0]");
        System.out.println("firstName = " + firstName);

        String lastName = jsonPath.getString("teachers.lastName[0]");
        System.out.println("lastName = " + lastName);

        String phone = jsonPath.getString("teachers.phone[0]");
        System.out.println("phone = " + phone);

        //json kodunda []  --> array varsa index number gerekir, {} varsa gerek yok
        // items.links[2].href[0] gibi

        //DENEMeLER
        String firstName2 = jsonPath.getString("teachers.firstName");  //>> list olmasi gerek, ama String icine ilk elemani atar
        System.out.println("firstName = " + firstName2);   //      [Florine]


//        String firstName3 = response.path("teachers.firstName");  //>> ancak bu hata verir. Jsonpath daha flexibel diyebiliriz
//        System.out.println("firstName = " + firstName3);

        //baska bir deneme: jsonpath ile int --> string icine atabiliriz ancak responde.path te hata verir, casting yapmak gerekir
        //bunun örnegi yok cünkü students DB calismiyor









    }



}
