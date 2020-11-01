import java.util.ArrayList;
import java.util.Collections;

public class LiuChen {
    
    /*

    */
    public static ArrayList<Point> convexHull(ArrayList<Point> points) {

        // Extreme points, ordered clockwise, 
        // beginning at the lowest leftmost point
        ArrayList<Point> M = extremePoints(points);

        // The (to be) complete hull, ordered clockwise, 
        // beginning at the lowest leftmost point
        ArrayList<Point> hull = new ArrayList<Point>();


        // From leftTop to topLeft
        ArrayList<Point> NW = partialHull(M.get(0), M.get(1), points);
        hull.addAll(NW);


        return hull;
    }

    /*
        Returns ordered partial hull, starting at `m1` and ending at `m2`
    */
    public static ArrayList<Point> partialHull(Point m1, Point m2, ArrayList<Point> points) {

        ArrayList<Point> hull = new ArrayList<Point>();
        hull.add(m1);

        if(m1.equals(m2)) {
            return hull;
        }

        // r = hull.size()
        // 
        for(int i = 0; i < points.size(); i++) {
            if(isCandidate(i, m1, m2, points) == true) {
                // point v is a candidate
                Point v = points.get(i);


            }
        }


        hull.add(m2);
        return hull;
    }

    /*
        Function to check if V (points[v_index]) is 
        a candidate for further consideration

        TODO: razdeli tocke v 4 regije, 
        odstrani prvi pogoj, S(m1, m2, v)...
        Ta pogoj bo vedno zadoscen, ce upostevamo
        samo tocke, ki so v isti regiji kot m1 in m2
    */
    public static boolean isCandidate(int v_index, Point m1, Point m2, ArrayList<Point> points) {
        Point v = points.get(v_index);
        Point next = points.get( (v_index + 1) % points.size() );

        int prev_index = v_index - 1;
        if(prev_index < 0) 
            prev_index += points.size();

        Point prev = points.get(prev_index);

        return (Util.orientation(m1, m2, v) < 0) 
            && (Util.orientation(prev, v, next) > 0);
    }


    /*
        Returns the 8 extreme points
    */
    public static ArrayList<Point> extremePoints(ArrayList<Point> points) {

        int minX = points.get(0).x;
        int minY = points.get(0).y;
        int maxX = points.get(0).x;
        int maxY = points.get(0).y;

        Point leftTop  = points.get(0);
        Point leftBot  = points.get(0);
        Point rightTop = points.get(0);
        Point rightBot = points.get(0);
        Point topLeft  = points.get(0);
        Point topRight = points.get(0);
        Point botLeft  = points.get(0);
        Point botRight = points.get(0);

        for(int i = 1; i < points.size(); i++) {
            Point pi = points.get(i);

            // left
            if(pi.x < minX) {
                minX = pi.x;
                leftTop = pi;
                leftBot = pi;
            }
            else if(pi.x == minX) {
                if(pi.y > leftTop.y)
                    leftTop = pi;
                else if(pi.y < leftBot.y)
                    leftBot = pi;
            }

            // right
            else if(pi.x > maxX) {
                maxX = pi.x;
                rightTop = pi;
                rightBot = pi;
            }
            else if(pi.x == maxX) {
                if(pi.y > rightTop.y)
                    rightTop = pi;
                else if(pi.y < rightBot.y) 
                    rightBot = pi;
            }

            // bottom
            if(pi.y < minY) {
                minY = pi.y;
                botLeft = pi;
                botRight = pi;
            }
            else if(pi.y == minY) {
                if(pi.x > botRight.x)
                    botRight = pi;
                else if(pi.x < botLeft.x)
                    botLeft = pi;
            }
            
            // top
            else if(pi.y > maxY) {
                maxY = pi.y;
                topLeft = pi;
                topRight = pi;
            }
            else if(pi.y == maxY) {
                if(pi.x > topRight.x)
                    topRight = pi;
                else if(pi.x < topLeft.x)
                    topLeft = pi;
            }
        }  // for pi in points


        ArrayList<Point> extreme = new ArrayList<Point>(8);
        extreme.add(leftBot);
        extreme.add(leftTop);
        extreme.add(topLeft);
        extreme.add(topRight);
        extreme.add(rightTop);
        extreme.add(rightBot);
        extreme.add(botRight);
        extreme.add(botLeft);

        return extreme;
    }


    public static void main(String[] args) {

        Point[] points0 = Testing.testSet8();
        ArrayList<Point> points = new ArrayList<Point>(points0.length);
        Collections.addAll(points, points0);

        //points.forEach(System.out::print);

        ArrayList<Point> extreme = extremePoints(points);
        new Plotting(points, extreme, true, 0);
    }
}
