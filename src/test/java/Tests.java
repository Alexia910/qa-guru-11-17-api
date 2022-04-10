import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

public class Tests {

    @Test
    void checkTotal() {
        given()
                .when()
                .get("https://reqres.in/api/users?page=2")
                .then()
                .statusCode(200)
                .body("total", is(12));
    }

    @Test
    void checkUser() {
        given()
                .when()
                .get("https://reqres.in/api/users/3")
                .then()
                .statusCode(200)
                .body("data.first_name", is("Emma"));
    }

    @Test
    void checkRegister() {

        String userData = "{\"email\": \"eve.holt@reqres.in\", \"password\": \"pistol\"}";

        given()
                .body(userData)
                .contentType(JSON)
                .when()
                .post("https://reqres.in/api/register")
                .then()
                .body("token", is(notNullValue()));
    }

    @Test
    void checkUnknown() {
        given()
                .when()
                .get("https://reqres.in/api/unknown/2")
                .then()
                .statusCode(200)
                .body("data.color", is("#C74375"));
    }

    @Test
    void checkUpdate() {

        String userData = "{\"name\": \"kostya\", \"job\": \"zion resident\"}";

        given()
                .body(userData)
                .contentType(JSON)
                .when()
                .put("https://reqres.in/api/users/2")
                .then()
                .body("name", is("kostya"));
    }
}


