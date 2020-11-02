import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.util.ArrayList;
import java.util.Collections;


public class Plotting extends javax.swing.JFrame {

    static final long serialVersionUID = 12;

    public static int thickness = 7;
    public static int zoom = 10;
    public static int xMin = 10, yMin = 10, xMax, yMax;

    public static int margin = 100;
    
    public Plotting(Point[] points, Point[] hull, boolean connect, int leftMargin) {
        super("Plot");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        findBounds(points);

        JPanel panel = new JPanel() {
            static final long serialVersionUID = 1337;

            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);

                Graphics2D g2d = (Graphics2D) g.create();
                g.setColor(Color.BLACK);

                g2d.setPaint(Color.BLACK);
                for(int i = 0; i < points.length; i++) {
                    Point p = scale(points[i]);
                    //int x = (p.x + xMin) * zoom;
                    //int y = (p.y + yMin) * zoom;
                    Ellipse2D dot = new Ellipse2D.Float(p.x, p.y, thickness, thickness);
                    g2d.fill(dot);
                }

                if(hull != null) {
                    g2d.setPaint(Color.RED);
                    for(int i = 0; i < hull.length; i++) {
                        Point p = scale(hull[i]);
                        //int x = (p.x + xMin) * zoom;
                        //int y = (p.y + yMin) * zoom;
                        Ellipse2D dot = new Ellipse2D.Float(p.x, p.y, thickness + 1, thickness + 1);
                        g2d.fill(dot);

                        if(connect == true && i != 0) {
                            Point a = scale(hull[i - 1]);
                            Point b = scale(hull[i]);
                            g2d.drawLine(a.x + thickness / 2, a.y + thickness / 2, b.x + thickness / 2, b.y + thickness / 2);
                        }
                    }
                    if(connect == true) {
                        // draw last line
                        Point a = scale(hull[hull.length - 1]);
                        Point b = scale(hull[0]);
                        g2d.drawLine(a.x + thickness / 2, a.y + thickness / 2, b.x + thickness / 2, b.y + thickness / 2);
                    }
                }

                g2d.dispose();
            }
        };

        setContentPane(panel);
        setBounds(leftMargin + margin, margin, xMax + margin, yMax + margin);

        setVisible(true);

    }

    public Plotting(Point[] points, ArrayList<Point> hull, boolean connect, int leftMargin) {

        Point[] hull0 = new Point[hull.size()];
        hull0 = hull.toArray(hull0);

        new Plotting(points, hull0, connect, leftMargin);
    }

    public Plotting(ArrayList<Point> points, ArrayList<Point> hull, boolean connect, int leftMargin) {

        Point[] points0 = new Point[points.size()];
        points0 = points.toArray(points0);

        Point[] hull0 = new Point[hull.size()];
        hull0 = hull.toArray(hull0);

        new Plotting(points0, hull0, connect, leftMargin);
    }

    public static Point scale(Point p) {

        if(p == null) {
            int x = (1 + xMin) * zoom + thickness / 2;
            int y = (1 + yMin) * zoom + thickness / 2;
            return new Point(x, y);
        }

        int x = (p.x + xMin) * zoom + thickness / 2;
        int y = (-p.y + yMin) * zoom + thickness / 2;

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
