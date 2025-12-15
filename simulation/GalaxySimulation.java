import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.List;

public class GalaxySimulation {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Galaxy Simulation");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setContentPane(new GalaxyPanel());
            frame.setSize(1200, 800);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}

class GalaxyPanel extends JPanel {
    private final List<Star> stars = new ArrayList<>();
    private final javax.swing.Timer timer;
    private final int numStars = 3000; // number of stars
    private final double centerX, centerY;

    private double rotationSpeed = 0.0005; // radians per frame

    public GalaxyPanel() {
        setBackground(Color.black);
        centerX = 600;
        centerY = 400;

        // Initialize stars in a spiral galaxy
        Random rand = new Random();
        for (int i = 0; i < numStars; i++) {
            double angle = rand.nextDouble() * 2 * Math.PI;
            double radius = Math.pow(rand.nextDouble(), 0.5) * 350; // denser near center
            double armOffset = (rand.nextDouble() - 0.5) * 0.5; // spread along arms
            int arm = rand.nextInt(2); // two spiral arms
            angle += arm * Math.PI + armOffset;

            double x = radius * Math.cos(angle);
            double y = radius * Math.sin(angle) * 0.5; // flatten a bit vertically

            double speed = 0.0005 + radius / 700000.0; // stars rotate faster near center
            float brightness = 0.6f + 0.4f * rand.nextFloat();

            stars.add(new Star(x, y, angle, radius, speed, brightness));
        }

        // Timer for animation
        timer = new javax.swing.Timer(16, e -> {
            updateStars();
            repaint();
        });
        timer.start();

        // Optional: Mouse wheel zoom
        addMouseWheelListener(e -> {
            double zoomFactor = 1.0 + 0.1 * e.getPreciseWheelRotation();
            for (Star s : stars) {
                s.radius *= zoomFactor;
            }
        });
    }

    private void updateStars() {
        for (Star s : stars) {
            s.angle += s.angularSpeed;
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        for (Star s : stars) {
            double x = centerX + s.radius * Math.cos(s.angle);
            double y = centerY + s.radius * Math.sin(s.angle);
            int size = s.brightness > 0.8 ? 3 : 2;
            g2.setColor(new Color(s.brightness, s.brightness, s.brightness));
            g2.fillOval((int) x, (int) y, size, size);
        }

        g2.dispose();
    }

    static class Star {
        double x, y;
        double angle;
        double radius;
        double angularSpeed;
        float brightness;

        public Star(double x, double y, double angle, double radius, double angularSpeed, float brightness) {
            this.x = x;
            this.y = y;
            this.angle = angle;
            this.radius = radius;
            this.angularSpeed = angularSpeed;
            this.brightness = brightness;
        }
    }
}
