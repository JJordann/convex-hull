import java.util.ArrayList;
import java.util.Arrays;


public class Quickhull {

    public static ArrayList<Point> hull = new ArrayList<Point>();
    
    public static void findHull(ArrayList<Point> points, Point p, Point q) {

        if(points == null || points.size() == 0) 
            return;

        int farthestIndex = -1;
        int max_distance = 0;

        // Find the farthest point from the line pq
        for(int i = 0; i < points.size(); i++) {
            int dist = points.get(i).distanceFromLine(p, q);
            if(dist > max_distance) {
                   farthestIndex = i;
                   max_distance = dist;
               }
        }

        if(farthestIndex == -1)
           return;

        Point farthest = points.get(farthestIndex);

        ArrayList<Point> U = new ArrayList<Point>();
        ArrayList<Point> L = new ArrayList<Point>();

        /*  Split the set of points into 3 regions, {S, U, L},
            depending on their position relative to the farthest point, r.
            Points in S are discarded
            p
            |\
            | \   L
            |  \  
            | S r
            |  /
            | /   U
            |/
            q 
        */
        for(int i = 0; i < points.size(); i++) {
            Point pt = points.get(i);
            if(Util.orientation(p, farthest, pt) < 0)
                L.add(pt);
            else if(Util.orientation(farthest, q, pt) < 0)
                U.add(pt);
        }

        // Recur for both regions separately
        // (p = lowest, q = highest)
        findHull(L, p, farthest); 

        // Farthest point is added to the hull
        // Add the point between recursive calls to preserve order
        hull.add(farthest);

        findHull(U, farthest, q);

    } // convexHull


    public static Point[] convexHull(Point[] points0) {
        ArrayList<Point> points = new ArrayList<Point>(Arrays.asList(points0));
        hull = new ArrayList<Point>();

        int lowest  = 0;
        int highest = 0;

        // Find lowest and highest point of the set
        for(int i = 1; i < points.size(); i++) {
            if(points.get(i).y < points.get(lowest).y) {
                lowest = i;
            }
            if(points.get(i).y > points.get(highest).y) {
                highest = i;
            }
        }

        Point pLowest  = points.get(lowest);
        Point pHighest = points.get(highest);


        // Split the set of points into {left, right}, 
        // based on their position relative to the line (pLowest, pHighest)
        ArrayList<Point> left  = new ArrayList<Point>();
        ArrayList<Point> right = new ArrayList<Point>();

        for(int i = 0; i < points.size(); i++) {
            Point pt = points.get(i);
            float dir = Util.direction(pLowest, pHighest, pt);
            if(!pt.equals(pHighest) && !pt.equals(pLowest)) {
                if(dir > 0.0) {
                    left.add(pt);
                }
                else if(dir < 0.0) {
                    right.add(pt);
                }
            }
        }

        // Begin recursion for both sides separately
        // Initial extreme points are also added to the hull
        hull.add(pLowest);
        findHull(left,  pLowest, pHighest);
        hull.add(pHighest);
        findHull(right, pHighest, pLowest);

        return hull.toArray(new Point[hull.size()]);
    }

} // Quickhull
