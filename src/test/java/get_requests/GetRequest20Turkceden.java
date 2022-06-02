package get_requests;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class GetRequest20Turkceden extends JsonPlaceHolderBaseUrl {
    /*
    https://jsonplaceholder.typicode.com/todos/2
    1)Status kodun 200 olduğunu,
    2)response body de
    "completed": değerinin false olduğunu,
    "userId" sinin 1 ve
    header değerlerinden "via" değerinin "1.1 vegur " ve
    "Server" değerinin "cloudflare " olduğunu test edin.
     */


    @Test
    public void test20 (){
        // 1) Url oluştur
        spec.pathParams("1","todos","2",2);

        //2) Expected Data oluştur.

        HashMap<String,Object> expectedData = new HashMap<>();


        expectedData.put("statusCode",200);
        expectedData.put("completed",false);
        expectedData.put("userId",1);
        expectedData.put("via","1.1 vegur");
        expectedData.put("title","quis ut nam facilis et officia qui");
        expectedData.put("Server","cloudflare");


        //3)Request oluştur.
        Response response = given().spec(spec).when().get("/{1}/{2}");
        response.prettyPrint();
        response.prettyPeek();

        //4)Doğrulama

        //1)Matchers Class ile
        response.then().assertThat()
                .statusCode((Integer)expectedData.get("statusCode"))
                .headers("via",equalTo(expectedData.get("via")),
                        "Server",equalTo(expectedData.get("Server")))
                .body("userId",equalTo(expectedData.get("userId")),
                        "completed",equalTo(expectedData.get("completed")),
                        "title",equalTo(expectedData.get("title")));


        //response.then().assertThat().statusCode((Integer)expectedData.get("statusCode"));

        //2)JSON Path ile

        JsonPath json= response.jsonPath();
        //=======> Headers
        Assert.assertEquals(expectedData.get("statusCode"),response.statusCode());
        Assert.assertEquals(expectedData.get("via"),response.getHeader("via"));
        Assert.assertEquals(expectedData.get("Server"),response.getHeader("Server"));

        //=========>Body
        Assert.assertEquals(expectedData.get("userId"),json.getInt("userId"));
        Assert.assertEquals(expectedData.get("title"),json.getString("title"));
        Assert.assertEquals(expectedData.get("completed"),json.getBoolean("completed"));


        //3) DE-Serialaziation    // headers la ilgili bir bilgi yok sadece bunları doğrulayabilirim.
        // ancak matchers class da hepsini yapabilirim.

        HashMap<String,Object> actualData =response.as(HashMap.class);
        System.out.println(actualData);

        Assert.assertEquals(expectedData.get("userId"),actualData.get("userId"));
        Assert.assertEquals(expectedData.get("title"),actualData.get("title"));
        Assert.assertEquals(expectedData.get("completed"),actualData.get("completed"));



    }
}
