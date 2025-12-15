import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.List;

/**
 * SolarSystemSimulation.java
 *
 * A simple Swing-based solar system simulator.
 * - Uses basic circular orbits for the planets
 * - Animates using a Swing Timer
 * - Supports zooming (mouse wheel), panning (drag), pause/resume (space), speed
 * +/- keys,
 * and toggling trails (T)
 *
 * Compile: javac SolarSystemSimulation.java
 * Run: java SolarSystemSimulation
 *
 * This is intended as an educational, visual simulator — not an
 * astronomically-accurate
 * physics engine. Orbital radii, periods and sizes are scaled for
 * visualization.
 */
public class SolarSystemSimulation {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Solar System Simulation");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            SolarPanel panel = new SolarPanel();
            frame.setContentPane(panel);
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
            panel.start();
        });
    }
}

class SolarPanel extends JPanel {
    private final List<Planet> planets = new ArrayList<>();
    private final Timer timer;
    private double simulationSpeed = 1.0; // scale factor for time
    private boolean running = true;
    private boolean showTrails = true;

    // view transform
    private double zoom = 1.0;
    private double offsetX = 0.0, offsetY = 0.0; // world -> screen offset
    private Point lastDrag;

    // time keeping (seconds)
    private double time = 0.0;
    private final int fps = 60;

    SolarPanel() {
        setPreferredSize(new Dimension(1000, 800));
        setBackground(Color.black);

        // add planets: orbitalRadius (pixels), radius (pixels), color, orbitalPeriod
        // (seconds), name
        // orbitalPeriod here is scaled down heavily so motion is visible
        planets.add(new Planet(0, 30, Color.yellow, 0.0, "Sun"));
        planets.add(new Planet(60, 4, new Color(0x9e9e9e), 88, "Mercury"));
        planets.add(new Planet(90, 7, new Color(0xffd699), 225, "Venus"));
        planets.add(new Planet(120, 8, new Color(0x2a8bd6), 365, "Earth"));
        planets.add(new Planet(150, 6, new Color(0xff7043), 687, "Mars"));
        planets.add(new Planet(210, 14, new Color(0xffcc80), 4333, "Jupiter"));
        planets.add(new Planet(270, 12, new Color(0xfff3b0), 10759, "Saturn"));
        planets.add(new Planet(330, 10, new Color(0x80deea), 30687, "Uranus"));
        planets.add(new Planet(390, 10, new Color(0x8c9eff), 60190, "Neptune"));

        // Initialize timer: actionPerformed called on EDT
        timer = new Timer(1000 / fps, e -> {
            if (running) {
                double dt = (1.0 / fps) * simulationSpeed; // seconds per frame in sim's time units
                // We'll treat the planetary periods as "days" scaled: to make planets move
                // nicely
                // convert dt (s) to days: dtDays = dt / 1 -> (we use dt units directly)
                time += dt;
                for (Planet p : planets) {
                    p.update(time);
                }
            }
            repaint();
        });

        // controls
        addMouseWheelListener(this::onWheel);
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                lastDrag = e.getPoint();
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                if (SwingUtilities.isRightMouseButton(e)) {
                    // reset view
                    resetView();
                }
            }
        });
        addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                Point p = e.getPoint();
                double dx = p.x - lastDrag.x;
                double dy = p.y - lastDrag.y;
                offsetX += dx / zoom;
                offsetY += dy / zoom;
                lastDrag = p;
                repaint();
            }
        });

        setFocusable(true);
        requestFocusInWindow();
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_SPACE -> running = !running;
                    case KeyEvent.VK_PLUS, KeyEvent.VK_ADD, KeyEvent.VK_EQUALS -> simulationSpeed *= 1.5;
                    case KeyEvent.VK_MINUS, KeyEvent.VK_SUBTRACT -> simulationSpeed /= 1.5;
                    case KeyEvent.VK_T -> showTrails = !showTrails;
                    case KeyEvent.VK_R -> resetView();
                }
            }
        });

        // Tooltips with instructions
        setToolTipText(
                "Space: pause/resume | +/-: speed | T: trails | Drag: pan | Mouse wheel: zoom | R or right-click: reset view");
    }

    public void start() {
        timer.start();
    }

    private void onWheel(MouseWheelEvent e) {
        double notches = e.getPreciseWheelRotation();
        double factor = Math.pow(1.12, -notches);
        // zoom about mouse pos
        Point mouse = e.getPoint();
        double wxBefore = (mouse.x - getWidth() / 2.0) / zoom - offsetX;
        double wyBefore = (mouse.y - getHeight() / 2.0) / zoom - offsetY;
        zoom *= factor;
        double wxAfter = (mouse.x - getWidth() / 2.0) / zoom - offsetX;
        double wyAfter = (mouse.y - getHeight() / 2.0) / zoom - offsetY;
        offsetX += (wxAfter - wxBefore);
        offsetY += (wyAfter - wyBefore);
        repaint();
    }

    private void resetView() {
        zoom = 1.0;
        offsetX = 0.0;
        offsetY = 0.0;
        simulationSpeed = 1.0;
        planets.forEach(Planet::clearTrail);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g.create();
        // smooth
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // world center is panel center
        int cx = getWidth() / 2;
        int cy = getHeight() / 2;

        // apply view transform
        AffineTransform at = new AffineTransform();
        at.translate(cx, cy);
        at.scale(zoom, zoom);
        at.translate(offsetX, offsetY);
        g2.setTransform(at);

        // optionally draw orbits
        g2.setStroke(new BasicStroke((float) (1.0 / Math.max(0.0001, zoom))));
        for (Planet p : planets) {
            if (!p.name.equals("Sun")) {
                g2.setColor(new Color(255, 255, 255, 40));
                double r = p.orbitalRadius;
                g2.drawOval((int) -r, (int) -r, (int) (2 * r), (int) (2 * r));
            }
        }

        // draw trails
        if (showTrails) {
            for (Planet p : planets) {
                if (!p.name.equals("Sun")) {
                    p.drawTrail(g2);
                }
            }
        }

        // draw planets
        for (Planet p : planets) {
            p.draw(g2);
        }

        // HUD overlay (draw in screen coordinates)
        g2.setTransform(new AffineTransform()); // reset
        g2.setColor(Color.white);
        g2.drawString(String.format("Time: %.1f | Speed: %.2fx | %s | Trails: %s", time, simulationSpeed,
                running ? "Running" : "Paused", showTrails ? "On" : "Off"), 10, 18);
        g2.drawString(
                "Controls: Space=Pause | +/- Speed | T=Toggle Trails | Drag=Pan | Wheel=Zoom | R/RightClick=Reset", 10,
                36);

        g2.dispose();
    }
}

class Planet {
    final double orbitalRadius; // in "pixels"
    final double radius; // rendered radius in pixels
    final Color color;
    final double orbitalPeriod; // in days (or arbitrary units) — larger = slower
    final String name;

    // current angle in radians
    private double angle = 0.0;

    // trail points (simple, stores world coordinates)
    private final java.util.List<Point.Double> trail = new ArrayList<>();
    private final int maxTrail = 600; // how many points to keep

    Planet(double orbitalRadius, double radius, Color color, double orbitalPeriod, String name) {
        this.orbitalRadius = orbitalRadius;
        this.radius = radius;
        this.color = color;
        this.orbitalPeriod = orbitalPeriod;
        this.name = name;
    }

    void update(double simTime) {
        if (name.equals("Sun"))
            return;
        // convert orbitalPeriod to a circular angular velocity
        // angular velocity w = 2pi / T. Here we treat sim time units such that
        // when simTime increases by 1, it's 1 day (arbitrary). To make outer planets
        // move,
        // we scale periods down by a constant factor so animation is visible.
        double visualPeriod = Math.max(1.0, orbitalPeriod / 60.0); // scale down
        angle = (2 * Math.PI * (simTime % visualPeriod)) / visualPeriod;

        // save trail
        double x = orbitalRadius * Math.cos(angle);
        double y = orbitalRadius * Math.sin(angle);
        trail.add(new Point.Double(x, y));
        if (trail.size() > maxTrail)
            trail.remove(0);
    }

    void draw(Graphics2D g2) {
        double x = (name.equals("Sun")) ? 0 : orbitalRadius * Math.cos(angle);
        double y = (name.equals("Sun")) ? 0 : orbitalRadius * Math.sin(angle);
        int r = (int) Math.max(1, radius);

        // glow for sun
        if (name.equals("Sun")) {
            Composite old = g2.getComposite();
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.9f));
            int glow = (int) (radius * 4);
            g2.setColor(new Color(255, 220, 100, 40));
            g2.fillOval((int) (-glow), (int) (-glow), glow * 2, glow * 2);
            g2.setComposite(old);
        }

        g2.setColor(color);
        g2.fillOval((int) (x - r), (int) (y - r), r * 2, r * 2);

        // label Earth-ish planets a bit
        if (!name.equals("Sun") && (name.equals("Earth") || name.equals("Mars") || name.equals("Jupiter"))) {
            g2.setColor(new Color(200, 200, 200, 200));
            g2.drawString(name, (int) (x + r + 4), (int) (y + r + 4));
        }
    }

    void drawTrail(Graphics2D g2) {
        if (trail.size() < 2)
            return;
        Stroke old = g2.getStroke();
        g2.setStroke(new BasicStroke(1f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
        // Fade trail: earlier points more transparent
        for (int i = 1; i < trail.size(); i++) {
            Point.Double a = trail.get(i - 1);
            Point.Double b = trail.get(i);
            float alpha = (float) i / (float) trail.size();
            Color c = new Color(color.getRed(), color.getGreen(), color.getBlue(), (int) (alpha * 255));
            g2.setColor(c);
            g2.drawLine((int) a.x, (int) a.y, (int) b.x, (int) b.y);
        }
        g2.setStroke(old);
    }

    void clearTrail() {
        trail.clear();
    }
}