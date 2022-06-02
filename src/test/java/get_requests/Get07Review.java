package get_requests;

import base_urls.JsonPlaceHolderBaseUrlReview;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.testng.AssertJUnit.*;

public class Get07Review extends JsonPlaceHolderBaseUrlReview {
     /*
        Given
	   	    https://jsonplaceholder.typicode.com/todos
		When
			 I send GET Request to the URL
		Then
			 1)Status code is 200
			 2)Print all ids greater than 190 on the console
			   Assert that there are 10 ids greater than 190
			 3)Print all userIds whose ids are less than 5 on the console
			   Assert that the number of userIds whose ids are less than 5 is 4
			 4)Print all titles whose ids are less than 5
			   Assert that “delectus aut autem” is one of the titles whose id is less than 5
     */
@Test
    public void get01(){
    //1.
    spec.pathParam("first","todos");
    //2.
    //3.
    Response response = given().spec(spec).when().get("/{first}");
    response.prettyPrint();
    //4.
    response.then().assertThat().statusCode(200);

    JsonPath json = response.jsonPath();

    List<Integer> ids = json.getList("findAll{it.id>190}.id");
    System.out.println(ids);

    assertEquals("not equal",10,ids.size());

    List<Integer> ids5 = json.getList("findAll{it.id<5}.userId");
    System.out.println(ids5);

    assertEquals("not four",4,ids5.size());

    List<String> idStr = json.getList("findAll{it.id<5}.title");
    System.out.println(idStr);

    assertTrue("not exist",idStr.contains("delectus aut autem"));
    assertTrue("",idStr.stream().anyMatch(t-> t.equals("delectus aut autem")));





}




}
