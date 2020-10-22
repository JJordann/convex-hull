import java.util.ArrayList;
import java.lang.Math;


public class Chan {

    public static Point[] convexHull(Point[] points, int t) {

        // m: number of points in each mini-hull
        int m = Math.min(points.length, (int) Math.pow(2, Math.pow(2, t)));

        // number of mini-hulls
        int numGroups = (int) Math.ceil( (float) points.length / (float) m);

        ArrayList<ArrayList<Point>> K = new ArrayList<ArrayList<Point>>(numGroups);
        for(int i = 0; i <= numGroups; i++) {
            K.add(new ArrayList<Point>());
        }

        for(int i = 0, h = 0; i < points.length; i++) {
            K.get(h).add(points[i]);
            if(K.get(h).size() == m)
                h++;
        }
        
        //K.forEach(l -> printList(l));

        for(int i = 0; i < numGroups; i++) {
            K.set(i, GrahamScan.convexHull_list(K.get(i)));
        }

        //System.out.println("---");
        //K.forEach(l -> printList(l));


        Point currentPoint = K.get(0).get(0);
        int currentHull = 0;

        // Begin with the leftmost point (lowest leftmost in case of tie)
        for(int h = 0; h < numGroups; h++) {
            for(int i = 0; i < K.get(h).size(); i++) {
                Point p = K.get(h).get(i);
                if(p.x < currentPoint.x || p.x == currentPoint.x && p.y < currentPoint.y) {
                    currentPoint = p;
                    currentHull  = h;
                }
            }
        }

        // stores the final hull
        ArrayList<Point> hull = new ArrayList<Point>();

        // stores index of subhull, which each point in hull belongs to
        ArrayList<Integer> subhullIndex = new ArrayList<Integer>();
        subhullIndex.add(currentHull);

        //System.out.println("Lowest: " + currentPoint + "; hull: " + currentHull);


        Point nextPoint;
        ArrayList<Point> tangents = null;

        do {
            hull.add(currentPoint);
            //hull.forEach(System.out::print);
            //System.out.println(" (hull: " + currentHull + ")");

            // find tangents to all other subhulls
            tangents = new ArrayList<Point>(2 * numGroups);
            for(int h = 0; h < numGroups; h++) {
                if(h != currentHull) {

                    if(K.get(h).size() <= 2) {
                        tangents.addAll(K.get(h));
                    }
                    else {
                        //int lTangent = Util.leftTangent(K.get(h).toArray(new Point[K.get(h).size()]), currentPoint);
                        //int rTangent = Util.rightTangent(K.get(h).toArray(new Point[K.get(h).size()]), currentPoint);

                        //tangents.add(K.get(h).get(lTangent));
                        //tangents.add(K.get(h).get(rTangent));

                        tangents.addAll(Util.convexTangents(K.get(h), currentPoint));


                    }
                }
                else {
                    //tangents.addAll(K.get(h));
                    tangents.addAll(Util.convexTangents(K.get(h), currentPoint));
                }
            }

            //tangents.forEach(System.out::print);
            //System.out.println(" <- tangents");

            nextPoint = tangents.get(0);
            currentHull = 0;

            for(int i = 0; i < tangents.size(); i++) {
                if(Util.orientation(currentPoint, tangents.get(i), nextPoint) == -1) {
                    nextPoint = tangents.get(i);
                    currentHull = i;
                }
            }

            currentPoint = nextPoint;


            if(hull.get(0).equals(currentPoint)) {
                // Full circle, hull is complete
                System.out.println("t = " + t + " succeeded");
                return hull.toArray(new Point[hull.size()]);
            }

        } while (hull.size() < m);


        // hull is not complete, try again with larger mini-hull size
        System.out.println("t = " + t + " failed");
        return convexHull(points, t + 1);
    } // convexHull


    // wrapper
    public static Point[] convexHull(Point[] points) {
        return convexHull(points, 1);
    }

    public static void printList(ArrayList<Point> a) {
        a.forEach(System.out::print);
        System.out.println();
    }


    public static void main(String[] args) {

        Point[] points = Testing.testSet7();
        Point[] hull = convexHull(points);
        new Plotting(points, hull, true, 0);
    }
    
}
