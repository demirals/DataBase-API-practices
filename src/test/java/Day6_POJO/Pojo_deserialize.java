package Day6_POJO;
import com.google.gson.Gson;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.testng.Assert.assertEquals;

public class Pojo_deserialize {

    @Test
    public void oneSpartanPojo(){
        Response response = given().accept(ContentType.JSON)
                .pathParam("id", 15)
                .when().get("http://54.92.248.102:8000/api/spartans/{id}");

        assertEquals(response.statusCode(),200);

        //JSON to POJO --> de serialize to java custom class
        //JSON to Our Spartan class object

        Spartan spartan15 = response.body().as(Spartan.class);

        System.out.println(spartan15);

        System.out.println("spartan15.getName() = " + spartan15.getName());
        System.out.println("spartan15.getPhone() = " + spartan15.getPhone());

        //assertion
        assertEquals(spartan15.getId(),15);
        assertEquals(spartan15.getName(),"Peter");

        //jsonshema2pojo.org web seite var ona bak
        //oradaki kodlari zip ile alip bu klasöre kopyaladik. simdi calistiracagiz
        //Item, link, lnk_1 ve Regions classlari
    }

    @Test
    public void regionToPojo(){
        Response response = when().get("http://54.92.248.102:1000/ords/hr/regions");
        assertEquals(response.statusCode(), 200);

        //json to pojo (region class)

        Regions regions = response.body().as(Regions.class);
        System.out.println("regions.getHasMore() = " + regions.getHasMore());
        System.out.println("regions.getCount() = " + regions.getCount());

        System.out.println(regions.getItems().get(0).getRegionName());


        List<Item> items = regions.getItems();

        System.out.println(items.get(1).getRegionId());

    }
    @Test
    public void gson_example(){

     Gson gson = new Gson();
     //JSON to JAVA collections or Pojo >> De_serialization
        String myJsonData = "{\n" +
                "    \"id\": 15,\n" +
                "    \"name\": \"Peter\",\n" +
                "    \"gender\": \"Male\",\n" +
                "    \"phone\": 8811111111\n" +
                "}";

        Map<String,Object> map = gson.fromJson(myJsonData, Map.class);
        System.out.println(map);

        Spartan spartan15 = gson.fromJson(myJsonData,Spartan.class);
        System.out.println(spartan15);    //bu düzgün calismadi, yazmadi


        //------serialization-------------//
        //JAVA collection or POJO to JSON

        Spartan spartanEU = new Spartan(200, "Peter", "Male", 8811111111l);

        String jsonSaprtanEU = gson.toJson(spartanEU);
        System.out.println(jsonSaprtanEU);

    }


}












