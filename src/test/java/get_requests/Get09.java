package get_requests;

import base_urls.HerOkuAppBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import java.util.HashMap;
import java.util.Map;
import static io.restassured.RestAssured.*;
import static org.junit.Assert.assertEquals;

public class Get09 extends HerOkuAppBaseUrl {
    /*
        Given
            https://restful-booker.herokuapp.com/booking/1
        When
         I send GET Request to the url
      Then
         Response body should be like that;
         {
             "firstname": "Eric",
             "lastname": "Smith",
             "totalprice": 456,
             "depositpaid": false,
             "bookingdates": {
                 "checkin": "2016-09-09",
                 "checkout": "2017-09-21"
              }
         }
     */
    @Test
    public void get01(){

        //1.Step: Set the URL
        spec.pathParams("first", "booking", "second", 4);

        //2.Step: Set the Expected Data
        Map<String, String> bookingDatesMap = new HashMap<>();
        bookingDatesMap.put("checkin", "2019-01-20");
        bookingDatesMap.put("checkout", "2021-07-23");

        Map<String, Object> expectedDataMap = new HashMap<>();
        expectedDataMap.put("firstname", "Eric");
        expectedDataMap.put("lastname", "Smith");
        expectedDataMap.put("totalprice", 764);
        expectedDataMap.put("depositpaid", true);
        expectedDataMap.put("bookingdates", bookingDatesMap);
        System.out.println(expectedDataMap);

        //3.Step: Send the Request and Get the Response
        Response response  = given().spec(spec).when().get("/{first}/{second}");

        Map<String, Object> actualDataMap = response.as(HashMap.class);
        System.out.println(actualDataMap);

        //4.Step : Do Assertions
        assertEquals(expectedDataMap.get("firstname"), actualDataMap.get("firstname"));
        assertEquals(expectedDataMap.get("lastname"), actualDataMap.get("lastname"));
        assertEquals(expectedDataMap.get("depositpaid"), actualDataMap.get("depositpaid"));
        assertEquals(expectedDataMap.get("totalprice"), actualDataMap.get("totalprice"));


        assertEquals(bookingDatesMap.get("checkin"), ((Map)actualDataMap.get("bookingdates")).get("checkin"));
        assertEquals(bookingDatesMap.get("checkout"), ((Map)actualDataMap.get("bookingdates")).get("checkout"));

    }

}