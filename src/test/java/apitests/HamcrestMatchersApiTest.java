package apitests;

import io.restassured.http.ContentType;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.testng.Assert.*;

public class HamcrestMatchersApiTest {
    /*
         given accept type is Json
         And path param id is 15
         When user sends a get request to spartans/{id}
         Then status code is 200
         And content type is Json
         And json data has following
             "id": 15,
             "name": "Meta",
             "gender": "Female",
             "phone": 1938695106            */
    @Test
    public void OneSpartanWithHamcrest(){
        given().accept(ContentType.JSON)
                .and().pathParam("id", 15)
                .when().get("http://54.92.248.102:8000/api/spartans/{id}")  //burada durmuyor devam ediyoruz
                .then().assertThat().statusCode(200)
                .and().assertThat().contentType("application/json")
                .and().assertThat().body("id", equalTo(15),
                        "name", equalTo("Peter"), "gender", equalTo("Male"),
                        "phone", equalTo(8811111111l));                              //chain yapiyoruz burada
    }

//    {
//        "teacherId": 10774,
//            "firstName": "Florine",
//            "lastName": "Hickle",
//            "emailAddress": "florine.hickle@gmail.com",
//            "joinDate": "06/08/1981",
//            "password": "796460",
//            "phone": "2599782757",
//            "subject": "Community-Services",
//            "gender": "Male",
//            "department": "Banking",
//            "birthDate": "10/17/1997",
//            "salary": 6,
//            "batch": 1,
//            "section": "Liaison",
//            "premanentAddress": "6480 Valentin Mission, Leonilamouth, ID 13108-7024"
//    },
    @Test                          //COK KLASS YÖNTEM
    public void teacherData(){
        given().accept(ContentType.JSON)
                .and().pathParam("id", 10774)
                .when().log().all().get("http://api.cybertektraining.com/teacher/{id}")     //log().all()
                .then().assertThat().statusCode(200)
                .and().contentType(equalTo("application/json;charset=UTF-8"))
                .and().header("Content-Encoding", equalTo("gzip"))
                .and().header("Connection", equalTo("Keep-Alive"))
                .and().header("Date", notNullValue())                                //exist or not ?
                .and().assertThat().body("teachers.firstName[0]", equalTo("Florine"),
                        "teachers.lastName[0]", equalTo("Hickle"));
//                .log().all()          //ekrana yazdirir, ancak bunu basa yaziyoruz
//                .log().headers();
    }

    @Test                                 //hasItems:multipple assertion
    public void teachersWithDepartments(){
        given().accept(ContentType.JSON)
                .and().pathParam("name", "Computer")
                .when().log().all().get("http://api.cybertektraining.com/teacher/department/{name}")
                .then().statusCode(200).and()
                .contentType(equalTo("application/json;charset=UTF-8")).and()
                .body("teachers.firstName", hasItems("Alexander", "Marteen"));
    }


}
