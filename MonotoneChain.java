import java.util.Comparator;

import java.util.Arrays;

public class MonotoneChain {
    
    public static Point[] convexHull(Point[] points) {

        int hullSize = 0;
        Point[] hull = new Point[2 * points.length];

        Comparator<Point> xComparator = new Comparator<Point>() {
            public int compare(Point p, Point q) {
                if(p.x == q.x)
                    return p.y - q.y;
                else
                    return p.x - q.x;
            }
        };

        Arrays.sort(points, xComparator);

        for(int i = 0; i < points.length; i++) {
            while(hullSize >= 2 && Util.orientation(hull[hullSize - 2], hull[hullSize - 1], points[i]) <= 0) {
                hullSize--;
            }
            hull[hullSize++] = points[i];
        }

        for(int i = points.length - 1, upperHullSize = hullSize + 1; i > 0; i--) {
            while(hullSize >= upperHullSize && Util.orientation(hull[hullSize - 2], hull[hullSize - 1], points[i - 1]) <= 0) {
                hullSize--;
            }
            hull[hullSize++] = points[i - 1];
        }

        return Arrays.copyOf(hull, hullSize - 1);
    }

}
