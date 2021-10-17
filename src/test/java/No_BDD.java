import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.with;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;


public class No_BDD {
    RequestSpecification requestSpecification;
    @BeforeClass
    public void beforeClass() {
         requestSpecification = with()
                .baseUri("https://reqres.in/").log().all();
    }
    @Test
    public void without_bdd_status() {
        given(requestSpecification).when().get("api/users?page=2").then().log().all().assertThat().statusCode(200);
    }
    @Test
    public void without_bdd_validate_body() {
        given(requestSpecification)
                .when().get("api/users?page=2")
                .then().log().all().assertThat().
                body("data[1].email",equalTo("lindsay.ferguson@reqres.in"));
    }
    @Test
    public void validate_status_code() {
        Response response =
                requestSpecification.get("api/users?page=2").then().log().all().extract().response();
        assertThat(response.statusCode(),is(equalTo(200)));
        assertThat(response.path("data[1].email").toString(), equalTo("lindsay.ferguson@reqres.in"));



    }
}
