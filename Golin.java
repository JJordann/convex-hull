import java.util.ArrayList;
import java.lang.Math;
import java.util.Collections;


public class Golin {

    public static ArrayList<Point> eliminate(ArrayList<Point> points) {

        // Find 4 points that minimize f(x, y) = +-x +-y
        int SW = 0; // bottom left
        int NE = 0; // top right
        int SE = 0; // bottom right
        int NW = 0; // top left

        // current minimums
        int valSW = points.get(0).x + points.get(0).y;
        int valNE = points.get(0).x - points.get(0).y;
        int valSE = valSW;
        int valNW = valNE;

        for(int i = 1; i < points.size(); i++) {
            Point p = points.get(i);

            if(p.x + p.y < valSW) {
                SW = i;
                valSW = p.y + p.x;
            }
            else if(p.x + p.y > valNW) {
                NW = i;
                valNW = p.x + p.y;
            }
            if(p.x - p.y < valNE) {
                NE = i;
                valNE = p.x - p.y;
            }
            else if(p.x - p.y > valSE) {
                SE = i;
                valSE = p.x - p.y;
            }
        }

        // Find the largest axis-aligned rectangle
        // inside the (SW, NE, SE, NW) quadrilateral 
        int lowX  = Math.max(points.get(SW).x, points.get(NE).x);
        int lowY  = Math.max(points.get(SW).y, points.get(SE).y);
        int highX = Math.min(points.get(SE).x, points.get(NW).x);
        int highY = Math.min(points.get(NE).y, points.get(NW).y);

        // Number of residual points should, on average, be around sqrt(N),
        // overestimate slightly to avoid reallocation
        int sizeEstimate = (int) Math.ceil(1.5 * Math.sqrt(points.size()));
        ArrayList<Point> residual = new ArrayList<Point>(sizeEstimate);

        // Discard points inside the rectangle
        // formed by lowX, lowY, highX, highY
        for(int i = 0; i < points.size(); i++) {
            Point p = points.get(i);
            if( ! (lowX < p.x && p.x < highX && lowY < p.y && p.y < highY) ) {
                residual.add(p);
            }
        }

        return residual;
    }
    
    public static void main(String[] args) {
        Point[] points0 = Testing.testSet7();
        ArrayList<Point> points = new ArrayList<Point>();
        Collections.addAll(points, points0);
        ArrayList<Point> pruned = eliminate(points);

        Point[] pruned0 = new Point[pruned.size()];
        pruned0 = pruned.toArray(pruned0);
        new Plotting(points, pruned, false, 0);
        

    }
}
