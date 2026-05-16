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
        
        StringBuilder html = new StringBuilder();
        html.append("<!DOCTYPE html>");
        html.append("<html>");
        html.append("<head>");
        html.append("    <title>Random Planet Visualization</title>");
        html.append("    <style>");
        html.append("        body { margin: 0; overflow: hidden; background-color: #000; font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif; color: white; }");
        html.append("        #container { position: relative; width: 100vw; height: 100vh; }");
        html.append("        #info { position: absolute; top: 20px; left: 20px; color: white; z-index: 10; background-color: rgba(0,0,0,0.7); padding: 15px; border-radius: 10px; max-width: 400px; backdrop-filter: blur(5px); }");
        html.append("        #loading { position: absolute; top: 50%; left: 50%; transform: translate(-50%, -50%); color: white; font-size: 24px; }");
        html.append("        canvas { display: block; }");
        html.append("        .planet-name { font-size: 24px; font-weight: bold; color: #4fc3f7; margin-bottom: 10px; }");
        html.append("        .planet-stats { margin-top: 15px; font-size: 14px; }");
        html.append("        .stat { margin: 5px 0; }");
        html.append("        .stat-value { color: #4caf50; font-weight: bold; }");
        html.append("        .intelligent-life { color: #ff9800; font-weight: bold; }");
        html.append("        .weather-effect { color: #2196f3; }");
        html.append("    </style>");
        html.append("</head>");
        html.append("<body>");
        html.append("    <div id=\"container\">");
        html.append("        <div id=\"info\">");
        html.append("            <div class=\"planet-name\">").append(planet.getName()).append("</div>");
        html.append("            <div class=\"planet-stats\">");
        html.append("                <div class=\"stat\">Planet Type: <span class=\"stat-value\">").append(planet.getPlanetType()).append("</span></div>");
        html.append("                <div class=\"stat\">Diameter: <span class=\"stat-value\">").append(planet.getDiameter()).append(" km</span></div>");
        html.append("                <div class=\"stat\">Rotation Period: <span class=\"stat-value\">").append(planet.getRotationPeriod()).append(" hours</span></div>");
        html.append("                <div class=\"stat\">Orbital Period: <span class=\"stat-value\">").append(planet.getOrbitalPeriod()).append(" days</span></div>");
        html.append("                <div class=\"stat\">Temperature: <span class=\"stat-value\">").append(planet.getTemperature()).append("°C</span></div>");
        html.append("                <div class=\"stat\">Atmosphere: <span class=\"stat-value\">").append(planet.getAtmosphere()).append("</span></div>");
        html.append("                <div class=\"stat\">Oceans: <span class=\"stat-value\">").append(planet.getOceanCoverage()).append("%</span></div>");
        html.append("                <div class=\"stat\">Continents: <span class=\"stat-value\">").append(planet.getContinentCount()).append("</span></div>");
        html.append("                <div class=\"stat\">Intelligent Life: <span class=\"stat-value ").append(planet.hasIntelligentLife() ? "intelligent-life" : "").append("\">").append(planet.hasIntelligentLife() ? "YES" : "NO").append("</span></div>");
        html.append("                <div class=\"stat\">Weather: <span class=\"stat-value weather-effect\">").append(planet.getWeather()).append("</span></div>");
        html.append("            </div>");
        html.append("        </div>");
        html.append("        <div id=\"loading\">Generating planet visualization...</div>");
        html.append("        <canvas id=\"planetCanvas\"></canvas>");
        html.append("    </div>");
        html.append("    <script>");
        html.append("        // Random planet visualization with WebGL-like effects");
        html.append("        function initPlanet() {");
        html.append("            const canvas = document.getElementById('planetCanvas');");
        html.append("            const ctx = canvas.getContext('2d');");
        html.append("            const info = document.getElementById('info');");
        html.append("            canvas.width = window.innerWidth;");
        html.append("            canvas.height = window.innerHeight;");
        html.append("            document.getElementById('loading').style.display = 'none';");
        html.append("            const centerX = canvas.width / 2;");
        html.append("            const centerY = canvas.height / 2;");
        html.append("            const radius = Math.min(canvas.width, canvas.height) * 0.3;");
        html.append("            function drawPlanet() {");
        html.append("                ctx.clearRect(0, 0, canvas.width, canvas.height);");
        html.append("                drawStars();");
        html.append("                drawPlanetSphere();");
        html.append("                drawClouds();");
        html.append("                drawWeatherPatterns();");
        html.append("                drawIntelligentLife();");
        html.append("                drawAtmosphere();");
        html.append("                drawSunGlow();");
        html.append("                requestAnimationFrame(drawPlanet);");
        html.append("            }");
        html.append("            function drawStars() {");
        html.append("                ctx.fillStyle = 'white';");
        html.append("                for (let i = 0; i < 300; i++) {");
        html.append("                    const x = Math.random() * canvas.width;");
        html.append("                    const y = Math.random() * canvas.height;");
        html.append("                    const size = Math.random() * 1.5;");
        html.append("                    ctx.beginPath();");
        html.append("                    ctx.arc(x, y, size, 0, Math.PI * 2);");
        html.append("                    ctx.fill();");
        html.append("                }");
        html.append("            }");
        html.append("            function drawPlanetSphere() {");
        html.append("                const gradient = ctx.createRadialGradient(centerX, centerY, 0, centerX, centerY, radius);");
        html.append("                const colors = getPlanetColors();");
        html.append("                gradient.addColorStop(0, colors[0]); // Ocean");
        html.append("                gradient.addColorStop(0.4, colors[1]); // Land");
        html.append("                gradient.addColorStop(1, colors[2]); // Mountains");
        html.append("                ctx.beginPath();");
        html.append("                ctx.arc(centerX, centerY, radius, 0, Math.PI * 2);");
        html.append("                ctx.fillStyle = gradient;");
        html.append("                ctx.fill();");
        html.append("                drawContinents();");
        html.append("            }");
        html.append("            function getPlanetColors() {");
        html.append("                switch (Math.floor(Math.random() * 4)) {");
        html.append("                    case 0: return ['#1a73e8', '#388e3c', '#5d4037'];");
        html.append("                    case 1: return ['#ff9800', '#ff5722', '#795548'];");
        html.append("                    case 2: return ['#b3e5fc', '#e1f5fe', '#e0f7fa'];");
        html.append("                    case 3: return ['#2e7d32', '#4caf50', '#1b5e20'];");
        html.append("                    default: return ['#1a73e8', '#388e3c', '#5d4037'];");
        html.append("                }");
        html.append("            }");
        html.append("            function drawContinents() {");
        html.append("                const continentCount = 3 + Math.floor(Math.random() * 4);");
        html.append("                for (let i = 0; i < continentCount; i++) {");
        html.append("                    const x = centerX + (Math.random() - 0.5) * radius * 1.5;");
        html.append("                    const y = centerY + (Math.random() - 0.5) * radius * 1.5;");
        html.append("                    const size = radius * (0.15 + Math.random() * 0.2);");
        html.append("                    const shape = Math.floor(Math.random() * 3);");
        html.append("                    ctx.fillStyle = getContinentColor();");
        html.append("                    ctx.beginPath();");
        html.append("                    if (shape === 0) {");
        html.append("                        ctx.ellipse(x, y, size, size * 0.6, 0, 0, Math.PI * 2);");
        html.append("                    } else if (shape === 1) {");
        html.append("                        drawIrregularContinent(x, y, size);");
        html.append("                    } else {");
        html.append("                        drawIslandCluster(x, y, size);");
        html.append("                    }");
        html.append("                    ctx.fill();");
        html.append("                }");
        html.append("            }");
        html.append("            function getContinentColor() {");
        html.append("                const colors = ['#388e3c', '#4caf50', '#2e7d32', '#1b5e20', '#388e3c'];");
        html.append("                return colors[Math.floor(Math.random() * colors.length)];");
        html.append("            }");
        html.append("            function drawIrregularContinent(x, y, size) {");
        html.append("                const points = 8 + Math.floor(Math.random() * 5);");
        html.append("                ctx.moveTo(x + size, y);");
        html.append("                for (let i = 1; i <= points; i++) {");
        html.append("                    const angle = (i / points) * Math.PI * 2;");
        html.append("                    const radius = size * (0.7 + Math.random() * 0.3);");
        html.append("                    const px = x + Math.cos(angle) * radius;");
        html.append("                    const py = y + Math.sin(angle) * radius;");
        html.append("                    ctx.lineTo(px, py);");
        html.append("                }");
        html.append("                ctx.closePath();");
        html.append("            }");
        html.append("            function drawIslandCluster(x, y, size) {");
        html.append("                const islands = 3 + Math.floor(Math.random() * 4);");
        html.append("                for (let i = 0; i < islands; i++) {");
        html.append("                    const islandX = x + (Math.random() - 0.5) * size * 1.5;");
        html.append("                    const islandY = y + (Math.random() - 0.5) * size * 1.5;");
        html.append("                    const islandSize = size * (0.3 + Math.random() * 0.4);");
        html.append("                    ctx.beginPath();");
        html.append("                    ctx.arc(islandX, islandY, islandSize, 0, Math.PI * 2);");
        html.append("                    ctx.fill();");
        html.append("                }");
        html.append("            }");
        html.append("            function drawClouds() {");
        html.append("                ctx.fillStyle = 'rgba(255, 255, 255, 0.7)';");
        html.append("                const cloudCount = 5 + Math.floor(Math.random() * 8);");
        html.append("                for (let i = 0; i < cloudCount; i++) {");
        html.append("                    const x = centerX + (Math.random() - 0.5) * radius * 2;");
        html.append("                    const y = centerY + (Math.random() - 0.5) * radius * 2;");
        html.append("                    const size = radius * (0.05 + Math.random() * 0.1);");
        html.append("                    ctx.beginPath();");
        html.append("                    ctx.arc(x, y, size, 0, Math.PI * 2);");
        html.append("                    ctx.fill();");
        html.append("                    ctx.beginPath();");
        html.append("                    ctx.arc(x + size * 0.5, y - size * 0.2, size * 0.7, 0, Math.PI * 2);");
        html.append("                    ctx.fill();");
        html.append("                    ctx.beginPath();");
        html.append("                    ctx.arc(x - size * 0.5, y + size * 0.3, size * 0.6, 0, Math.PI * 2);");
        html.append("                    ctx.fill();");
        html.append("                }");
        html.append("            }");
        html.append("            function drawWeatherPatterns() {");
        html.append("                if (Math.random() > 0.7) {");
        html.append("                    ctx.strokeStyle = 'rgba(100, 150, 255, 0.5)';");
        html.append("                    ctx.lineWidth = 2;");
        html.append("                    for (let i = 0; i < 10; i++) {");
        html.append("                        const x = centerX + (Math.random() - 0.5) * radius * 2;");
        html.append("                        const y = centerY + (Math.random() - 0.5) * radius * 2;");
        html.append("                        const length = radius * (0.1 + Math.random() * 0.2);");
        html.append("                        const angle = Math.random() * Math.PI * 2;");
        html.append("                        ctx.beginPath();");
        html.append("                        ctx.moveTo(x, y);");
        html.append("                        ctx.lineTo(x + Math.cos(angle) * length, y + Math.sin(angle) * length);");
        html.append("                        ctx.stroke();");
        html.append("                    }");
        html.append("                }");
        html.append("            }");
        html.append("            function drawIntelligentLife() {");
        html.append("                if (Math.random() > 0.8) {");
        html.append("                    const structureCount = 1 + Math.floor(Math.random() * 3);");
        html.append("                    for (let i = 0; i < structureCount; i++) {");
        html.append("                        const x = centerX + (Math.random() - 0.5) * radius * 1.5;");
        html.append("                        const y = centerY + (Math.random() - 0.5) * radius * 1.5;");
        html.append("                        ctx.fillStyle = '#ff5722';");
        html.append("                        ctx.fillRect(x - 5, y - 10, 10, 20);");
        html.append("                        ctx.fillStyle = '#795548';");
        html.append("                        ctx.beginPath();");
        html.append("                        ctx.moveTo(x - 8, y - 10);");
        html.append("                        ctx.lineTo(x, y - 15);");
        html.append("                        ctx.lineTo(x + 8, y - 10);");
        html.append("                        ctx.fill();");
        html.append("                    }");
        html.append("                }");
        html.append("            }");
        html.append("            function drawAtmosphere() {");
        html.append("                const gradient = ctx.createRadialGradient(centerX, centerY, radius, centerX, centerY, radius * 1.2);");
        html.append("                const colors = ['#4fc3f7', '#ff9800', '#2196f3', '#8bc34a'];");
        html.append("                const atmosphereColor = colors[Math.floor(Math.random() * colors.length)];");
        html.append("                gradient.addColorStop(0, atmosphereColor + '80');");
        html.append("                gradient.addColorStop(1, atmosphereColor + '00');");
        html.append("                ctx.beginPath();");
        html.append("                ctx.arc(centerX, centerY, radius * 1.2, 0, Math.PI * 2);");
        html.append("                ctx.fillStyle = gradient;");
        html.append("                ctx.fill();");
        html.append("            }");
        html.append("            function drawSunGlow() {");
        html.append("                const gradient = ctx.createRadialGradient(centerX + radius * 0.8, centerY - radius * 0.8, 0, centerX + radius * 0.8, centerY - radius * 0.8, radius * 0.5);");
        html.append("                gradient.addColorStop(0, 'rgba(255, 255, 200, 0.8)');");
        html.append("                gradient.addColorStop(1, 'rgba(255, 255, 200, 0)');");
        html.append("                ctx.beginPath();");
        html.append("                ctx.arc(centerX + radius * 0.8, centerY - radius * 0.8, radius * 0.5, 0, Math.PI * 2);");
        html.append("                ctx.fillStyle = gradient;");
        html.append("                ctx.fill();");
        html.append("            }");
        html.append("            drawPlanet();");
        html.append("            window.addEventListener('resize', function() {");
        html.append("                canvas.width = window.innerWidth;");
        html.append("                canvas.height = window.innerHeight;");
        html.append("            });");
        html.append("        }");
        html.append("        window.addEventListener('load', initPlanet);");
        html.append("    </script>");
        html.append("</body>");
        html.append("</html>");
        
        return html.toString();
    }
}
