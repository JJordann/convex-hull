import java.util.ArrayList;
import java.util.Collections;


public class Bhattacharya {
    
    public static ArrayList<Point> hull = new ArrayList<Point>();

    public static ArrayList<Point> convexHull(ArrayList<Point> points, Point leftmost, Point rightmost) {

        // If only one points lies above, return it
        if(points.size() == 1) {
            hull.add(points.get(0));
            return points;
        }

        // Select a random pair of points
        int j = 0;

        // Find 2 points p, q, such that p, q, leftmost, 
        // rightmost form a convex quadrilateral
        boolean foundPoints = false;
        Point p = null;
        Point q = null;
        while(foundPoints == false) {

            if(points.size() == 1) {
                //hull.add(points.get(0));
                return points;
            }

            foundPoints = true;

            p = points.get(0);
            q = points.get(1);

            // If p lies inside the (leftmost, q, rightmost) triangle, delete it
            if(p.isInTriangle(leftmost, q, rightmost)) {
                points.remove(2 * j);
                foundPoints = false;
            }

            // If q lies inside the (leftmost, p, rightmost) triangle, delete it
            if(q.isInTriangle(leftmost, p, rightmost)) {
                points.remove(2 * j + 1);
                foundPoints = false;
            }
        }

        Point pm = supportingPoint(points, Util.slope(p, q));

        ArrayList<Point> left  = new ArrayList<Point>();
        ArrayList<Point> right = new ArrayList<Point>();

        for(j = 0; 2 * j < points.size(); j++) {
            p = points.get(2 * j);
            q = points.get(2 * j + 1);

            // assert p is left of q
            if(p.x > q.x) {
                Point tmp = p;
                p = q;
                q = tmp;
            }

            // Case 1
            if(q.x < pm.x) {
                if(Util.orientation(pm, q, p) < 0) {
                    left.add(p);
                    left.add(q);
                }
                else {
                    left.add(p);
                }
            } 

            // Case 2
            else if(pm.x < p.x) {
                if(Util.orientation(pm, p, q) < 0) {
                    right.add(q);
                }
                else {
                    right.add(p);
                    right.add(q);
                }
            }

            // Case 3
            else if(p.x < pm.x && pm.x < q.x) {
                left.add(p);
                right.add(q);
            }
            else {
                System.out.println("aaa");
            }

        } // for pairs...

        ArrayList<Point> aa = new ArrayList<Point>();
        aa.add(pm);
        new Plotting(points, aa, false, 500);

        new Plotting(points, left, false, 0);
        new Plotting(points, right, false, 250);


        // Eliminate points from `left` which lie below (leftmost, pm)

        for(int i = 0; i < left.size(); i++) {
            if(Util.orientation(leftmost, pm, left.get(i)) >= 0) {
                left.remove(i);
            }
        }

        // Eliminate points from `right` which lie below (pm, rightmost)
        
        for(int i = 0; i < right.size(); i++) {
            if(Util.orientation(pm, rightmost, right.get(i)) >= 0) {
                right.remove(i);
            }
        }


        if(left.size() > 0) 
            convexHull(left, leftmost, pm);


        hull.add(pm);

        if(right.size() > 0)
            convexHull(right, pm, rightmost);



        //new Plotting(points, above, false, 0);
        //new Plotting(points, below, false, 250);

        return null;
    }


    /*

    */
    public static Point supportingPoint(ArrayList<Point> points, double slope) {
        int max = 0;
        double max_val = points.get(max).y - slope * points.get(max).x;

        for(int i = 1; i < points.size(); i++) {
            double current_val = points.get(i).y - slope * points.get(i).x;

            if(current_val > max_val) {
                max_val = current_val;
                max = i;
            }
        }

        return points.get(max);
    }


    public static void main(String[] args) {

        Point[] points0 = Testing.testSet8();
        ArrayList<Point> points = new ArrayList<Point>(points0.length);
        Collections.addAll(points, points0);

        //points.forEach(System.out::print);

        // Find leftmost and rightmost points
        Point leftmost  = points.get(0);
        Point rightmost = points.get(0);

        for(int i = 1; i < points.size(); i++) {
            Point p = points.get(i);

            if(p.x < leftmost.x)
                leftmost = p;
            else if(p.x > rightmost.x)
                rightmost = p;
        }


        // Divide the point set into two, by the (leftmost, rightmost) line.
        // Points on the dividing line are not added, including the endpoints
        ArrayList<Point> above = new ArrayList<Point>();
        ArrayList<Point> below = new ArrayList<Point>();
        for(int i = 0; i < points.size(); i++) {
            Point p = points.get(i);
            int angle = Util.orientation(leftmost, rightmost, p);
            if(angle < 0)
                above.add(p);
            else if(angle > 0)
                below.add(p);
        }

        hull = new ArrayList<Point>();
        convexHull(points, leftmost, rightmost);
        new Plotting(points, hull, true, 750);

    }
}
