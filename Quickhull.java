import java.util.HashSet;
import java.util.ArrayList;
import java.util.Arrays;


public class Quickhull {

    public static HashSet<Point> hull = new HashSet<Point>();
    
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

        // Farthest point is added to the hull
        hull.add(farthest);

        ArrayList<Point> U = new ArrayList<Point>();
        ArrayList<Point> L = new ArrayList<Point>();

        /*  Split the set of points into 3 regions, {S, U, L},
            depending on their position relative to the farthest point, r.
            Points in S are discarded
            q
            |\
            | \   U
            |  \  
            | S r
            |  /
            | /   L
            |/
            p 
        */
        for(int i = 0; i < points.size(); i++) {
            Point pt = points.get(i);
            float dir = Util.direction(p, farthest, pt);
            if(!pt.isInTriangle2(p, q, farthest)) {
                if(dir > 0.0) {
                    L.add(pt);
                }
                else if (dir < 0.0) {
                    U.add(pt);
                }
            }
        }
        

        // Recur for both regions separately
        // (p = lowest, q = highest)
        findHull(L, p, farthest); 
        findHull(U, farthest, q);

    } // convexHull


    public static Point[] convexHull(Point[] points0) {
        ArrayList<Point> points = new ArrayList<Point>(Arrays.asList(points0));
        hull = new HashSet<Point>();

        int lowest  = 0;
        int highest = 0;

        // TODO: v primeru ==
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

        // Initial extreme points are added to the hull
        hull.add(pLowest);
        hull.add(pHighest);


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
        findHull(left,  pLowest, pHighest);
        findHull(right, pHighest, pLowest);

        return hull.toArray(new Point[hull.size()]);
    }

} // Quickhull
