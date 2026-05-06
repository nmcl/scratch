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

bash
mvn clean package


This complete implementation provides:

1. **Quarkus Backend**: Full REST API and WebSocket support for real-time updates
2. **Web Interface**: Interactive 3D visualization using Three.js
3. **Real-time Simulation**: WebSocket-based updates for live visualization
4. **Responsive Design**: Works on different screen sizes
5. **Complete Controls**: All necessary controls for managing the simulation
6. **Interactive 3D View**: Rotating 3D visualization of the cellular automata

To use this application:
1. Build with `mvn clean package`
2. Run with `mvn quarkus:dev` or `java -jar target/game-of-life-3d-web-1.0.0-SNAPSHOT-runner.jar`
3. Access `http://localhost:8080` in your browser
4. Use the controls to create and manage your 3D Game of Life simulation

The web interface will show a rotating 3D visualization of the cellular automata that updates in real-time as the simulation runs.
