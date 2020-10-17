import java.util.HashSet;
import java.util.ArrayList;
import java.util.Arrays;


// Ima tezave s kolinearnimi tockami
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
                if(!points.get(i).equals(p) && !points.get(i).equals(q) && !points.get(i).equals(farthest)
                 && points.size() > 3 && points.get(i).isInTriangle(p, q, farthest)) {                    
                    points.remove(i);
                    i--;
                }
            }
        }

        int angle1 = -Util.orientation(farthest, p, q);
        int angle2 = -Util.orientation(farthest, q, p);

        if(points != null && points.size() >= 2 && farthest != null) {
            findHull(points, farthest, p, angle1);
            findHull(points, farthest, q, angle2);
        }

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

        Point pLeftmost  = points.get(leftmost);
        Point pRightmost = points.get(rightmost);

        findHull(points, pLeftmost, pRightmost,  1);
        findHull(points, pLeftmost, pRightmost, -1);

        return hull.toArray(new Point[hull.size()]);
    }

}
