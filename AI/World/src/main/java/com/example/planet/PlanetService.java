// src/main/java/com/example/planet/PlanetService.java
package com.example.planet;

import jakarta.enterprise.context.ApplicationScoped;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@ApplicationScoped
public class PlanetService {

    private static final Random RANDOM = new Random();
    private final String status;
    private final String lastUpdated;

    public PlanetService() {
        this.status = "Operational";
        this.lastUpdated = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    public Planet generateRandomPlanet() {
        String[] planetNames = {
            "Xenon-7", "Terra-Prime", "Aurelia-IV", "Zephyria-9", "Crimson-3",
            "Nebulos-2", "Vespera-5", "Celestia-1", "Aether-8", "Olympus-4",
            "Elysium-6", "Nova-10", "Orbital-3", "Stellar-7", "Cosmos-2"
        };

        String[] planetTypes = {
            "Earth-like", "Desert World", "Ice World", "Forest World", 
            "Ocean World", "Volcanic World", "Gas Giant", "Terraformed"
        };

        String[] atmospheres = {
            "Nitrogen-Oxygen", "Carbon Dioxide", "Methane", "Hydrogen-Helium",
            "Nitrogen-Methane", "Oxygen-Carbon", "Argon-Oxygen", "Carbon-Dioxide"
        };

        String[] weatherPatterns = {
            "Calm", "Moderate Winds", "Stormy", "Hurricane Conditions",
            "Cyclonic", "Clear Skies", "Thunderstorms", "Snow Storms"
        };

        String[] vegetationTypes = {
            "Tropical Forests", "Desert Vegetation", "Tundra", "Grasslands",
            "Boreal Forests", "Savanna", "Mangrove", "Alpine Plants"
        };

        // Generate random planet properties
        String name = planetNames[RANDOM.nextInt(planetNames.length)];
        String planetType = planetTypes[RANDOM.nextInt(planetTypes.length)];
        int diameter = 5000 + RANDOM.nextInt(10000); // km
        int rotationPeriod = 6 + RANDOM.nextInt(48); // hours
        int orbitalPeriod = 100 + RANDOM.nextInt(1000); // days
        int temperature = -100 + RANDOM.nextInt(200); // degrees Celsius
        String atmosphere = atmospheres[RANDOM.nextInt(atmospheres.length)];
        int oceanCoverage = RANDOM.nextInt(101); // percentage
        int continentCount = 1 + RANDOM.nextInt(10); // number of continents
        boolean hasIntelligentLife = RANDOM.nextDouble() > 0.8;
        String weather = weatherPatterns[RANDOM.nextInt(weatherPatterns.length)];
        String vegetation = vegetationTypes[RANDOM.nextInt(vegetationTypes.length)];

        return new Planet(
            name, planetType, diameter, rotationPeriod, orbitalPeriod,
            temperature, atmosphere, oceanCoverage, continentCount, 
            hasIntelligentLife, weather, vegetation
        );
    }

    public String getStatus() {
        return status;
    }

    public String getLastUpdated() {
        return lastUpdated;
    }

    public String getPlanetInfo() {
        Planet planet = generateRandomPlanet();
        return "{\n" +
            "  \"name\": \"" + planet.getName() + "\",\n" +
            "  \"planetType\": \"" + planet.getPlanetType() + "\",\n" +
            "  \"diameter\": " + planet.getDiameter() + ",\n" +
            "  \"rotationPeriod\": " + planet.getRotationPeriod() + ",\n" +
            "  \"orbitalPeriod\": " + planet.getOrbitalPeriod() + ",\n" +
            "  \"temperature\": " + planet.getTemperature() + ",\n" +
            "  \"atmosphere\": \"" + planet.getAtmosphere() + "\",\n" +
            "  \"oceanCoverage\": " + planet.getOceanCoverage() + ",\n" +
            "  \"continentCount\": " + planet.getContinentCount() + ",\n" +
            "  \"hasIntelligentLife\": " + planet.hasIntelligentLife() + ",\n" +
            "  \"weather\": \"" + planet.getWeather() + "\",\n" +
            "  \"vegetation\": \"" + planet.getVegetation() + "\"\n" +
            "}";
    }
}
