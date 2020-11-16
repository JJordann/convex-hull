import java.util.PriorityQueue;
import java.util.Stack;


public class Improved {

    public static Point[] convexHull(Point[] points) {

        int leftmost = 0, rightmost = 0, lowest = 0, highest = 0;

        // Find extreme points
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


        // Discard points inside of the quadrilateral formed by connecting extreme points,
        // divide residual points into 4 priority queues based on their position 
        // relative to the quadrilateral
        /*
                   / \
         topLeft  /   \ topRight
                 /     \
                /discard\
                \       /
        botLeft  \     / botRight
                  \   /
                   \ /
        */
        PriorityQueue<Point> topLeft  = new PriorityQueue<Point>(points.length, Util.xComparatorReverse);
        PriorityQueue<Point> topRight = new PriorityQueue<Point>(points.length, Util.xComparatorReverse);
        PriorityQueue<Point> botLeft  = new PriorityQueue<Point>(points.length, Util.xComparator);
        PriorityQueue<Point> botRight = new PriorityQueue<Point>(points.length, Util.xComparator);

        //for(int i = 0; i < points.length; i++) {
            //if( ! Util.isInsideQuadrilateral(points[i], points[leftmost], points[rightmost], points[lowest], points[highest])) {

                //// TODO: dodaj else in ekstremne tocke vstavi loceno
                //if(points[i].x <= points[highest].x && points[i].y >= points[leftmost].y) {
                    //topLeft.add(points[i]);
                //}
                //else if(points[i].x >= points[highest].x && points[i].y >= points[rightmost].y) {
                    //topRight.add(points[i]);
                //}
                //else if(points[i].x <= points[lowest].x && points[i].y <= points[leftmost].y) {
                    //botLeft.add(points[i]);
                //}
                //else if(points[i].x >= points[lowest].x && points[i].y <= points[rightmost].y){
                    //botRight.add(points[i]);
                //}
            //} // if !inside
        //} // for

        Point pLeftmost  = points[leftmost];
        Point pRightmost = points[rightmost];
        Point pLowest    = points[lowest];
        Point pHighest   = points[highest];

        topLeft.add(pLeftmost);
        topRight.add(pHighest);
        botRight.add(pRightmost);
        botLeft.add(pLowest);

        for(int i = 0; i < points.length; i++) {
            if(Util.orientation(pLeftmost, pHighest, points[i]) < 0)
                topLeft.add(points[i]);
            else if(Util.orientation(pHighest, pRightmost, points[i]) < 0)
                topRight.add(points[i]);
            else if(Util.orientation(pRightmost, pLowest, points[i]) < 0)
                botRight.add(points[i]);
            else if(Util.orientation(pLowest, pLeftmost, points[i]) < 0)
                botLeft.add(points[i]);
            // else: point is inside the quadrilatral, discard it
        }

        topLeft.add(pHighest);
        topRight.add(pRightmost);
        botRight.add(pLowest);
        botLeft.add(pLeftmost);

        // Construct the hull using the monotone chain method,
        // one region at a time, moving in counter-clockwise direction
        Stack<Point> hull = new Stack<Point>();
        Point p = null;

        // From leftmost to lowest point
        // TODO: if != null
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
    } // convexHull
} // 
