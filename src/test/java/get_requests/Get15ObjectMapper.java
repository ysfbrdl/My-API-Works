package get_requests;

import base_urls.HerOkuAppBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import org.testng.asserts.SoftAssert;
import pojos.BookingPojo;
import test_data.HerOkuAppTestData;
import utils.JsonUtil;

import java.util.HashMap;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.assertEquals;

public class Get15ObjectMapper extends HerOkuAppBaseUrl {
    /*
        Given
               https://restful-booker.herokuapp.com/booking/4
        When
            I send GET Request to the URL
        Then
            Status code is 200
            {
                "firstname": "Sally",
                "lastname": "Jackson",
                "totalprice": 363,
                "depositpaid": false,
                "bookingdates": {
                    "checkin": "2019-05-14",
                    "checkout": "2022-02-25"
                    },
                "additionalneeds": "Breakfast"
}
     */
    @Test
    public void get01ObjectMapper(){
        //1. set the url
        spec.pathParams("first","booking","second",4);

        //2.Step: Set the Expected Data

        String expectedData = "{\n" +
                "\"firstname\": \"Sally\",\n" +
                "\"lastname\": \"Jackson\",\n" +
                "\"totalprice\": 363,\n" +
                "\"depositpaid\": false,\n" +
                "\"bookingdates\": {\n" +
                "\"checkin\": \"2019-05-14\",\n" +
                "\"checkout\": \"2022-02-25\"\n" +
                "},\n" +
                "\"additionalneeds\": \"Breakfast\"\n" +
                "}";

        BookingPojo expectedDataPojo = JsonUtil.convertJsonToJavaObject(expectedData,BookingPojo.class);

        //3. send GET request and get the response
        Response response = given().spec(spec).when().get("/{first}/{second}");
        response.prettyPrint();

        //4.Step: Do Assertion
        BookingPojo actualDataPojo = JsonUtil.convertJsonToJavaObject(response.asString(),BookingPojo.class);

        assertEquals(200,response.getStatusCode());

        //Hard Assertion
//        assertEquals(expectedDataPojo.getFirstname(),actualDataPojo.getFirstname());
//        assertEquals(expectedDataPojo.getLastname(),actualDataPojo.getLastname());
//        assertEquals(expectedDataPojo.getTotalprice(),actualDataPojo.getTotalprice());
//        assertEquals(expectedDataPojo.getDepositpaid(),actualDataPojo.getDepositpaid());
//        assertEquals(expectedDataPojo.getBookingdates().getCheckin(),actualDataPojo.getBookingdates().getCheckin());
//        assertEquals(expectedDataPojo.getBookingdates().getCheckout(),actualDataPojo.getBookingdates().getCheckout());
//        assertEquals(expectedDataPojo.getAdditionalneeds(),actualDataPojo.getAdditionalneeds());

//        //Soft Assertion
        // 1) Create SoftAssert Object
        SoftAssert softAssert = new SoftAssert();
//
//        // 2) Do assertions
        softAssert.assertEquals(actualDataPojo.getFirstname(),expectedDataPojo.getFirstname(),"Firstname did not match" );
        softAssert.assertEquals(actualDataPojo.getLastname(), expectedDataPojo.getLastname(),"Lastname did not match" );
        softAssert.assertEquals(actualDataPojo.getTotalprice(),expectedDataPojo.getTotalprice(),"Total price did not match" );
//        softAssert.assertEquals(actualDataPojo.getDepositpaid(),expectedDataPojo.getDepositpaid(),"Deposit paid did not match");
//        softAssert.assertEquals(actualDataPojo.getBookingdates().getCheckin(),expectedDataPojo.getBookingdates().getCheckin(),"Checkin paid did not match");
//        softAssert.assertEquals(actualDataPojo.getBookingdates().getCheckout(),expectedDataPojo.getBookingdates().getCheckout(),"Checkout paid did not match");
//
//        // 3) AssertAll() method
        softAssert.assertAll();

    }
}