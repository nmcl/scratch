// src/main/java/com/example/planet/Planet.java
package com.example.planet;

public class Planet {
    private final String name;
    private final String planetType;
    private final int diameter;
    private final int rotationPeriod;
    private final int orbitalPeriod;
    private final int temperature;
    private final String atmosphere;
    private final int oceanCoverage;
    private final int continentCount;
    private final boolean hasIntelligentLife;
    private final String weather;
    private final String vegetation;

    public Planet(String name, String planetType, int diameter, int rotationPeriod, 
                  int orbitalPeriod, int temperature, String atmosphere, int oceanCoverage,
                  int continentCount, boolean hasIntelligentLife, String weather, String vegetation) {
        this.name = name;
        this.planetType = planetType;
        this.diameter = diameter;
        this.rotationPeriod = rotationPeriod;
        this.orbitalPeriod = orbitalPeriod;
        this.temperature = temperature;
        this.atmosphere = atmosphere;
        this.oceanCoverage = oceanCoverage;
        this.continentCount = continentCount;
        this.hasIntelligentLife = hasIntelligentLife;
        this.weather = weather;
        this.vegetation = vegetation;
    }

    // Getters
    public String getName() { return name; }
    public String getPlanetType() { return planetType; }
    public int getDiameter() { return diameter; }
    public int getRotationPeriod() { return rotationPeriod; }
    public int getOrbitalPeriod() { return orbitalPeriod; }
    public int getTemperature() { return temperature; }
    public String getAtmosphere() { return atmosphere; }
    public int getOceanCoverage() { return oceanCoverage; }
    public int getContinentCount() { return continentCount; }
    public boolean hasIntelligentLife() { return hasIntelligentLife; }
    public String getWeather() { return weather; }
    public String getVegetation() { return vegetation; }
}
