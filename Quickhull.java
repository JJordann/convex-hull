import java.util.HashSet;
import java.util.ArrayList;
import java.util.Arrays;


// Ima tezave s kolinearnimi tockami
public class Quickhull {

    public static HashSet<Point> hull = new HashSet<Point>();
    
    public static void findHull(ArrayList<Point> points, Point p, Point q) {

        if(points == null || points.size() == 0) 
            return;

        int farthestIndex = -1;
        int max_distance = 0;

        for(int i = 0; i < points.size(); i++) {

            int dist = points.get(i).distanceFromLine(p, q);
            if(dist > max_distance) {
                   farthestIndex = i;
                   max_distance = dist;
               }
        }

        //Point[] ext = new Point[3];
        //ext[0] = p;
        //ext[1] = q;
        //ext[2] = points.get(farthestIndex);
        //new Plotting(points.toArray(new Point[points.size()]), ext, true, 0);


        if(farthestIndex == -1) {
           //hull.add(p);
           //hull.add(q);
           return;
        } 
        else {
            hull.add(points.get(farthestIndex));

            //Point[] ext = new Point[3];
            //ext[0] = p;
            //ext[1] = q;
            //ext[2] = points.get(farthestIndex);
            //new Plotting(points.toArray(new Point[points.size()]), ext, true, 0);
        }

        Point farthest = points.get(farthestIndex);

        // TODO: razdeli tocke v dve regiji !!!!!
        ArrayList<Point> U = new ArrayList<Point>();
        ArrayList<Point> L = new ArrayList<Point>();

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


        //new Plotting(points.toArray(new Point[points.size()]), U.toArray(new Point[U.size()]), false, 250);
        //new Plotting(points.toArray(new Point[points.size()]), L.toArray(new Point[L.size()]), false, 500);
        

        // Recur for both regions separately
        // (p = lowest, q = highest)

        findHull(L, p, farthest); 
        findHull(U, farthest, q);

        return;
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

        hull.add(pLowest);
        hull.add(pHighest);

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



       // Point[] ext = new Point[2];
       // ext[0] = points0[leftmost];
       // ext[1] = points0[rightmost];

       // new Plotting(points.toArray(new Point[hull.size()]), ext, true, 0);



        findHull(left,  pLowest, pHighest);
        findHull(right, pHighest, pLowest);

        return hull.toArray(new Point[hull.size()]);
    }


    public static void main(String[] args) {
        Point[] points = Testing.testSet5();
        Point[] hull = convexHull(points);

        new Plotting(points, hull, false, 1000);
    }

}
