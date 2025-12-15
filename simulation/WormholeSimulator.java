import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

/**
 * WormholeSimulator
 * Simulates the visual effect of traveling through a wormhole or warp tunnel
 * using radial lines and color distortion in Java Swing.
 */
public class WormholeSimulator {

    // --- Main Application Entry Point ---
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Wormhole Travel Simulation");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(800, 600);
            frame.add(new WormholePanel());
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}

/**
 * WormholePanel
 * Custom JPanel for handling the animation and rendering of the wormhole
 * effect.
 */
class WormholePanel extends JPanel implements ActionListener {

    // Timer for animation: Triggers a repaint every 16 milliseconds (approx. 60
    // FPS).
    private final Timer timer = new Timer(16, this);

    // Time variable controls the progression and rotation of the wormhole.
    private double time = 0;

    // Array to store the properties of individual "stars" or energy lines
    private final WarpStar[] stars;
    private final int STAR_COUNT = 500;

    // Random number generator for initial star placement
    private final Random rand = new Random();

    public WormholePanel() {
        setBackground(Color.BLACK);

        // Initialize the star array
        stars = new WarpStar[STAR_COUNT];
        for (int i = 0; i < STAR_COUNT; i++) {
            stars[i] = new WarpStar(rand);
        }

        timer.start();
    }

    /**
     * Data structure for a single element (star or energy point) in the warp field.
     */
    private static class WarpStar {
        double angle; // Radial position (0 to 2*PI)
        double radius; // Distance from the center (0.0 to 1.0 normalized)
        int colorOffset; // Used to select color from a gradient

        public WarpStar(Random rand) {
            // Random angle and initial radius (closer to center for the "tunnel" effect)
            angle = rand.nextDouble() * 2 * Math.PI;
            radius = rand.nextDouble() * 0.2;
            colorOffset = rand.nextInt(256); // Random initial color index
        }

        /**
         * Resets the star back to the center of the tunnel.
         */
        public void reset(Random rand) {
            angle = rand.nextDouble() * 2 * Math.PI;
            radius = 0.0; // Start at the center
            colorOffset = rand.nextInt(256);
        }
    }

    /**
     * Main drawing method.
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // Enable Anti-aliasing for smoother lines
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int centerX = getWidth() / 2;
        int centerY = getHeight() / 2;
        int maxRadius = Math.min(centerX, centerY);

        // --- Draw the Energy Tunnel Effect ---

        // We draw the stars from the center outwards, so the drawing order creates
        // depth.
        for (WarpStar star : stars) {

            // 1. Calculate current position with rotation
            double currentAngle = star.angle + time * 0.1; // Add rotation over time

            // Convert normalized radius to screen pixels
            int rScreen = (int) (star.radius * maxRadius);

            // Calculate Cartesian coordinates
            int x = (int) (rScreen * Math.cos(currentAngle));
            int y = (int) (rScreen * Math.sin(currentAngle));

            int screenX = centerX + x;
            int screenY = centerY + y;

            // 2. Calculate Color based on distance (radius)
            // Color shifts from purple/blue near the center to bright white/cyan near the
            // edges

            // Distance factor (0.0 to 1.0)
            double distanceFactor = star.radius / 1.0;

            // Create a gradient: Blue/Purple -> Cyan/White
            int r = (int) (0 + distanceFactor * 255);
            // Renamed 'g' to 'green' to avoid conflict with 'Graphics g' parameter
            int green = (int) (100 + distanceFactor * 155);
            int b = (int) (200 - distanceFactor * 100);

            // Clamp values
            r = Math.min(255, Math.max(0, r));
            green = Math.min(255, Math.max(0, green));
            b = Math.min(255, Math.max(0, b));

            // Set the color for the star/line
            g2d.setColor(new Color(r, green, b));

            // 3. Draw the Star/Line
            // Draw lines that get longer and thicker as they approach the edge
            // (speed/perspective)
            int lineLength = (int) (distanceFactor * 30) + 2;
            int thickness = (int) (distanceFactor * 2) + 1;

            g2d.setStroke(new BasicStroke(thickness));

            // Draw a line segment pointing radially outward
            int x2 = (int) ((rScreen + lineLength) * Math.cos(currentAngle));
            int y2 = (int) ((rScreen + lineLength) * Math.sin(currentAngle));

            g2d.drawLine(screenX, screenY, centerX + x2, centerY + y2);
        }
    }

    /**
     * Animation loop: updates the state of the stars.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        time += 0.05; // Increment total time (for rotation)

        // --- Star Movement (Simulate forward travel) ---
        double travelSpeed = 0.04;

        for (WarpStar star : stars) {
            // Increase the radius (move the star outward)
            star.radius += travelSpeed;

            // If the star reaches the edge (normalized radius > 1.0), reset it to the
            // center
            if (star.radius > 1.0) {
                star.reset(rand);
            }
        }

        // Request repaint to show the updated positions
        repaint();
    }
}