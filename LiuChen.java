import java.util.Arrays;

public class LiuChen {
    

    public static Point[] convexHull(Point[] points) {

        Point[] M = extremePoints(points);

        /* division by quadrant:
                y
                |
             Q1 | Q2
          ------+----->x
             Q3 | Q4
                |
        */

        Point[] Q1 = new Point[points.length]; int n1 = 0;
        Point[] Q2 = new Point[points.length]; int n2 = 0;
        Point[] Q3 = new Point[points.length]; int n3 = 0;
        Point[] Q4 = new Point[points.length]; int n4 = 0;

        // Split candidates into 4 groups,
        // depending on which quadrant they lie in
        for(int i = 0; i < points.length; i++) {
            if(is_cand_pps(M[0], M[1], points[i]))
                Q1[n1++] = points[i];
            else if(is_cand_pps(M[2], M[3], points[i]))
                Q2[n2++] = points[i];
            else if(is_cand_pps(M[4], M[5], points[i]))
                Q3[n3++] = points[i];
            else if(is_cand_pps(M[6], M[7], points[i]))
                Q4[n4++] = points[i];
        }

        // Resize arrays
        Q1 = Arrays.copyOf(Q1, n1);
        Q2 = Arrays.copyOf(Q2, n2);
        Q3 = Arrays.copyOf(Q3, n3);
        Q4 = Arrays.copyOf(Q4, n4);

        //System.out.println("Q1: "); Util.printSet(Q1);
        //System.out.println("Q2: "); Util.printSet(Q2);
        //System.out.println("Q3: "); Util.printSet(Q3);
        //System.out.println("Q4: "); Util.printSet(Q4);

        //System.out.println("m1: " + M[6] + "m2: " + M[7]);

        Point[] H1 = ord_chull_pps(M[0], M[1], Q1, 1);
        Point[] H2 = ord_chull_pps(M[2], M[3], Q2, 2);
        Point[] H3 = ord_chull_pps(M[4], M[5], Q3, 3);
        Point[] H4 = ord_chull_pps(M[6], M[7], Q4, 4);

        //System.out.println("H1: "); Util.printSet(H1);
        //System.out.println("H2: "); Util.printSet(H2);
        //System.out.println("H3: "); Util.printSet(H3);
        //System.out.println("H4: "); Util.printSet(H4);

        return concatHulls(H1, H2, H3, H4);
    }


    /*
        Concatenates the 4 hulls in clockwise order, omitting duplicates
    */
    public static Point[] concatHulls(final Point[] H1, final Point[] H2, final Point[] H3, final Point[] H4) {
        Point[] H = new Point[H1.length + H2.length + H3.length + H4.length];
        int r = 0;

        // H1 -----------------------------------
        for(int i = 0; i < H1.length; i++) 
            H[r++] = H1[i];
        

        // H2 -----------------------------------
        if(H[r - 1].equals(H2[0]) == false)
            H[r++] = H2[0];

        if(H2.length > 1)
            for(int i = 1; i < H2.length; i++)
                H[r++] = H2[i];


        // H3 -----------------------------------
        if(H[r - 1].equals(H3[0]) == false)
            H[r++] = H3[0];
        
        if(H3.length > 1)
            for(int i = 1; i < H3.length; i++)
                H[r++] = H3[i];

        // H4 -----------------------------------
        if(H[r - 1].equals(H4[0]) == false)
            H[r++] = H4[0];

        if(H4.length > 1)
            for(int i = 1; i < H4.length - 1; i++)
                H[r++] = H4[i];

        if(H4[H4.length - 1].equals(H[0]) == false)
            H[r++] = H4[H4.length - 1];


        return Arrays.copyOf(H, r);
    }


    /*

    */
    public static Point[] ord_chull_pps(Point m1, Point m2, Point[] Q, int quadrant) {
        Point[] H = new Point[Q.length + 2];

        int r = 0;
        H[r] = m1; 

        if(m1.equals(m2))
            return Arrays.copyOf(H, 1);

        for(int i = 0; i < Q.length; i++) {
            r = deal_cand_pps(H, Q[i], m2, r, quadrant);
        }

        r = r + 1;
        H[r] = m2;

        return Arrays.copyOf(H, r + 1);
    }


    public static boolean is_cand_pps(Point m1, Point m2, Point v) {
        return Util.S(m1, m2, v) > 0;
    }

    /*
        Linear search for active region
    */
    public static int find_sar1(Point v, int l, int u, Point[] hull, int r) {


        if(Util.S(hull[0], hull[1], v) >= 0) {
            return 0;
        }

        int res = find_sar(v, l, u, hull, r);

        for(int j = l; j <= u; j++) {
            if(Util.S(hull[j - 1], hull[j], v) < 0 && Util.S(hull[j], hull[j + 1], v) >= 0) {
                if( j != res) {
                    System.out.println("--- find_sar ---");
                    System.out.println("linear: " + j + ", binary: " + res);
                    Util.printHull_r(hull, r);
                    System.out.println("v: " + v + ", l: " + l + ", u: " + u + ", r: " + r);
                }
                return j;
            }
        }
        return -1;
    }

    /*
        Binary search for active region
    */ 
    public static int find_sar(Point v, int l, int u, Point[] hull, int r) {

        //System.out.println("Finding SAR");

        // special case:
        if(Util.S(hull[0], hull[1], v) >= 0) {
            return 0;
        }

        while(l <= u) {

            int mid = (int) Math.floor((l + u) / 2);

            float backward = Util.S(hull[mid - 1], hull[mid], v);
            float forward  = Util.S(hull[mid], hull[mid + 1], v);

            if(backward > 0 && forward >= 0)
                u = mid - 1;
            else if(backward < 0 && forward < 0)
                l = mid + 1;
            else {
                return mid;
            }

        }

        return -1;
    } // find_sar


    /*
        Linear search for inverse active region
    */
    public static int[] find_isar1(Point v, int l, int u, Point[] h) {

        int [] res =  find_isar(v, l, u, h);

        for(int k = l; k <= u; k++) {
            float sf = Util.S(h[k + 1], h[k], v); // forward border
            float sb = Util.S(h[k], h[k - 1], v); // backward border

            if(sb < 0 && sf >= 0) {
                int[] nt = new int[2];
                nt[0] = k;

                if(sf == 0.0)
                    nt[1] = 1;
                else
                    nt[1] = 0;


                if(res[0] != nt[0] || res[1] != nt[1]) {
                    System.out.println("--- find_isar ---");
                    Util.printHull_r(h, u);
                    System.out.println("l: " + l + ", u: " + u + ", v: " + v + 
                    ", n: " + nt[0] + ", t: " + nt[1] + "n_res: " + res[0] + ", t_res: " + res[1]);
                }

                return nt;
            }
        }

        return null;
    }

    /*
        Binary search for inverse active region

        returns:
            [n, t]:
                n: index of inverse active region
                t: 1 if collinear with h[n], h[n+1]

    */
    public static int[] find_isar(Point v, int l, int u, Point[] h) {
        //System.out.println("finding ISAR");

        while(l <= u) {
            int mid = (int) Math.floor((l + u) / 2);

            float forward  = Util.S(h[mid + 1], h[mid], v); 
            float backward = Util.S(h[mid], h[mid - 1], v); 

            if(backward > 0 && forward >= 0)
                u = mid - 1;
            else if(backward < 0 && forward < 0)
                l = mid + 1;
            else {
                // backward < 0 && forward >= 0
                int[] nt = new int[2];
                nt[0] = mid;

                if(forward == 0.0)
                    nt[1] = 1;
                else
                    nt[1] = 0;

                return nt;
            }

        }

        return null;
    }

    /*
        Linear search
    */
     public static int findJ1(Point v, Point[] h, int r, int quadrant) {

        int res = findJ(v, h, r, quadrant);

        switch(quadrant) {
            case 1: {
                for(int j = 0; j < r; j++) {
                    if(h[j].x < v.x && v.x <= h[j + 1].x) {

                        if(j != res) {
                            Util.printHull_r(h, r);
                            System.out.println("v:" + v + ", r: " + r + 
                            ", quadrant: " + quadrant + ", j = " + j + ", res = " + res);
                        }
                        return j;
                    }
                }
            }; break;
            case 2: {
                for(int j = 0; j < r; j++) {
                    if(h[j].y > v.y && v.y >= h[j + 1].y) {

                        if(j != res) {
                            Util.printHull_r(h, r);
                            System.out.println("v:" + v + ", r: " + r + 
                            ", quadrant: " + quadrant + ", j = " + j + ", res = " + res);
                        }
                        return j;
                    }
                }
            }; break;
            case 3: {
                for(int j = 0; j < r; j++) {
                    if(h[j].x > v.x && v.x >= h[j + 1].x) {

                        if(j != res) {
                            Util.printHull_r(h, r);
                            System.out.println("v:" + v + ", r: " + r + 
                            ", quadrant: " + quadrant + ", j = " + j + ", res = " + res);
                        }
                        return j;
                    }
                }
            }; break;
            case 4: {
                for(int j = 0; j < r; j++) {
                    if(h[j].y < v.y && v.y <= h[j + 1].y) {

                        if(j != res) {
                            Util.printHull_r(h, r);
                            System.out.println("v:" + v + ", r: " + r + 
                            ", quadrant: " + quadrant + ", j = " + j + ", res = " + res);
                        }
                        return j;
                    }
                }
            }; break;
            default:
                return -1;
        }
        return -1;
    }

    /*
        Binary search
    */
    public static int findJ(Point v, Point[] h, int r, int quadrant) {
        
        //System.out.println("Searching for J");

        if(quadrant == 1) {
            // For quadrants 1 and 2, polygon is ordered from left to right
            int l = 0, u = r;

            while(l < u) {
                int mid = (int) Math.floor((l + u) / 2);

                if(h[mid].x < v.x && v.x <= h[mid + 1].x)
                    return mid;

                if(h[mid].x < v.x && h[mid + 1].x < v.x)
                    l = mid + 1;
                else 
                    u = mid - 1;
            }
            return u;
        }
        else if(quadrant == 2) {
            int l = 0, u = r;

            while(l < u) {
                int mid = (int) Math.floor((l + u) / 2);

                if(h[mid].y > v.y && v.y >= h[mid + 1].y)
                    return mid;

                if(h[mid].y > v.y && h[mid + 1].y > v.y)
                    l = mid + 1;
                else 
                    u = mid - 1;
            }
            return u;
        }
        else if(quadrant == 3) {
            // For quadrants 3 and 4, polygon is ordered from right to left
            int l = 0, u = r;

            while(l < u) {
                int mid = (int) Math.floor((l + u) / 2);

                if(h[mid].x > v.x && v.x >= h[mid + 1].x)
                    return mid;

                if(h[mid].x > v.x && h[mid + 1].x > v.x)
                    l = mid + 1;
                else 
                    u = mid - 1;
            }
            return u;
        }
        else if(quadrant == 4) {
            int l = 0, u = r;

            while(l < u) {
                int mid = (int) Math.floor((l + u) / 2);

                if(h[mid].y < v.y && v.y <= h[mid + 1].y)
                    return mid;

                if(h[mid].y < v.y && h[mid + 1].y < v.y)
                    l = mid + 1;
                else 
                    u = mid - 1;
            }
            return u;
        }
        else {
            return -2;
        }

        // Did not find such vertex, return -1
        // (Should never happen)
        //return -1;

    } // findJ
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
        else if(h[r].x < v.x && v.x < m2.x) {
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
        //System.out.println("v: " + v + ", h[r]: " + h[r]);

        // Case 1: new point is directly above/below the last hull vertex
        if(v.y == h[r].y) {                                 
            n = -1;
            if(v.x > h[r].x) {                              
                // if new point is above, find which active 
                // region it belongs to. Otherwise, discard it
                m = find_sar(v, 1, r - 1, h, r);
            }
        }
        // Case 2: new point is to the right of the last hull vertex
        else if(h[r].y > v.y && v.y > m2.y) {               
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
                int j = findJ(v, h, r, 2);                      
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
        // Case 2: new point is to the left of the last hull vertex
        else if(h[r].x > v.x && v.x > m2.x) {
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
        else if(h[r].y < v.y && v.y < m2.y) { // <--------------------!!!!!!!!!!!!!
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
        int m = mnt[0], n = mnt[1], t = mnt[2];

        //System.out.println("m: " + mnt[0] + ", n: " + mnt[1] + ", t: " + mnt[2]);

        // Case 2:
        if(m == -1)
            return r;
    
        if(n == -1) {
            // Step 3

            // Check if candidate is ahead of the last hull vertex
            // (in order of traversal)
            boolean ahead = false;
            switch(quadrant) {
                case 1: ahead = (v.x >= h[r].x); break;
                case 2: ahead = (v.y <= h[r].y); break;
                case 3: ahead = (v.x <= h[r].x); break;
                case 4: ahead = (v.y >= h[r].y); break;
            }

            if(ahead) {     
                r = m + 1;
                h[r] = v;
                return r;
            }
            // Step 4
            else if(Util.S(v, m2, h[r]) > 0 ) { 

                //System.out.println("First");

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

            //System.out.println(m + ", " + n + ", " + t);

            // make space for v
            r++; 
            n++;
            for(int i = r; i > m; i--)
                h[i] = h[i - 1];

            // insert v after m
            h[m + 1] = v;

            if(t == 1) {
                // delete up to including h[n]
                int gap = n - m - 1;
                if(gap > 0)
                    for(int i = n + 1; i <= r; i++)
                        h[i - gap] = h[i];

                r = r - gap ;
            }
            else {
                // delete up to h[n], excluding h[n]
                int gap = n - m - 2;
                if(gap > 0) 
                    for(int i = n; i <= r; i++)
                        h[i - gap] = h[i];

                r = r - gap;
            }

            return r;

        } // Step 5

    } // deal_cand_pps






    /*
        v: point to insert
        j: integer such that: h[j].x < v.x < h[j+1].x
        h: current hull

        returns:
            [m, n, t]:
                m: index of active region
                n: index of inverse active region
                t: 1 if collinear with h[j], h[j+1], otherwise 0

                TODO: binary search

    */
    public static int[] find_avr_pps(Point v, int j, Point[] h, int r) {

        int m = -1, n = -1, t = -1;

        // Case 1: inserting immediately after h[0]
        if(j == 0) {
            m = 0;           

            if(Util.S(h[2], h[1], v) == 0) { 
                n = 1;                         // <-- decrement
                t = 1;
                // quit
            }
            else if(Util.S(h[2], h[1], v) > 0) { 
                n = 1;                            // <-- decrement
                t = 0;
                // quit
            }
            else {
                int[] nt = find_isar(v, 1, r - 1, h);
                n = nt[0];
                t = nt[1];
                // quit
            }
        }

        // Case 2: inserting before the second to last vertex
        else if(j == r - 2) {
            //System.out.println("here");
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
                    m = find_sar(v, 1, r - 3, h, r);
                    // quit
                }

            } // else step 4
            else {
                float S1 = Util.S(h[j - 1], h[j], v);
                float S2 = Util.S(h[j + 1], h[j + 2], v);
                return table(S1, S2, j, r, v, h);
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

        //System.out.println("S1" + S1 + ", S2: " + S2);

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
        else if(S1 < 0 && S2 == 0) { // <-.--
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
            m = find_sar(v, 1, j - 1, h, r);
            n = j + 1;
            t = 1;
        }
        else if(S1 > 0 && S2 < 0) {
            m = find_sar(v, 1, j - 1, h, r);
            n = j + 1;
            t = 0;
        }
        else if(S1 > 0 && S2 > 0) {
            m = find_sar(v, 1, j - 1, h, r);
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

        int r = 4;
        Point[] points = new Point[r + 1];

        int i = 0;
        //points[i++] = new Point(-12,-6);
        //points[i++] = new Point(-9,-9);
        //points[i++] = new Point(-2,-12);
        //points[i++] = new Point(-5,-11);
        //points[i++] = new Point(-8,-10); // <---
        
        points[i++] = new Point(2, -13);
        points[i++] = new Point(-2, -12);
        points[i++] = new Point(-5, -11);
        points[i++] = new Point(-9, -9);
        points[i++] = new Point(-12, -6);

        
       // Hull: (2,-13) (-2,-12) (-5,-11) (-9,-9) (-12,-6) (-13,4)
       
        Point m1 = new Point(2, -13);
        Point m2 = new Point(-13, 4);
        Point p =  new Point(-8, -10); 

        //Point[] hull = ord_chull_pps(m1, m2, points, 4);
        //Util.printSet(hull);
        //new Plotting(points, hull, true, 0);
        
        Point[] hull2 = Arrays.copyOf(points, r + 5);
        r = deal_cand_pps(hull2, p, m2, r, 4);
        r = r + 1;
        hull2[r] = m2;
        hull2 = Arrays.copyOf(hull2, r + 1);
        new Plotting(points, hull2, true, 0);

    }

    public static void debug() {
    //(1,78)(6,77)(23,73)(31,68)(43,60)(51,54)(66,42)
    //l: 4, u: 6, v: (27,72), n: 4, t: 1n_res: 5, t_res: 0
    //, binary: 5, 0, linear: 4, 1

    int r = 6;
    Point[] h = new Point[r + 1];

    int i = 0;
    h[i++] = new Point(1, 78);
    h[i++] = new Point(6, 77);
    h[i++] = new Point(23, 73);
    h[i++] = new Point(31, 68);
    h[i++] = new Point(43, 60);
    h[i++] = new Point(51, 54);
    h[i++] = new Point(66, 42);

    Point p = new Point(27, 72);

    int[] nt = find_isar1(p, 4, 6, h);

    System.out.println("n: " + nt[0] + ", t: " + nt[1]);

    }

    public static void main(String[] args) {

        //Point[] points = Testing.testSet14();
        //Point[] hull = convexHull(points);
        ////Util.printSet(hull);
        //new Plotting(points, hull, true, 0);

        //avr_test();

        debug();

        
    } // main
}
