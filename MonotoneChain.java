import java.util.Comparator;
import java.util.Stack;
import java.util.Arrays;

public class MonotoneChain {
    
    public static Point[] convexHull(Point[] points) {

        Comparator<Point> xComparator = new Comparator<Point>() {
            public int compare(Point p, Point q) {
                if(p.x == q.x)
                    return p.y - q.y;
                else
                    return p.x - q.x;
            }
        };

        // Sort points by x coordinate
        Arrays.sort(points, xComparator);


        Stack<Point> S = new Stack<Point>();
        S.add(points[0]);
        S.add(points[1]);


        // Use a 1-way Graham scan to construct the upper hull,
        // starting at leftmost and ending at rightmost point
        for(int i = 0; i < points.length; i++) {
            while(S.size() >= 2 && Util.orientation(Util.getSecond(S), S.peek(), points[i]) <= 0) {
                S.pop();
            }
            S.push(points[i]);
        }

        int upperHullSize = S.size() + 1;

        // Reverse the search order and construct the lower hull
        for(int i = points.length - 1; i > 0; i--) {
            while(S.size() >= upperHullSize && Util.orientation(Util.getSecond(S), S.peek(), points[i - 1]) <= 0) {
                S.pop();
            }
            S.push(points[i - 1]);
        }

        return S.toArray(new Point[S.size()]);
    }

}
