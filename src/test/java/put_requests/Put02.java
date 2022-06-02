package put_requests;

import base_urls.DummyRestApiBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.DummyApiDataPojo;
import pojos.ResponseBodyPojo;

import static io.restassured.RestAssured.*;
import static org.testng.AssertJUnit.*;

public class Put02 extends DummyRestApiBaseUrl {

    /*
        URL: https://dummy.restapiexample.com/api/v1/update/21
       HTTP Request Method: PUT Request
       Request body: {
                        "employee_name": "Tom Hanks",
                        "employee_salary": 111111,
                        "employee_age": 23,
                        "profile_image": "Perfect image"
                     }
       Test Case: Type by using Gherkin Language
       Assert:
                i) Status code is 200
                ii) Response body should be like the following
                    {
                        "status": "success",
                        "data": {
                            "employee_name": "Tom Hanks",
                            "employee_salary": 111111,
                            "employee_age": 23,
                            "profile_image": "Perfect image"
                        },
                        "message": "Successfully! Record has been updated."
                    }
     */

    /*
    Given
        1) https://dummy.restapiexample.com/api/v1/update/21
                    2){
                        "employee_name": "Tom Hanks",
                        "employee_salary": 111111,
                        "employee_age": 23,
                        "profile_image": "Perfect image"
                      }
    When
        Send PUT Request to the Url
    Then
        Status code is 200
    And
        Response body should be like the following
                        {
                        "status": "success",
                        "data": {
                            "employee_name": "Tom Hanks",
                            "employee_salary": 111111,
                            "employee_age": 23,
                            "profile_image": "Perfect image"
                        },
                        "message": "Successfully! Record has been updated."
                    }


     */
    @Test
    public void put01(){
        //1.Step
        spec.pathParams("first","update","second",21);
        //2.Step

        DummyApiDataPojo DataPojo = new DummyApiDataPojo("Tom Hanks",111111,23,"Perfect image");
        ResponseBodyPojo responseBody = new ResponseBodyPojo("success",DataPojo,"Successfully! Record has been updated.");
        //3.Step
        Response response = given().spec(spec).contentType(ContentType.JSON).when().body(DataPojo).put("/{first}/{second}");
        response.prettyPrint();

        //4.Step
        JsonPath json = response.jsonPath();
        response.then().assertThat().statusCode(200);
        assertEquals(responseBody.getStatus(),json.getString("status"));
        assertEquals(responseBody.getMessage(),json.getString("message"));
        assertEquals(responseBody.getData().getEmployeeName(),json.getString("data.employeeName"));
        assertEquals(responseBody.getData().getEmployeeSalary(),(Integer) json.getInt("data.employeeSalary"));
        assertEquals(responseBody.getData().getEmployeeAge(),(Integer) json.getInt("data.employeeAge"));
        assertEquals(responseBody.getData().getProfileImage(), json.getString("data.profileImage"));
    }
}