package tests;
import base.ApiBase;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.*;

public class ProductApiTest extends ApiBase {
  @Test public void getProductById(){given().when().get("/products/1").then().statusCode(200).body("id",equalTo(1)).body("title",not(emptyOrNullString())).body("price",greaterThan(0F));}
  @Test public void listProductsHasExpectedContract(){given().when().get("/products?limit=3").then().statusCode(200).body(matchesJsonSchemaInClasspath("schemas/products-schema.json")).body("products.size()",equalTo(3));}
  @Test public void searchProductsReturnsResults(){given().queryParam("q","phone").when().get("/products/search").then().statusCode(200).body("products",notNullValue());}
  @Test
public void createProduct() {
    String p = "{\"title\":\"QA Automation Product\",\"price\":99.99,\"category\":\"testing\"}";

    given()
        .contentType(ContentType.JSON)
        .body(p)
    .when()
        .post("/products/add")
    .then()
        .statusCode(anyOf(is(200), is(201)))
        .body("title", equalTo("QA Automation Product"))
        .body("id", notNullValue());
}
@Test
public void updateProduct() {
    String p = "{\"title\":\"Updated QA Product\"}";

    given()
        .contentType(ContentType.JSON)
        .body(p)
    .when()
        .put("/products/1")
    .then()
        .statusCode(200)
        .body("id", equalTo(1))
        .body("title", equalTo("Updated QA Product"));
}
  @Test public void deleteProduct(){given().when().delete("/products/1").then().statusCode(200).body("id",equalTo(1)).body("isDeleted",equalTo(true));}
  @Test public void invalidProductReturnsClientError(){given().when().get("/products/999999").then().statusCode(anyOf(is(404),is(400)));}
  @Test public void responseTimeIsReasonable(){given().when().get("/products/1").then().time(lessThan(5000L));}
}
