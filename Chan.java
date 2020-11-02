import java.util.ArrayList;
import java.lang.Math;

class IndexPair {
    // Because the set of points will be divided into smaller 
    // subsets of points, representing points as pairs of 
    // indices is necessary for O(1) access to a point's
    // successor within its subset
    public int hull;
    public int point;

    public IndexPair(int h, int p) {
        this.hull = h;
        this.point = p;
    }

    public Point getPoint(ArrayList<ArrayList<Point>> K) {
        return K.get(this.hull).get(this.point);
    }

    public boolean equals(IndexPair p) {
        return (this.hull == p.hull) && (this.point == p.point);
    }
}

public class Chan {

    public static Point[] convexHull(Point[] points, int t) {

        // m: number of points in each mini-hull
        int m = Math.min(points.length, (int) Math.pow(2, Math.pow(2, t)));

        // number of mini-hulls
        int numGroups = (int) Math.ceil( (float) points.length / (float) m);

        // divide the points into groups
        ArrayList<ArrayList<Point>> K = new ArrayList<ArrayList<Point>>(numGroups);
        for(int i = 0; i <= numGroups; i++) {
            K.add(new ArrayList<Point>());
        }

        for(int i = 0, h = 0; i < points.length; i++) {
            K.get(h).add(points[i]);
            if(K.get(h).size() == m)
                h++;
        }
        
        // compute the hull for each subset using Graham's scan
        for(int i = 0; i < numGroups; i++) {
            K.set(i, GrahamScan.convexHull_list(K.get(i)));
        }


        IndexPair currentPoint = new IndexPair(0, 0);

        // Begin with the leftmost point (lowest leftmost in case of tie)
        for(int h = 0; h < numGroups; h++) {
            for(int i = 0; i < K.get(h).size(); i++) {
                Point p = K.get(h).get(i);
                if(p.x < currentPoint.getPoint(K).x || p.x == currentPoint.getPoint(K).x && p.y < currentPoint.getPoint(K).y) {
                    currentPoint = new IndexPair(h, i);
                }
            }
        }

        // stores index of subhull, which each point in hull belongs to
        ArrayList<IndexPair> hull = new ArrayList<IndexPair>();

        // stores tangents from current point to each mini-hull
        ArrayList<IndexPair> tangents = null;

        IndexPair nextPoint;

        do {
            // In each step, run the gift-wrapping algorithm on the 
            // set of tangents to find the next point on the hull

            hull.add(currentPoint);

            // find tangents to all other subhulls
            tangents = new ArrayList<IndexPair>(numGroups);
            for(int h = 0; h < numGroups; h++) {
                if(h != currentPoint.hull) {
                    // If the current point belongs to a different mini-hull,
                    // tangent to the mini-hull can be computed in O(logn) using binary search
                    int rTangent = Util.rightTangent(K.get(h), currentPoint.getPoint(K));
                    tangents.add(new IndexPair(h, rTangent));

                }
                else {
                    // If the point belongs to the same mini-hull, add the next
                    // point of the same mini-hull instead of the tangent.
                    // Because points in (convex) mini-hulls are ordered 
                    // counter-clockwise, the point maximizing the angle to the current hull 
                    // will always be the next point of the mini-hull, counter-clockwise
                    int nextIndex = (currentPoint.point + 1) % K.get(currentPoint.hull).size();
                    tangents.add(new IndexPair(currentPoint.hull, nextIndex));
                }
            }


            // Among all candidates, find the one maximizing the angle to the current hull
            // (this part of the procedure is identical to the gift-wrapping algorithm)
            nextPoint = tangents.get(0);
            for(int i = 0; i < tangents.size(); i++) {
                if(Util.orientation(currentPoint.getPoint(K), tangents.get(i).getPoint(K), nextPoint.getPoint(K)) == -1) {
                    nextPoint = tangents.get(i);
                }
            }

            currentPoint = nextPoint;

            // If the hull connects, return it
            if(hull.get(0).equals(currentPoint)) {

                // map pairs to points and return the hull
                Point[] polygon = new Point[hull.size()];
                for(int i = 0; i < hull.size(); i++) {
                    polygon[i] = hull.get(i).getPoint(K);
                }

                return polygon;
            }

        // Repeat for a maximum of `m` iterations before
        // starting over with a larger `m`
        } while (hull.size() < m);


        // hull is not complete, try again with larger mini-hull size
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
