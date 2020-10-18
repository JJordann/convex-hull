import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.util.Iterator;


public class Plotting extends javax.swing.JFrame {

    public static int thickness = 4;
    public static int zoom = 10;
    public static int xMin = 10;
    public static int yMin = 10;
    
    public Plotting(Point[] points, Point[] hull, boolean connect) {
        super("Plotting");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

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

                        if(connect == true) {

                        }
                    }
                    if(connect == true) {
                        // draw last line

                    }
                }

                g2d.dispose();
            }
        };

        // todo: poisci bounds
        setContentPane(panel);
        setBounds(10, 10, 200, 200);

        setVisible(true);

    }

}
