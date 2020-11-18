import java.util.ArrayList;
import java.util.List;
import java.util.LinkedList;
import java.util.Collections;

/*
  Jarvis march a.k.a Gift Wrapping
 (has troubles with colinear points)
*/
public class Jarvis {

    public static Point[] convexHull(Point[] points) {
        if (points.length < 3) {
            return points;
        }

        ArrayList<Point> hull = new ArrayList<Point>();

        // Find the lowest point
        int lowestIndex = 0;
        for(int i = 1; i < points.length; i++) {
            if(points[i].y < points[lowestIndex].y) {
                lowestIndex = i;
            }
            else if(points[i].y == points[lowestIndex].y) {
                if(points[i].x < points[lowestIndex].x) {
                    lowestIndex = i;
                }
            }
        }

        // Begin at the lowest point
        int currentPoint = lowestIndex;
        int nextPoint; 

        do {
            hull.add(points[currentPoint]);

            nextPoint = (currentPoint + 1) % (points.length);

            // Find the point with the largest counter-clockwise angle,
            // relative to the current hull
            for(int i = 0; i < points.length; i++) {
                if(Util.orientation(points[currentPoint], points[i], points[nextPoint]) == -1)
                    nextPoint = i;
            }

            currentPoint = nextPoint;

            // Repeat until connected
        } while(currentPoint != lowestIndex);

        return hull.toArray(new Point[hull.size()]);
        
    } 




    public static Point[] improved(Point[] points) {

        if (points.length < 3) {
            return points;
        }

        ArrayList<Point> hull = new ArrayList<Point>();

        // Find the lowest point
        int lowestIndex = 0;
        for(int i = 1; i < points.length; i++) {
            if(points[i].y < points[lowestIndex].y) {
                lowestIndex = i;
            }
            else if(points[i].y == points[lowestIndex].y) {
                if(points[i].x < points[lowestIndex].x) {
                    lowestIndex = i;
                }
            }
        }

        Point p0 = points[lowestIndex];
        // Begin at the lowest point
        int currentPoint = lowestIndex;
        int nextPoint; 

        int size = points.length;

        do {
            hull.add(points[currentPoint]);

            nextPoint = (currentPoint + 1) % size;

            // Find the point with the largest counter-clockwise angle,
            // relative to the current hull
            for(int i = 0; i < size; i++) {
                if(Util.orientation(points[currentPoint], points[i], points[nextPoint]) == -1)
                    nextPoint = i;
                else if(hull.size() % 8 == 0 && Util.orientation(points[currentPoint], p0, points[i]) < 0) {

                    points[i] = points[size - 1];

                    if(size > 0) 
                        size--;

                    if(i > 0)
                        i--;
                    // mind: nextPoint, currentPoint, lowestIndex, negative i
                }
            }

            currentPoint = nextPoint;

            // Repeat until connected
        } while(points[currentPoint].equals(p0) == false);

        return hull.toArray(new Point[hull.size()]);
        
    }  // improved


    public static void main(String[] args) {

        Point[] points = Testing.testSet14();
        Point[] hull = improved(points);

        new Plotting(points, hull, true, 0);



    }


} // Jarvis