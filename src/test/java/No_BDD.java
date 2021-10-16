import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class No_BDD {
    RequestSpecification requestSpecification;
    @BeforeClass
    public void beforeClass() {
         requestSpecification = given()
                .baseUri("https://reqres.in/");
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
                body("data[1l].email",equalTo("lindsay.ferguson@reqres.in"));
    }
}
