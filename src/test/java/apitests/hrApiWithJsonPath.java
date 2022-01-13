package apitests;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.ConfigurationReader;

import java.util.List;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.*;
import static org.testng.Assert.*;

public class hrApiWithJsonPath {

    @BeforeClass
    public void beforeclass() {
        baseURI = ConfigurationReader.get("hr_api_url");
    }
  //JSONPATH IKINCI KULLANIMI; GET ALL ::
    @Test
    public void test1(){
        Response response = get("/countries");

        //asssign to jsonPath
        JsonPath jsonPath = response.jsonPath();

        String secondCountryName = jsonPath.getString("items.country_name[1]");
        System.out.println("secondCountryName = " + secondCountryName);

        //get all country ids
        List<String> countryIDs = jsonPath.getList("items.country_id");
        System.out.println("countryIDs = " + countryIDs);

        //get all country name swhere their region id is equal to 2
        //implement filter with GPATH ***************************************************
        List<String> countryNamesWithRegionId2 = jsonPath.getList("items.findAll {it.region_id ==2}.country_name");
        System.out.println("countryNamesWithRegionId2 = " + countryNamesWithRegionId2);


    }
    @Test
    public void test2(){
        Response response = given().queryParam("limit", 107)
                .when().get("/employees");

        JsonPath jsonPath = response.jsonPath();

        //get me all firstname of employees who is working as IT_PROG
        List<String> firstNames = jsonPath.getList("items.first_name");
        System.out.println("firstNames = " + firstNames);      //bu filtre olmadan hepsini bulur

        List<String> firstNamesIT_PROG = jsonPath.getList("items.findAll {it.job_id==\"IT_PROG\"}.first_name");
        System.out.println("firstNamesIT_PROG = " + firstNamesIT_PROG);

        //get me all firstname of employees who is making more than 10000
        List<String> emails2 = jsonPath.getList("items.findAll {it.salary > 10000}.email");
        System.out.println("firstNames2 who is making more than 10K= " + emails2);

        //get me all firstname of employees who is making highest salary
        String firstNames3 = jsonPath.getString("items.min {it.salary}.first_name");
        System.out.println("firstNames2 who is making highest salary= " + firstNames3);
    }


}












