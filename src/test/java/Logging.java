import org.testng.annotations.Test;

import  static io.restassured.RestAssured.*;
public class Logging {
    @Test
    public void request_response_logging() {
        given().baseUri("https://reqres.in/").log().headers().
                when().get("api/users?page=2").
                then().
//                log().ifError().statusCode(200);
            log().ifValidationFails().statusCode(201);
    }
}
