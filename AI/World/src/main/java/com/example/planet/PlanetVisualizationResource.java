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
        
        return "<!DOCTYPE html>\n" +
                "<html>\n" +
                "<head>\n" +
                "    <title>Random Planet Visualization</title>\n" +
                "    <style>\n" +
                "        body { \n" +
                "            margin: 0; \n" +
                "            overflow: hidden; \n" +
                "            background-color: #000;\n" +
                "            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;\n" +
                "            color: white;\n" +
                "        }\n" +
                "        #container {\n" +
                "            position: relative;\n" +
                "            width: 100vw;\n" +
                "            height: 100vh;\n" +
                "        }\n" +
                "        #info {\n" +
                "            position: absolute;\n" +
                "            top: 20px;\n" +
                "            left: 20px;\n" +
                "            color: white;\n" +
                "            z-index: 10;\n" +
                "            background-color: rgba(0,0,0,0.7);\n" +
                "            padding: 15px;\n" +
                "            border-radius: 10px;\n" +
                "            max-width: 400px;\n" +
                "            backdrop-filter: blur(5px);\n" +
                "        }\n" +
                "        #loading {\n" +
                "            position: absolute;\n" +
                "            top: 50%;\n" +
                "            left: 50%;\n" +
                "            transform: translate(-50%, -50%);\n" +
                "            color: white;\n" +
                "            font-size: 24px;\n" +
                "        }\n" +
                "        canvas {\n" +
                "            display: block;\n" +
                "        }\n" +
                "        .planet-name {\n" +
                "            font-size: 24px;\n" +
                "            font-weight: bold;\n" +
                "            color: #4fc3f7;\n" +
                "            margin-bottom: 10px;\n" +
                "        }\n" +
                "        .planet-stats {\n" +
                "            margin-top: 15px;\n" +
                "            font-size: 14px;\n" +
                "        }\n" +
                "        .stat {\n" +
                "            margin: 5px 0;\n" +
                "        }\n" +
                "        .stat-value {\n" +
                "            color: #4caf50;\n" +
                "            font-weight: bold;\n" +
                "        }\n" +
                "        .intelligent-life {\n" +
                "            color: #ff9800;\n" +
                "            font-weight: bold;\n" +
                "        }\n" +
                "        .weather-effect {\n" +
                "            color: #2196f3;\n" +
                "        }\n" +
                "    </style>\n" +
                "</head>\n" +
                "<body>\n" +
                "    <div id=\"container\">\n" +
                "        <div id=\"info\">\n" +
                "            <div class=\"planet-name\">" + planet.getName() + "</div>\n" +
                "            <div class=\"planet-stats\">\n" +
                "                <div class=\"stat\">Planet Type: <span class=\"stat-value\">" + planet.getPlanetType() + "</span></div>\n" +
                "                <div class=\"stat\">Diameter: <span class=\"stat-value\">" + planet.getDiameter() + " km</span></div>\n" +
                "                <div class=\"stat\">Rotation Period: <span class=\"stat-value\">" + planet.getRotationPeriod() + " hours</span></div>\n" +
                "                <div class=\"stat\">Orbital Period: <span class=\"stat-value\">" + planet.getOrbitalPeriod() + " days</span></div>\n" +
                "                <div class=\"stat\">Temperature: <span class=\"stat-value\">" + planet.getTemperature() + "°C</span></div>\n" +
                "                <div class=\"stat\">Atmosphere: <span class=\"stat-value\">" + planet.getAtmosphere() + "</span></div>\n" +
                "                <div class=\"stat\">Oceans: <span class=\"stat-value\">" + planet.getOceanCoverage() + "%</span></div>\n" +
                "                <div class=\"stat\">Continents: <span class=\"stat-value\">" + planet.getContinentCount() + "</span></div>\n" +
                "                <div class=\"stat\">Intelligent Life: <span class=\"stat-value " + (planet.hasIntelligentLife() ? "intelligent-life" : "") + "\">" + (planet.hasIntelligentLife() ? "YES" : "NO") + "</span></div>\n" +
                "                <div class=\"stat\">Weather: <span class=\"stat-value weather-effect\">" + planet.getWeather() + "</span></div>\n" +
                "            </div>\n" +
                "        </div>\n" +
                "        <div id=\"loading\">Generating planet visualization...</div>\n" +
                "        <canvas id=\"planetCanvas\"></canvas>\n" +
                "    </div>\n" +
                "\n" +
                "    <script>\n" +
                "        // Random planet visualization with WebGL-like effects\n" +
                "        function initPlanet() {\n" +
                "            const canvas = document.getElementById('planetCanvas');\n" +
                "            const ctx = canvas.getContext('2d');\n" +
                "            const info = document.getElementById('info');\n" +
                "            \n" +
                "            // Set canvas size to full window\n" +
                "            canvas.width = window.innerWidth;\n" +
                "            canvas.height = window.innerHeight;\n" +
                "            \n" +
                "            // Hide loading message\n" +
                "            document.getElementById('loading').style.display = 'none';\n" +
                "            \n" +
                "            // Planet parameters\n" +
                "            const centerX = canvas.width / 2;\n" +
                "            const centerY = canvas.height / 2;\n" +
                "            const radius = Math.min(canvas.width, canvas.height) * 0.3;\n" +
                "            \n" +
                "            // Draw planet\n" +
                "            function drawPlanet() {\n" +
                "                // Clear canvas\n" +
                "                ctx.clearRect(0, 0, canvas.width, canvas.height);\n" +
                "                \n" +
                "                // Draw stars background\n" +
                "                drawStars();\n" +
                "                \n" +
                "                // Draw planet sphere\n" +
                "                drawPlanetSphere();\n" +
                "                \n" +
                "                // Draw clouds\n" +
                "                drawClouds();\n" +
                "                \n" +
                "                // Draw weather patterns\n" +
                "                drawWeatherPatterns();\n" +
                "                \n" +
                "                // Draw intelligent life if present\n" +
                "                drawIntelligentLife();\n" +
                "                \n" +
                "                // Draw atmosphere glow\n" +
                "                drawAtmosphere();\n" +
                "                \n" +
                "                // Draw sun glow (simulated)\n" +
                "                drawSunGlow();\n" +
                "                \n" +
                "                // Continue animation\n" +
                "                requestAnimationFrame(drawPlanet);\n" +
                "            }\n" +
                "            \n" +
                "            function drawStars() {\n" +
                "                ctx.fillStyle = 'white';\n" +
                "                for (let i = 0; i < 300; i++) {\n" +
                "                    const x = Math.random() * canvas.width;\n" +
                "                    const y = Math.random() * canvas.height;\n" +
                "                    const size = Math.random() * 1.5;\n" +
                "                    ctx.beginPath();\n" +
                "                    ctx.arc(x, y, size, 0, Math.PI * 2);\n" +
                "                    ctx.fill();\n" +
                "                }\n" +
                "            }\n" +
                "            \n" +
                "            function drawPlanetSphere() {\n" +
                "                // Create gradient for planet\n" +
                "                const gradient = ctx.createRadialGradient(\n" +
                "                    centerX, centerY, 0,\n" +
                "                    centerX, centerY, radius\n" +
                "                );\n" +
                "                \n" +
                "                // Set colors based on planet type\n" +
                "                const colors = getPlanetColors();\n" +
                "                gradient.addColorStop(0, colors[0]); // Ocean\n" +
                "                gradient.addColorStop(0.4, colors[1]); // Land\n" +
                "                gradient.addColorStop(1, colors[2]); // Mountains\n" +
                "                \n" +
                "                // Draw planet sphere\n" +
                "                ctx.beginPath();\n" +
                "                ctx.arc(centerX, centerY, radius, 0, Math.PI * 2);\n" +
                "                ctx.fillStyle = gradient;\n" +
                "                ctx.fill();\n" +
                "                \n" +
                "                // Draw continent shapes\n" +
                "                drawContinents();\n" +
                "            }\n" +
                "            \n" +
                "            function getPlanetColors() {\n" +
                "                // Different color schemes based on planet type\n" +
                "                switch (Math.floor(Math.random() * 4)) {\n" +
                "                    case 0: // Earth-like\n" +
                "                        return ['#1a73e8', '#388e3c', '#5d4037'];\n" +
                "                    case 1: // Desert\n" +
                "                        return ['#ff9800', '#ff5722', '#795548'];\n" +
                "                    case 2: // Ice world\n" +
                "                        return ['#b3e5fc', '#e1f5fe', '#e0f7fa'];\n" +
                "                    case 3: // Forest world\n" +
                "                        return ['#2e7d32', '#4caf50', '#1b5e20'];\n" +
                "                    default:\n" +
                "                        return ['#1a73e8', '#388e3c', '#5d4037'];\n" +
                "                }\n" +
                "            }\n" +
                "            \n" +
                "            function drawContinents() {\n" +
                "                // Draw continents with different shapes\n" +
                "                const continentCount = 3 + Math.floor(Math.random() * 4);\n" +
                "                \n" +
                "                for (let i = 0; i < continentCount; i++) {\n" +
                "                    const x = centerX + (Math.random() - 0.5) * radius * 1.5;\n" +
                "                    const y = centerY + (Math.random() - 0.5) * radius * 1.5;\n" +
                "                    const size = radius * (0.15 + Math.random() * 0.2);\n" +
                "                    const shape = Math.floor(Math.random() * 3);\n" +
                "                    \n" +
                "                    ctx.fillStyle = getContinentColor();\n" +
                "                    ctx.beginPath();\n" +
                "                    \n" +
                "                    if (shape === 0) {\n" +
                "                        // Elliptical continent\n" +
                "                        ctx.ellipse(x, y, size, size * 0.6, 0, 0, Math.PI * 2);\n" +
                "                    } else if (shape === 1) {\n" +
                "                        // Irregular polygon\n" +
                "                        drawIrregularContinent(x, y, size);\n" +
                "                    } else {\n" +
                "                        // Cluster of islands\n" +
                "                        drawIslandCluster(x, y, size);\n" +
                "                    }\n" +
                "                    ctx.fill();\n" +
                "                }\n" +
                "            }\n" +
                "            \n" +
                "            function getContinentColor() {\n" +
                "                // Different colors for different continent types\n" +
                "                const colors = ['#388e3c', '#4caf50', '#2e7d32', '#1b5e20', '#388e3c'];\n" +
                "                return colors[Math.floor(Math.random() * colors.length)];\n" +
                "            }\n" +
                "            \n" +
                "            function drawIrregularContinent(x, y, size) {\n" +
                "                const points = 8 + Math.floor(Math.random() * 5);\n" +
                "                ctx.moveTo(x + size, y);\n" +
                "                \n" +
                "                for (let i = 1; i <= points; i++) {\n" +
                "                    const angle = (i / points) * Math.PI * 2;\n" +
                "                    const radius = size * (0.7 + Math.random() * 0.3);\n" +
                "                    const px = x + Math.cos(angle) * radius;\n" +
                "                    const py = y + Math.sin(angle) * radius;\n" +
                "                    ctx.lineTo(px, py);\n" +
                "                }\n" +
                "                ctx.closePath();\n" +
                "            }\n" +
                "            \n" +
                "            function drawIslandCluster(x, y, size) {\n" +
                "                const islands = 3 + Math.floor(Math.random() * 4);\n" +
                "                \n" +
                "                for (let i = 0; i < islands; i++) {\n" +
                "                    const islandX = x + (Math.random() - 0.5) * size * 1.5;\n" +
                "                    const islandY = y + (Math.random() - 0.5) * size * 1.5;\n" +
                "                    const islandSize = size * (0.3 + Math.random() * 0.4);\n" +
                "                    \n" +
                "                    ctx.beginPath();\n" +
                "                    ctx.arc(islandX, islandY, islandSize, 0, Math.PI * 2);\n" +
                "                    ctx.fill();\n" +
                "                }\n" +
                "            }\n" +
                "            \n" +
                "            function drawClouds() {\n" +
                "                ctx.fillStyle = 'rgba(255, 255, 255, 0.7)';\n" +
                "                \n" +
                "                // Draw some clouds\n" +
                "                const cloudCount = 5 + Math.floor(Math.random() * 8);\n" +
                "                \n" +
                "                for (let i = 0; i < cloudCount; i++) {\n" +
                "                    const x = centerX + (Math.random() - 0.5) * radius * 2;\n" +
                "                    const y = centerY + (Math.random() - 0.5) * radius * 2;\n" +
                "                    const size = radius * (0.05 + Math.random() * 0.1);\n" +
                "                    \n" +
                "                    ctx.beginPath();\n" +
                "                    ctx.arc(x, y, size, 0, Math.PI * 2);\n" +
                "                    ctx.fill();\n" +
                "                    \n" +
                "                    // Add cloud details\n" +
                "                    ctx.beginPath();\n" +
                "                    ctx.arc(x + size * 0.5, y - size * 0.2, size * 0.7, 0, Math.PI * 2);\n" +
                "                    ctx.fill();\n" +
                "                    \n" +
                "                    ctx.beginPath();\n" +
                "                    ctx.arc(x - size * 0.5, y + size * 0.3, size * 0.6, 0, Math.PI * 2);\n" +
                "                    ctx.fill();\n" +
                "                }\n" +
                "            }\n" +
                "            \n" +
                "            function drawWeatherPatterns() {\n" +
                "                // Draw weather patterns like storms or wind\n" +
                "                if (Math.random() > 0.7) {\n" +
                "                    ctx.strokeStyle = 'rgba(100, 150, 255, 0.5)';\n" +
                "                    ctx.lineWidth = 2;\n" +
                "                    \n" +
                "                    // Draw some wind lines\n" +
                "                    for (let i = 0; i < 10; i++) {\n" +
                "                        const x = centerX + (Math.random() - 0.5) * radius * 2;\n" +
                "                        const y = centerY + (Math.random() - 0.5) * radius * 2;\n" +
                "                        const length = radius * (0.1 + Math.random() * 0.2);\n" +
                "                        const angle = Math.random() * Math.PI * 2;\n" +
                "                        \n" +
                "                        ctx.beginPath();\n" +
                "                        ctx.moveTo(x, y);\n" +
                "                        ctx.lineTo(\n" +
                "                            x + Math.cos(angle) * length,\n" +
                "                            y + Math.sin(angle) * length\n" +
                "                        );\n" +
                "                        ctx.stroke();\n" +
                "                    }\n" +
                "                }\n" +
                "            }\n" +
                "            \n" +
                "            function drawIntelligentLife() {\n" +
                "                // Draw signs of intelligent life if present\n" +
                "                if (Math.random() > 0.8) {\n" +
                "                    // Draw some structures\n" +
                "                    const structureCount = 1 + Math.floor(Math.random() * 3);\n" +
                "                    \n" +
                "                    for (let i = 0; i < structureCount; i++) {\n" +
                "                        const x = centerX + (Math.random() - 0.5) * radius * 1.5;\n" +
                "                        const y = centerY + (Math.random() - 0.5) * radius * 1.5;\n" +
                "                        \n" +
                "                        // Draw a building or structure\n" +
                "                        ctx.fillStyle = '#ff5722';\n" +
                "                        ctx.fillRect(x - 5, y - 10, 10, 20);\n" +
                "                        \n" +
                "                        // Draw roof\n" +
                "                        ctx.fillStyle = '#795548';\n" +
                "                        ctx.beginPath();\n" +
                "                        ctx.moveTo(x - 8, y - 10);\n" +
                "                        ctx.lineTo(x, y - 15);\n" +
                "                        ctx.lineTo(x + 8, y - 10);\n" +
                "                        ctx.fill();\n" +
                "                    }\n" +
                "                }\n" +
                "            }\n" +
                "            \n" +
                "            function drawAtmosphere() {\n" +
                "                const gradient = ctx.createRadialGradient(\n" +
                "                    centerX, centerY, radius,\n" +
                "                    centerX, centerY, radius * 1.2\n" +
                "                );\n" +
                "                \n" +
                "                // Different atmosphere colors based on planet type\n" +
                "                const colors = ['#4fc3f7', '#ff9800', '#2196f3', '#8bc34a'];\n" +
                "                const atmosphereColor = colors[Math.floor(Math.random() * colors.length)];\n" +
                "                \n" +
                "                gradient.addColorStop(0, atmosphereColor + '80'); // Semi-transparent\n" +
                "                gradient.addColorStop(1, atmosphereColor + '00'); // Transparent\n" +
                "                \n" +
                "                ctx.beginPath();\n" +
                "                ctx.arc(centerX, centerY, radius * 1.2, 0, Math.PI * 2);\n" +
                "                ctx.fillStyle = gradient;\n" +
                "                ctx.fill();\n" +
                "            }\n" +
                "            \n" +
                "            function drawSunGlow() {\n" +
                "                const gradient = ctx.createRadialGradient(\n" +
                "                    centerX + radius * 0.8, centerY - radius * 0.8, 0,\n" +
                "                    centerX + radius * 0.8, centerY - radius * 0.8, radius * 0.5\n" +
                "                );\n" +
                "                gradient.addColorStop(0, 'rgba(255, 255, 200, 0.8)');\n" +
                "                gradient.addColorStop(1, 'rgba(255, 255, 200, 0)');\n" +
                "                \n" +
                "                ctx.beginPath();\n" +
                "                ctx.arc(centerX + radius * 0.8, centerY - radius * 0.8, radius * 0.5, 0, Math.PI * 2);\n" +
                "                ctx.fillStyle = gradient;\n" +
                "                ctx.fill();\n" +
                "            }\n" +
                "            \n" +
                "            // Start animation\n" +
                "            drawPlanet();\n" +
                "            \n" +
                "            // Handle window resize\n" +
                "            window.addEventListener('resize', function() {\n" +
                "                canvas.width = window.innerWidth;\n" +
                "                canvas.height = window.innerHeight;\n" +
                "            });\n" +
                "        }\n" +
                "        \n" +
                "        // Initialize when page loads\n" +
                "        window.addEventListener('load', initPlanet);\n" +
                "    </script>\n" +
                "</body>\n" +
                "</html>";
    }
}
