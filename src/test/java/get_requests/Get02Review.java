package get_requests;

import base_urls.HerOkuAppBaseUrl;
import base_urls.HerOkuAppBaseUrlReview;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static org.testng.AssertJUnit.*;

public class Get02Review extends HerOkuAppBaseUrlReview {

    /*
        Given
            https://restful-booker.herokuapp.com/booking/1001
        When
            User send a GET Request to the url
        Then
            HTTP Status code should be 404
        And
            Status Line should be HTTP/1.1 404 Not Found
        And
            Response body contains "Not Found"
        And
            Response body does not contain "TechProEd"
        And
            Server is "Cowboy"
     */

    @Test
    public void get01(){
        //1. Set the url
        //String url = "https://restful-booker.herokuapp.com/booking/1001";
        spec.pathParams("first","booking","second",1001);

        //2. Set the Expected Data
        //3. Send the Get Request
        Response response = given().spec(spec).when().get("/{first}/{second}");
        response.prettyPrint();
        //4. Do Assertions
        System.out.println(response.getStatusCode());
        response.then().assertThat().statusCode(404).statusLine("HTTP/1.1 404 Not Found");

        assertTrue(response.asString().contains("Not Found"));
        assertFalse(response.asString().contains("TechProEd"));
        assertEquals("Cowboy",response.getHeader("Server"));



    }
}
