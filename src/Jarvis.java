import java.util.ArrayList;

/*
  Jarvis march a.k.a Gift Wrapping
 (has troubles with colinear points)
*/
public class Jarvis {

    public static Point[] convexHull(Point[] points) {
        if (points.length < 3) {
            return null;
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

} // Jarvis