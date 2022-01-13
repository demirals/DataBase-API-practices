package apitests;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.*;
import static org.testng.Assert.*;

public class jsonToJavaCollection {

    @BeforeClass
    public void beforeclass() {
        baseURI = "http://54.92.248.102:8000";
    }

    @Test
    public void SpartanToMap(){
        Response response = given().accept(ContentType.JSON)
                .pathParam("id", 15)
                .when().get("/api/spartans/{id}");

        assertEquals(response.statusCode(), 200);

        ////////////////////5. YÖNTEM______DESERIALIZATION   from json to java collection
        ///////////////burada sadece 1 spartanin bilgisini map a aliyoruz.////////////////

        //convert json response to java map____COK KISA BIR YÖNTEM
        Map<String,Object> jsonDataMap = response.body().as(Map.class);  //bu hata verir önce, google gson indir mvnrepository den
        //java.lang.IllegalStateException: Cannot parse object because no JSON deserializer found in classpath. Please put either Jackson (Databind) or Gson in the classpath.
        System.out.println("jsonDataMap = " + jsonDataMap);

        String name = (String) jsonDataMap.get("name");
        assertEquals(name, "Peter");
                                                    //BIGDECIMAL
        BigDecimal phone = new BigDecimal(String.valueOf(jsonDataMap.get("phone")));
        System.out.println("phone = " + phone);
    } ///////////////////////////burada ise cok saprtani atiyoruz list of map a/////////////////////////////////////////////////////////

    @Test
    public void allSpartansToListOfMap(){
        Response response = given().accept(ContentType.JSON)
                .when().get("/api/spartans");

        assertEquals(response.statusCode(),200);

        //we need to de-serialize JSON response to List of Maps
        List<Map<String, Object>> allSpartanList = response.body().as(List.class);         //list
        System.out.println("allSpartanList = " + allSpartanList);
        //print 2. spartans firstName

        System.out.println(allSpartanList.get(1).get("name"));

        //sadece 2. spartanla calismak istiyoruz ve map icine atmak istiyoruz
        Map<String, Object> spartan3 = allSpartanList.get(2);
        System.out.println("spartan3 = " + spartan3);
    }

    @Test
    public void regionToMap() {
        Response response = when().get("http://54.92.248.102:1000/ords/hr/regions");

        assertEquals(response.statusCode(), 200);

        //we de-serialize JSON response to Map
        Map<String, Object> regionMap = response.body().as(Map.class);

        System.out.println("regionMap.get(\"count\") = " + regionMap.get("count"));
        System.out.println("regionMap.get(\"hasMore\") = " + regionMap.get("hasMore"));
        System.out.println("regionMap.get(\"items\") = " + regionMap.get("items"));
        List<Map<String, Object>> itemsList = (List<Map<String, Object>>) regionMap.get("items"); //casting var burda

        //print the first region name
        System.out.println("itemsList.get(0).get(\"region_name\") = " + itemsList.get(0).get("region_name"));

        //FINAL WAY_JSON to POJO(Custom Class)
        // bunca karisik sey icin yeni birsey ögrenecegiz POJO





    }




    }










