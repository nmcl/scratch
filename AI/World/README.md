A randomly created Earth-like planet each time it runs. There should be oceans, continents with different types of vegetation, randomly some signs of intelligent life (no intelligent life is fine), weather patterns and clouds.

This implementation now creates a completely randomized Earth-like planet visualization each time it runs with:

Random Planet Generation:

Unique planet names from a predefined list
Random planet types (Earth-like, Desert, Ice, etc.)
Random physical properties (diameter, rotation, orbital period, temperature)
Random atmospheric composition
Random ocean coverage percentage
Random number of continents

Visual Features:

Dynamic continent shapes (ellipses, irregular polygons, island clusters)
Different vegetation types for continents
Cloud formations with random positioning
Weather patterns (storms, winds, etc.)
Signs of intelligent life (buildings/structures) with 20% chance
Atmospheric glow effects
Starry background
Sun glow effect

Planet Properties Display:

Planet name and type
Physical characteristics
Atmospheric composition
Ocean coverage
Weather conditions
Vegetation types
Intelligent life detection

Endpoints:

GET /planet/current - Returns HTML visualization page with random planet
GET /planet/status - Returns service status as JSON
GET /planet/info - Returns detailed planet information as JSON

Testing:

Unit tests for PlanetService
Integration tests for REST endpoints
Tests verify HTML content, JSON responses, and planet properties

The visualization creates a unique planet each time it runs, with realistic features like:

Oceans and continents with different shapes and sizes
Various vegetation types
Weather patterns and cloud formations
Signs of intelligent life (20% chance)
Atmospheric effects
Space environment with stars and sun glow

To run:

Build with mvn clean install
Run with mvn quarkus:dev
Access http://localhost:8080/planet/current for visualization
Access http://localhost:8080/planet/info for planet details