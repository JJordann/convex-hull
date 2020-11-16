import java.util.*;

public class DivideAndConquer {

    public static Point[] convexHull(Point[] points) {

        if(points.length <= 5) 
            return Jarvis.convexHull(points);

        Arrays.sort(points, Util.xComparator);
        
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
