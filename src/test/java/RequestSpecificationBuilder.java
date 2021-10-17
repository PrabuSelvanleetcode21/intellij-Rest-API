import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static  org.hamcrest.Matchers.*;
import static  org.hamcrest.MatcherAssert.assertThat;

public class RequestSpecificationBuilder {
    RequestSpecification requestSpecification;
    @BeforeClass
    public void RequestSpecificationmethod() {
        RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder();
        requestSpecBuilder.setBaseUri("https://reqres.in/");
    requestSpecification = requestSpecBuilder.build();
    }
@Test
    public void validate_status_code() {
    Response response= given(requestSpecification).get("api/users?page=2").then().log().all().extract().response();
    assertThat(response.statusCode(),is(equalTo(200)));
    }
}
