import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

/**
 * SolarSystemHybridTextures.java
 *
 * Hybrid solar system: Keplerian (precomputed) elliptical orbits (no N-body),
 * with built-in (procedurally generated) textures embedded in the code.
 *
 * Features:
 * - Keplerian orbital motion: solves Kepler's equation for true anomaly
 * - Procedural textures generated at runtime (no external files required)
 * - Planet rotation (texture rotates on the planet)
 * - Trails toggle, pause/resume, time-scale control
 * - Zoom & pan, reset view
 *
 * Compile: javac SolarSystemHybridTextures.java
 * Run: java SolarSystemHybridTextures
 *
 * This aims for a balance of realism (elliptical orbits) and interactive
 * performance.
 */
public class SolarSystemHybridTextures {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Hybrid Solar System — Textured Planets");
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
    private final List<Body> bodies = new ArrayList<>();
    private final Timer timer;
    private boolean running = true;
    private boolean showTrails = true;

    // view
    private double zoom = 1.0;
    private double offsetX = 0.0, offsetY = 0.0;
    private Point lastDrag;

    // time
    private double simDays = 0.0; // simulation time in days
    private double dayPerSecond = 60.0; // how many simulated days pass per real second
    private final int fps = 60;

    SolarPanel() {
        setPreferredSize(new Dimension(1100, 800));
        setBackground(Color.black);

        // Sun (central body)
        bodies.add(new Body("Sun", 0, 0, 0, 30, 0, Color.YELLOW, 0.0));

        // planets: name, semiMajorAxis (pixels), eccentricity, orbitalPeriod(days),
        // visualRadius(px), rotationPeriod(days), baseColor, textureSeed
        bodies.add(new Body("Mercury", 60, 0.205, 88, 4, 58.6, new Color(0x9e9e9e), 1.0));
        bodies.add(new Body("Venus", 90, 0.0067, 225, 7, -243, new Color(0xffd699), 2.0));
        bodies.add(new Body("Earth", 120, 0.0167, 365, 8, 1.0, new Color(0x2a8bd6), 3.0));
        bodies.add(new Body("Mars", 150, 0.0934, 687, 6, 1.03, new Color(0xff7043), 4.0));
        bodies.add(new Body("Jupiter", 210, 0.0484, 4333, 14, 0.41, new Color(0xffcc80), 5.0));
        bodies.add(new Body("Saturn", 270, 0.0539, 10759, 12, 0.44, new Color(0xfff3b0), 6.0));
        bodies.add(new Body("Uranus", 330, 0.0472, 30687, 10, -0.72, new Color(0x80deea), 7.0));
        bodies.add(new Body("Neptune", 390, 0.0086, 60190, 10, 0.67, new Color(0x8c9eff), 8.0));

        // create textures (procedural) for each body
        for (Body b : bodies)
            b.generateTexture();

        // timer
        timer = new Timer(1000 / fps, e -> {
            if (running) {
                double dt = 1.0 / fps; // seconds
                simDays += dt * dayPerSecond;
                for (Body b : bodies)
                    b.update(simDays);
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
                if (SwingUtilities.isRightMouseButton(e))
                    resetView();
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
                    case KeyEvent.VK_T -> showTrails = !showTrails;
                    case KeyEvent.VK_R -> resetView();
                    case KeyEvent.VK_PLUS, KeyEvent.VK_EQUALS -> dayPerSecond *= 2.0;
                    case KeyEvent.VK_MINUS -> dayPerSecond /= 2.0;
                }
            }
        });

        // UI components: simple slider for time scaling and a checkbox for trails
        JSlider speedSlider = new JSlider(JSlider.HORIZONTAL, 1, 240, (int) dayPerSecond);
        speedSlider.setToolTipText("Days per second");
        speedSlider.addChangeListener(ev -> dayPerSecond = Math.max(1.0, speedSlider.getValue()));

        JCheckBox trailsCheckbox = new JCheckBox("Show Trails", showTrails);
        trailsCheckbox.addActionListener(ev -> showTrails = trailsCheckbox.isSelected());

        JPanel controls = new JPanel();
        controls.setOpaque(false);
        controls.add(new JLabel("Time scale (days/sec):"));
        controls.add(speedSlider);
        controls.add(trailsCheckbox);

        setLayout(new BorderLayout());
        add(controls, BorderLayout.SOUTH);

        setToolTipText(
                "Space: pause/resume | +/-: change time scale | T: trails | Drag: pan | Wheel: zoom | R/right-click: reset");
    }

    public void start() {
        timer.start();
    }

    private void onWheel(MouseWheelEvent e) {
        double notches = e.getPreciseWheelRotation();
        double factor = Math.pow(1.12, -notches);
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
        simDays = 0.0;
        dayPerSecond = 60.0;
        for (Body b : bodies)
            b.clearTrail();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int cx = getWidth() / 2;
        int cy = getHeight() / 2;

        AffineTransform at = new AffineTransform();
        at.translate(cx, cy);
        at.scale(zoom, zoom);
        at.translate(offsetX, offsetY);
        g2.setTransform(at);

        // draw orbits
        g2.setStroke(new BasicStroke((float) (1.0 / Math.max(0.0001, zoom))));
        for (Body b : bodies) {
            if (b.isSun())
                continue;
            g2.setColor(new Color(255, 255, 255, 30));
            double a = b.semiMajor;
            double bsemi = a * Math.sqrt(1 - b.ecc * b.ecc);
            g2.drawOval((int) (-(a)), (int) (-(bsemi)), (int) (2 * a), (int) (2 * bsemi));
        }

        // trails
        if (showTrails) {
            for (Body b : bodies) {
                if (b.isSun())
                    continue;
                b.drawTrail(g2);
            }
        }

        // draw bodies (textures)
        for (Body b : bodies)
            b.draw(g2);

        // HUD
        g2.setTransform(new AffineTransform());
        g2.setColor(Color.white);
        g2.drawString(String.format("Sim days: %.1f | Days/sec: %.1f | %s | Trails: %s", simDays, dayPerSecond,
                running ? "Running" : "Paused", showTrails ? "On" : "Off"), 10, 18);
        g2.dispose();
    }
}

class Body {
    final String name;
    final double semiMajor; // visual semi-major axis in pixels
    final double ecc; // eccentricity
    final double period; // days
    final double visualRadius; // pixels
    final double rotationPeriod; // days (positive prograde, negative retrograde)
    final Color baseColor;
    final double textureSeed;

    // dynamic
    double x, y; // world coords
    double rotationAngle = 0.0; // radians

    // trail
    private final java.util.List<Point.Double> trail = new ArrayList<>();
    private final int maxTrail = 800;

    // texture
    private BufferedImage texture;

    Body(String name, double semiMajor, double ecc, double period, double visualRadius, double rotationPeriod,
            Color baseColor, double textureSeed) {
        this.name = name;
        this.semiMajor = semiMajor;
        this.ecc = ecc;
        this.period = period;
        this.visualRadius = visualRadius;
        this.rotationPeriod = rotationPeriod;
        this.baseColor = baseColor;
        this.textureSeed = textureSeed;
    }

    boolean isSun() {
        return name.equals("Sun");
    }

    void generateTexture() {
        int size = Math.max(32, (int) visualRadius * 8); // texture resolution scales with radius
        texture = new BufferedImage(size, size, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = texture.createGraphics();
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // radial gradient base
        float[] dist = { 0.0f, 1.0f };
        Color inner = baseColor.brighter();
        Color outer = baseColor.darker().darker();
        RadialGradientPaint rg = new RadialGradientPaint(size / 2f, size / 2f, size / 2f, dist,
                new Color[] { inner, outer });
        g.setPaint(rg);
        g.fillRect(0, 0, size, size);

        // add noise speckles
        java.util.Random rnd = new java.util.Random((long) (textureSeed * 10007));
        int speckles = size * size / 80;
        for (int i = 0; i < speckles; i++) {
            int sx = rnd.nextInt(size);
            int sy = rnd.nextInt(size);
            float alpha = 0.35f * rnd.nextFloat();
            int rad = 1 + rnd.nextInt(Math.max(1, size / 30));
            g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
            g.setColor(new Color(Math.min(255, baseColor.getRed() + rnd.nextInt(60)),
                    Math.min(255, baseColor.getGreen() + rnd.nextInt(60)),
                    Math.min(255, baseColor.getBlue() + rnd.nextInt(60))));
            g.fillOval(sx - rad, sy - rad, rad * 2, rad * 2);
        }

        // subtle bands for gas giants
        if (name.equals("Jupiter") || name.equals("Saturn")) {
            for (int y = 0; y < size; y += 4) {
                int band = (int) (10 * Math.sin((y / (double) size) * Math.PI * 2 + textureSeed));
                g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.06f));
                g.setColor(new Color(200 - band, 180 - band, 160 - band));
                g.fillRect(0, y, size, 2);
            }
        }

        g.dispose();
    }

    void update(double simDays) {
        if (isSun()) {
            x = 0;
            y = 0;
            rotationAngle += 0.0;
            return;
        }

        // Mean anomaly M = 2pi * (t / period)
        double M = 2 * Math.PI * ((simDays % period) / period);
        // solve Kepler's equation for E (eccentric anomaly): E - e sin E = M
        double E = solveKepler(M, ecc);
        // true anomaly
        double cosE = Math.cos(E);
        double sinE = Math.sin(E);
        double sqrtOneMinusESq = Math.sqrt(1 - ecc * ecc);
        double trueAnom = Math.atan2(sqrtOneMinusESq * sinE, cosE - ecc);

        // distance r = a (1 - e cos E)
        double r = semiMajor * (1 - ecc * cosE);
        // position in orbital plane (focus at origin)
        x = r * Math.cos(trueAnom);
        y = r * Math.sin(trueAnom);

        // rotation of planet texture: rotationPeriod in days -> angular velocity
        if (rotationPeriod != 0)
            rotationAngle += (2 * Math.PI / Math.abs(rotationPeriod)) * (rotationPeriod > 0 ? 1 : -1) * (1.0); // per
                                                                                                               // sim
                                                                                                               // day,
                                                                                                               // but
                                                                                                               // we'll
                                                                                                               // scale
                                                                                                               // below
        // Note: rotationAngle will be scaled by simDays increment in panel loop; to
        // keep it tidy, we'll clamp later

        // append to trail
        trail.add(new Point.Double(x, y));
        if (trail.size() > maxTrail)
            trail.remove(0);
    }

    private double solveKepler(double M, double e) {
        double E = M;
        for (int i = 0; i < 30; i++) {
            double f = E - e * Math.sin(E) - M;
            double fp = 1 - e * Math.cos(E);
            double d = f / fp;
            E -= d;
            if (Math.abs(d) < 1e-6)
                break;
        }
        return E;
    }

    void draw(Graphics2D g2) {
        int r = (int) Math.max(1, visualRadius);
        int tx = (int) Math.round(x);
        int ty = (int) Math.round(y);

        // draw Sun glow
        if (isSun()) {
            Composite oldComp = g2.getComposite();
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.9f));
            int glow = (int) (visualRadius * 6);
            g2.setColor(new Color(255, 220, 120, 60));
            g2.fillOval(-glow, -glow, glow * 2, glow * 2);
            g2.setComposite(oldComp);
        }

        // draw textured planet: rotate texture according to rotationAngle and draw
        // clipped circular
        if (texture != null) {
            int size = texture.getWidth();
            // create rotated image
            BufferedImage rotated = new BufferedImage(size, size, BufferedImage.TYPE_INT_ARGB);
            Graphics2D rt = rotated.createGraphics();
            rt.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
            AffineTransform at = new AffineTransform();
            at.translate(size / 2.0, size / 2.0);
            // scale rotation by sim time speed so it's visually reasonable
            double visualRot = rotationAngle * 0.01; // small factor to keep rotation modest
            at.rotate(visualRot);
            at.translate(-size / 2.0, -size / 2.0);
            rt.drawImage(texture, at, null);
            rt.dispose();

            // draw circular clip
            Shape oldClip = g2.getClip();
            g2.setClip(new java.awt.geom.Ellipse2D.Double(tx - r, ty - r, r * 2, r * 2));
            // draw rotated texture scaled to planet radius
            g2.drawImage(rotated, tx - r, ty - r, r * 2, r * 2, null);
            g2.setClip(oldClip);
        } else {
            g2.setColor(baseColor);
            g2.fillOval(tx - r, ty - r, r * 2, r * 2);
        }

        // label
        if (!isSun()
                && (name.equals("Earth") || name.equals("Mars") || name.equals("Jupiter") || name.equals("Saturn"))) {
            g2.setColor(new Color(200, 200, 200, 220));
            g2.drawString(name, tx + r + 4, ty + r + 4);
        }
    }

    void drawTrail(Graphics2D g2) {
        if (trail.size() < 2)
            return;
        Stroke old = g2.getStroke();
        g2.setStroke(new BasicStroke(1f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
        for (int i = 1; i < trail.size(); i++) {
            Point.Double a = trail.get(i - 1);
            Point.Double b = trail.get(i);
            float alpha = (float) i / (float) trail.size();
            Color c = new Color(baseColor.getRed(), baseColor.getGreen(), baseColor.getBlue(), (int) (alpha * 200));
            g2.setColor(c);
            g2.drawLine((int) a.x, (int) a.y, (int) b.x, (int) b.y);
        }
        g2.setStroke(old);
    }

    void clearTrail() {
        trail.clear();
    }
}
