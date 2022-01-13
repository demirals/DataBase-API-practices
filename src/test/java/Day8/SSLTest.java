package Day8;

import org.testng.annotations.Test;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.*;
import static org.testng.Assert.*;

public class SSLTest {

    @Test
    public void test1(){
        given()
                .relaxedHTTPSValidation()
                .when().get("https://untrusted-root.badssl.com/")
                .prettyPrint();
        //1. SSLHandshakeException hatasi verir ssl zert. yoksa.
        //bunu asmak icin relaxed ... ekleriz yukardakini
   }

   //veya asagidaki yöntem
   @Test
    public void keyStore(){
        given()
                .keyStore("pathoffile", "password")  //bu calismadi
                .when().get("myapi");




   }

}
