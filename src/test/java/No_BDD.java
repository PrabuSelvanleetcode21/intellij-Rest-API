import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
public class No_BDD {

    @Test
    public void without_bdd() {

        RequestSpecification requestSpecification;
        given().baseUri("https://reqres.in/").when().get("api/users?page=2").then().log().all().assertThat().statusCode(200).

    }
}
