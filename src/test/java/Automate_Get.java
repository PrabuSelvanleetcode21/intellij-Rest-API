
import static io.restassured.RestAssured.*;
import  io.restassured.response.Response;
import static org.hamcrest.MatcherAssert.assertThat;
import  static org.hamcrest.Matchers.*;

import org.omg.CosNaming.NamingContextPackage.NotEmpty;
import org.testng.Assert;
import org.testng.annotations.Test;
import io.restassured.path.json.JsonPath;
public class Automate_Get {

    @Test (enabled = false)
    public void validate_get_status_code() {
    given().baseUri("https://reqres.in/").
            when().get("api/users?page=2").
            then().
            log().all().statusCode(200);
    }
    @Test(enabled = false)
    public void validate_response_body( ) {
        given().baseUri("https://reqres.in/").
                when().get("api/users?page=2").
                then().
                log().all().statusCode(200)
                .body("data.first_name", hasItems("Michael", "Byron","Rachel"),
                        "data.last_name", hasItems("Lawson","Fields","Howell"),
                        "page",equalTo(2),
                        "per_page", equalTo(6),
                        "total_pages",equalTo(2),
                        "total", equalTo(12),
                        "data[0].email", equalTo("michael.lawson@reqres.in"),
                        "data.size()", equalTo(6),
                        "data.first_name",hasItem("George")

                );
    }

    @Test(enabled = false)
    public void extract_response() {
        Response resp = given().baseUri("https://reqres.in/").
                when().get("api/users?page=2").
                then().log().all().assertThat().statusCode(200).
                extract().response();


        System.out.println("Converting json to string "+ resp.asString());

    }

    @Test(enabled = false)
    public void extract_single_value_from_response() {
//        converting json to string
        Response resp = given().baseUri("https://reqres.in/").
                when().get("api/users?page=2").
                then().log().all().assertThat().statusCode(200).
                extract().response();
//        converting string to json
        JsonPath jsonPath= new JsonPath(resp.asString());
        System.out.println("String to JSON is  "+ jsonPath.getString("data[0].email"));
//        System.out.println("Converting json to string "+ resp.asString());
    }

    @Test(enabled = false)
    public void hamcrest_assert_on_extracted_response() {
// Getting the single value from the api as the string and validating using assertThat
        String name = given().baseUri("https://reqres.in/").
                when().get("api/users?page=2").
                then().log().all().assertThat().statusCode(200).
                extract().response().path("data[0].first_name");
//        assertThat(name, equalTo("Michael"));
        Assert.assertEquals(name,"Michael");
    }

    @Test(enabled = false)
    public void validate_response_body_hamcrest_learnings() {
    given().baseUri("https://reqres.in/").when().get("api/users?page=2").then().log().all().assertThat().statusCode(200).
            body("data.first_name",containsInAnyOrder("Lindsay","Tobias","Byron","George","Rachel","Michael"));
    }

    @Test(enabled = false)
    public void validate_response_not_empty() {
        given().baseUri("https://reqres.in/").when().get("api/users?page=2").then().log().all().assertThat().statusCode(200).
                body("data.first_name",is(not(emptyArray())),
                        "data.first_name", hasSize(6),
                        "data.email",everyItem(endsWith("@reqres.in"))
                );
    }
    @Test(enabled = true)
    public void validate_response_with_hashMap() {
        given().baseUri("https://reqres.in/").when().get("api/users?page=2").then().log().all().assertThat().statusCode(200).
                body("data.first_name",is(not(emptyArray())),
                        "data.first_name", hasSize(6),
                        "data[0]", hasKey("email"),
                        "data[0]", hasValue("michael.lawson@reqres.in"),
                        "data[0]", hasEntry("email","michael.lawson@reqres.in"),
                        "data[0].first_name",allOf(startsWith("Mi")), containsString("Michael"));



    }
}