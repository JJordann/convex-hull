import java.util.*;

public class DivideAndConquer {

    public static Point[] convexHull(Point[] points) {

        if(points.length <= 5) 
            return Jarvis.convexHull(points);

        Comparator<Point> xComparator = new Comparator<Point>() {
            public int compare(Point p, Point q) {
                if(p.x == q.x) {
                    return p.y - q.y;
                }
                else {
                    return p.x - q.x;
                }
            }
        };

        Arrays.sort(points, xComparator);
        
        // Divide points into 2 groups by x coordinate
        Point[] leftPoints  = Arrays.copyOfRange(points, 0, points.length / 2);
        Point[] rightPoints = Arrays.copyOfRange(points, points.length / 2, points.length);

        // Compute both hulls
        Point[] leftHull  = convexHull(leftPoints);
        Point[] rightHull = convexHull(rightPoints);

        // Merge both hulls into one
        return Util.merge(leftHull, rightHull);
    } // convexHull



} // DivideAndConquer
