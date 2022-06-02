package get_requests;

import base_urls.HerOkuAppBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.BookingDatesPojo;
import pojos.BookingPojo;

import static io.restassured.RestAssured.*;
import static org.testng.AssertJUnit.*;

public class Get09ReviewWithPojo extends HerOkuAppBaseUrl {
    /*
        Given
            https://restful-booker.herokuapp.com/booking/3
        When
         I send GET Request to the url
        Then
         Response body should be like that;
         {
            "firstname": "Jim",
            "lastname": "Smith",
            "totalprice": 843,
            "depositpaid": false,
            "bookingdates": {
                "checkin": "2018-11-01",
                "checkout": "2022-04-10"
                },
            "additionalneeds": "Breakfast"
        }
  */
    @Test
    public void get01(){
        //Set Url
        spec.pathParams("first","booking","second",3);
        //Set Expected Data
        BookingDatesPojo bookingDatesPojo = new BookingDatesPojo("2018-11-01","2022-04-10");
        BookingPojo bookingPojo = new BookingPojo("Jim","Smith",843,false,bookingDatesPojo,"Breakfast");

        //Send Request get Response
        Response response = given().spec(spec).when().get("/{first}/{second}");
        response.prettyPrint();

        BookingPojo actualData = response.as(BookingPojo.class);

        //Do Assertions
        assertEquals(bookingPojo.getFirstname(),actualData.getFirstname());
        assertEquals(bookingPojo.getLastname(),actualData.getLastname());
        assertEquals(bookingPojo.getTotalprice(),actualData.getTotalprice());
        assertEquals(bookingPojo.getDepositpaid(),actualData.getDepositpaid());
        assertEquals(bookingPojo.getBookingdates().getCheckin(),actualData.getBookingdates().getCheckin());
        assertEquals(bookingPojo.getBookingdates().getCheckout(),actualData.getBookingdates().getCheckout());
        assertEquals(bookingPojo.getAdditionalneeds(),actualData.getAdditionalneeds());





    }







}
