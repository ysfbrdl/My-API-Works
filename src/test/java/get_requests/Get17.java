package get_requests;

import base_urls.DummyRestApiBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.DummyApiDataPojo;
import pojos.ResponseBodyPojo;
import utils.JsonUtil;

import static io.restassured.RestAssured.*;
import static org.testng.AssertJUnit.assertEquals;

public class Get17 extends DummyRestApiBaseUrl {
    /*
   URL: https://dummy.restapiexample.com/api/v1/employee/1
   HTTP Request Method: GET Request
   Test Case: Type by using Gherkin Language
   Assert:      i) Status code is 200
                ii) "employee_name" is "Tiger Nixon"
                iii) "employee_salary"is 320800
                iv)  "employee_age" is 61
                v) "status" is "success"
                vi)  "message" is "Successfully! Record has been fetched."
 */
    /*
    Given
        URL: https://dummy.restapiexample.com/api/v1/employee/1
    When
        User Send the Get Request to Url
    Then
        Status code is 200
    And
        "employee_name" is "Tiger Nixon"
    And
        "employee_salary"is 320800
    And
        "employee_age" is 61
    And
        "status" is "success"
    And
        "message" is "Successfully! Record has been fetched."
*/
    @Test
    public void get01(){
        //1.Step: Set the Url
        spec.pathParams("first","employee","second",1);

        //2.Step: Set the Expected Data
        DummyApiDataPojo dataPojo = new DummyApiDataPojo("Tiger Nixon", 320800, 61, "");
        ResponseBodyPojo responsePojo = new ResponseBodyPojo("success",dataPojo,"Successfully! Record has been fetched.");

        //3.Step: Send Get Request to get Response
        Response response = given().spec(spec).when().get("/{first}/{second}");
        response.prettyPrint();

        //4.Step: Do Assertions
        response.then().assertThat().statusCode(200);

        ResponseBodyPojo responseBodyPojo = JsonUtil.convertJsonToJavaObject(response.asString(),ResponseBodyPojo.class);
        System.out.println(responseBodyPojo);

        JsonPath json = response.jsonPath();
        assertEquals(responsePojo.getStatus(),responseBodyPojo.getStatus());
        assertEquals(responsePojo.getMessage(),responseBodyPojo.getMessage());
        assertEquals(responsePojo.getData().getEmployeeName(),json.getString("data.employee_name"));
        assertEquals(responsePojo.getData().getEmployeeSalary(), (Integer)json.getInt("data.employee_salary"));
        assertEquals(responsePojo.getData().getEmployeeAge(),(Integer)json.getInt("data.employee_age"));
        assertEquals(responsePojo.getData().getProfileImage(),json.getString("data.profile_image"));
    }



}
