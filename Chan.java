import java.util.ArrayList;


public class Chan {

    public static Point[] convexHull(Point[] points) {

        int lowest = 0;
        // Find the lowest point (leftmost in case of tie)
        for(int i = 1; i < points.length; i++) {
            if(points[i].y < points[lowest].y 
            || points[i].y == points[lowest].x && points[i].x < points[lowest].x) 
            {
                lowest = i;
            }
        }

        // number of subgroups
        int m = 4;
        int numPoints = points.length / (m - 1);

        ArrayList<ArrayList<Point>> K = new ArrayList<ArrayList<Point>>(m);
        for(int i = 0; i < m; i++) {
            K.add(new ArrayList<Point>());
        }

        for(int i = 0; i < points.length; i++) {
            K.get(i / numPoints).add(points[i]);
        }
        
        K.forEach(l -> printList(l));

        for(int i = 0; i < m; i++) {
            K.set(i, GrahamScan.convexHull_list(K.get(i)));
        }


        System.out.println("---");
        K.forEach(l -> printList(l));


        return null;
    }

    public static void printList(ArrayList<Point> a) {
        a.forEach(System.out::print);
        System.out.println();
    }


    public static void main(String[] args) {

        Point[] points = Testing.testSet7();
        convexHull(points);
    }
    
}
