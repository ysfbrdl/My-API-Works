package delete_requests;

import base_urls.DummyRestApiBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;
import static org.testng.AssertJUnit.*;

public class Delete02 extends DummyRestApiBaseUrl {

    /*
        URL: https://dummy.restapiexample.com/api/v1/delete/2
       HTTP Request Method: DELETE Request
       Test Case: Type by using Gherkin Language
       Assert:      i) Status code is 200
                    ii) "status" is "success"
                    iii) "data" is "2"
                    iv) "message" is "Successfully! Record has been deleted"
     */

    /*
    Given
        https://dummy.restapiexample.com/api/v1/delete/2
    When
        Send the DELETE Request and get Response
    Then
        Status code is 200
    And
        "status" is "success"
    And
        "data" is "2"
    And
        "message" is "Successfully! Record has been deleted"

     */

    @Test
    public void delete01(){
        //1.Step
        spec.pathParams("first","delete","second",2);

        //2.Step
        Map<String,Object> expectedData = new HashMap<>();

        //3.Step
        Response response = given().spec(spec).when().delete("/{first}/{second}");
        response.prettyPrint();

        //4.Step
        JsonPath json = response.jsonPath();

        //Do Assertion
        response.then().assertThat().statusCode(200);
        assertEquals("success",json.getString("status"));
        assertEquals("Successfully! Record has been deleted",json.getString("message"));
        assertEquals(2,json.getInt("data"));



    }
}