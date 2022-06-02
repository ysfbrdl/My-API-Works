package get_requests;

import base_urls.JsonPlaceHolderBaseUrlReview;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.testng.AssertJUnit.assertEquals;

public class Get03Review extends JsonPlaceHolderBaseUrlReview {
    /*
    Given
        https://jsonplaceholder.typicode.com/todos/23
    When
        User send GET Request to the URL
    Then
        HTTP Status Code should be 200
    And
        Response format should be "application/json"
    And
		"title" is "et itaque necessitatibus maxime molestiae qui quas velit",
    And
		"completed" is false
    And
		"userId" is 2
*/
    @Test
    public void get01(){
        //1
        //String url = "https://jsonplaceholder.typicode.com/todos/23";
        spec.pathParams("first","todos","second",23);

        //2
        Response response = given().spec(spec).when().get("/{first}/{second}");
        response.prettyPrint();

        //3
        //4
        //1.way
        response.
                then().
                assertThat().
                statusCode(200).
                contentType("application/json").
                body("title",equalTo("et itaque necessitatibus maxime molestiae qui quas velit")).
                body("completed",equalTo(false)).
                body("userId",equalTo(2));

        //2.way
        response.
                then().
                assertThat().
                body("title",equalTo("et itaque necessitatibus maxime molestiae qui quas velit"),
                        "completed",equalTo(false),
                        "userId",equalTo(2));








    }












}
