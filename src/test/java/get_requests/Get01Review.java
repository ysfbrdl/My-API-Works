package get_requests;

import base_urls.HerOkuAppBaseUrl;
import base_urls.HerOkuAppBaseUrlReview;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static org.testng.AssertJUnit.*;

public class Get01Review extends HerOkuAppBaseUrlReview {

    /*

                c)Start to type the Automation Scripts
                    i)Set the URL
                    ii)Set the expected data(POST-PUT-PATCH)
                    iii)Type code to send request
                    iv)Do Assertions
         */

    /*
        Given
            https://restful-booker.herokuapp.com/booking/3
        When
            User sends a GET Request to the url
        Then
            HTTP Status Code should be 200
        And
            Content Type should be JSON
        And
            Status Line should be HTTP/1.1 200 OK
     */

    @Test
    public void get01(){
        //i)Set the URL
        //String url = "https://restful-booker.herokuapp.com/booking/3";
        spec.pathParams("first","booking","second",3);

        //ii)Set the expected data(POST-PUT-PATCH)
        //iii)Type code to send request
        Response response = given().spec(spec).when().get("/{first}/{second}");
        response.prettyPrint();
        //iv)Do Assertions
        response.then().assertThat().statusCode(200).contentType("application/json").statusLine("HTTP/1.1 200 OK");

        System.out.println(response.headers());
        System.out.println("----------------");

        System.out.println(response.getStatusCode());
        System.out.println(response.getContentType());
        System.out.println(response.statusLine());

        System.out.println(response.getHeader("Server"));
        assertEquals(response.getHeader("Server"),"Cowboy");

        System.out.println(response.getHeader("Content-Type"));


    }





}
