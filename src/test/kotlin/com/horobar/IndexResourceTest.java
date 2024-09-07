package com.horobar;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@Disabled
@QuarkusTest
class IndexResourceTest {
    @Test
    void testHelloEndpoint() {
        given()
          .when().get("/darkenwald-claude")
          .then()
             .statusCode(200)
             .body(is("Hello from Quarkus REST"));
    }
}