package Day8;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.ConfigurationReader;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.*;
import static org.testng.Assert.*;
import static io.restassured.RestAssured.basePath;
import static io.restassured.RestAssured.baseURI;

public class HW_SpartanFlow {
    @BeforeClass
    public void setUp() {
        baseURI = ConfigurationReader.get("spartan_api_url");
        basePath = "/api/spartans";
    }

    @Test(priority = 1)
    public void POSTNewSpartan(){


    }

    @Test(priority = 2)
    public void PUTExistingSpartan(){

    }

    @Test(priority = 3)
    public void PATCHTExistingSpartan(){

    }

    @Test(priority = 4)
    public void GETThatSpartan(){

    }

    @Test(priority = 5)
    public void DELETETHatSpartan(){

    }
}
