import java.util.Comparator;
import java.util.Stack;
import java.util.PriorityQueue;
import java.util.ArrayList;

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


    public static boolean below(Point p, Point a, Point b) {
        // true if a is below b
        return Util.orientation(p, a, b) < 0;
    }


    public static boolean above(Point p, Point a, Point b) {
        // true if a is above b
        return Util.orientation(p, a, b) > 0;
    }


    public static Point nextPoint(ArrayList<Point> points, Point p) {
        for(int i = 0; i < points.size(); i++) {
            if(points.get(i).equals(p)) {
                int nextIndex = (i + 1) % points.size();
                return points.get(nextIndex);
            }
        }
        return null;
    }

    public static int rightTangent(ArrayList<Point> points, Point p) {

        int n = points.size();

        int left  = 0;
        int right = n;

        int left_prev = orientation(p, points.get(0), points.get(n - 1));
        int left_next = orientation(p, points.get(0), points.get((left + 1) % n));

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
        return left;
    } // rightTangent

    public static int rightTangentBruteForce(Point[] points, Point p) {

        if(points.length <= 1) {
            return 0;
        }

        int left  = 0;
        int right = 0; 

        float next;
        float prev = isLeft(points[0], points[1], p);

        for(int i = 1; i < points.length; i++) {
            next = isLeft(points[i], points[ (i + 1) % points.length ], p);
            if( (prev <= 0) && (next > 0) ) {
                right = i;
            }
            else if( (prev > 0) && (next <= 0) ) {
                if( !above(p, points[i], points[left]) ) {
                    left = i;
                }
            }

            prev = next;

        }

        return right;
    }

    // linear search for tangents
    public static ArrayList<Point> convexTangents(ArrayList<Point> points, Point p) {

        if(points.size() <= 2) {
            return points;
        }

        int left  = 0;
        int right = 0; 

        float next;
        float prev = isLeft(points.get(0), points.get(1), p);

        for(int i = 1; i < points.size(); i++) {
            next = isLeft(points.get(i), points.get( (i + 1) % points.size() ), p);
            if( (prev <= 0) && (next > 0) ) {
                right = i;
            }
            else if( (prev > 0) && (next <= 0) ) {
                if( !above(p, points.get(i), points.get(left)) ) {
                    left = i;
                }
            }

            prev = next;

        }

        ArrayList<Point> tangents = new ArrayList<Point>(2);
        //tangents.add(points.get(left));
        tangents.add(points.get(right));
        return tangents;
    }


    public static float isLeft(Point a, Point b, Point c) {
        return (b.x - a.x) * (c.y - a.y) - (c.x - a.x) * (b.y - a.y);
    }


    public static Point[] fastMerge(Point[] left, Point[] right) {
        return null;
    }

    public static Point flipRight1(Point p) {
        return new Point(-p.x, p.y);
    }

    public static ArrayList<Point> flipRight(final ArrayList<Point> points) {
        ArrayList<Point> flipped = new ArrayList<Point>();

        for(int i = 0; i < points.size(); i++) {
            Point p = points.get(i);
            flipped.add(new Point(-p.x, p.y));
        }

        return flipped;
    }

    public static ArrayList<Point> flipDown(final ArrayList<Point> points) {
        ArrayList<Point> flipped = new ArrayList<Point>();

        for(int i = 0; i < points.size(); i++) {
            Point p = points.get(i);
            flipped.add(new Point(p.x, -p.y));
        }

        return flipped;
    }

    public static ArrayList<Point> flipDiagonal(final ArrayList<Point> points) {
        ArrayList<Point> flipped = new ArrayList<Point>();

        for(int i = 0; i < points.size(); i++) {
            Point p = points.get(i);
            flipped.add(new Point(-p.x, -p.y));
        }

        return flipped;
    }

}
