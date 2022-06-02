package get_requests;

import base_urls.JsonPlaceHolderBaseUrlReview;
import io.restassured.response.Response;
import org.junit.Test;
import test_data.JsonPlaceHolderTestDataReview;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.testng.AssertJUnit.assertEquals;

public class Get08Review extends JsonPlaceHolderBaseUrlReview {
 /*
         Given
            https://jsonplaceholder.typicode.com/todos/2
        When
         I send GET Request to the URL
       Then
         Status code is 200
         And "completed" is false
         And "userId" is 1
         And "title" is "quis ut nam facilis et officia qui"
         And header "Via" is "1.1 Vegur"
         And header "Server" is "cloudflare"
         {
                "userId": 1,
                "id": 2,
                "title": "quis ut nam facilis et officia qui",
                "completed": false
            }
     */
//    @Test
//    public void get01(){
//        //1.
//        spec.pathParams("first","todos","second",2);
//        //2.
//        Map<String,Object> expectedData = new HashMap<>();
//        expectedData.put("StatusCode",200);
//        expectedData.put("completed",false);
//        expectedData.put("userId",1);
//        expectedData.put("title","quis ut nam facilis et officia qui");
//        expectedData.put("Via","1.1 vegur");
//        expectedData.put("Server","cloudflare");
//
//        //3.
//        Response response = given().spec(spec).when().get("/{first}/{second}");
//        System.out.println("Response results");
//        response.prettyPrint();
//
//        Map<String,Object> actualData = response.as(HashMap.class);
//        System.out.println("Actual datas");
//        System.out.println(actualData);
//
//        //4.
//
//        assertEquals(expectedData.get("StatusCode"),response.getStatusCode());
//        assertEquals(expectedData.get("completed"),actualData.get("completed"));
//        assertEquals(expectedData.get("userId"),actualData.get("userId"));
//        assertEquals(expectedData.get("title"),actualData.get("title"));
//        assertEquals(expectedData.get("Via"),response.getHeader("Via"));
//        assertEquals(expectedData.get("Server"),response.getHeader("Server"));
// }

    /*
         Given
            https://jsonplaceholder.typicode.com/todos/2
        When
         I send GET Request to the URL
       Then
         Status code is 200
         And "completed" is false
         And "userId" is 1
         And "title" is "quis ut nam facilis et officia qui"
         And header "Via" is "1.1 vegur"
         And header "Server" is "cloudflare"
         {
                "userId": 1,
                "id": 2,
                "title": "quis ut nam facilis et officia qui",
                "completed": false
            }
     */
 @Test
    public void get02(){
        //1.
     spec.pathParams("first","todos","second",2);
     //2.
     JsonPlaceHolderTestDataReview expectedData = new JsonPlaceHolderTestDataReview();
     Map<String, Object> expectedDataMap = expectedData.expectedDataWithAllKeys(1,"quis ut nam facilis et officia qui",false);
     expectedDataMap.put("StatusCode",200);
     expectedDataMap.put("Via","1.1 vegur");
     expectedDataMap.put("Server","cloudflare");

     //3.
     Response response = given().spec(spec).when().get("/{first}/{second}");

     Map<String, Object> actualData = response.as(HashMap.class);
     System.out.println(actualData);

     //4.
     assertEquals(expectedDataMap.get("userId"),actualData.get("userId"));
     assertEquals(expectedDataMap.get("title"),actualData.get("title"));
     assertEquals(expectedDataMap.get("completed"),actualData.get("completed"));
     assertEquals(expectedDataMap.get("StatusCode"),response.getStatusCode());
     assertEquals(expectedDataMap.get("Via"),response.getHeader("Via"));
     assertEquals(expectedDataMap.get("Server"),response.getHeader("Server"));




 }











}
