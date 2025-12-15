import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.ArrayList;

/**
 * SolarSystemSimulator
 * This program simulates the orbits of the inner solar system planets (Mercury,
 * Venus, Earth, Mars, Jupiter)
 * around the Sun using Java Swing graphics and a Timer for animation.
 */
public class SolarSystemSimulatorGemini {

    // --- Main Application Entry Point ---
    public static void main(String[] args) {
        // Ensures the GUI runs on the Event Dispatch Thread (EDT)
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Animated Solar System Simulation (Top View)");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(800, 800);
            frame.add(new SolarSystemPanel());
            frame.setVisible(true);
            frame.setResizable(true);
        });
    }
}

/**
 * A record to hold the static properties of a celestial body (Planet or Sun).
 * Records provide a concise way to create data classes in modern Java.
 */
record CelestialBody(String name, double semiMajorAxis, double orbitalPeriod, int visualRadius, Color color) {
}

/**
 * SolarSystemPanel
 * This custom JPanel contains all the drawing logic and the animation loop.
 */
class SolarSystemPanel extends JPanel implements ActionListener {

    // Scale Factor: Controls the visual distance between objects on the screen.
    // 1 unit of semiMajorAxis (AU) in our data roughly corresponds to 100 pixels.
    private static final double SCALE = 100.0;

    // Time multiplier: Controls the speed of the simulation (how much time passes
    // per frame).
    private static final double TIME_MULTIPLIER = 0.05;

    // Current time elapsed in the simulation (used to calculate position).
    private double time = 0;

    // List of celestial bodies to simulate.
    private final List<CelestialBody> solarSystem = new ArrayList<>();

    // Timer for animation: Triggers a repaint every 16 milliseconds (approx. 60
    // FPS).
    private final Timer timer = new Timer(16, this);

    public SolarSystemPanel() {
        setBackground(Color.BLACK);

        // --- Initialize Celestial Bodies (Data is highly simplified and scaled) ---
        // Semi-Major Axis (AU) and Orbital Period (in Earth Years) are used as relative
        // values.

        // Sun (Not orbiting, but a body for drawing)
        solarSystem.add(new CelestialBody("Sun", 0.0, 0.0, 20, Color.YELLOW));

        // Mercury: 0.387 AU, 0.241 YR
        solarSystem.add(new CelestialBody("Mercury", 0.387, 0.241, 4, Color.GRAY));

        // Venus: 0.723 AU, 0.615 YR
        solarSystem.add(new CelestialBody("Venus", 0.723, 0.615, 6, new Color(255, 165, 0))); // Orange

        // Earth: 1.0 AU, 1.0 YR
        solarSystem.add(new CelestialBody("Earth", 1.0, 1.0, 8, Color.BLUE));

        // Mars: 1.524 AU, 1.88 YR
        solarSystem.add(new CelestialBody("Mars", 1.524, 1.88, 5, Color.RED));

        // Jupiter: 5.2 AU, 11.86 YR (Large, distant, and slow)
        solarSystem.add(new CelestialBody("Jupiter", 5.2, 11.86, 12, new Color(205, 133, 63))); // Tan

        // Start the animation timer
        timer.start();
    }

    /**
     * The primary drawing method for the simulation.
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // Enable Anti-aliasing for smoother circles and lines
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Calculate the center of the panel
        int centerX = getWidth() / 2;
        int centerY = getHeight() / 2;

        // --- Draw Orbits (Concentric Circles) ---
        for (int i = 1; i < solarSystem.size(); i++) {
            CelestialBody body = solarSystem.get(i);
            int orbitRadius = (int) (body.semiMajorAxis() * SCALE);

            // Set color and style for the orbit lines
            g2d.setColor(new Color(50, 50, 50, 150)); // Dark Gray, slightly transparent
            g2d.setStroke(new BasicStroke(1));

            // Draw a circle centered at (centerX, centerY)
            g2d.drawOval(centerX - orbitRadius, centerY - orbitRadius,
                    orbitRadius * 2, orbitRadius * 2);
        }

        // --- Draw Celestial Bodies ---
        for (CelestialBody body : solarSystem) {

            // The Sun is always at the center
            if (body.name().equals("Sun")) {
                drawBody(g2d, centerX, centerY, body);
                continue;
            }

            // Calculate current orbital position (x, y) for planets

            // 1. Calculate the current orbital angle (theta)
            // The position is calculated based on: (2 * PI / Period) * Time
            // This gives the angle in radians.
            double angularVelocity = 2 * Math.PI / body.orbitalPeriod();
            double theta = angularVelocity * time;

            // 2. Calculate the distance (radius of orbit)
            int orbitRadius = (int) (body.semiMajorAxis() * SCALE);

            // 3. Convert polar coordinates (r, theta) to Cartesian (x, y)
            // Note: We use Math.sin for X and Math.cos for Y to start planets at the top (0
            // degrees)
            // and have them orbit clockwise, which is common convention in screen space.
            int x = (int) (orbitRadius * Math.sin(theta));
            int y = (int) (orbitRadius * Math.cos(theta));

            // 4. Translate to screen coordinates
            int screenX = centerX + x;
            int screenY = centerY + y;

            // 5. Draw the planet
            drawBody(g2d, screenX, screenY, body);
        }

        // --- Draw Simulation Info ---
        g2d.setColor(Color.WHITE);
        g2d.setFont(new Font("Monospaced", Font.BOLD, 12));
        g2d.drawString(String.format("Time Elapsed: %.2f Earth Years", time), 10, 20);
        g2d.drawString("Scale: 1 AU = " + (int) SCALE + " px", 10, 40);
    }

    /**
     * Helper method to draw a celestial body (Sun or Planet).
     */
    private void drawBody(Graphics2D g2d, int x, int y, CelestialBody body) {
        int r = body.visualRadius();

        // 1. Draw the filled circle (the body itself)
        g2d.setColor(body.color());
        g2d.fillOval(x - r, y - r, 2 * r, 2 * r);

        // 2. Add a white border for visibility
        g2d.setColor(Color.WHITE);
        g2d.drawOval(x - r, y - r, 2 * r, 2 * r);

        // 3. Label the planet
        g2d.setFont(new Font("SansSerif", Font.PLAIN, 10));
        // Position the label slightly below the planet
        g2d.drawString(body.name(), x + r + 2, y + 4);
    }

    /**
     * This method is called repeatedly by the Timer (the animation loop).
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        // Increment the simulation time
        time += TIME_MULTIPLIER;

        // Request the panel to repaint itself with the new time value
        repaint();
    }
}