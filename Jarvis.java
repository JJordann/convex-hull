import java.util.ArrayList;

// Jarvis march a.k.a Gift Wrapping
public class Jarvis {

    public static Point[] convexHull(Point[] points) {
        if (points.length < 3) {
            return null;
        }

        ArrayList<Point> hull = new ArrayList<Point>();

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

        int currentPoint = lowestIndex;
        int nextPoint; 

        do {
            hull.add(points[currentPoint]);

            nextPoint = (currentPoint + 1) % (points.length);

            for(int i = 0; i < points.length; i++) {
                if(Util.orientation(points[currentPoint], points[i], points[nextPoint]) == -1)
                    nextPoint = i;
            }

            currentPoint = nextPoint;

        } while(currentPoint != lowestIndex);

        return hull.toArray(new Point[hull.size()]);
        
    } 

} // Jarvis