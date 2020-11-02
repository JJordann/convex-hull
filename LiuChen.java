import java.util.ArrayList;
import java.util.Collections;

public class LiuChen {
    
    /*

    */
    public static ArrayList<Point> convexHull(ArrayList<Point> points) {

        // Extreme points, ordered clockwise, 
        // beginning at the lowest leftmost point
        ArrayList<Point> M = extremePoints(points);

        // The (to be) complete hull, ordered clockwise, 
        // beginning at the lowest leftmost point
        ArrayList<Point> hull = new ArrayList<Point>();


        // From leftTop to topLeft
        ArrayList<Point> NW = partialHull(M.get(1), M.get(2), points);
        hull.addAll(NW);


        return hull;
    }

    /*
        Returns ordered partial hull, starting at `m1` and ending at `m2`
    */
    public static ArrayList<Point> partialHull(Point m1, Point m2, ArrayList<Point> points) {

        ArrayList<Point> hull = new ArrayList<Point>(points.size());
        hull.add(m1);

        if(m1.equals(m2)) {
            return hull;
        }

        // r = hull.size()
        //
        for(int i = 0; i < points.size(); i++) {
            if(isCandidate(i, m1, m2, points) == true) {
                // point v is a candidate
                Point v = points.get(i);

                if(hull.size() == 1) {
                    hull.add(v);
                }
                else {
                    int j = in_avr(v, hull, m2);
                    if(j != -1) {
                        if(v.x >= hull.get(hull.size() - 1).x) {
                            //hull.set(j + 1, v);
                            hull.add(v);
                        }
                        else {
                            Point hr = hull.get(hull.size() - 1);
                            if(Util.S(v, m2, hr) > 0) {
                                hull.set(j + 1, v);
                                hull.set(j + 2, hr);
                            }
                            else {
                                hull.set(j + 1, v);
                            }
                        }
                    } // j != -1
                } // r != 0


            } // if cand
        } // for pi in points


        hull.add(m2);
        return hull;
    }


    public static int in_avr(Point v, ArrayList<Point> hull, Point m2) {

        int r = hull.size() - 1;
        Point hr = hull.get(r);

        if(v.x == hr.x) {
            // Step 2
            if(v.y > hr.y) {
                return find_sar(v, 1, r - 1, hull);
            }
            else {
                return -1;
            }

        }
        else if(hr.x < v.x && v.x < m2.x) {
            // Step 3
            if(Util.S(hull.get(r - 1), hr, v) >= 0) {
                return find_sar(v, 1, r - 1, hull);
            }
            else if(Util.S(hr, m2, v) > 0) {
                return r;
            }
            else {
                return -1;
            }
        }
        else {
            // Step 5
            if(Util.S(hull.get(r - 1), hr, v) > 0) {
                return find_sar(v, 1, r - 1, hull);
            }
            else {
                return -1;
            }
        }
    } // in_avr



    /*
        finds the hull vertex, whose active region contains `v`
    */
    public static int find_sar(Point v, int l, int u, ArrayList<Point> hull) {

        // special case:
        if(Util.S(hull.get(0), hull.get(1), v) >= 0) {
            return 0;
        }

        Point hl = hull.get(l);
        Point hu = hull.get(u);

        for(int j = l; j <= u; j++) {
            if(Util.S(hull.get(j - 1), hull.get(j), v) < 0 
            && Util.S(hull.get(j), hull.get(j + 1), v) >= 0) {
                return j;
            }

        }

        return -1;
    }



    /*
        Function to check if `V` (points[v_index]) is 
        a candidate for further consideration

        TODO: razdeli tocke v 4 regije, 
        odstrani prvi pogoj, S(m1, m2, v)...
        Ta pogoj bo vedno zadoscen, ce upostevamo
        samo tocke, ki so v isti regiji kot m1 in m2
    */
    public static boolean isCandidate(int v_index, Point m1, Point m2, ArrayList<Point> points) {
        Point v = points.get(v_index);
        Point next = points.get( (v_index + 1) % points.size() );

        int prev_index = v_index - 1;
        if(prev_index < 0) 
            prev_index += points.size();

        Point prev = points.get(prev_index);

        return (Util.orientation(m1, m2, v) < 0) 
            && (Util.orientation(prev, v, next) > 0);
    }


    /*
        Returns the 8 extreme points
    */
    public static ArrayList<Point> extremePoints(ArrayList<Point> points) {

        int minX = points.get(0).x;
        int minY = points.get(0).y;
        int maxX = points.get(0).x;
        int maxY = points.get(0).y;

        Point leftTop  = points.get(0);
        Point leftBot  = points.get(0);
        Point rightTop = points.get(0);
        Point rightBot = points.get(0);
        Point topLeft  = points.get(0);
        Point topRight = points.get(0);
        Point botLeft  = points.get(0);
        Point botRight = points.get(0);

        for(int i = 1; i < points.size(); i++) {
            Point pi = points.get(i);

            // left
            if(pi.x < minX) {
                minX = pi.x;
                leftTop = pi;
                leftBot = pi;
            }
            else if(pi.x == minX) {
                if(pi.y > leftTop.y)
                    leftTop = pi;
                else if(pi.y < leftBot.y)
                    leftBot = pi;
            }

            // right
            else if(pi.x > maxX) {
                maxX = pi.x;
                rightTop = pi;
                rightBot = pi;
            }
            else if(pi.x == maxX) {
                if(pi.y > rightTop.y)
                    rightTop = pi;
                else if(pi.y < rightBot.y) 
                    rightBot = pi;
            }

            // bottom
            if(pi.y < minY) {
                minY = pi.y;
                botLeft = pi;
                botRight = pi;
            }
            else if(pi.y == minY) {
                if(pi.x > botRight.x)
                    botRight = pi;
                else if(pi.x < botLeft.x)
                    botLeft = pi;
            }
            
            // top
            else if(pi.y > maxY) {
                maxY = pi.y;
                topLeft = pi;
                topRight = pi;
            }
            else if(pi.y == maxY) {
                if(pi.x > topRight.x)
                    topRight = pi;
                else if(pi.x < topLeft.x)
                    topLeft = pi;
            }
        }  // for pi in points


        ArrayList<Point> extreme = new ArrayList<Point>(8);
        extreme.add(leftBot);
        extreme.add(leftTop);
        extreme.add(topLeft);
        extreme.add(topRight);
        extreme.add(rightTop);
        extreme.add(rightBot);
        extreme.add(botRight);
        extreme.add(botLeft);

        return extreme;
    }


    public static void main(String[] args) {

        Point[] points0 = Testing.testSet6();
        ArrayList<Point> points = new ArrayList<Point>(points0.length);
        Collections.addAll(points, points0);


        ArrayList<Point> sp = Torch.approximateHull(points0);
        Collections.reverse(sp);

        //sp.forEach(System.out::println);

        ArrayList<Point> hull = convexHull(sp);
        ArrayList<Point> extreme = extremePoints(sp);
        new Plotting(points, sp, true, 0);
        new Plotting(points, extreme, true, 250);
        new Plotting(points, hull, true, 500);
    }
}
