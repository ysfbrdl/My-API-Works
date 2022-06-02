package get_requests;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.*;

public class Get07 extends JsonPlaceHolderBaseUrl {
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
        //1.Step : Set the Url
        spec.pathParam("first","todos");

        //2.Step : Set the Expected Data
        //3.Step : Send the Request and Get the Response
        Response response = given().spec(spec).when().get("/{first}");
        //response.prettyPrint();

        //Do Assertions
        response.then().assertThat().statusCode(200);

        //2)Print all ids greater than 190 on the console
        JsonPath json = response.jsonPath();
                                              //filter part    select part
        List<Integer>ids = json.getList("findAll{it.id>190}.id"); //Groovy Language: Java Based Programming Language
        System.out.println(ids);
        //Assert that there are 10 ids greater than 190
        assertEquals("Number of ids didnt match",10,ids.size());

        //3)Print all userIds whose ids are less than 5 on the console
        List<Integer> userIds = json.getList("findAll{it.id<5}.userId");
        System.out.println(userIds);
        //Assert that the number of userIds whose ids are less than 5 is 4
        assertEquals("The number of userIds whose ids are less than 5 is not 4", 4, userIds.size());

        //4)Print all titles whose ids are less than 5
        List<String> titles = json.getList("findAll{it.id<5}.title");
        System.out.println(titles);
        //Assert that "delectus aut autem" is one of the titles whose id is less than 5
        //1.Way:
        assertTrue("Expected title is not among them", titles.contains("delectus aut autem"));
        //2.Way:
        assertTrue("Expected title is not among them", titles.stream().anyMatch(t->t.equals("delectus aut autem")));
  }
}
