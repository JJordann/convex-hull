import java.util.Comparator;
import java.util.Stack;
import java.util.PriorityQueue;
import java.util.ArrayList;
import java.lang.Math;

public class Util {

    public static Comparator<Point> xComparator = new Comparator<Point>() {
            public int compare(Point p, Point q) {
                if(p.x == q.x)
                    return p.y - q.y;
                else
                    return p.x - q.x;
            }
    };

    public static Comparator<Point> xComparatorReverse = new Comparator<Point>() {
            public int compare(Point p, Point q) {
                if(p.x == q.x)
                    return p.y - q.y;
                else
                    return q.x - p.x;
            }
        };

    
    public static float sign(Point p, Point q, Point r) {
        return (p.x - r.x) * (q.y - r.y) -
               (q.x - r.x) * (p.y - r.y);
    }

    public static double slope(Point p, Point q) {
        if(p.x == q.x) 
            return 0;
        else
            return (q.y - p.y) / (q.x - p.x);
    }
    
    public static int orientation(Point p, Point q, Point r) {
        int d1 = (q.y - p.y) * (r.x - q.x);
        int d2 = (q.x - p.x) * (r.y - q.y);

        if(d1 > d2) {
            return 1; // clockwise
        }
        else if (d1 < d2) {
            return -1; // counter-clockwise
        }
        else {
            return 0; // colinear
        }
    }

    // S > 0 => left
    // S < 0 => right
    public static float S(Point a, Point b, Point c) {
        return (b.x - a.x) * (c.y - a.y) - (c.x - a.x) * (b.y - a.y);
    }

    public static float direction(Point u, Point v, Point p) {
        return (v.x - u.x) * (p.y - u.y) - (v.y - u.y) * (p.x - u.x);
    }


    public static void printSet(Point s[]) {
        for(int i = 0; i < s.length; i++) {
            System.out.print(s[i].toString() + " "); 
        }
        System.out.println();
    }


    public static Point getSecond(Stack<Point> s) {
        Point top = s.pop();
        Point second = s.peek();
        s.push(top);
        return second;
    }

    public static void printQueue(PriorityQueue<Point> q) {
        q.forEach(System.out::print);
        System.out.println();
    }

    public static boolean isInsideQuadrilateral(Point p, Point left, Point right, Point low, Point high) {

        boolean inside = p.isInTriangle(left, high, right) 
                      || p.isInTriangle(left, low, right);

        boolean notAnEdge = !p.equals(left) 
                         && !p.equals(right) 
                         && !p.equals(low) 
                         && !p.equals(high);

        return inside && notAnEdge;
    }


    public static Point[] merge(Point[] leftHalf, Point[] rightHalf) {

        ArrayList<Point> merged = new ArrayList<Point>();

        if(leftHalf == null) {
            return Jarvis.convexHull(rightHalf);
        }

        if(rightHalf == null) {
            return Jarvis.convexHull(leftHalf);
        }

        int rightmostA = 0;
        int leftmostB  = 0;

        for(int i = 1; i < leftHalf.length; i++) {
            if(leftHalf[i].x > leftHalf[rightmostA].x) {
                rightmostA = i;
            }
        }

        for(int i = 1; i < rightHalf.length; i++) {
            if(rightHalf[i].x < rightHalf[leftmostB].x) {
                leftmostB = i;
            }
        }

        int currentA = rightmostA;
        int currentB = leftmostB;

        boolean done = false;
        while(done == false) {
            done = true;
            while(Util.orientation(rightHalf[currentB], leftHalf[currentA], 
                  leftHalf[(currentA + 1) % leftHalf.length]) >= 0) {
                      currentA = (currentA + 1) % leftHalf.length;
                  }
                  
            while(Util.orientation(leftHalf[currentA], rightHalf[currentB], 
                  rightHalf[(rightHalf.length + currentB - 1) % rightHalf.length]) <= 0) {
                      currentB = (currentB - 1 + rightHalf.length) % rightHalf.length;
                      done = false;
            }
        }

        int upperA = currentA;
        int upperB = currentB;

        currentA = rightmostA;
        currentB = leftmostB;
        
        done = false;
        while(done == false) {
            done = true;
            while(Util.orientation(leftHalf[currentA], rightHalf[currentB], 
                 rightHalf[(currentB + 1) % rightHalf.length]) >= 0) {
                    currentB = (currentB + 1) % rightHalf.length;
                 }

            while(Util.orientation(rightHalf[currentB], leftHalf[currentA], 
                 leftHalf[(leftHalf.length + currentA - 1) % leftHalf.length]) <= 0) {
                     currentA = (currentA - 1 + leftHalf.length) % leftHalf.length;
                     done = false;
                 }
        }

        int lowerA = currentA;
        int lowerB = currentB;

        for(int i = upperA; i != lowerA; i = (i + 1) % leftHalf.length) {
            merged.add(leftHalf[i]);
        }
        merged.add(leftHalf[lowerA]);

        for(int i = lowerB; i != upperB; i = (i + 1) % rightHalf.length) {
            merged.add(rightHalf[i]);
        }
        merged.add(rightHalf[upperB]);

        return merged.toArray(new Point[merged.size()]);
    }


    /*
    public static int rightTangent1(final ArrayList<Point> points, Point p) {

        int n = points.size();

        int left  = 0;
        int right = n;

        int left_prev = orientation(p, points.get(0), points.get(n - 1));
        int left_next = orientation(p, points.get(0), points.get((left + 1) % n));

        if(left_prev != 1 && left_next != 1)
            return left;

        while(left < right) {

            int mid = (left + right) / 2;
            int midprevIndex = mid - 1;
            if(midprevIndex < 0)
                midprevIndex += n;

            int mid_prev = orientation(p, points.get(mid), points.get(midprevIndex));
            int mid_next = orientation(p, points.get(mid), points.get((mid + 1) % n));
            int mid_side = orientation(p, points.get(left), points.get(mid));

            if(mid_prev != 1 && mid_next != 1) {
                return mid;
            }
            else if(mid_side == -1
                 && (left_next == 1 || left_prev == left_next)
                 || (mid_side == 1 && mid_prev == 1)) { 
                     right = mid;
                 }
            else {
                left = mid + 1; // modulo
            }

            left_prev = -mid_next;
            left_next = orientation(p, points.get(1), points.get((left + 1) % n));
        }
        return left % n;
    } // rightTangent
    */

    public static int rightTangentLinear(ArrayList<Point> points, Point p) {

        if(points.size() <= 1)
            return 0;

        int right = 0; 

        int prev = orientation(points.get(0), points.get(1), p);
        int next;

        for(int i = 1; i < points.size(); i++) {
            next = orientation(points.get(i), points.get( (i + 1) % points.size() ), p);
            if( (prev >= 0) && (next < 0) ) {
                right = i;
            }

            prev = next;

        }

        return right;
    }


    public static int rightTangent(ArrayList<Point> points, Point p) {

        // If points.size is small, use linear search
        if(points.size() <= 3)
            return rightTangentLinear(points, p);

        int n = points.size();
        int left = 0;
        int right = n;

        // Check if first point is the right tangent
        if(orientation(p, points.get(1), points.get(0)) > 0 
        && orientation(p, points.get(n - 1), points.get(0)) >= 0)
            return 0;

        do {
            int mid = (left + right) / 2;

            //points.forEach(System.out::print);
            //System.out.println(" | " + p + " | " + mid);
            int nextMid = orientation(p, points.get((mid + 1) % n), points.get(mid));

            int midPrev = mid - 1;
            if(midPrev < 0)
                midPrev += n;

            // Check if `mid` is the right tangent
            if(nextMid > 0 && orientation(p, points.get(midPrev), points.get(mid)) >= 0)
                return mid;


            int nextLeft = orientation(p, points.get((left + 1) % n), points.get(left));
            if(nextLeft < 0) {
                if(nextMid > 0)
                    right = mid; // interval := [left, mid]
                else {
                    if(orientation(p, points.get(left), points.get(mid)) < 0)
                        right = mid; // interval := [left, mid]
                    else 
                        left = mid; // interval := [mid, right]
                }
            } // if above
            else {
                if(nextMid <= 0)
                    left = mid;  
                else {
                    if(orientation(p, points.get(left), points.get(mid)) > 0)
                        right = mid;
                    else
                        left = mid;
                }
            }

        } while(left < right - 1); // while true

        return left;
    } // rightTangent


    public static Point[] fastMerge(Point[] left, Point[] right) {
        return null;
    }



    public static int randomInt(int min, int max) {
        return (int) ( (Math.random() * (max - min) ) + min);
    }


    public static Point rotate90(Point p) {
        return new Point(p.y, -p.x);
    }

    public static Point rotate180(Point p) {
        return new Point(-p.x, -p.y);
    }


    public static void printHull_r(Point[] h, int r) {
        for(int i = 0; i <= r; i++)
            System.out.print(h[i]);
        System.out.println();
    }

}
