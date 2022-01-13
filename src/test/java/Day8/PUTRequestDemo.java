package Day8;


import io.restassured.http.ContentType;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.*;
import static org.testng.Assert.*;

public class PUTRequestDemo {
    @BeforeClass
    public void setUp() {
        baseURI = "http://54.92.248.102:8000";
        //       basePath = "/api/spartans";
    }

    @Test
    public void PutTest() {
        //create one map fot the PUT request json body
        Map<String, Object> putRequestMap = new HashMap<>();
        putRequestMap.put("name", "PutName");
        putRequestMap.put("gender", "Male");
        putRequestMap.put("phone", 12131232514l);

        given().log().all()
                .and()
                .contentType(ContentType.JSON)
                .pathParam("id", 500)
                .and()
                .body(putRequestMap)
                .when()
                .put("/api/spartans/{id}")
                .then().log().all()
                .assertThat().statusCode(204);

        //send get request to verify body
    }

    @Test
    public void PatchTest() {
        Map<String, Object> putRequestMap = new HashMap<>();
        putRequestMap.put("name", "TJ");                                //

        given().log().all()
                .and()
                .contentType(ContentType.JSON)
                .pathParam("id", 500)
                .and()
                .body(putRequestMap)
                .when()
                .patch("/api/spartans/{id}")                          //
                .then().log().all()
                .assertThat().statusCode(204);
    }
}



















