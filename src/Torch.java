import java.util.Comparator;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;


/**
 * 
 *  Assuming no 3 points are collinear
 */
public class Torch {

    public static Stack<Point> convexHull(Point[] points) {

        // Calculate approximate hull
        ArrayList<Point> A = approximateHull(points);

        // Inflate the approximate hull
        Stack<Point> hull = new Stack<Point>();
        hull.push(A.get(0));
        hull.push(A.get(1));

        for(int i = 2; i < A.size(); i++) {
            while(hull.size() >= 2 && Util.orientation(Util.getSecond(hull), hull.peek(), A.get(i)) != -1)
                hull.pop();
            hull.push(A.get(i));
        }

        while(Util.orientation(Util.getSecond(hull), hull.peek(), A.get(0)) != -1) {
            hull.pop();
        }

        
        return hull;
    } // convexHull


    /*
        Calculates approximate hull
        Result is a simple polygon that is not necessarily convex
    */
    public static ArrayList<Point> approximateHull(Point[] points) {

        // Sort by x coordinate
        Arrays.sort(points, Util.xComparatorFast);

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
        int min = points[leftmost].y;
        ArrayList<Point> HSW = new ArrayList<Point>();
        HSW.add(points[leftmost]);

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
        int max = points[leftmost].y;
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
        Collections.reverse(HSE);
        A.addAll(HSE);
        A.addAll(HNE);
        Collections.reverse(HNW);
        A.addAll(HNW);

        return A;
    }
    

    /* 
        Improved by merging 4 loops into 2. 
        Is slightly more efficient 
        than the original algorithm. 
    */ 
    public static Stack<Point> convexHullImproved(Point[] points) {

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

        // Find the left hull, start at leftmost,
        // finish at lowest and highest points
        int min = points[leftmost].y;
        int max = points[leftmost].y;
        ArrayList<Point> HSW = new ArrayList<Point>();
        ArrayList<Point> HNW = new ArrayList<Point>();
        HSW.add(points[leftmost]);

        int leftStop = Math.max(highest, lowest);
        for(int i = 1; i <= leftStop; i++) {
            if(points[i].y <= min) {
                min = points[i].y;
                HSW.add(points[i]);
            }
            if(points[i].y >= max) {
                max = points[i].y;
                HNW.add(points[i]);
            }
        } // for

        // Find the right hull, start at rightmost,
        // finish at lowest and highest points
        min = points[rightmost].y;
        max = points[rightmost].y;
        ArrayList<Point> HSE = new ArrayList<Point>();
        ArrayList<Point> HNE = new ArrayList<Point>();
        HSE.add(points[rightmost]);

        int rightStop = Math.min(highest, lowest);
        for(int i = rightmost - 1; i > rightStop; i--) {
            if(points[i].y <= min) {
                min = points[i].y;
                HSE.add(points[i]);
            }
            if(points[i].y >= max) {
                max = points[i].y;
                HNE.add(points[i]);
            }
        } // for


        // Join all 4 lateral hulls to construct 
        // an approximate hull
        Collections.reverse(HSE);
        HSW.addAll(HSE);
        HSW.addAll(HNE);
        Collections.reverse(HNW);
        HSW.addAll(HNW);


        Stack<Point> hull = new Stack<Point>();
        hull.push(HSW.get(0));
        hull.push(HSW.get(1));

        for(int i = 2; i < HSW.size(); i++) {
            while(hull.size() >= 2 && Util.orientation(Util.getSecond(hull), hull.peek(), HSW.get(i)) != -1)
                hull.pop();
            hull.push(HSW.get(i));
        }

        // Inflate the approximate hull
        while(Util.orientation(Util.getSecond(hull), hull.peek(), HSW.get(0)) != -1) {
            hull.pop();
        }

        return hull;
    } // convexHullImproved

} // Torch
