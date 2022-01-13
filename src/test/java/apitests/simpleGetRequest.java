package apitests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class simpleGetRequest{
    /////////////////DAY2 - API ILK HAZIRLIKLAR///////////////
    //önce apitests packace olustur java altinda
    //pom.xml dosyasina restAssured dependency en üste ekle
    //url yazarken asagiya, http yazmayi unutma

    String hrurl = "http://54.92.248.102:1000/ords/hr/regions";  //normalde burayi dynamic yapabilirsin

    @Test
    public void test1(){
     // RestAssured.get(hrurl) //alt + enter
        Response response = RestAssured.get(hrurl);

        System.out.println("response.statusCode() = " + response.statusCode());

        response.prettyPrint();      //print the json body
    }
    /*                              /////////ILK  TEST GÖREVI////////////
    Given accept type is json
    When user sends get request to regions endpoint
    Then response status code must be 200
    and body is json format
     */
    @Test
    public void test2(){
        Response response = given().accept(ContentType.JSON)            //postman daki Accept -->> application/json satiri
                            .when().get(hrurl);

        Assert.assertEquals(response.statusCode(), 200);

        System.out.println("response.contentType() = " + response.contentType());
        Assert.assertEquals(response.contentType(), "application/json");
    }
    ///ayni testi restAssured ile yapacagiz;
    @Test
    public void test3(){
        given().accept(ContentType.JSON)
                    .when().get(hrurl).then()
                    .assertThat().statusCode(200)
                    .and().contentType("application/json");
    }
    /*            TASK
    Given accept type is json
    When user sends get request to regions/2
    Then response status code must be 200
    and body is json format
    and response body contains Americas
     */

    @Test
    public void test4(){
        Response response = given().accept(ContentType.JSON)
                .when().get(hrurl + "/2");                         //alt + enter
        Assert.assertEquals(response.statusCode(), 200);
        Assert.assertEquals(response.contentType(), "application/json");
        Assert.assertTrue(response.body().asString().contains("Americas"));
    }
}
