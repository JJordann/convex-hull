import java.util.*;

public class GrahamScan {
    
    public static Point[] convexHull(Point[] points) {

        int lowestIndex = 0;
        int y_min = points[lowestIndex].y;

        for(int i = 0; i < points.length; i++) {
            if(points[i].y < y_min) {
                lowestIndex = i;
                y_min = points[i].y;
            }
            else if(points[i].y == y_min) {
                if(points[i].x < points[lowestIndex].x) {
                    lowestIndex = i;
                    y_min = points[i].y;
                }
            }
        }

        Point tmp = points[lowestIndex];
        points[lowestIndex] = points[0];
        points[0] = tmp;

        Point p0 = points[0];
        Comparator<Point> angleComparator = new Comparator<Point>() {
            public int compare(Point p, Point q) {
                int angle = Util.orientation(p0, p, q);
                if(angle == 0) {
                    if(p0.distance(q) >= p0.distance(p)) 
                        return -1;
                    else
                        return 1;
                }
                else {
                    if(angle == -1)
                        return -1;
                    else  
                        return 1;
                }
            }
        };

        Arrays.sort(points, angleComparator);


        // odstrani vse kolinearne tocke
        int prunedLength = 1;
        for(int i = 1; i < points.length; i++) {
            while((i < points.length - 1) && 
                  Util.orientation(p0, points[i], points[i + 1]) == 0) {
                i++;
            }
            points[prunedLength] = points[i];
            prunedLength++;
        }

        if(prunedLength < 3) 
            return null;


        Stack<Point> s = new Stack<Point>();
        s.push(points[0]);
        s.push(points[1]);
        s.push(points[2]);


        for(int i = 3; i < prunedLength; i++) {
            while(Util.orientation(Util.getSecond(s), s.peek(), points[i]) != -1) {
                s.pop();
            }
            s.push(points[i]);
        }

        Point[] hull = new Point[s.size()];
        return s.toArray(hull);
    } // grahamScan
}
