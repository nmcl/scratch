import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * FractalLandscapeGenerator
 * This program generates a fractal heightmap and renders it with a 3D
 * perspective
 * using a fast scanline/raycasting technique onto a BufferedImage for optimal
 * performance.
 * Features include mountains, valleys, water, and atmospheric fog.
 */
public class FractalLandscapeGenerator3D {

    // --- Main Application Entry Point ---
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("3D Perspective Fractal Terrain");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            int size = 800;
            frame.setSize(size, size);

            LandscapePanel panel = new LandscapePanel(size, size);
            frame.add(panel);

            frame.setLocationRelativeTo(null);
            frame.setVisible(true);

            // Ensure rendering starts after the frame is visible
            panel.renderLandscape();
        });
    }
}

/**
 * LandscapePanel
 * This JPanel handles the noise generation and the 3D perspective rendering.
 */
class LandscapePanel extends JPanel {

    private final int width;
    private final int height;
    private final double[][] heightMap;
    private final long seed = 12345;

    // Buffer to hold the final rendered image for fast drawing
    private BufferedImage renderedImage;

    // --- 3D Camera and Rendering Constants ---
    // Adjusted CAMERA_HEIGHT for a slightly lower perspective.
    private static final double CAMERA_HEIGHT = 0.10;
    private static final double VIEW_DISTANCE = 0.5;
    private static final double FOG_START = 0.05;
    private static final double FOG_END = 0.7;
    private static final Color SKY_COLOR = new Color(173, 216, 230);

    // --- Noise Generation Parameters ---
    private static final int OCTAVES = 6;
    private static final double PERSISTENCE = 0.5;

    public LandscapePanel(int w, int h) {
        this.width = w;
        this.height = h;
        this.heightMap = new double[w][h];
        this.renderedImage = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);

        // Generate the base data once
        generateHeightMap();
    }

    /**
     * Public method to trigger the complex rendering process.
     */
    public void renderLandscape() {
        // Run the main projection and update the buffered image
        perspectiveRender();
        // Request repaint to display the new image
        repaint();
    }

    /**
     * Generates the fractal height map using a multi-octave noise function.
     */
    private void generateHeightMap() {
        double maxAltitude = 0.0;

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                double noiseValue = fractalNoise(x, y, OCTAVES, PERSISTENCE);
                heightMap[x][y] = noiseValue;
                if (noiseValue > maxAltitude) {
                    maxAltitude = noiseValue;
                }
            }
        }

        if (maxAltitude > 0) {
            for (int x = 0; x < width; x++) {
                for (int y = 0; y < height; y++) {
                    heightMap[x][y] /= maxAltitude;
                }
            }
        }
    }

    /**
     * Calculates fractal (Perlin-style) noise.
     */
    private double fractalNoise(int x, int y, int octaves, double persistence) {
        double total = 0;
        double frequency = 0.005;
        double amplitude = 1;
        double maxValue = 0;

        for (int i = 0; i < octaves; i++) {
            double noise = perlinNoise(x * frequency, y * frequency);
            total += noise * amplitude;
            maxValue += amplitude;
            amplitude *= persistence;
            frequency *= 2;
        }

        return (total / maxValue + 1.0) / 2.0;
    }

    // --- Simplified Perlin Noise Core Functions (Helper Methods) ---

    private double perlinNoise(double x, double y) {
        int x0 = (int) Math.floor(x);
        int y0 = (int) Math.floor(y);
        int x1 = x0 + 1;
        int y1 = y0 + 1;
        double tx = x - x0;
        double ty = y - y0;
        double n00 = gradientNoise(x0, y0, tx, ty);
        double n10 = gradientNoise(x1, y0, tx - 1, ty);
        double n01 = gradientNoise(x0, y1, tx, ty - 1);
        double n11 = gradientNoise(x1, y1, tx - 1, ty - 1);
        double ix0 = interpolate(n00, n10, tx);
        double ix1 = interpolate(n01, n11, tx);
        return interpolate(ix0, ix1, ty);
    }

    private double interpolate(double a, double b, double w) {
        w = w * w * w * (w * (w * 6 - 15) + 10);
        return a + w * (b - a);
    }

    private double gradientNoise(int ix, int iy, double px, double py) {
        int v = (int) (ix * 31 + iy * 101 + seed) & 0x7FFFFFFF;
        int gradient = v % 8;
        double gx = switch (gradient) {
            case 0, 1, 7 -> 1.0;
            case 3, 4, 5 -> -1.0;
            default -> 0.0;
        };
        double gy = switch (gradient) {
            case 1, 2, 3 -> 1.0;
            case 5, 6, 7 -> -1.0;
            default -> 0.0;
        };
        return (gx * px + gy * py);
    }

    // --- Color Mapping and Shading Helpers ---

    /**
     * Maps the normalized altitude (0 to 1) to an RGB base color.
     */
    private static Color getHeightColor(double altitude) {
        if (altitude < 0.2) {
            // Deep water
            int blue = (int) (60 + altitude * 150);
            return new Color(0, 0, Math.min(255, blue));
        } else if (altitude < 0.3) {
            // Shallow water / Coastline
            return new Color(30, 144, 255);
        } else if (altitude < 0.45) {
            // Grasslands / Plains
            return new Color(34, 139, 34);
        } else if (altitude < 0.7) {
            // Hills / Mountains
            return new Color(139, 101, 8);
        } else if (altitude < 0.9) {
            // Rocky Mountains
            return new Color(105, 105, 105);
        } else {
            // Snow caps
            return Color.WHITE;
        }
    }

    /**
     * Applies atmospheric fog based on the distance.
     */
    private static Color applyFog(Color base, double distance) {
        double fogDensity = (distance - FOG_START) / (FOG_END - FOG_START);
        fogDensity = Math.min(1.0, Math.max(0.0, fogDensity));

        int r = (int) (base.getRed() * (1 - fogDensity) + SKY_COLOR.getRed() * fogDensity);
        int g = (int) (base.getGreen() * (1 - fogDensity) + SKY_COLOR.getGreen() * fogDensity);
        int b = (int) (base.getBlue() * (1 - fogDensity) + SKY_COLOR.getBlue() * fogDensity);

        r = Math.min(255, Math.max(0, r));
        g = Math.min(255, Math.max(0, g));
        b = Math.min(255, Math.max(0, b));

        return new Color(r, g, b);
    }

    /**
     * Performs the 3D perspective projection and draws the result into the
     * BufferedImage.
     */
    private void perspectiveRender() {
        // Use a Graphics object for drawing into the buffered image
        Graphics2D imageG2D = renderedImage.createGraphics();

        int screenW = width;
        int screenH = height;
        int halfH = screenH / 2;

        // 1. Draw Sky/Horizon (everything above the halfway point)
        imageG2D.setColor(SKY_COLOR);
        imageG2D.fillRect(0, 0, screenW, screenH); // Clear the whole image with sky/fog color

        // Track the highest drawn screen pixel for hidden surface removal
        double[] maxProjectionY = new double[screenW];
        for (int x = 0; x < screenW; x++) {
            // Start the max projection tracking at the bottom of the screen
            maxProjectionY[x] = screenH;
        }

        // 2. Perspective Rendering Loop (Scanline from far to near / bottom of screen)
        // Iterate over screen rows from bottom to top (y = screenH - 1 to halfH)
        for (int y = screenH - 1; y >= halfH; y--) {

            // Calculate distance factor (d): closer screen rows (y approaching halfH) mean
            // larger d
            double d = (double) halfH / (y - halfH + 1);

            // Calculate world depth/distance (wy)
            double wy = d * VIEW_DISTANCE;

            // Iterate over screen columns (x)
            for (int x = 0; x < screenW; x++) {

                // Calculate the world X offset (p) relative to the center
                double p = (x - screenW / 2.0) / screenW;

                // Calculate the world coordinates (wx) by scaling the offset by distance
                double wx = (p * wy * 2.0) + VIEW_DISTANCE;

                // Map normalized world coordinates (0.0 to VIEW_DISTANCE*2) to heightmap array
                // indices
                int mapX = (int) (wx * width) % width;
                int mapY = (int) (wy * height) % height;

                // Handle map coordinate wrapping and bounds check
                if (mapX < 0)
                    mapX += width;
                if (mapY < 0)
                    mapY += height;

                if (mapX >= 0 && mapX < width && mapY >= 0 && mapY < height) {

                    double altitude = heightMap[mapX][mapY];

                    // Core 3D projection formula: screenY = halfH - (height difference / distance)
                    // * vertical scale
                    double screenAltitude = (altitude - CAMERA_HEIGHT) / wy;

                    // Vertical scaling factor (200 * VIEW_DISTANCE) controls mountain prominence
                    int projectionY = halfH - (int) (screenAltitude * screenH * 200 * VIEW_DISTANCE);

                    // --- Hidden Surface Removal (Z-buffering) ---
                    // Only draw this pixel if its projected Y is higher (closer to the horizon)
                    // than the maximum Y coordinate drawn so far in this column
                    // (maxProjectionY[x]).
                    if (projectionY < maxProjectionY[x]) {

                        // Get color, apply shading, and fog
                        Color baseColor = getHeightColor(altitude);

                        // Simple Shading based on simulated sun position (e.g., top-left)
                        // Use a neighbor to calculate slope for simple light/shadow contrast
                        double shadingFactor = 1.0;
                        if (mapX > 0) {
                            shadingFactor = 1.0 + (heightMap[mapX][mapY] - heightMap[mapX - 1][mapY]) * 0.5;
                            shadingFactor = Math.min(1.2, Math.max(0.7, shadingFactor));
                        }

                        int r = (int) (baseColor.getRed() * shadingFactor);
                        int g = (int) (baseColor.getGreen() * shadingFactor);
                        int b = (int) (baseColor.getBlue() * shadingFactor);

                        // FIX: Clamp the RGB values to the 0-255 range to prevent
                        // IllegalArgumentException
                        r = Math.min(255, Math.max(0, r));
                        g = Math.min(255, Math.max(0, g));
                        b = Math.min(255, Math.max(0, b));

                        baseColor = new Color(r, g, b);

                        Color finalColor = applyFog(baseColor, wy);

                        // Draw the vertical line segment from the current scanline (y) up to the
                        // projected height (projectionY)
                        for (int i = projectionY; i < y; i++) {
                            // Only draw pixels within the screen bounds
                            if (i >= halfH && i < screenH) {
                                renderedImage.setRGB(x, i, finalColor.getRGB());
                            }
                        }

                        // Update the highest drawn point for this column
                        maxProjectionY[x] = projectionY;
                    }
                }
            }
        }
        imageG2D.dispose();
    }

    /**
     * Draws the generated buffered image onto the panel.
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Draw the calculated image to fill the panel
        g.drawImage(renderedImage, 0, 0, getWidth(), getHeight(), this);
    }
}