package apitests;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.ConfigurationReader;

import java.util.List;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class hrApiWithPath {

        @BeforeClass
        public void beforeclass() {
            baseURI = ConfigurationReader.get("hr_api_url");
        }

        @Test
        public void getCountriesWithPath(){
            Response response = given().accept(ContentType.JSON)
                    .and().queryParam("q", "{\"region_id\":2}")
                    .when().get("/countries");
            assertEquals(response.statusCode(), 200);

            //json yapisindaki limiti bilgisini al mesela;
            System.out.println("response.path(\"limit\") = " + response.path("limit"));
            System.out.println("response.path(\"hasMore\") = " + response.path("hasMore"));

            ///////////////but problem starts with the "items";
     //       items.region_id[0]

//            "items": [
//            {
//                "region_id": 2,
//                    "region_name": "Americas",
//                    "links": [
//                {
//                    "rel": "self",
//                        "href": "http://54.92.248.102:1000/ords/hr/regions/2"
//          ////////////////////////////////////////////////////////////////////

            String firstCountryId = response.path("items.country_id[0]");
            System.out.println("firstCountryId = " + firstCountryId);

            String secondCountryName = response.path("items.country_name[4]");
            System.out.println("secondCountryName = " + secondCountryName);

            String canada_Href = response.path("items.links[2].href[0]");   ////////Burada Array oldugu icin mutlaka Index NUm. vermeliyiz
            System.out.println("canada_Href = " + canada_Href);

            //get all countries
            // items.county_name -->> list of array verir tüm countryleri
            List<String> countryNames = response.path("items.country_name");
            System.out.println("countryNames = " + countryNames);

            //TASK
            //assert that all region ID s are equal to 2
            List<Integer> regionIds = response.path("items.region_id");   //burada Integer sec, String secersen asagida Object secebilirsin
            System.out.println("regionIds = " + regionIds);
            for (int regionId : regionIds) {                     //burada int seciyoruz dikkat et
                assertEquals(regionId, 2);
            }
        }

    ///////////////TASK make sure we have only IT_PROG as a job_id
    @Test
    public void test2(){
        Response response = given().accept(ContentType.JSON)
                .and().queryParams("q", "{\"job_id\":\"IT_PROG\"}")
                .when().get("/employees");
        assertEquals(response.statusCode(), 200);
        assertEquals(response.contentType(), "application/json");
        assertTrue(response.body().asString().contains("IT_PROG"));

        List<String> job_ids = response.path("items.job_id");
        for (String job_id : job_ids) {
            System.out.println("job_id = " + job_id);
            assertEquals(job_id, "IT_PROG");
        }


    }

    }











