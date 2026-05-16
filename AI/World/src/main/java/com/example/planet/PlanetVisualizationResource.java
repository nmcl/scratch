// src/main/java/com/example/planet/PlanetVisualizationResource.java
package com.example.planet;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.io.IOException;

@Path("/planet")
public class PlanetVisualizationResource {

    private final PlanetService planetService;

    public PlanetVisualizationResource(PlanetService planetService) {
        this.planetService = planetService;
    }

    @GET
    @Path("/current")
    @Produces(MediaType.TEXT_HTML)
    public Response getCurrentVisualization() throws IOException {
        String htmlContent = generateHtmlContent();
        return Response.ok(htmlContent, MediaType.TEXT_HTML).build();
    }

    @GET
    @Path("/status")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getStatus() {
        return Response.ok(planetService.getStatus()).build();
    }

    @GET
    @Path("/info")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPlanetInfo() {
        return Response.ok(planetService.getPlanetInfo()).build();
    }

    private String generateHtmlContent() {
        // Generate random planet data
        Planet planet = planetService.generateRandomPlanet();
        
        StringBuilder htmlContent = new StringBuilder();
        htmlContent.append("""
                <!DOCTYPE html>
                <html>
                <head>
                    <title>Random Planet Visualization</title>
                    <style>
                        body { 
                            margin: 0; 
                            overflow: hidden; 
                            background-color: #000;
                            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
                            color: white;
                        }
                        #container {
                            position: relative;
                            width: 100vw;
                            height: 100vh;
                        }
                        #info {
                            position: absolute;
                            top: 20px;
                            left: 20px;
                            color: white;
                            z-index: 10;
                            background-color: rgba(0,0,0,0.7);
                            padding: 15px;
                            border-radius: 10px;
                            max-width: 400px;
                            backdrop-filter: blur(5px);
                        }
                        #loading {
                            position: absolute;
                            top: 50%;
                            left: 50%;
                            transform: translate(-50%, -50%);
                            color: white;
                            font-size: 24px;
                        }
                        canvas {
                            display: block;
                        }
                        .planet-name {
                            font-size: 24px;
                            font-weight: bold;
                            color: #4fc3f7;
                            margin-bottom: 10px;
                        }
                        .planet-stats {
                            margin-top: 15px;
                            font-size: 14px;
                        }
                        .stat {
                            margin: 5px 0;
                        }
                        .stat-value {
                            color: #4caf50;
                            font-weight: bold;
                        }
                        .intelligent-life {
                            color: #ff9800;
                            font-weight: bold;
                        }
                        .weather-effect {
                            color: #2196f3;
                        }
                    </style>
                </head>
                <body>
                    <div id="container">
                        <div id="info">
                            <div class="planet-name">").append(planet.getName()).append("\""</div>
                            <div class="planet-stats">
                                <div class="stat">Planet Type: <span class="stat-value">").append(planet.getPlanetType()).append("""</span></div>
                                <div class="stat">Diameter: <span class="stat-value">").append(planet.getDiameter()).append(""" km</span></div>
                                <div class="stat">Rotation Period: <span class="stat-value">").append(planet.getRotationPeriod()).append(""" hours</span></div>
                                <div class="stat">Orbital Period: <span class="stat-value">").append(planet.getOrbitalPeriod()).append(""" days</span></div>
                                <div class="stat">Temperature: <span class="stat-value">").append(planet.getTemperature()).append("""°C</span></div>
                                <div class="stat">Atmosphere: <span class="stat-value">").append(planet.getAtmosphere()).append("""</span></div>
                                <div class="stat">Oceans: <span class="stat-value">").append(planet.getOceanCoverage()).append("""%</span></div>
                                <div class="stat">Continents: <span class="stat-value">").append(planet.getContinentCount()).append("""</span></div>
                                <div class="stat">Intelligent Life: <span class="stat-value """).append(planet.hasIntelligentLife() ? "intelligent-life" : "").append("""">").append(planet.hasIntelligentLife() ? "YES" : "NO").append("""</span></div>
                                <div class="stat">Weather: <span class="stat-value weather-effect">").append(planet.getWeather()).append("""</span></div>
                            </div>
                        </div>
                        <div id="loading">Generating planet visualization...</div>
                        <canvas id="planetCanvas"></canvas>
                    </div>

                    <script>
                        // Random planet visualization with WebGL-like effects
                        function initPlanet() {
                            const canvas = document.getElementById('planetCanvas');
                            const ctx = canvas.getContext('2d');
                            const info = document.getElementById('info');
                            
                            // Set canvas size to full window
                            canvas.width = window.innerWidth;
                            canvas.height = window.innerHeight;
                            
                            // Hide loading message
                            document.getElementById('loading').style.display = 'none';
                            
                            // Planet parameters
                            const centerX = canvas.width / 2;
                            const centerY = canvas.height / 2;
                            const radius = Math.min(canvas.width, canvas.height) * 0.3;
                            
                            // Draw planet
                            function drawPlanet() {
                                // Clear canvas
                                ctx.clearRect(0, 0, canvas.width, canvas.height);
                                
                                // Draw stars background
                                drawStars();
                                
                                // Draw planet sphere
                                drawPlanetSphere();
                                
                                // Draw clouds
                                drawClouds();
                                
                                // Draw weather patterns
                                drawWeatherPatterns();
                                
                                // Draw intelligent life if present
                                drawIntelligentLife();
                                
                                // Draw atmosphere glow
                                drawAtmosphere();
                                
                                // Draw sun glow (simulated)
                                drawSunGlow();
                                
                                // Continue animation
                                requestAnimationFrame(drawPlanet);
                            }
                            
                            function drawStars() {
                                ctx.fillStyle = 'white';
                                for (let i = 0; i < 300; i++) {
                                    const x = Math.random() * canvas.width;
                                    const y = Math.random() * canvas.height;
                                    const size = Math.random() * 1.5;
                                    ctx.beginPath();
                                    ctx.arc(x, y, size, 0, Math.PI * 2);
                                    ctx.fill();
                                }
                            }
                            
                            function drawPlanetSphere() {
                                // Create gradient for planet
                                const gradient = ctx.createRadialGradient(
                                    centerX, centerY, 0,
                                    centerX, centerY, radius
                                );
                                
                                // Set colors based on planet type
                                const colors = getPlanetColors();
                                gradient.addColorStop(0, colors[0]); // Ocean
                                gradient.addColorStop(0.4, colors[1]); // Land
                                gradient.addColorStop(1, colors[2]); // Mountains
                                
                                // Draw planet sphere
                                ctx.beginPath();
                                ctx.arc(centerX, centerY, radius, 0, Math.PI * 2);
                                ctx.fillStyle = gradient;
                                ctx.fill();
                                
                                // Draw continent shapes
                                drawContinents();
                            }
                            
                            function getPlanetColors() {
                                // Different color schemes based on planet type
                                switch (Math.floor(Math.random() * 4)) {
                                    case 0: // Earth-like
                                        return ['#1a73e8', '#388e3c', '#5d4037'];
                                    case 1: // Desert
                                        return ['#ff9800', '#ff5722', '#795548'];
                                    case 2: // Ice world
                                        return ['#b3e5fc', '#e1f5fe', '#e0f7fa'];
                                    case 3: // Forest world
                                        return ['#2e7d32', '#4caf50', '#1b5e20'];
                                    default:
                                        return ['#1a73e8', '#388e3c', '#5d4037'];
                                }
                            }
                            
                            function drawContinents() {
                                // Draw continents with different shapes
                                const continentCount = 3 + Math.floor(Math.random() * 4);
                                
                                for (let i = 0; i < continentCount; i++) {
                                    const x = centerX + (Math.random() - 0.5) * radius * 1.5;
                                    const y = centerY + (Math.random() - 0.5) * radius * 1.5;
                                    const size = radius * (0.15 + Math.random() * 0.2);
                                    const shape = Math.floor(Math.random() * 3);
                                    
                                    ctx.fillStyle = getContinentColor();
                                    ctx.beginPath();
                                    
                                    if (shape === 0) {
                                        // Elliptical continent
                                        ctx.ellipse(x, y, size, size * 0.6, 0, 0, Math.PI * 2);
                                    } else if (shape === 1) {
                                        // Irregular polygon
                                        drawIrregularContinent(x, y, size);
                                    } else {
                                        // Cluster of islands
                                        drawIslandCluster(x, y, size);
                                    }
                                    ctx.fill();
                                }
                            }
                            
                            function getContinentColor() {
                                // Different colors for different continent types
                                const colors = ['#388e3c', '#4caf50', '#2e7d32', '#1b5e20', '#388e3c'];
                                return colors[Math.floor(Math.random() * colors.length)];
                            }
                            
                            function drawIrregularContinent(x, y, size) {
                                const points = 8 + Math.floor(Math.random() * 5);
                                ctx.moveTo(x + size, y);
                                
                                for (let i = 1; i <= points; i++) {
                                    const angle = (i / points) * Math.PI * 2;
                                    const radius = size * (0.7 + Math.random() * 0.3);
                                    const px = x + Math.cos(angle) * radius;
                                    const py = y + Math.sin(angle) * radius;
                                    ctx.lineTo(px, py);
                                }
                                ctx.closePath();
                            }
                            
                            function drawIslandCluster(x, y, size) {
                                const islands = 3 + Math.floor(Math.random() * 4);
                                
                                for (let i = 0; i < islands; i++) {
                                    const islandX = x + (Math.random() - 0.5) * size * 1.5;
                                    const islandY = y + (Math.random() - 0.5) * size * 1.5;
                                    const islandSize = size * (0.3 + Math.random() * 0.4);
                                    
                                    ctx.beginPath();
                                    ctx.arc(islandX, islandY, islandSize, 0, Math.PI * 2);
                                    ctx.fill();
                                }
                            }
                            
                            function drawClouds() {
                                ctx.fillStyle = 'rgba(255, 255, 255, 0.7)';
                                
                                // Draw some clouds
                                const cloudCount = 5 + Math.floor(Math.random() * 8);
                                
                                for (let i = 0; i < cloudCount; i++) {
                                    const x = centerX + (Math.random() - 0.5) * radius * 2;
                                    const y = centerY + (Math.random() - 0.5) * radius * 2;
                                    const size = radius * (0.05 + Math.random() * 0.1);
                                    
                                    ctx.beginPath();
                                    ctx.arc(x, y, size, 0, Math.PI * 2);
                                    ctx.fill();
                                    
                                    // Add cloud details
                                    ctx.beginPath();
                                    ctx.arc(x + size * 0.5, y - size * 0.2, size * 0.7, 0, Math.PI * 2);
                                    ctx.fill();
                                    
                                    ctx.beginPath();
                                    ctx.arc(x - size * 0.5, y + size * 0.3, size * 0.6, 0, Math.PI * 2);
                                    ctx.fill();
                                }
                            }
                            
                            function drawWeatherPatterns() {
                                // Draw weather patterns like storms or wind
                                if (Math.random() > 0.7) {
                                    ctx.strokeStyle = 'rgba(100, 150, 255, 0.5)';
                                    ctx.lineWidth = 2;
                                    
                                    // Draw some wind lines
                                    for (let i = 0; i < 10; i++) {
                                        const x = centerX + (Math.random() - 0.5) * radius * 2;
                                        const y = centerY + (Math.random() - 0.5) * radius * 2;
                                        const length = radius * (0.1 + Math.random() * 0.2);
                                        const angle = Math.random() * Math.PI * 2;
                                        
                                        ctx.beginPath();
                                        ctx.moveTo(x, y);
                                        ctx.lineTo(
                                            x + Math.cos(angle) * length,
                                            y + Math.sin(angle) * length
                                        );
                                        ctx.stroke();
                                    }
                                }
                            }
                            
                            function drawIntelligentLife() {
                                // Draw signs of intelligent life if present
                                if (Math.random() > 0.8) {
                                    // Draw some structures
                                    const structureCount = 1 + Math.floor(Math.random() * 3);
                                    
                                    for (let i = 0; i < structureCount; i++) {
                                        const x = centerX + (Math.random() - 0.5) * radius * 1.5;
                                        const y = centerY + (Math.random() - 0.5) * radius * 1.5;
                                        
                                        // Draw a building or structure
                                        ctx.fillStyle = '#ff5722';
                                        ctx.fillRect(x - 5, y - 10, 10, 20);
                                        
                                        // Draw roof
                                        ctx.fillStyle = '#795548';
                                        ctx.beginPath();
                                        ctx.moveTo(x - 8, y - 10);
                                        ctx.lineTo(x, y - 15);
                                        ctx.lineTo(x + 8, y - 10);
                                        ctx.fill();
                                    }
                                }
                            }
                            
                            function drawAtmosphere() {
                                const gradient = ctx.createRadialGradient(
                                    centerX, centerY, radius,
                                    centerX, centerY, radius * 1.2
                                );
                                
                                // Different atmosphere colors based on planet type
                                const colors = ['#4fc3f7', '#ff9800', '#2196f3', '#8bc34a'];
                                const atmosphereColor = colors[Math.floor(Math.random() * colors.length)];
                                
                                gradient.addColorStop(0, atmosphereColor + '80'); // Semi-transparent
                                gradient.addColorStop(1, atmosphereColor + '00'); // Transparent
                                
                                ctx.beginPath();
                                ctx.arc(centerX, centerY, radius * 1.2, 0, Math.PI * 2);
                                ctx.fillStyle = gradient;
                                ctx.fill();
                            }
                            
                            function drawSunGlow() {
                                const gradient = ctx.createRadialGradient(
                                    centerX + radius * 0.8, centerY - radius * 0.8, 0,
                                    centerX + radius * 0.8, centerY - radius * 0.8, radius * 0.5
                                );
                                gradient.addColorStop(0, 'rgba(255, 255, 200, 0.8)');
                                gradient.addColorStop(1, 'rgba(255, 255, 200, 0)');
                                
                                ctx.beginPath();
                                ctx.arc(centerX + radius * 0.8, centerY - radius * 0.8, radius * 0.5, 0, Math.PI * 2);
                                ctx.fillStyle = gradient;
                                ctx.fill();
                            }
                            
                            // Start animation
                            drawPlanet();
                            
                            // Handle window resize
                            window.addEventListener('resize', function() {
                                canvas.width = window.innerWidth;
                                canvas.height = window.innerHeight;
                            });
                        }
                        
                        // Initialize when page loads
                        window.addEventListener('load', initPlanet);
                    </script>
                </body>
                </html>
                """);
        
        return htmlContent.toString();
    }
}
