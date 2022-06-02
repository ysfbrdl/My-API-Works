package base_urls;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.junit.Before;

public class AgroMonitoringBaseUrl {

    protected RequestSpecification spec;

    @Before      // we use this key to make this method run before all test methods.
    public void setUp(){
        spec = new RequestSpecBuilder().setBaseUri("http://api.agromonitoring.com").build();
    }

}
