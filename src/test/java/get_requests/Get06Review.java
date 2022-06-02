package get_requests;

import base_urls.HerOkuAppBaseUrlReview;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;
import org.testng.asserts.SoftAssert;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;
import static org.testng.AssertJUnit.assertEquals;

public class Get06Review extends HerOkuAppBaseUrlReview {
    /*
    Given
        https://restful-booker.herokuapp.com/booking/5
    When
        User send a GET request to the URL
    Then
        HTTP Status Code should be 200
    And
        Response content type is "application/json"
    And
        Response body should be like;
        {
            "firstname": "Mary",
            "lastname": "Jackson",
            "totalprice": 111,
            "depositpaid": false,
            "bookingdates": {   "checkin": "2017-05-23",
                                "checkout":"2019-07-02" }
        }
     */
    @Test
    public void get01(){
        //1
        spec.pathParams("first","booking","second",5);
        //2
        Response response = given().spec(spec).when().get("/{first}/{second}");
        response.prettyPrint();
        //3
        //4
//        response.
//                then().
//                assertThat().
//                statusCode(200).
//                contentType("application/json").
//                body("firstname",equalTo("Jim"),
//                        "lastname",equalTo("Jackson"),
//                        "bookingdates.checkin",equalTo("2018-09-02"));


        JsonPath json = response.jsonPath();
//        assertEquals("Jim",json.getString("firstname"));
//        assertEquals("Jackson",json.getString("lastname"));
//        assertEquals("2018-09-02",json.getString("bookingdates.checkin"));


        SoftAssert softAssert = new SoftAssert();

        softAssert.assertEquals("Jim",json.getString("firstname"));
        softAssert.assertEquals("Jackson",json.getString("lastname"));
        softAssert.assertEquals(false,json.getBoolean("depositpaid"));
        softAssert.assertEquals("bookingdates.checkin",json.getString("2018-09-02"));


    }

}
