import java.util.HashSet;
import java.util.ArrayList;
import java.util.Arrays;


public class Quickhull {

    public static HashSet<Point> hull = null;
    
    public static void findHull(ArrayList<Point> points, Point p, Point q, int side) {

        int farthestIndex = -1;
        int max_distance = 0;

        for(int i = 0; i < points.size(); i++) {

            int dist = points.get(i).distanceFromLine(p, q);
            if(Util.orientation(p, q, points.get(i)) == side && 
               dist > max_distance) {
                   farthestIndex = i;
                   max_distance = dist;
               }
        }

        if(farthestIndex == -1) {
            hull.add(p);
            hull.add(q);
            return;
        }

        Point farthest = points.get(farthestIndex);

        // odstrani tocke v trikotniku (p, q, farthest)
        if(points.size() > 3) {
            for(int i = 0; i < points.size(); i++) {                
                if(points.get(i) != p && points.get(i) != q && points.get(i) != farthest &&
                  points.get(i).isInTriangle(p, q, farthest)) {                    
                    points.remove(i);
                    i--;
                }
            }
        }

        int angle1 = -Util.orientation(farthest, p, q);
        int angle2 = -Util.orientation(farthest, q, p);

        findHull(points, farthest, p, angle1);
        findHull(points, farthest, q, angle2);

        return;
    } // convexHull


    public static Point[] convexHull(Point[] points0) {
        ArrayList<Point> points = new ArrayList<Point>(Arrays.asList(points0));
        hull = new HashSet<Point>();

        int leftmost  = 0;
        int rightmost = 0;

        // TODO: v primeru ==
        for(int i = 1; i < points.size(); i++) {
            if(points.get(i).x < points.get(leftmost).x) {
                leftmost = i;
            }
            if(points.get(i).x > points.get(rightmost).x) {
                rightmost = i;
            }
        }


        findHull(points, points.get(leftmost), points.get(rightmost),  1);
        findHull(points, points.get(leftmost), points.get(rightmost), -1);

        return hull.toArray(new Point[hull.size()]);
    }

}
