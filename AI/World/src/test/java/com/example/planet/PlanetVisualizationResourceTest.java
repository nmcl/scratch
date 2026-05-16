// src/test/java/com/example/planet/PlanetVisualizationResourceTest.java
package com.example.planet;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@QuarkusTest
public class PlanetVisualizationResourceTest {

    @Test
    public void testGetCurrentVisualization() {
        given()
            .when().get("/planet/current")
            .then()
            .statusCode(200)
            .contentType(ContentType.HTML)
            .body(containsString("<html>"))
            .body(containsString("Random Planet Visualization"))
            .body(containsString("Planet Type"))
            .body(containsString("Diameter"));
    }

    @Test
    public void testGetStatus() {
        given()
            .when().get("/planet/status")
            .then()
            .statusCode(200)
            .contentType(ContentType.JSON)
            .body(containsString("Operational"));
    }

    @Test
    public void testGetPlanetInfo() {
        given()
            .when().get("/planet/info")
            .then()
            .statusCode(200)
            .contentType(ContentType.JSON)
            .body(containsString("\"name\":"))
            .body(containsString("\"planetType\":"))
            .body(containsString("\"diameter\":"))
            .body(containsString("\"rotationPeriod\":"))
            .body(containsString("\"orbitalPeriod\":"))
            .body(containsString("\"temperature\":"))
            .body(containsString("\"atmosphere\":"))
            .body(containsString("\"oceanCoverage\":"))
            .body(containsString("\"continentCount\":"))
            .body(containsString("\"hasIntelligentLife\":"))
            .body(containsString("\"weather\":"))
            .body(containsString("\"vegetation\":"));
    }
}
