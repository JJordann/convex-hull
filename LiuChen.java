import java.util.ArrayList;
import java.util.Collections;

public class LiuChen {
    

    public static ArrayList<Point> convexHull(ArrayList<Point> points) {


        // TODO: indeksiranje z 1
        // TODO: uporabi navaden array namesto ArrayList



        ArrayList<Point> M = extremePoints(points);
        ArrayList<Point> H = new ArrayList<Point>();
        H.add(M.get(1));
        H.add(M.get(1));

        for(int i = 0; i < points.size(); i++) {
            Point v = points.get(i);
            if(is_cand_pps(M.get(1), M.get(2), v)) {
                deal_cand_pps(H, v, M.get(2));
            }
        }

        H.add(M.get(2));

        return H;
    }

    public static boolean is_cand_pps(Point m1, Point m2, Point v) {
        return Util.S(m1, m2, v) > 0;
    }

    public static void safeSet(ArrayList<Point> h, int index, Point v) {
        if(index >= h.size()) {
            h.add(v);
        }
        else {
            h.set(index, v);
        }
    }


    public static void deal_cand_pps(ArrayList<Point> h, Point v, Point m2) {

        int r = h.size() - 1;
        Point hr = h.get(r);

        // Case 1: adding the first point
        if(r == 1) {
            h.add(v);
            return;
        }

        int[] mnt = in_avr_pps(v, h, m2);
        int m = mnt[0], n = mnt[1], t = mnt[2];

        // Case 2:
        if(m == -1)
            return;
    
        if(n == -1) {
            // Step 3
            if(v.x >= hr.x) {                    
                //r = m + 1;      // TODO: resize h to size r !!!!!!!!!
                                // izbrisi vsa vozlisca po `hm`, nato appendaj `v`
                //h.set(r, v);    // TODO: safe set

                // Remove all hull vertices after h[m]
                for(int i = h.size() - 1; i > m; i--) {
                    h.remove(i);
                }
                // insert `v` after h[m]
                h.add(v);


                return;
            }
            // Step 4
            else if(Util.S(v, m2, hr) > 0 ) { 
                r = m + 1;
                safeSet(h, r, v);
                //h.set(r, v);
                r = r + 1;
                //h.set(r, hr);               
                safeSet(h, r, hr);
            
                // delete all after h[r]

                while(h.size() > r + 1) {
                    h.remove(h.size() - 1);
                }

            }
            else {
                r = m + 1;
                //h.set(r, v);
                safeSet(h, r, v);

                // delete all after h[r]
                while(h.size() > r + 1) {
                    h.remove(h.size() - 1);
                }

                return;
            }

        }
        else {
            // n > 0
            // Step 5

            //if(t == 0)  {
                //r = r - n + m + 2;
            //}
            //else {
                //r = r - n + m + 1;
            //}

            // delete points after h[m] and before h[n]
            for(int i = m + 1; i < n; i++) {
                h.remove(i);
            }

            if(t == 1)
                h.remove(n - 1);

            // insert `v` between h[m] and h[n] 
            h.add(m + 1, v);

            return;

        } // Step 5

    } // deal_cand_pps


    /*

    */
    public static int[] in_avr_pps(Point v, ArrayList<Point> h, Point m2) {

        int m = -1, n = -1, t = 0;
        int r = h.size() - 1;
        Point hr = h.get(r);

        // Case 1: new point is directly above/below the last hull vertex
        if(v.x == hr.x) {
            n = -1;
            if(v.y > hr.y) {
                // if new point is above, find which active 
                // region it belongs to. Otherwise, discard it
                m = find_sar(v, 1, r - 1, h);
            }
        }
        // Case 2: new point is to the right of the last hull vertex
        else if(h.get(r - 1).x < v.x && v.x < m2.x) {
            // Step 3
            n = -1;
            if(Util.S(h.get(r - 1), hr, v) >= 0) {
                m = find_sar(v, 1, r - 1, h);
                // quit
            }
            else if(Util.S(hr, m2, v) > 0) { // && Util.S(h.get(r - 1), hr, v) < 0
                 m = r;
                // quit
            }
            else {
                m = -1;
                // quit
            }
        }
        // Case 3: new point lies to the left of the last hull vertex
        else {
            if(Util.S(h.get(r - 1), hr, v) > 0) {
                n = -1;
                m = find_sar(v, 1, r - 1, h);
                // quit
            }
            else {
                int j = findJ(v, h);
                if(Util.S(h.get(j), h.get(j + 1), v) <= 0) {
                    m = -1;
                    n = -1;
                    // quit
                }
                else {
                    return find_avr_pps(v, j, h);
                }

            }
        }
        
        int[] mnt = new int[3];
        mnt[0] = m; mnt[1] = n; mnt[2] = t;
        return mnt;
    }

    /*
        finds j such that: h[j].x < v.x <= h[j + 1].x
    */
    public static int findJ(Point v, ArrayList<Point> h) {

        for(int j = 1; j < h.size() - 1; j++) {
            if(h.get(j).x < v.x && v.x <= h.get(j + 1).x)
                return j;
        }
        return -1;
    }

    /*
        v: point to insert
        j: integer such that: h[j].x < v.x < h[j+1].x
        h: current hull

        returns:
            [m, n, t]:
                m: index of active region
                n: index of inverse active region
                t: 1 if collinear with h[j], h[j+1], otherwise 0

    */
    public static int[] find_avr_pps(Point v, int j, ArrayList<Point> h) {

        int r = h.size() - 1;
        int m = -1, n = -1, t = -1;

        // Case 1: inserting immediately after h[0]
        if(j == 1) {
            m = 1;           

            if(Util.S(h.get(3), h.get(2), v) == 0) {
                n = 2;
                t = 1;
                // quit
            }
            else if(Util.S(h.get(3), h.get(2), v) > 0) {
                n = 2;
                t = 0;
                // quit
            }
            else {
                int[] nt = find_isar(v, 3, r - 1, h);
                n = nt[0];
                t = nt[1];
                // quit
            }
        }

        // Case 2: inserting before the second to last vertex
        else if(j == r - 2) {
            n = r - 1;
            if(Util.S(h.get(r - 1), h.get(r), v) == 0)
                t = 1;
            
            if(Util.S(h.get(r - 1), h.get(r), v) < 0) {
                t = 0;
                // Step 3

                if(Util.S(h.get(r - 3), h.get(r - 2), v) < 0) {
                    m = r - 2;
                    // quit
                }
                else {
                    m = find_sar(v, 1, r - 3, h);
                    // quit
                }

            }
        }

        // Case 3: the general case
        else {
            // Step 4
            float S1 = Util.S(h.get(j - 1), h.get(j), v);
            float S2 = Util.S(h.get(j + 1), h.get(j + 2), v);

            return table(S1, S2, j, r, v, h);
        }

        int[] mnt = new int[3];
        mnt[0] = m;
        mnt[1] = n;
        mnt[2] = t;
        return mnt;
    }


    /*

    */
    public static int[] table(float S1, float S2, int j, int r, Point v, ArrayList<Point> h) {

        int m = -1, n = -1337, t = -1;

        if(S1 == 0 && S2 == 0) {
            m = j - 1;
            n = j + 1;
            t = 1;
        }
        else if(S1 == 0 && S2 < 0) {
            m = j - 1;
            n = j + 1;
            t = 0;
        }
        else if(S1 == 0 && S2 > 0) {
            m = j - 1; 
            int[] nt = find_isar(v, j + 2, r - 1, h);
            n = nt[0];
            t = nt[1];
        }
        else if(S1 < 0 && S2 == 0) {
            m = j;
            n = j + 1;
            t = 1;
        }
        else if(S1 < 0 && S2 < 0) {
            m = j;
            n = j + 1;
            t = 0;
        }
        else if(S1 < 0 && S2 > 0) {
            m = j;
            int[] nt = find_isar(v, j + 2, r - 1, h);
            n = nt[0];
            t = nt[1];
        }
        else if(S1 > 0 && S2 == 0) {
            m = find_sar(v, 1, j - 1, h);
            n = j + 1;
            t = 1;
        }
        else if(S1 > 0 && S2 < 0) {
            m = find_sar(v, 1, j - 1, h);
            n = j + 1;
            t = 0;
        }
        else if(S1 > 0 && S2 > 0) {
            m = find_sar(v, 1, j - 1, h);
            int[] nt = find_isar(v, j + 2, r - 1, h);
            n = nt[0];
            t = nt[1];
        }

        int[] mnt = new int[3];
        mnt[0] = m;
        mnt[1] = n;
        mnt[2] = t;
        return mnt;
    }



    /*
        returns:
            [n, t]:
                n: index of inverse active region
                t: 1 if collinear with h[n], h[n+1]
    */
    public static int[] find_isar(Point v, int l, int u, ArrayList<Point> h) {

        for(int k = l; k < u; k++) {
            float sf = Util.S(h.get(k + 1), h.get(k), v); // forward border
            float sb = Util.S(h.get(k), h.get(k - 1), v); // backward border

            if(sb < 0 && sf >= 0) {
                int[] nt = new int[2];
                nt[0] = k;

                if(sf == 0.0)
                    nt[1] = 1;
                else
                    nt[1] = 0;

                return nt;
            }
        }

        return null;
    }

    /*
        finds the hull vertex, whose active region contains `v`
    */
    public static int find_sar(Point v, int l, int u, ArrayList<Point> hull) {

        // special case:
        if(Util.S(hull.get(1), hull.get(2), v) >= 0) {
            return 1;
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

    */
    public static ArrayList<Point> convexHull_SP(ArrayList<Point> points) {

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
                    // First candidate is added to the hull immediately
                    hull.add(v);
                }
                else {
                    // Current candidate `v` lies in 
                    // the active region of hull[j]
                    int j = in_avr(v, hull, m2);
                    // If `v` is not in the active region of any point,
                    // it is inside the current polyline. Ignore it.
                    if(j != -1) {
                        // First scenario: `v` lies on the right side of the hull vertex
                        // Add `v` to the hull
                        if(v.x >= hull.get(hull.size() - 1).x) {
                            //hull.set(j + 1, v);
                            hull.add(v);
                        }
                        else {
                            Point hr = hull.get(hull.size() - 1);
                            if(Util.S(v, m2, hr) > 0) {
                                // Second scenario: `v` lies on the left side of the last hull vertex
                                // Insert `v` after hull[j]
                                hull.set(j + 1, v);
                                hull.set(j + 2, hr);
                            }
                            else {
                                // Third scenario: 
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


    /*

    */
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

    public static void avr_test() {

        ArrayList<Point> hull = new ArrayList<Point>();
        hull.add(new Point(-10, -1)); // 
        hull.add(new Point(-10, -1)); // M1
        hull.add(new Point(-9, 2));
        hull.add(new Point(-8, 4));
        hull.add(new Point(-7, 5));
        hull.add(new Point(-4, 6));
        hull.add(new Point(1, 7));
        //hull.add(new Point(8, 8)); // M2

        Point p1 = new Point(-8, 6);
        Point p2 = new Point(-7, 6);
        Point p3 = new Point(-4, 7);
        Point p4 = new Point(-9, 3);
        Point p5 = new Point(-4, 4);

        Point p = p4;


        //Point m2 = hull.get(hull.size() - 1);
        Point m2 = new Point(8, 8);

        int[] mnt = in_avr_pps(p, hull, m2);

        ArrayList<Point> res = new ArrayList<Point>();
        if(mnt[0] != -1) {
            res.add(hull.get(mnt[0]));
        }

        res.add(p);

        if(mnt[1] != -1) {
            res.add(hull.get(mnt[1]));
        }
        else {
            res.add(m2);
        }
        System.out.println(mnt[0] + ", " + mnt[1] + ", " + mnt[2]);

        ArrayList<Point> hull2 = new ArrayList<Point>();
        hull2.addAll(hull);

        deal_cand_pps(hull2, p, m2);

        new Plotting(hull, hull2, true, 0);


        // convexity check
        //Point[] hull0 = new Point[hull.size()];
        //hull0 = GrahamScan.convexHull(hull.toArray(hull0));
        //ArrayList<Point> hull0ar = new ArrayList<Point>();
        //Collections.addAll(hull0ar, hull0);
        //hull.add(p1);
        //hull.add(p2);
        //hull.add(p3);
        //new Plotting(hull, hull0ar, true, 0);

    }

    public static void main(String[] args) {

        Point[] points0 = Testing.testSet8();
        ArrayList<Point> points = new ArrayList<Point>();
        Collections.addAll(points, points0);

        ArrayList<Point> hull = convexHull(points);

        new Plotting(points, hull, true, 0);



        
    } // main
}
