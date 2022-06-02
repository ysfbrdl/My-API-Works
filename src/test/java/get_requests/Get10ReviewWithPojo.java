package get_requests;

import base_urls.GoRestBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.GoRestDataPojo;
import pojos.GoRestResponseBodyPojo;

import static io.restassured.RestAssured.*;
import static org.testng.AssertJUnit.*;

public class Get10ReviewWithPojo extends GoRestBaseUrl {
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
                "name": "Rev. Mangalya Naik",
                "email": "naik_mangalya_rev@mayert-friesen.io",
                "gender": "male",
                "status": "active"
             }
        }
     */

    @Test
    public void get01(){
        //Set Url
        spec.pathParams("first","users","second",13);

        //Set Expected Data
        GoRestDataPojo goRestDataPojo = new GoRestDataPojo(13,"Rev. Mangalya Naik","naik_mangalya_rev@mayert-friesen.io","male","active");
        GoRestResponseBodyPojo goRestResponseBodyPojo = new GoRestResponseBodyPojo(null,goRestDataPojo);

        //Send Request get Response
        Response response = given().spec(spec).when().get("/{first}/{second}");
        response.prettyPrint();

        GoRestResponseBodyPojo actualData = response.as(GoRestResponseBodyPojo.class);
        System.out.println(actualData);

        assertEquals(goRestResponseBodyPojo.getMeta(),actualData.getMeta());
        assertEquals(goRestResponseBodyPojo.getData().getId(),actualData.getData().getId());
        assertEquals(goRestResponseBodyPojo.getData().getName(),actualData.getData().getName());
        assertEquals(goRestResponseBodyPojo.getData().getEmail(),actualData.getData().getEmail());
        assertEquals(goRestResponseBodyPojo.getData().getGender(),actualData.getData().getGender());
        assertEquals(goRestResponseBodyPojo.getData().getStatus(),actualData.getData().getStatus());
    }
}
