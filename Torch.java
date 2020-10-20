import java.util.Comparator;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Collections;


public class Torch {

    public static Point[] convexHull(Point[] points) {

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

        // Sort by x coordinate
        Arrays.sort(points, xComparator);

        //Util.printSet(points);

        // After sorting by x coordinate, leftmost and rightmost 
        // points are the first and last elements of the array
        int leftmost  = 0;
        int rightmost = points.length - 1;

        // Find lowest and highest points
        int lowest  = 0;
        int highest = 0;

        for(int i = 1; i < points.length; i++) {
            if(points[i].y < points[lowest].y)
                lowest = i;
            if(points[i].y > points[highest].y)
                highest = i;
        }

        // Find the southwest (bottom left) hull
        // Start at leftmost, finish at lowest point
        int min = points[0].y;
        ArrayList<Point> HSW = new ArrayList<Point>();
        HSW.add(points[0]);

        for(int i = 1; i <= lowest; i++) {
            if(points[i].y <= min) {
                min = points[i].y;
                HSW.add(points[i]);
            }
        } // for

        // Find the southeast (bottom right) hull
        // Start at rightmost point, finish at lowest
        min = points[rightmost].y;
        ArrayList<Point> HSE = new ArrayList<Point>();
        HSE.add(points[rightmost]);

        for(int i = rightmost - 1; i > lowest; i--) {
            if(points[i].y <= min) {
                min = points[i].y;
                HSE.add(points[i]);
            }
        } // for


        // Find the northwest (top left) hull
        // Start at the leftmost, finish at highest point
        int max = points[0].y;
        ArrayList<Point> HNW = new ArrayList<Point>();

        for(int i = 1; i <= highest; i++) {
            if(points[i].y >= max) {
                max = points[i].y;
                HNW.add(points[i]);
            }
        } // for
        
        // Find the northeast (top right) hull
        // Start at leftmost, finish at highest point
        max = points[rightmost].y;
        ArrayList<Point> HNE = new ArrayList<Point>();

        for(int i = rightmost - 1; i > highest; i--) {
            if(points[i].y >= max) {
                max = points[i].y;
                HNE.add(points[i]);
            }
        } // for


        // Join all 4 lateral hulls to construct 
        // an approximate hull, A
        ArrayList<Point> A = new ArrayList<Point>();
        A.addAll(HSW);
        // TODO: uporabi LinkedList za HSE, HNW 
        // in jim dodajaj elemente spredaj
        Collections.reverse(HSE);
        A.addAll(HSE);
        A.addAll(HNE);
        Collections.reverse(HNW);
        A.addAll(HNW);


        ArrayList<Point> hull = new ArrayList<Point>();

        // Inflate the approximate hull
        int backtrack = 0;
        int n = A.size();
        if(n >= 3) {
            for(int i = 0; i < n; i++) {
                Point a = A.get((i - backtrack) % n);
                Point b = A.get((i + 1) % n);
                Point c = A.get((i + 2) % n);

                int det = (b.x - a.x) * (c.y - a.y) - (b.y - a.y) * (c.x - a.x);
                //int det = -Util.orientation(a, b, c);

                if(det <= 0) {
                    backtrack++;
                }
                else {
                    backtrack = 0;
                    hull.add(b);
                }
            }
        }

        return hull.toArray(new Point[hull.size()]);
    }
    

    public static void main(String[] args) {

        //Point[] points = Testing.testSet7();
        //Point[] pts = new Point[1];
        //pts[0] = new Point(0, 10);
        //new Plotting(points, pts, false, 0);

    }
    
}
