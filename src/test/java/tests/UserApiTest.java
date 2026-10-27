package tests;
import base.ApiBase;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
public class UserApiTest extends ApiBase {
  @Test public void getUser(){given().when().get("/users/1").then().statusCode(200).body("id",equalTo(1)).body("email",containsString("@"));}
  @Test public void usersPaginationWorks(){given().queryParam("limit",5).queryParam("skip",5).when().get("/users").then().statusCode(200).body("users.size()",equalTo(5)).body("skip",equalTo(5));}
}
