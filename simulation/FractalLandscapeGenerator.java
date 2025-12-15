import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * FractalLandscapeGenerator
 * This program generates and displays a fractal landscape (heightmap)
 * using a multi-octave Perlin-style noise algorithm implemented in pure Java
 * Swing.
 * Terrain features like oceans, rivers, plains, and mountains are created
 * through color mapping based on altitude.
 */
public class FractalLandscapeGenerator {

    // --- Main Application Entry Point ---
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Fractal Terrain Simulation");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            // Set the simulation size
            int size = 800;
            frame.setSize(size, size);

            // Create and add the panel that generates and draws the landscape
            frame.add(new LandscapePanel(size, size));

            frame.setLocationRelativeTo(null); // Center the window
            frame.setVisible(true);
        });
    }
}

/**
 * LandscapePanel
 * This JPanel handles the noise generation, color mapping, and drawing.
 */
class LandscapePanel extends JPanel {

    private final int width;
    private final int height;
    private final BufferedImage image;
    private final double[][] heightMap;
    private final long seed = 12345; // Fixed seed for reproducible terrain

    // --- Noise Generation Parameters ---
    private static final int OCTAVES = 6;
    private static final double PERSISTENCE = 0.5; // How much detail is added in each octave

    public LandscapePanel(int w, int h) {
        this.width = w;
        this.height = h;
        this.image = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
        this.heightMap = new double[w][h];

        generateHeightMap();
        colorMapHeightMap();
    }

    /**
     * Generates the fractal height map using a multi-octave noise function.
     */
    private void generateHeightMap() {
        double maxAltitude = 0.0;

        // Loop through every pixel/point on the map
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {

                // Calculate the noise value for the current (x, y) coordinate
                double noiseValue = fractalNoise(x, y, OCTAVES, PERSISTENCE);
                heightMap[x][y] = noiseValue;

                if (noiseValue > maxAltitude) {
                    maxAltitude = noiseValue;
                }
            }
        }

        // Normalize the heightmap (optional but good practice)
        if (maxAltitude > 0) {
            for (int x = 0; x < width; x++) {
                for (int y = 0; y < height; y++) {
                    heightMap[x][y] /= maxAltitude;
                }
            }
        }
    }

    /**
     * Calculates fractal (Perlin-style) noise by summing multiple octaves of base
     * noise.
     */
    private double fractalNoise(int x, int y, int octaves, double persistence) {
        double total = 0;
        double frequency = 0.005; // Base frequency (controls zoom/scale of terrain features)
        double amplitude = 1;
        double maxValue = 0; // Used for normalization

        for (int i = 0; i < octaves; i++) {
            // Get the base noise value at the current frequency
            // The result is smoothed, interpolated noise between -1 and 1
            double noise = perlinNoise(x * frequency, y * frequency);

            total += noise * amplitude;
            maxValue += amplitude;

            // Prepare for the next octave (higher frequency, lower amplitude)
            amplitude *= persistence;
            frequency *= 2;
        }

        // Normalize the accumulated value to ensure output is in a controlled range (0
        // to 1)
        return (total / maxValue + 1.0) / 2.0;
    }

    // --- Simplified Perlin Noise Core Functions ---

    /**
     * * Calculates Perlin-style noise for a given floating point coordinate (x, y).
     * This simulates the gradient and interpolation steps of classical Perlin
     * Noise.
     */
    private double perlinNoise(double x, double y) {
        // Find unit grid cell coordinates
        int x0 = (int) Math.floor(x);
        int y0 = (int) Math.floor(y);
        int x1 = x0 + 1;
        int y1 = y0 + 1;

        // Determine interpolation weights (0.0 to 1.0)
        double tx = x - x0;
        double ty = y - y0;

        // Get the gradient noise at the four corners
        double n00 = gradientNoise(x0, y0, tx, ty);
        double n10 = gradientNoise(x1, y0, tx - 1, ty);
        double n01 = gradientNoise(x0, y1, tx, ty - 1);
        double n11 = gradientNoise(x1, y1, tx - 1, ty - 1);

        // Interpolate along x axis
        double ix0 = interpolate(n00, n10, tx);
        double ix1 = interpolate(n01, n11, tx);

        // Interpolate along y axis
        return interpolate(ix0, ix1, ty);
    }

    /**
     * Smooth Hermite interpolation function (6t^5 - 15t^4 + 10t^3).
     */
    private double interpolate(double a, double b, double w) {
        w = w * w * w * (w * (w * 6 - 15) + 10);
        return a + w * (b - a);
    }

    /**
     * Calculates the dot product between a pseudo-random gradient vector at the
     * grid
     * corner (ix, iy) and the distance vector to the point (px, py).
     */
    private double gradientNoise(int ix, int iy, double px, double py) {
        // Pseudo-random number generator used to pick a gradient direction (8 possible)
        int v = (int) (ix * 31 + iy * 101 + seed) & 0x7FFFFFFF;
        // Simple modulo 8 gives 8 directions (0-7)
        int gradient = v % 8;

        // Convert the 8 directions into x and y gradient components
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

        // Dot product: (gX * dX) + (gY * dY)
        return (gx * px + gy * py);
    }

    /**
     * Maps the normalized altitude (heightMap value from 0 to 1) to an RGB color.
     */
    private void colorMapHeightMap() {
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                double altitude = heightMap[x][y];
                Color color;

                // --- Altitude Coloring ---
                if (altitude < 0.2) {
                    // Deep water / Ocean
                    int blue = (int) (120 + altitude * 200);
                    color = new Color(0, 0, Math.min(255, blue));
                } else if (altitude < 0.3) {
                    // Shallow water / Rivers / Coastline
                    color = new Color(30, 144, 255); // Dodger Blue
                } else if (altitude < 0.45) {
                    // Beach / Grasslands
                    color = new Color(34, 139, 34); // Forest Green
                } else if (altitude < 0.7) {
                    // Hills / Plains
                    color = new Color(139, 101, 8); // Brown/Tan
                } else if (altitude < 0.9) {
                    // Mountains
                    color = new Color(105, 105, 105); // Dim Gray
                } else {
                    // Snow caps / Peaks
                    color = Color.WHITE;
                }

                // --- Simple Simulated Shading (Gives a 3D effect) ---
                // Calculate basic slope (difference from a neighbor) to simulate shading
                // as if the light source is from the top-left (e.g., x-1, y-1)
                double shadingFactor = 1.0;
                if (x > 0 && y > 0) {
                    double slopeX = heightMap[x][y] - heightMap[x - 1][y];
                    double slopeY = heightMap[x][y] - heightMap[x][y - 1];
                    // Adjust shading based on the combined slope (more slope = darker shadow or
                    // lighter highlight)
                    shadingFactor = 1.0 + (slopeX * 0.1) + (slopeY * 0.1);
                    shadingFactor = Math.min(1.2, Math.max(0.7, shadingFactor)); // Clamp factor
                }

                // Apply shading to the final color
                int r = (int) (color.getRed() * shadingFactor);
                int g = (int) (color.getGreen() * shadingFactor);
                int b = (int) (color.getBlue() * shadingFactor);

                r = Math.min(255, Math.max(0, r));
                g = Math.min(255, Math.max(0, g));
                b = Math.min(255, Math.max(0, b));

                image.setRGB(x, y, new Color(r, g, b).getRGB());
            }
        }
    }

    /**
     * Draws the generated buffered image onto the panel.
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Draw the calculated image to fill the panel
        g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
    }
}