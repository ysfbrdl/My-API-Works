package get_requests;

import base_urls.HerOkuAppBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;
import org.testng.asserts.SoftAssert;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.testng.Assert.*;

public class Get06 extends HerOkuAppBaseUrl {
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

        //1.Step : Set the Url
        spec.pathParams("first","booking","second",5);

        //2.Step : Set the Expected Data

        //3.Step : Send the Request and Get the Response
        Response response = given().spec(spec).when().get("/{first}/{second}");
        response.prettyPrint();

        //4.Step : Do Assertion
        //1.Way
//        response.then().
//                assertThat().
//                statusCode(200).
//                contentType(ContentType.JSON).
//                body("firstname",equalTo("Susan"),
//                        "lastname",equalTo("Jackson"),
//                        "totalprice",equalTo(671),
//                        "depositpaid",equalTo(true),
//                        "bookingdates.checkin",equalTo("2019-11-13"),
//                        "bookingdates.checkout",equalTo("2020-01-10"));

//        //2.Way: We will use JsonPath Class to be able to navigate in Json format
          JsonPath json = response.jsonPath();
//        assertEquals("Sally",json.getString("firstname"));
//        assertEquals("Jackson",json.getString("lastname"));
//        assertEquals(949,json.getInt("totalprice"));
//        assertEquals(false,json.getBoolean("depositpaid"));
//        assertEquals("2016-09-04",json.getString("bookingdates.checkin"));
//        assertEquals("2017-04-17",json.getString("bookingdates.checkout"));


        //3.Way: We will use Soft Assertion
        //To use Soft Assertion follow given 3 Step
        //1) Create SoftAssert Object
        SoftAssert softAssert = new SoftAssert();

        //2) By using softAssert object do assertion
        softAssert.assertEquals(json.getString("firstname"),"Susan","Firstname did not match");
        softAssert.assertTrue(json.getString("lastname").equals("Jackson"),"Lastname did not match");
        softAssert.assertEquals(json.getInt("totalprice"),671,"totalprice did not match");
        softAssert.assertTrue(json.getBoolean("depositpaid"), "Deposit paid is true");
        softAssert.assertEquals(json.getString("bookingdates.checkin"), "2019-11-13", "Check in date did not match");
        softAssert.assertEquals(json.getString("bookingdates.checkout"), "2020-01-10", "Check out date did not match");

        //3) Use assertAll() method, otherwise you will get pass everytime, but it will not be meaningful
        softAssert.assertAll();





    }
}
