import java.util.PriorityQueue;
import java.util.Comparator;
import java.util.Stack;


public class Improved {

    public static Point[] convexHull(Point[] points) {

        int leftmost = 0, rightmost = 0, lowest = 0, highest = 0;

        for(int i = 1; i < points.length; i++) {

            if(points[i].x < points[leftmost].x)
                leftmost = i;

            if(points[i].x > points[rightmost].x)
                rightmost = i;

            if(points[i].y > points[highest].y)
                highest = i;

            if(points[i].y < points[lowest].y)
                lowest = i;
        }


        // TODO: 4 comparatorje, obravnavaj primer p.x == q.x
        Comparator<Point> maxComparator = new Comparator<Point>() {
            public int compare(Point p, Point q) {
                if(p.x == q.x)
                    return p.y - q.y;
                else
                    return q.x - p.x;
            }
        };

        Comparator<Point> minComparator = new Comparator<Point>() {
            public int compare(Point p, Point q) {
                if(p.x == q.x)
                    return p.y - q.y;
                else
                    return p.x - q.x;
            }
        };


        PriorityQueue<Point> topLeft  = new PriorityQueue<Point>(points.length, maxComparator);
        PriorityQueue<Point> topRight = new PriorityQueue<Point>(points.length, maxComparator);
        PriorityQueue<Point> botLeft  = new PriorityQueue<Point>(points.length, minComparator);
        PriorityQueue<Point> botRight = new PriorityQueue<Point>(points.length, minComparator);

        for(int i = 0; i < points.length; i++) {
            if( ! isInsideQuadrilateral(points[i], points[leftmost], points[rightmost], points[lowest], points[highest])) {

                // todo: dodaj else in ekstremne tocke vstavi loceno
                if(points[i].x <= points[highest].x && points[i].y >= points[leftmost].y) {
                    topLeft.add(points[i]);
                }
                if(points[i].x >= points[highest].x && points[i].y >= points[rightmost].y) {
                    topRight.add(points[i]);
                }
                if(points[i].x <= points[lowest].x && points[i].y <= points[leftmost].y) {
                    botLeft.add(points[i]);
                }
                if(points[i].x >= points[lowest].x && points[i].y <= points[rightmost].y){
                    botRight.add(points[i]);
                }
            }
            else {
                //System.out.println("Discarded: " + points[i]);
            }
        }


        Stack<Point> hull = new Stack<Point>();
        Point p = null;

        // From leftmost to lowest point
        hull.add(botLeft.poll());
        hull.add(botLeft.poll());
        while((p = botLeft.poll()) != null) {
            while(hull.size() >= 2 && Util.orientation(Util.getSecond(hull), hull.peek(), p) >= 0) {
                hull.pop();
            }
            if(p != null) 
                hull.add(p);
        }

        // From lowest to rightmost point
        while((p = botRight.poll()) != null) {
            while(hull.size() >= 2 && Util.orientation(Util.getSecond(hull), hull.peek(), p) >= 0) {
                hull.pop();
            }
            hull.add(p);
        }

        // From rightmost to highest point
        while((p = topRight.poll()) != null) {
            while(hull.size() >= 2 && Util.orientation(Util.getSecond(hull), hull.peek(), p) >= 0) {
                hull.pop();
            }
            hull.add(p);
        }

        // From highest to leftmost point
        while((p = topLeft.poll()) != null) {
            while(hull.size() >= 2 && Util.orientation(Util.getSecond(hull), hull.peek(), p) >= 0) {
                hull.pop();
            }
            hull.add(p);
        }

        Point[] ret = new Point[hull.size()];
        return hull.toArray(ret);
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


    public static void main(String[] args) {
        Point[] points = Testing.testSet4();
        Util.printSet(convexHull(points));
    }
    
}
