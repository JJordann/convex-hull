import java.util.*;

public class Util {
    
    
    public static int orientation(Point p, Point q, Point r) {
        int d = (q.y - p.y) * (r.x - q.x) - 
                (q.x - p.x) * (r.y - q.y);

        if(d > 0) {
            return 1; // clockwise
        }
        else if (d < 0) {
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
