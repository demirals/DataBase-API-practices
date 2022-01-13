package apiReviews.w12_04_21;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.testng.Assert.*;
import static io.restassured.RestAssured.*;

import static io.restassured.RestAssured.baseURI;

public class jsonPathMethod {

    //  1:
//            - Given accept type is Json
//             -Query param value q= region_id 3
//            - When users sends request to /countries
//              - Then status code is 200
//            - content type application/json
//              - has header "Date"
//            - And count is 6
//            - And hasMore is false
//  2:
//            - And all regions_id is 3
//            - And Country_name are;
//    Australia,China,India,Japan,Malaysia,Singapore

    @BeforeClass
    public void setUp(){baseURI="http://54.92.248.102:1000/ords/hr";}   //restassured importun icinde

    @Test
    public void jsonPath(){

        Response response = given().accept(ContentType.JSON)
                .and().queryParam("q", "{\"region_id\":3}")
                //     .when().get(hrurl + "/countries");
                .when().get("/countries");                            //burada baseURl kullandi

        assertEquals(response.statusCode(), 200);
        assertEquals(response.getContentType(), "application/json");
        assertTrue(response.headers().hasHeaderWithName("Date"));
        //////////////buraya kadar ayni idi /////////////////////

        JsonPath jsonPath = response.jsonPath();
        //we reach some usefull methods from jsonPath

        assertEquals(jsonPath.getInt("count"), 6);
        assertEquals(jsonPath.getBoolean("hasMore"), false);

        //extra task_ country name India, how can I reach this with gpath syntax
        String actualCountry = jsonPath.getString("items.country_name[2]");
        String actualCountryAlternativ = jsonPath.getString("items[2].country_name");
        System.out.println("actualCountry = " + actualCountry);
        System.out.println("actualCountryAlternativ = " + actualCountryAlternativ);

        //extra task _ href i bul  : ))))))))))))) sasirtmali
  //    System.out.println("response.path(\"items[2].links[0].href\") = " + response.path("items[2].links[0].href"));
        System.out.println("jsonPath.getString(\"items.links[2].href[0]\") = " + jsonPath.getString("items.links[2].href[0]"));

        List<String> expectedCountries = new ArrayList(Arrays.asList("Australia","India","Japan","China","Malaysia","Singapore"));
        Collections.sort(expectedCountries);
        System.out.println("expectedCountries = " + expectedCountries);
        List<String> actualCountries = jsonPath.getList("items.country_name");
        Collections.sort(actualCountries);
        System.out.println("actualCountries = " + actualCountries);

        assertEquals(actualCountries,expectedCountries);

    }


}
