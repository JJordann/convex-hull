import java.util.*;

public class DivideAndConquer {

    public static Point[] convexHull(Point[] points) {

        if(points.length <= 5) 
            return GrahamScan.convexHull(points);

        Comparator<Point> xComparator = new Comparator<Point>() {
            public int compare(Point p, Point q) {
                return p.x - q.x;
            }
        };

        Arrays.sort(points, xComparator);
        
        Point[] leftPoints  = Arrays.copyOfRange(points, 0, points.length / 2);
        Point[] rightPoints = Arrays.copyOfRange(points, points.length / 2, points.length);

        Point[] leftHull = convexHull(leftPoints);
        Point[] rightHull = convexHull(rightPoints);

        return merge(leftHull, rightHull);
    } // convexHull


    public static Point[] merge(Point[] leftHalf, Point[] rightHalf) {

        List<Point> merged = new LinkedList<Point>();

        if(leftHalf == null) {
            return GrahamScan.convexHull(rightHalf);
        }

        if(rightHalf == null) {
            return GrahamScan.convexHull(leftHalf);
        }

        int rightmostA = 0;
        int leftmostB  = 0;

        for(int i = 1; i < leftHalf.length; i++) {
            if(leftHalf[i].x > leftHalf[rightmostA].x) {
                rightmostA = i;
            }
        }

        for(int i = 1; i < rightHalf.length; i++) {
            if(rightHalf[i].x < rightHalf[leftmostB].x) {
                leftmostB = i;
            }
        }

        int currentA = rightmostA;
        int currentB = leftmostB;

        boolean done = false;
        while(done == false) {
            done = true;
            while(Util.orientation(rightHalf[currentB], leftHalf[currentA], 
                  leftHalf[(currentA + 1) % leftHalf.length]) >= 0) {
                      currentA = (currentA + 1) % leftHalf.length;
                  }
                  
            while(Util.orientation(leftHalf[currentA], rightHalf[currentB], 
                  rightHalf[(rightHalf.length + currentB - 1) % rightHalf.length]) <= 0) {
                      currentB = (currentB - 1 + rightHalf.length) % rightHalf.length;
                      done = false;
            }
        }

        int upperA = currentA;
        int upperB = currentB;

        currentA = rightmostA;
        currentB = leftmostB;
        
        done = false;
        while(done == false) {
            done = true;
            while(Util.orientation(leftHalf[currentA], rightHalf[currentB], 
                 rightHalf[(currentB + 1) % rightHalf.length]) >= 0) {
                    currentB = (currentB + 1) % rightHalf.length;
                 }

            while(Util.orientation(rightHalf[currentB], leftHalf[currentA], 
                 leftHalf[(leftHalf.length + currentA - 1) % leftHalf.length]) <= 0) {
                     currentA = (currentA - 1 + leftHalf.length) % leftHalf.length;
                     done = false;
                 }
        }

        int lowerA = currentA;
        int lowerB = currentB;

        for(int i = upperA; i != lowerA; i = (i + 1) % leftHalf.length) {
            merged.add(leftHalf[i]);
        }
        merged.add(leftHalf[lowerA]);

        for(int i = lowerB; i != upperB; i = (i + 1) % rightHalf.length) {
            merged.add(rightHalf[i]);
        }
        merged.add(rightHalf[upperB]);

        return merged.toArray(new Point[merged.size()]);
    }

} // DivideAndConquer
