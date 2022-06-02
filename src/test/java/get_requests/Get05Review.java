package get_requests;

import base_urls.HerOkuAppBaseUrlReview;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;
import static org.testng.AssertJUnit.*;

public class Get05Review extends HerOkuAppBaseUrlReview {
    /*
        Given
            https://restful-booker.herokuapp.com/booking
        When
            User send a request to the URL
        Then
            Status code is 200
      And
         Among the data there should be someone whose firstname is "Eric" and last name is "Smith"
     */

    @Test
    public void get01(){
        //1
        //https://restful-booker.herokuapp.com/booking?firstname=Eric&lastname=Smith
        spec.pathParams("first","booking").queryParams("firstname","Eric","lastname","Smith");//?????
        //2
        //3
        Response response = given().spec(spec).when().get("/{first}");
        response.prettyPrint();
        //4
        response.then().assertThat().statusCode(200);
        //assertEquals("Eric",response.asString("firstname"));
        assertTrue(response.asString().contains("bookingid")); //?????
    }



}
