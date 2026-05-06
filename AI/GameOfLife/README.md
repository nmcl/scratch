# Game of Life 3D - Quarkus Application

A 3D implementation of Conway's Game of Life with high-resolution image rendering, built with Quarkus.

## Features

- 3D Game of Life simulation with configurable world dimensions
- Real-time simulation with adjustable speed
- High-resolution image rendering of cellular automata
- RESTful API for world management and visualization
- Web-based visualization with slice rendering capabilities

## Getting Started

### Prerequisites

- Java 17+
- Maven 3.8+

### Building

```bash
mvn clean package

# Create a new 3D world
curl -X POST "http://localhost:8080/game-of-life/world/world1?width=100&height=100&depth=100"

# Randomize the world
curl -X POST "http://localhost:8080/game-of-life/world/world1/randomize?density=30"

# Start simulation
curl -X POST "http://localhost:8080/game-of-life/world/world1/start?delay=100"

# Render world as image
curl -X GET "http://localhost:8080/render/world/world1/image?width=800&height=600" -o world.png
