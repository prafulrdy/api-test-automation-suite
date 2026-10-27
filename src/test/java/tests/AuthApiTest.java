package tests;

import base.ApiBase;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class AuthApiTest extends ApiBase {

    @Test
    public void validUserCanAuthenticate() {
        String p = "{\"username\":\"emilys\",\"password\":\"emilyspass\",\"expiresInMins\":30}";

        given()
            .contentType(ContentType.JSON)
            .body(p)
        .when()
            .post("/auth/login")
        .then()
            .statusCode(200)
            .body("accessToken", not(emptyOrNullString()))
            .body("refreshToken", not(emptyOrNullString()))
            .body("username", equalTo("emilys"));
    }

    @Test
    public void invalidCredentialsAreRejected() {
        String p = "{\"username\":\"invalid-user\",\"password\":\"invalid-password\"}";

        given()
            .contentType(ContentType.JSON)
            .body(p)
        .when()
            .post("/auth/login")
        .then()
            .statusCode(anyOf(is(400), is(401)));
    }

    @Test
    public void missingPasswordIsRejected() {
        String p = "{\"username\":\"emilys\"}";

        given()
            .contentType(ContentType.JSON)
            .body(p)
        .when()
            .post("/auth/login")
        .then()
            .statusCode(anyOf(is(400), is(401)));
    }
}
