package get_requests;

import base_urls.GoRestBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.GoRestDataPojo;
import pojos.GoRestResponseBodyPojo;

import static io.restassured.RestAssured.*;
import static org.testng.AssertJUnit.assertEquals;

public class Get13Pojo extends GoRestBaseUrl {
    /*
    Given
        https://gorest.co.in/public/v1/users/13
    When
        User send GET Request to the URL
    Then
        Status Code should be 200
    And
        Response body should be like
           {
                "meta": null,
                "data": {
                            "id": 13,
                            "name": "Devak Varma DO",
                            "email": "varma_devak_do@schamberger-roob.net",
                            "gender": "male",
                            "status": "active"
                        }
            }
*/
    /*
    To do that task, do the followings;
    1)Check the response body on Postman
    2)Create Pojo Classes
    3)Follow the 4 steps in API automation
     */
    @Test
    public void get01Pojo(){
        //1.Step: Set the Url
        spec.pathParams("first","users","second",13);

        //2.Step: Set the Expected Data
        GoRestDataPojo goRestDataPojo = new GoRestDataPojo(13,"Devak Varma DO","varma_devak_do@schamberger-roob.net","male","active");
        GoRestResponseBodyPojo goRestResponseBodyPojo = new GoRestResponseBodyPojo(null,goRestDataPojo);

        //3.Step: Send the Get Request and get the Response
        Response response = given().spec(spec).when().get("/{first}/{second}");
        response.prettyPrint();

        //4.Step: Do Assertions
        GoRestResponseBodyPojo actualPojo = response.as(GoRestResponseBodyPojo.class);

        assertEquals(goRestResponseBodyPojo.getMeta(),actualPojo.getMeta());
        assertEquals(goRestDataPojo.getId(),actualPojo.getData().getId());
        assertEquals(goRestDataPojo.getName(),actualPojo.getData().getName());
        assertEquals(goRestDataPojo.getEmail(),actualPojo.getData().getEmail());
        assertEquals(goRestDataPojo.getGender(),actualPojo.getData().getGender());
        assertEquals(goRestDataPojo.getStatus(),actualPojo.getData().getStatus());

    }

}
