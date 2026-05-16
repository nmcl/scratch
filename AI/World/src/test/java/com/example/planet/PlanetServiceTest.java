// src/test/java/com/example/planet/PlanetServiceTest.java
package com.example.planet;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
public class PlanetServiceTest {

    private PlanetService planetService;

    public PlanetServiceTest() {
        this.planetService = new PlanetService();
    }

    @Test
    public void testGenerateRandomPlanet() {
        Planet planet = planetService.generateRandomPlanet();
        
        assertNotNull(planet);
        assertNotNull(planet.getName());
        assertFalse(planet.getName().isEmpty());
        assertNotNull(planet.getPlanetType());
        assertNotNull(planet.getAtmosphere());
        assertNotNull(planet.getWeather());
        assertNotNull(planet.getVegetation());
        
        assertTrue(planet.getDiameter() > 0);
        assertTrue(planet.getRotationPeriod() > 0);
        assertTrue(planet.getOrbitalPeriod() > 0);
        assertTrue(planet.getOceanCoverage() >= 0 && planet.getOceanCoverage() <= 100);
        assertTrue(planet.getContinentCount() > 0);
    }

    @Test
    public void testGetStatus() {
        String status = planetService.getStatus();
        assertNotNull(status);
        assertEquals("Operational", status);
    }

    @Test
    public void testGetLastUpdated() {
        String lastUpdated = planetService.getLastUpdated();
        assertNotNull(lastUpdated);
        assertTrue(lastUpdated.matches("\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}"));
    }

    @Test
    public void testGetPlanetInfo() {
        String info = planetService.getPlanetInfo();
        assertNotNull(info);
        assertTrue(info.contains("\"name\":"));
        assertTrue(info.contains("\"planetType\":"));
        assertTrue(info.contains("\"diameter\":"));
        assertTrue(info.contains("\"rotationPeriod\":"));
        assertTrue(info.contains("\"orbitalPeriod\":"));
        assertTrue(info.contains("\"temperature\":"));
        assertTrue(info.contains("\"atmosphere\":"));
        assertTrue(info.contains("\"oceanCoverage\":"));
        assertTrue(info.contains("\"continentCount\":"));
        assertTrue(info.contains("\"hasIntelligentLife\":"));
        assertTrue(info.contains("\"weather\":"));
        assertTrue(info.contains("\"vegetation\":"));
    }
}
