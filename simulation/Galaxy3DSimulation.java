import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.List;

public class Galaxy3DSimulation {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("3D Galaxy Simulation");
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
    private final int numStars = 5000; // more stars
    private final double centerX, centerY;
    private final Random rand = new Random();

    private double rotationSpeedFactor = 1.0; // multiplier for speed
    private double zoom = 1.0;
    private double offsetX = 0, offsetY = 0;
    private Point lastDrag;

    private final int spiralArms = 3; // number of spiral arms
    private final boolean barred = true; // add bar in center
    private final int trailLength = 30;

    public GalaxyPanel() {
        setBackground(Color.black);
        centerX = 600;
        centerY = 400;

        // Initialize stars
        for (int i = 0; i < numStars; i++) {
            double angle = rand.nextDouble() * 2 * Math.PI;
            double radius = Math.pow(rand.nextDouble(), 0.5) * 350;

            // Assign spiral arm
            int arm = rand.nextInt(spiralArms);
            double armOffset = (rand.nextDouble() - 0.5) * 0.6;
            angle += arm * 2 * Math.PI / spiralArms + armOffset;

            // Z-depth (pseudo 3D)
            double z = rand.nextDouble() * 200 - 100; // -100 to +100

            // Differential rotation: faster near center
            double speed = 0.0005 + 0.5 * Math.exp(-radius / 200);

            // Color variation
            Color color;
            double type = rand.nextDouble();
            if (type < 0.2)
                color = new Color(0.5f, 0.5f, 1f); // blue
            else if (type < 0.7)
                color = new Color(1f, 1f, 0.7f); // yellow
            else
                color = new Color(1f, 0.6f, 0.6f); // red

            stars.add(new Star(radius, angle, z, speed, color));
        }

        // Timer for animation
        timer = new javax.swing.Timer(16, e -> {
            updateStars();
            repaint();
        });
        timer.start();

        // Zoom
        addMouseWheelListener(e -> zoom *= Math.pow(1.12, -e.getPreciseWheelRotation()));

        // Drag for panning
        addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                lastDrag = e.getPoint();
            }
        });
        addMouseMotionListener(new MouseAdapter() {
            public void mouseDragged(MouseEvent e) {
                Point p = e.getPoint();
                offsetX += (p.x - lastDrag.x) / zoom;
                offsetY += (p.y - lastDrag.y) / zoom;
                lastDrag = p;
                repaint();
            }
        });
    }

    private void updateStars() {
        for (Star s : stars) {
            s.angle += s.angularSpeed * rotationSpeedFactor;
            s.updateTrail(trailLength);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Draw dust/mist in background
        g2.setColor(new Color(0.2f, 0.2f, 0.25f, 0.1f));
        g2.fillOval((int) (centerX - 350 + offsetX), (int) (centerY - 350 + offsetY), 700, 700);

        for (Star s : stars) {
            // Perspective projection
            double scale = 1.0 - s.z / 500.0;
            double x = centerX + (s.radius * Math.cos(s.angle)) * scale * zoom + offsetX;
            double y = centerY + (s.radius * Math.sin(s.angle)) * scale * 0.6 * zoom + offsetY;

            // Draw trails
            for (int i = 0; i < s.trail.size(); i++) {
                double[] t = s.trail.get(i);
                float alpha = (float) i / s.trail.size();
                g2.setColor(new Color(s.color.getRed() / 255f, s.color.getGreen() / 255f, s.color.getBlue() / 255f,
                        alpha * 0.5f));
                g2.fillOval((int) (t[0]), (int) (t[1]), 2, 2);
            }

            // Draw star
            g2.setColor(s.color);
            int size = (int) (2 * scale);
            g2.fillOval((int) x, (int) y, size, size);
        }

        // Optional: central bar
        if (barred) {
            g2.setColor(new Color(1f, 0.8f, 0.6f, 0.1f));
            g2.fillRect((int) (centerX - 100 + offsetX), (int) (centerY - 10 + offsetY), 200, 20);
        }

        g2.dispose();
    }

    static class Star {
        double radius;
        double angle;
        double z;
        double angularSpeed;
        Color color;
        final List<double[]> trail = new ArrayList<>();

        Star(double radius, double angle, double z, double angularSpeed, Color color) {
            this.radius = radius;
            this.angle = angle;
            this.z = z;
            this.angularSpeed = angularSpeed;
            this.color = color;
        }

        void updateTrail(int maxLength) {
            double[] point = new double[] { radius * Math.cos(angle), radius * Math.sin(angle) };
            trail.add(point);
            if (trail.size() > maxLength)
                trail.remove(0);
        }
    }
}
