import java.util.Arrays;

public class AklToussaint {
    
    public static Point[] prune(Point[] points) {

        Point[] residual = new Point[points.length];
        int resSize = 0;
        
        int leftmost = 0, rightmost = 0, lowest = 0, highest = 0;

        for(int i = 0; i < points.length; i++) {
            if(points[i].x < points[leftmost].x)
                leftmost = i;

            if(points[i].x > points[rightmost].x)
                rightmost = i;

            if(points[i].y < points[lowest].y)
                lowest = i;
            
            if(points[i].y > points[highest].y)
                highest = i;
        }

        Point pLeftmost  = points[leftmost];
        Point pRightmost = points[rightmost];
        Point pLowest    = points[lowest];
        Point pHighest   = points[highest];

        for(int i = 0; i < points.length; i++) {
            if( ! Util.isInsideQuadrilateral(points[i], pLeftmost, pRightmost, pLowest, pHighest)) {
                residual[resSize++] = points[i];
            }
        }

        return Arrays.copyOf(residual, resSize);
    }

}
