import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class Plotting extends javax.swing.JFrame {

    public static int thickness = 7;
    public static int zoom = 10;
    public static int xMin = 10, yMin = 10, xMax, yMax;

    public static int margin = 100;
    
    public Plotting(Point[] points, Point[] hull, boolean connect, int leftMargin) {
        super("Plot");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        findBounds(points);

        JPanel panel = new JPanel() {
            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);

                Graphics2D g2d = (Graphics2D) g.create();
                g.setColor(Color.BLACK);

                g2d.setPaint(Color.BLACK);
                for(int i = 0; i < points.length; i++) {
                    Point p = points[i];
                    int x = (p.x + xMin) * zoom;
                    int y = (p.y + yMin) * zoom;
                    Ellipse2D dot = new Ellipse2D.Float(x, y, thickness, thickness);
                    g2d.fill(dot);
                }

                if(hull != null) {
                    g2d.setPaint(Color.RED);
                    for(int i = 0; i < hull.length; i++) {
                        Point p = hull[i];
                        int x = (p.x + xMin) * zoom;
                        int y = (p.y + yMin) * zoom;
                        Ellipse2D dot = new Ellipse2D.Float(x, y, thickness + 1, thickness + 1);
                        g2d.fill(dot);

                        if(connect == true && i != 0) {
                            Point a = scale(hull[i - 1]);
                            Point b = scale(hull[i]);
                            g2d.drawLine(a.x, a.y, b.x, b.y);
                        }
                    }
                    if(connect == true) {
                        // draw last line
                        Point a = scale(hull[hull.length - 1]);
                        Point b = scale(hull[0]);
                        g2d.drawLine(a.x, a.y, b.x, b.y);
                    }
                }

                g2d.dispose();
            }
        };

        setContentPane(panel);
        setBounds(leftMargin + margin, margin, xMax + margin, yMax + margin);

        setVisible(true);

    }

    public static Point scale(Point p) {

        int x = (p.x + xMin) * zoom + thickness / 2;
        int y = (p.y + yMin) * zoom + thickness / 2;

        return new Point(x, y);
    }

    public static void findBounds(Point[] points) {

        for(int i = 0; i < points.length; i++) {
            Point p = scale(points[i]);
            if(p.x < xMin) 
                xMin = p.x;
            if(p.x > xMax) 
                xMax = p.x;
            if(p.y < yMin) 
                yMin = p.y;
            if(p.y > yMax)
                yMax = p.y;
        }
    } // findBounds

} // Plotting
