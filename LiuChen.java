import java.util.Arrays;

public class LiuChen {
    

    public static Point[] convexHull(Point[] points) {

        Point[] M = extremePoints(points);
        Point[] H = new Point[points.length];

        int r = 0;

        Point endpoint;

        /* division by quadrant:
                y
                |
             Q1 | Q2
          ------+----->x
             Q3 | Q4
                |
        */

        // Quadrant 1 -----------------------------------------------
        H[r] = M[0]; 
        endpoint = M[1];

        for(int i = 0; i < points.length; i++) {
            Point v = points[i];
            if(is_cand_pps(M[0], endpoint, v)) {
                r = deal_cand_pps(H, v, endpoint, r, 1);
            }
        }

        r = r + 1;
        H[r] = endpoint;
        H = Arrays.copyOf(H, r + 1);

        // Quadrant 2 -------------------------------------------------

        // TODO: if m1 != m2
        // TODO: delete first vertex of each subhull

        Point[] H2 = new Point[points.length];
        r = 0;
        H2[r] = M[2];
        endpoint = M[3];

        for(int i = 0; i < points.length; i++) {
            Point v = points[i];
            if(is_cand_pps(M[2], endpoint, v)) {
                r = deal_cand_pps(H2, v, endpoint, r, 2);
            }
        }

        r = r + 1;
        H2[r] = endpoint;
        H2 = Arrays.copyOf(H2, r + 1);

        // Quadrant 3 --------------------------------------------------


        Point[] H3 = new Point[points.length];
        r = 0;
        H3[r] = M[4];
        endpoint = M[5];

        for(int i = 0; i < points.length; i++) {
            Point v = points[i];
            if(is_cand_pps(M[4], endpoint, v)) {
                System.out.println("cand:" + v);
                r = deal_cand_pps(H3, v, endpoint, r, 3);
            }
        }

        r = r + 1;
        H3[r] = endpoint;
        H3 = Arrays.copyOf(H3, r + 1);



        // Quadrant 4 --------------------------------------------------


        Point[] H4 = new Point[points.length];
        r = 0;
        H4[r] = M[6];
        endpoint = M[7];

        for(int i = 0; i < points.length; i++) {
            Point v = points[i];
            if(is_cand_pps(M[6], endpoint, v)) {
                System.out.println("cand:" + v);
                r = deal_cand_pps(H4, v, endpoint, r, 4);
            }
        }

        r = r + 1;
        H4[r] = endpoint;
        H4 = Arrays.copyOf(H4, r + 1);





        // Quadrant 4 --------------------------------------------------
        System.out.println("H1: ");
        Util.printSet(H);
        System.out.println("H2: ");
        Util.printSet(H2);
        System.out.println("H3: ");
        Util.printSet(H3);
        System.out.println("H4: ");
        Util.printSet(H4);

        return Util.concatHulls(H, Util.concatHulls(H2, Util.concatHulls(H3, H4)));
    }

    public static boolean is_cand_pps(Point m1, Point m2, Point v) {
        return Util.S(m1, m2, v) > 0;
    }

    /*

    */ 
    public static int find_sar(Point v, int l, int u, Point[] hull, int r) {

        // special case:
        if(Util.S(hull[0], hull[1], v) >= 0) {
            return 0;
        }

        Point hl = hull[l];
        Point hu = hull[u];

        for(int j = l; j <= u; j++) {
            if(Util.S(hull[j - 1], hull[j], v) < 0 
            && Util.S(hull[j], hull[j + 1], v) >= 0) {
                return j;
            }

        }

        return -1;
    }


    /*

    */
    public static int[] in_avr_pps1(Point v, Point[] h, Point m2, int r) {

        int m = -1, n = -1, t = 0;

        // Case 1: new point is directly above/below the last hull vertex
        if(v.x == h[r].x) {
            n = -1;
            if(v.y > h[r].y) {
                // if new point is above, find which active 
                // region it belongs to. Otherwise, discard it
                m = find_sar(v, 1, r - 1, h, r);
            }
        }
        // Case 2: new point is to the right of the last hull vertex
        else if(h[r - 1].x < v.x && v.x < m2.x) {
            // Step 3
            n = -1;
            if(Util.S(h[r - 1], h[r], v) >= 0) {
                m = find_sar(v, 1, r - 1, h, r);
                // quit
            }
            else if(Util.S(h[r], m2, v) > 0) { 
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
            if(Util.S(h[r - 1], h[r], v) > 0) {
                n = -1;
                m = find_sar(v, 1, r - 1, h, r);
                // quit
            }
            else {
                int j = findJ(v, h, r, 1);
                if(Util.S(h[j], h[j + 1], v) <= 0) {
                    m = -1;
                    n = -1;
                    // quit
                }
                else {
                    return find_avr_pps(v, j, h, r);
                }

            }
        }
        
        int[] mnt = new int[3];
        mnt[0] = m; mnt[1] = n; mnt[2] = t;
        return mnt;
    }

    public static int[] in_avr_pps2(Point v, Point[] h, Point m2, int r) {

        int m = -1, n = -1, t = 0;

        // Case 1: new point is directly above/below the last hull vertex
        if(v.y == h[r].y) {                                 // <-------------------
            n = -1;
            if(v.x > h[r].x) {                               // <-------------------
                // if new point is above, find which active 
                // region it belongs to. Otherwise, discard it
                m = find_sar(v, 1, r - 1, h, r);
            }
        }
        // Case 2: new point is to the right of the last hull vertex
        else if(h[r - 1].y > v.y && v.y > m2.y) {               // <-------------------          
            // Step 3
            n = -1;
            if(Util.S(h[r - 1], h[r], v) >= 0) {
                m = find_sar(v, 1, r - 1, h, r);
                // quit
            }
            else if(Util.S(h[r], m2, v) > 0) { 
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
            if(Util.S(h[r - 1], h[r], v) > 0) {
                n = -1;
                m = find_sar(v, 1, r - 1, h, r);
                // quit
            }
            else {
                int j = findJ(v, h, r, 2);                      // <-------------------                      
                if(Util.S(h[j], h[j + 1], v) <= 0) {
                    m = -1;
                    n = -1;
                    // quit
                }
                else {
                    return find_avr_pps(v, j, h, r);
                }

            }
        }
        
        int[] mnt = new int[3];
        mnt[0] = m; mnt[1] = n; mnt[2] = t;
        return mnt;
    }


    /*

    */
    public static int[] in_avr_pps3(Point v, Point[] h, Point m2, int r) {

        int m = -1, n = -1, t = 0;

        // Case 1: new point is directly above/below the last hull vertex
        if(v.x == h[r].x) {
            n = -1;
            if(v.y < h[r].y) {  // <----------------------------------------------
                // if new point is above, find which active 
                // region it belongs to. Otherwise, discard it
                m = find_sar(v, 1, r - 1, h, r);
            }
        }
        // Case 2: new point is to the right of the last hull vertex
        else if(h[r - 1].x > v.x && v.x > m2.x) {
            // Step 3
            n = -1;
            if(Util.S(h[r - 1], h[r], v) >= 0) {
                m = find_sar(v, 1, r - 1, h, r);
                // quit
            }
            else if(Util.S(h[r], m2, v) > 0) { 
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
            if(Util.S(h[r - 1], h[r], v) > 0) {
                n = -1;
                m = find_sar(v, 1, r - 1, h, r);
                // quit
            }
            else {
                int j = findJ(v, h, r, 3);
                if(Util.S(h[j], h[j + 1], v) <= 0) {
                    m = -1;
                    n = -1;
                    // quit
                }
                else {
                    return find_avr_pps(v, j, h, r);
                }

            }
        }
        
        int[] mnt = new int[3];
        mnt[0] = m; mnt[1] = n; mnt[2] = t;
        return mnt;
    }

    public static int[] in_avr_pps4(Point v, Point[] h, Point m2, int r) {

        int m = -1, n = -1, t = 0;

        // Case 1: new point is directly above/below the last hull vertex
        if(v.y == h[r].y) {
            n = -1;
            if(v.x < h[r].x) {  // <----------------------------------------------
                // if new point is above, find which active 
                // region it belongs to. Otherwise, discard it
                m = find_sar(v, 1, r - 1, h, r);
            }
        }
        // Case 2: new point is to the right of the last hull vertex
        else if(h[r - 1].y < v.y && v.y < m2.y) {
            // Step 3
            n = -1;
            if(Util.S(h[r - 1], h[r], v) >= 0) {
                m = find_sar(v, 1, r - 1, h, r);
                // quit
            }
            else if(Util.S(h[r], m2, v) > 0) { 
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
            if(Util.S(h[r - 1], h[r], v) > 0) {
                n = -1;
                m = find_sar(v, 1, r - 1, h, r);
                // quit
            }
            else {
                int j = findJ(v, h, r, 4);
                if(Util.S(h[j], h[j + 1], v) <= 0) {
                    m = -1;
                    n = -1;
                    // quit
                }
                else {
                    return find_avr_pps(v, j, h, r);
                }

            }
        }
        
        int[] mnt = new int[3];
        mnt[0] = m; mnt[1] = n; mnt[2] = t;
        return mnt;
    }


    /*
        Mutates `h` and returns updated `r` 
    */
    public static int deal_cand_pps(Point[] h, Point v, Point m2, int r, int quadrant) {

        // Case 1: adding the first point
        if(r == 0) {
            r = r + 1;
            h[r] = v;
            return r;
        }

        int[] mnt = new int[3];
        switch(quadrant) {
            case 1: mnt = in_avr_pps1(v, h, m2, r); break;
            case 2: mnt = in_avr_pps2(v, h, m2, r); break;
            case 3: mnt = in_avr_pps3(v, h, m2, r); break;
            case 4: mnt = in_avr_pps4(v, h, m2, r); break;
        }
        //int[] mnt = in_avr_pps(v, h, m2, r);
        int m = mnt[0], n = mnt[1], t = mnt[2];

        // Case 2:
        if(m == -1)
            return r;
    
        if(n == -1) {
            // Step 3
            if(v.x >= h[r].x) {                    
                r = m + 1;
                h[r] = v;
                return r;
            }
            // Step 4
            else if(Util.S(v, m2, h[r]) > 0 ) { 
                Point w = h[r];
                r = m + 1;
                h[r] = v;
                r = r + 1;
                h[r] = w;
                return r;

            }
            else {
                r = m + 1;
                h[r] = v;
                return r;
            }

        }
        else {
            // n > 0
            // Step 5

            // insert `v` after h[m]
            h[m + 1] = v;

            if(t == 1) {
                // delete up to including h[n]
                int gap = n - m - 1;
                for(int i = n + 1; i <= r; i++) {
                    h[i - gap] = h[i];
                }

                r = r - n + m + 1;
            }
            else {
                // delete up to h[n], excluding h[n]
                int gap = n - m - 2;
                for(int i = n; i <= r; i++) {
                    h[i - gap] = h[i];
                }

                r = r - n + m + 2;
            }

            return r;

        } // Step 5

    } // deal_cand_pps




    /*

    */
    public static int findJ(Point v, Point[] h, int r, int quadrant) {

        switch(quadrant) {
            case 1: {
                for(int j = 0; j < r; j++) {
                    if(h[j].x < v.x && v.x <= h[j + 1].x)
                        return j;
                }
            }; break;
            case 2: {
                for(int j = 0; j < r; j++) {
                    if(h[j].y > v.y && v.y >= h[j + 1].y)
                        return j;
                }
            }; break;
            case 3: {
                for(int j = 0; j < r; j++) {
                    if(h[j].x > v.x && v.x >= h[j + 1].x)
                        return j;
                }
            }; break;
            case 4: {
                for(int j = 0; j < r; j++) {
                    if(h[j].y < v.y && v.y <= h[j + 1].y)
                        return j;
                }
            }; break;
            default:
                return -1;
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
    public static int[] find_avr_pps(Point v, int j, Point[] h, int r) {

        int m = -1, n = -1, t = -1;

        // Case 1: inserting immediately after h[0]
        if(j == 0) {
            m = 0;           

            if(Util.S(h[2], h[1], v) == 0) {
                n = 2;
                t = 1;
                // quit
            }
            else if(Util.S(h[2], h[1], v) > 0) {
                n = 2;
                t = 0;
                // quit
            }
            else {
                int[] nt = find_isar(v, 2, r - 1, h);
                n = nt[0];
                t = nt[1];
                // quit
            }
        }

        // Case 2: inserting before the second to last vertex
        else if(j == r - 2) {
            n = r - 1;
            if(Util.S(h[r - 1], h[r], v) == 0)
                t = 1;
            
            if(Util.S(h[r - 1], h[r], v) < 0) {
                t = 0;
                // Step 3

                if(Util.S(h[r - 3], h[r - 2], v) < 0) {
                    m = r - 2;
                    // quit
                }
                else {
                    m = find_sar(v, 0, r - 3, h, r);
                    // quit
                }

            }
        }

        // Case 3: the general case
        else {
            // Step 4
            float S1 = Util.S(h[j - 1], h[j], v);
            float S2 = Util.S(h[j + 1], h[j + 2], v);

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
    public static int[] table(float S1, float S2, int j, int r, Point v, Point[] h) {

        int m = -1, n = -1, t = -1;

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
            m = find_sar(v, 0, j - 1, h, r);
            n = j + 1;
            t = 1;
        }
        else if(S1 > 0 && S2 < 0) {
            m = find_sar(v, 0, j - 1, h, r);
            n = j + 1;
            t = 0;
        }
        else if(S1 > 0 && S2 > 0) {
            m = find_sar(v, 0, j - 1, h, r);
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
    public static int[] find_isar(Point v, int l, int u, Point[] h) {

        for(int k = l; k < u; k++) {
            float sf = Util.S(h[k + 1], h[k], v); // forward border
            float sb = Util.S(h[k], h[k - 1], v); // backward border

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
        Returns the 8 extreme points
    */
    public static Point[] extremePoints(Point[] points) {

        int minX = points[0].x;
        int minY = points[0].y;
        int maxX = points[0].x;
        int maxY = points[0].y;

        Point leftTop  = points[0];
        Point leftBot  = points[0];
        Point rightTop = points[0];
        Point rightBot = points[0];
        Point topLeft  = points[0];
        Point topRight = points[0];
        Point botLeft  = points[0];
        Point botRight = points[0];

        for(int i = 1; i < points.length; i++) {
            Point pi = points[i];

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


        Point[] extreme = new Point[8];
        extreme[0] = leftTop;
        extreme[1] = topLeft;
        extreme[2] = topRight;
        extreme[3] = rightTop;
        extreme[4] = rightBot;
        extreme[5] = botRight;
        extreme[6] = botLeft;
        extreme[7] = leftBot;

        return extreme;
    }

    public static void avr_test() {

        Point[] hull = new Point[6];
        int r = 5;
        hull[0] = new Point(-10, -1); // M1
        hull[1] = new Point(-9, 2);
        hull[2] = new Point(-8, 4);
        hull[3] = new Point(-7, 5);
        hull[4] = new Point(-4, 6);
        hull[5] = new Point(1, 7);

        Point m2 = new Point(8, 8);

        Point p1 = new Point(-8, 6);
        Point p2 = new Point(-7, 6);
        Point p3 = new Point(-4, 7);
        Point p4 = new Point(-9, 3);
        Point p5 = new Point(-4, 4);

        Point p = p4;

        Point[] hull2 = hull.clone();

        Util.printSet(hull2);

        r = deal_cand_pps(hull2, p, m2, r, 1);
        hull2 = Arrays.copyOf(hull2, r + 1);

        Util.printSet(hull2);


        new Plotting(hull, hull2, true, 0);

    }

    public static void main(String[] args) {

        Point[] points = Testing.testSet9();
        Point[] points0 = Arrays.copyOf(points, points.length + 1);
        points0[points0.length - 1] = new Point(7, -9);
        //7, -9

        Point[] hull = convexHull(points0);
        Util.printSet(hull);

        new Plotting(points0, hull, true, 0);

        //int r = 2;
        //Point[] points = new Point[3];
        //points[0] = new Point(-8, 6);
        //points[1] = new Point(5, 5);
        //points[2] = new Point(7, 2);

        //Point m2 = new Point(8, -1);

        //Point v = new Point(7, 4);

        //Util.printSet(points);

        //new Plotting(points, points, true, 0);
        //Util.printSet(points);

        //r = deal_cand_pps_Q2(points.clone(), v, m2, r);
        
        //Point[] hull = Arrays.copyOf(points, r + 1);

        //new Plotting(points, hull, true, 0);

        //avr_test();

        
    } // main
}
