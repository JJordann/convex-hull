import java.util.*;

public class Util {
    
    public static float sign(Point p, Point q, Point r) {
        return (p.x - r.x) * (q.y - r.y) -
               (q.x - r.x) * (p.y - r.y);
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

}
