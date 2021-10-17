import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class Automate_Post {

    @BeforeClass
    public void beforeClass() {
        RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder()
                .setBaseUri("https://reqres.in")
                .addHeader("dummyheader","dummyvalue")
                .setContentType(ContentType.JSON)
                .log(LogDetail.ALL);
        RestAssured.requestSpecification = requestSpecBuilder.build();
        ResponseSpecBuilder responseSpecBuilder = new ResponseSpecBuilder()
                .expectStatusCode(201)
                .expectContentType(ContentType.JSON)
                .log(LogDetail.ALL);
        RestAssured.responseSpecification=responseSpecBuilder.build();
    }

    @Test
    public void validate_post_request_bdd_style() {
        String payload = "{\n" +
                "    \"name\": \"Prabu\",\n" +
                "    \"job\": \"QA\"\n" +
                "}";
        given().body(payload)
                .when().post("/api/users")
                .then().log().all()
                .assertThat().body("name", equalTo("Prabu"))
                .assertThat().body("job",equalTo("QA"));
    }

}
