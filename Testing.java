import java.util.HashSet;
import java.util.ArrayList;
import java.util.Collections;


public class Testing {

    public static Point[] testSet1() {
        Point points[] = new Point[7];

        points[0] = new Point(0, 3);
        points[1] = new Point(2, 3);
        points[2] = new Point(1, 1);
        points[3] = new Point(2, 1);
        points[4] = new Point(3, 0);
        points[5] = new Point(0, 0);
        points[6] = new Point(3, 3);

        return points;
    }

    // (0, 0) (0, 3) (3, 1) (4, 4)
    public static Point[] testSet2() {
        Point[] points = new Point[8];

        points[0] = new Point(0, 3);
        points[1] = new Point(1, 1);
        points[2] = new Point(2, 2);
        points[3] = new Point(4, 4);
        points[4] = new Point(0, 0);
        points[5] = new Point(1, 2);
        points[6] = new Point(3, 1);
        points[7] = new Point(3, 3);

        return points;
    }

    // primer s 3 kolinearnimi tockami
    // (-5,-3) (-1,-5) (1,-4) (0,0) (-1,1)
    public static Point[] testSet3() {
        Point[] points = new Point[10];

        points[0] = new Point(0, 0);
        points[1] = new Point(1, -4);
        points[2] = new Point(-1, -5);
        points[3] = new Point(-5, -3);
        points[4] = new Point(-3, -1);
        points[5] = new Point(-1, -3);
        points[6] = new Point(-2, -2);
        points[7] = new Point(-1, -1);
        points[8] = new Point(-2, -1);
        points[9] = new Point(-1, 1);

        return points;
    }

    public static Point[] testSet4() {
        Point[] points = new Point[17];

        points[0] = new Point(0, 0);
        points[1] = new Point(1, -4);
        points[2] = new Point(-1, -5);
        points[3] = new Point(-5, -3);
        points[4] = new Point(-3, -1);
        points[5] = new Point(-1, -3);
        points[6] = new Point(-2, -2);
        points[7] = new Point(-1, -1);
        points[8] = new Point(-2, -1);
        points[9] = new Point(-1, 1);
        points[10] = new Point(-5, 0);
        points[11] = new Point(-3, -5);
        points[12] = new Point(1, -5);
        points[13] = new Point(-5, -2);
        points[14] = new Point(-5, -6);
        points[15] = new Point(0, -6);
        points[16] = new Point(1, -2);
        //points[17] = new Point(-6, -3);
        //points[18] = new Point(3, 0);

        return points;
    }

    public static Point[] testSet5() {
        Point[] points = new Point[12];

        points[0] = new Point(0, 0);
        points[1] = new Point(0, 6);
        points[2] = new Point(5, 0);
        points[3] = new Point(0, -4);
        points[4] = new Point(-6, 0);
        points[5] = new Point(-5, 5);
        points[6] = new Point(-3, 5);
        points[7] = new Point(-2, -4);
        points[8] = new Point(-3, -4);
        points[9] = new Point(4, -3);
        points[10] = new Point(3, 4);
        points[11] = new Point(4, 4);

        return points;
    }

    public static Point[] testSet6() {
        Point[] points = new Point[20];

        points[0] = new Point(0, 0);
        points[1] = new Point(1, 1);
        points[2] = new Point(4, -1);
        points[3] = new Point(0, -4);
        points[4] = new Point(-3, -4);
        points[5] = new Point(-5, 5);
        points[6] = new Point(7, -2);
        points[7] = new Point(-2, -3);
        points[8] = new Point(4, -1);
        points[9] = new Point(4, 0);
        points[10] = new Point(1, 6);
        points[11] = new Point(2, 5);
        points[12] = new Point(-3, 7);
        points[13] = new Point(-2, -1);
        points[14] = new Point(6, 4);
        points[15] = new Point(7, 8);
        points[16] = new Point(7, -4);
        points[17] = new Point(-8, 3);
        points[18] = new Point(-8, -6);
        points[19] = new Point(2, 8);

        return points;
    }
    public static Point[] testSet7() {
        Point[] points = new Point[25];

        points[0] = new Point(0, 0);
        points[1] = new Point(1, 1);
        points[2] = new Point(4, -1);
        points[3] = new Point(0, -4);
        points[4] = new Point(-3, -4);
        points[5] = new Point(-5, 5);
        points[6] = new Point(7, -2);
        points[7] = new Point(-2, -3);
        points[8] = new Point(4, -1);
        points[9] = new Point(4, 0);
        points[10] = new Point(1, 6);
        points[11] = new Point(2, 5);
        points[12] = new Point(-3, 7);
        points[13] = new Point(-2, -1);
        points[14] = new Point(6, 4);
        points[15] = new Point(7, 8);
        points[16] = new Point(7, -4);
        points[17] = new Point(-8, 3);
        points[18] = new Point(-8, -6);
        points[19] = new Point(2, 8);
        points[20] = new Point(5, -5);
        points[21] = new Point(-5, 8);
        points[22] = new Point(8, 1);
        points[23] = new Point(8, 3);
        points[24] = new Point(-1, 8);

        return points;
    }

    // test set 7, mirrored along both axis
    public static Point[] testSet8() {
        Point[] points = testSet7();
        Point[] mirror = new Point[points.length];

        for(int i = 0; i < points.length; i++)
            mirror[i] = new Point(-points[i].x, -points[i].y);

        return mirror;
    }

    // test set 7, mirrored along the x axis
    public static Point[] testSet9() {
        Point[] points = testSet7();
        Point[] mirror = new Point[points.length];

        for(int i = 0; i < points.length; i++)
            mirror[i] = new Point(points[i].x, -points[i].y);

        return mirror;
    }


    public static Point[] testSet10() {
        Point[] points = new Point[15];

        points[0] = new Point(-10, 8);
        points[1] = new Point(-11, 7);
        points[2] = new Point(4, -2);
        points[3] = new Point(10, 0);
        points[4] = new Point(-6, -4);

        points[5] = new Point(0, 13);
        points[6] = new Point(4, -7);
        points[7] = new Point(-3, 4);
        points[8] = new Point(10, 7);
        points[9] = new Point(0, -3);

        points[10] = new Point(0, 8);
        points[11] = new Point(10, 0);
        points[12] = new Point(2, 8);
        points[13] = new Point(13, -9);
        points[14] = new Point(-4, 6);

        return points;
    }


    public static Point[] testSet11() {

        Point[] points = new Point[30];
    
        points[0] = new Point(-3, -1);
        points[1] = new Point(2, 3);
        points[2] = new Point(1, -12);
        points[3] = new Point(-3, 11);
        points[4] = new Point(-2, 6);

        points[5] = new Point(-6, -14);
        points[6] = new Point(2, 13);
        points[7] = new Point(-3, 11);
        points[8] = new Point(12, -8);
        points[9] = new Point(-12, -8);

        points[10] = new Point(4, 11);
        points[11] = new Point(11, 2);
        points[12] = new Point(5, -12);
        points[13] = new Point(-12, -1);
        points[14] = new Point(4, -6);

        points[15] = new Point(-4, 0);
        points[16] = new Point(-8, 10);
        points[17] = new Point(-4, -12);
        points[18] = new Point(-13, -5);
        points[19] = new Point(13, -4);

        points[20] = new Point(12, 2);
        points[21] = new Point(13, 8);
        points[22] = new Point(10, 2);
        points[23] = new Point(-4, 1);
        points[24] = new Point(-6, 6);

        points[25] = new Point(-8, 14);
        points[26] = new Point(4, 9);
        points[27] = new Point(14, 5);
        points[28] = new Point(-13, 0);
        points[29] = new Point(-9, 10);


        return points;
    }


    public static Point[] generateRectangle(int n, int xspan, int yspan) {

        // use set to prevent duplicates
        HashSet<Point> points = new HashSet<Point>();

        // generate `n` random points
        while(points.size() < n){

            int x = Util.randomInt(-xspan, xspan);
            int y = Util.randomInt(-yspan, yspan);

            points.add(new Point(x, y));
        }

        ArrayList<Point> ar = new ArrayList<Point>(points);
        Collections.shuffle(ar);
        return points.toArray(new Point[ar.size()]);
    }


    public static Point[] generateCircle(int n, int radius) {

        // use set to prevent duplicates
        HashSet<Point> points = new HashSet<Point>();

        while(points.size() < n) {

            double phi = Math.random() * 2 * Math.PI;
            int r = Util.randomInt(0, radius);

            int x = (int) (r * Math.cos(phi));
            int y = (int) (r * Math.sin(phi));

            points.add(new Point(x, y));
        }

        ArrayList<Point> ar = new ArrayList<Point>(points);
        Collections.shuffle(ar);
        return ar.toArray(new Point[ar.size()]);
    }

    public static Point[] testSet12() {

        Point[] points = new Point[200];

        int i = 0;

        points[i++] = new Point(0,-1);
        points[i++] = new Point(0,0);
        points[i++] = new Point(0,-2);
        points[i++] = new Point(0,1);
        points[i++] = new Point(0,2);
        points[i++] = new Point(0,3);
        points[i++] = new Point(0,5);
        points[i++] = new Point(0,-6);
        points[i++] = new Point(0,-7);
        points[i++] = new Point(0,7);
        points[i++] = new Point(0,8);
        points[i++] = new Point(0,-10);
        points[i++] = new Point(0,11);
        points[i++] = new Point(-13,3);
        points[i++] = new Point(-13,1);
        points[i++] = new Point(-13,-3);
        points[i++] = new Point(13,2);
        points[i++] = new Point(-12,3);
        points[i++] = new Point(-12,2);
        points[i++] = new Point(12,-2);
        points[i++] = new Point(12,0);
        points[i++] = new Point(-12,-3);
        points[i++] = new Point(12,3);
        points[i++] = new Point(12,5);
        points[i++] = new Point(11,-7);
        points[i++] = new Point(-11,5);
        points[i++] = new Point(11,-1);
        points[i++] = new Point(11,2);
        points[i++] = new Point(11,4);
        points[i++] = new Point(11,7);
        points[i++] = new Point(10,-9);
        points[i++] = new Point(10,-8);
        points[i++] = new Point(-10,7);
        points[i++] = new Point(-10,6);
        points[i++] = new Point(-10,5);
        points[i++] = new Point(-10,2);
        points[i++] = new Point(-10,1);
        points[i++] = new Point(-10,0);
        points[i++] = new Point(-10,-1);
        points[i++] = new Point(10,2);
        points[i++] = new Point(-10,-3);
        points[i++] = new Point(10,3);
        points[i++] = new Point(10,4);
        points[i++] = new Point(10,5);
        points[i++] = new Point(9,-10);
        points[i++] = new Point(9,-8);
        points[i++] = new Point(-9,7);
        points[i++] = new Point(9,-6);
        points[i++] = new Point(9,-5);
        points[i++] = new Point(-9,3);
        points[i++] = new Point(-9,2);
        points[i++] = new Point(9,-1);
        points[i++] = new Point(-9,-1);
        points[i++] = new Point(9,1);
        points[i++] = new Point(-9,-2);
        points[i++] = new Point(9,3);
        points[i++] = new Point(9,8);
        points[i++] = new Point(-9,-10);
        points[i++] = new Point(8,-8);
        points[i++] = new Point(8,-7);
        points[i++] = new Point(8,-5);
        points[i++] = new Point(-8,2);
        points[i++] = new Point(8,-3);
        points[i++] = new Point(-8,0);
        points[i++] = new Point(8,0);
        points[i++] = new Point(8,1);
        points[i++] = new Point(8,2);
        points[i++] = new Point(-8,-6);
        points[i++] = new Point(-8,-8);
        points[i++] = new Point(8,9);
        points[i++] = new Point(8,11);
        points[i++] = new Point(7,-10);
        points[i++] = new Point(-7,8);
        points[i++] = new Point(7,-8);
        points[i++] = new Point(-7,5);
        points[i++] = new Point(7,-2);
        points[i++] = new Point(7,-1);
        points[i++] = new Point(7,0);
        points[i++] = new Point(-7,-2);
        points[i++] = new Point(-7,-4);
        points[i++] = new Point(-7,-6);
        points[i++] = new Point(7,7);
        points[i++] = new Point(-7,-9);
        points[i++] = new Point(7,9);
        points[i++] = new Point(-7,-10);
        points[i++] = new Point(-7,-11);
        points[i++] = new Point(7,11);
        points[i++] = new Point(6,-12);
        points[i++] = new Point(6,-11);
        points[i++] = new Point(-6,8);
        points[i++] = new Point(6,-7);
        points[i++] = new Point(-6,0);
        points[i++] = new Point(6,0);
        points[i++] = new Point(6,1);
        points[i++] = new Point(6,2);
        points[i++] = new Point(-6,-3);
        points[i++] = new Point(-6,-4);
        points[i++] = new Point(6,5);
        points[i++] = new Point(-6,-10);
        points[i++] = new Point(-6,-11);
        points[i++] = new Point(-5,12);
        points[i++] = new Point(6,11);
        points[i++] = new Point(5,-12);
        points[i++] = new Point(5,-11);
        points[i++] = new Point(5,-9);
        points[i++] = new Point(-5,7);
        points[i++] = new Point(-5,5);
        points[i++] = new Point(5,-3);
        points[i++] = new Point(-5,0);
        points[i++] = new Point(5,-1);
        points[i++] = new Point(5,2);
        points[i++] = new Point(-5,-3);
        points[i++] = new Point(5,3);
        points[i++] = new Point(-5,-11);
        points[i++] = new Point(5,10);
        points[i++] = new Point(-4,10);
        points[i++] = new Point(4,-6);
        points[i++] = new Point(4,-5);
        points[i++] = new Point(4,-2);
        points[i++] = new Point(-4,0);
        points[i++] = new Point(4,0);
        points[i++] = new Point(-4,-3);
        points[i++] = new Point(4,3);
        points[i++] = new Point(-4,-4);
        points[i++] = new Point(4,4);
        points[i++] = new Point(4,5);
        points[i++] = new Point(-4,-7);
        points[i++] = new Point(4,7);
        points[i++] = new Point(-4,-8);
        points[i++] = new Point(4,8);
        points[i++] = new Point(-3,13);
        points[i++] = new Point(-4,-11);
        points[i++] = new Point(4,11);
        points[i++] = new Point(-3,12);
        points[i++] = new Point(-3,11);
        points[i++] = new Point(3,-11);
        points[i++] = new Point(3,-8);
        points[i++] = new Point(-3,6);
        points[i++] = new Point(3,-5);
        points[i++] = new Point(-3,3);
        points[i++] = new Point(-3,2);
        points[i++] = new Point(3,-3);
        points[i++] = new Point(3,-2);
        points[i++] = new Point(3,-1);
        points[i++] = new Point(-3,0);
        points[i++] = new Point(3,0);
        points[i++] = new Point(3,1);
        points[i++] = new Point(-3,-3);
        points[i++] = new Point(3,3);
        points[i++] = new Point(-3,-4);
        points[i++] = new Point(-3,-5);
        points[i++] = new Point(3,8);
        points[i++] = new Point(3,9);
        points[i++] = new Point(-2,13);
        points[i++] = new Point(-3,-11);
        points[i++] = new Point(3,11);
        points[i++] = new Point(2,-11);
        points[i++] = new Point(-2,10);
        points[i++] = new Point(2,-7);
        points[i++] = new Point(-2,5);
        points[i++] = new Point(-2,3);
        points[i++] = new Point(2,-4);
        points[i++] = new Point(-2,1);
        points[i++] = new Point(2,-2);
        points[i++] = new Point(-2,0);
        points[i++] = new Point(2,0);
        points[i++] = new Point(-2,-1);
        points[i++] = new Point(2,1);
        points[i++] = new Point(-2,-3);
        points[i++] = new Point(2,3);
        points[i++] = new Point(-2,-5);
        points[i++] = new Point(2,4);
        points[i++] = new Point(-2,-6);
        points[i++] = new Point(2,7);
        points[i++] = new Point(-2,-11);
        points[i++] = new Point(-1,13);
        points[i++] = new Point(1,-12);
        points[i++] = new Point(-2,-13);
        points[i++] = new Point(-1,6);
        points[i++] = new Point(1,-7);
        points[i++] = new Point(-1,5);
        points[i++] = new Point(-1,4);
        points[i++] = new Point(-1,3);
        points[i++] = new Point(-1,2);
        points[i++] = new Point(1,-3);
        points[i++] = new Point(1,-2);
        points[i++] = new Point(-1,1);
        points[i++] = new Point(1,-1);
        points[i++] = new Point(-1,0);
        points[i++] = new Point(1,0);
        points[i++] = new Point(-1,-1);
        points[i++] = new Point(1,1);
        points[i++] = new Point(-1,-2);
        points[i++] = new Point(-1,-3);
        points[i++] = new Point(1,3);
        points[i++] = new Point(1,4);
        points[i++] = new Point(-1,-7);
        points[i++] = new Point(-1,-8);
        points[i++] = new Point(-1,-9);
        points[i++] = new Point(1,9);
        return points;
    }
   
    public static Point[] testSet13() {

        Point[] points = new Point[200];
        int i = 0;

        points[i++] = new Point(11,-7);
        points[i++] = new Point(-12,2);
        points[i++] = new Point(0,-5);
        points[i++] = new Point(-4,-3);
        points[i++] = new Point(7,-6);
        points[i++] = new Point(-6,1);
        points[i++] = new Point(-11,-2);
        points[i++] = new Point(-5,8);
        points[i++] = new Point(9,3);
        points[i++] = new Point(12,-3);
        points[i++] = new Point(11,-4);
        points[i++] = new Point(13,4);
        points[i++] = new Point(12,6);
        points[i++] = new Point(-3,2);
        points[i++] = new Point(1,12);
        points[i++] = new Point(4,-3);
        points[i++] = new Point(9,-3);
        points[i++] = new Point(6,2);
        points[i++] = new Point(12,0);
        points[i++] = new Point(-5,0);
        points[i++] = new Point(-1,-12);
        points[i++] = new Point(-6,12);
        points[i++] = new Point(2,6);
        points[i++] = new Point(-11,1);
        points[i++] = new Point(6,12);
        points[i++] = new Point(2,3);
        points[i++] = new Point(-5,4);
        points[i++] = new Point(3,12);
        points[i++] = new Point(-7,1);
        points[i++] = new Point(9,-1);
        points[i++] = new Point(-12,-6);
        points[i++] = new Point(-10,2);
        points[i++] = new Point(13,3);
        points[i++] = new Point(-9,-9);
        points[i++] = new Point(-3,-3);
        points[i++] = new Point(11,2);
        points[i++] = new Point(-9,-6);
        points[i++] = new Point(11,0);
        points[i++] = new Point(9,-9);
        points[i++] = new Point(11,-3);
        points[i++] = new Point(0,-1);
        points[i++] = new Point(0,-11);
        points[i++] = new Point(-3,8);
        points[i++] = new Point(-9,-2);
        points[i++] = new Point(-6,4);
        points[i++] = new Point(-5,1);
        points[i++] = new Point(-6,-3);
        points[i++] = new Point(-2,7);
        points[i++] = new Point(-3,12);
        points[i++] = new Point(7,-9);
        points[i++] = new Point(2,1);
        points[i++] = new Point(5,-11);
        points[i++] = new Point(-10,5);
        points[i++] = new Point(-1,0);
        points[i++] = new Point(-4,-2);
        points[i++] = new Point(-3,1);
        points[i++] = new Point(-8,10);
        points[i++] = new Point(5,-9);
        points[i++] = new Point(1,-1);
        points[i++] = new Point(8,0);
        points[i++] = new Point(1,-12);
        points[i++] = new Point(0,-2);
        points[i++] = new Point(2,-13);
        points[i++] = new Point(3,2);
        points[i++] = new Point(-7,-6);
        points[i++] = new Point(-12,3);
        points[i++] = new Point(0,-12);
        points[i++] = new Point(5,-5);
        points[i++] = new Point(5,0);
        points[i++] = new Point(-3,13);
        points[i++] = new Point(5,-1);
        points[i++] = new Point(-6,0);
        points[i++] = new Point(6,-4);
        points[i++] = new Point(-4,0);
        points[i++] = new Point(3,0);
        points[i++] = new Point(2,-10);
        points[i++] = new Point(-9,-5);
        points[i++] = new Point(7,-5);
        points[i++] = new Point(-3,-1);
        points[i++] = new Point(-2,1);
        points[i++] = new Point(0,2);
        points[i++] = new Point(-3,-5);
        points[i++] = new Point(7,-2);
        points[i++] = new Point(3,-6);
        points[i++] = new Point(3,-3);
        points[i++] = new Point(0,12);
        points[i++] = new Point(-5,2);
        points[i++] = new Point(3,-13);
        points[i++] = new Point(-4,1);
        points[i++] = new Point(1,3);
        points[i++] = new Point(1,-9);
        points[i++] = new Point(-2,-1);
        points[i++] = new Point(11,-5);
        points[i++] = new Point(3,-2);
        points[i++] = new Point(1,-4);
        points[i++] = new Point(0,4);
        points[i++] = new Point(9,5);
        points[i++] = new Point(5,-8);
        points[i++] = new Point(10,-1);
        points[i++] = new Point(-3,-7);
        points[i++] = new Point(4,0);
        points[i++] = new Point(7,1);
        points[i++] = new Point(11,-6);
        points[i++] = new Point(8,-1);
        points[i++] = new Point(-9,-4);
        points[i++] = new Point(-3,6);
        points[i++] = new Point(-11,5);
        points[i++] = new Point(2,0);
        points[i++] = new Point(6,7);
        points[i++] = new Point(-6,5);
        points[i++] = new Point(4,2);
        points[i++] = new Point(5,10);
        points[i++] = new Point(5,11);
        points[i++] = new Point(-4,7);
        points[i++] = new Point(-1,-4);
        points[i++] = new Point(-11,-1);
        points[i++] = new Point(0,-6);
        points[i++] = new Point(0,0);
        points[i++] = new Point(-1,1);
        points[i++] = new Point(-2,-2);
        points[i++] = new Point(-7,-9);
        points[i++] = new Point(-5,-4);
        points[i++] = new Point(2,-1);
        points[i++] = new Point(-9,6);
        points[i++] = new Point(-12,5);
        points[i++] = new Point(10,4);
        points[i++] = new Point(13,-1);
        points[i++] = new Point(1,-5);
        points[i++] = new Point(-5,-6);
        points[i++] = new Point(1,-3);
        points[i++] = new Point(1,-2);
        points[i++] = new Point(7,6);
        points[i++] = new Point(4,1);
        points[i++] = new Point(12,-1);
        points[i++] = new Point(5,-2);
        points[i++] = new Point(1,10);
        points[i++] = new Point(-7,-10);
        points[i++] = new Point(9,-7);
        points[i++] = new Point(-5,-7);
        points[i++] = new Point(8,8);
        points[i++] = new Point(-4,9);
        points[i++] = new Point(-3,4);
        points[i++] = new Point(3,-1);
        points[i++] = new Point(-2,13);
        points[i++] = new Point(6,-12);
        points[i++] = new Point(0,5);
        points[i++] = new Point(5,6);
        points[i++] = new Point(1,0);
        points[i++] = new Point(-6,2);
        points[i++] = new Point(7,-10);
        points[i++] = new Point(0,-8);
        points[i++] = new Point(2,2);
        points[i++] = new Point(-6,7);
        points[i++] = new Point(1,-8);
        points[i++] = new Point(9,-5);
        points[i++] = new Point(-1,2);
        points[i++] = new Point(0,1);
        points[i++] = new Point(-8,6);
        points[i++] = new Point(10,6);
        points[i++] = new Point(-2,0);
        points[i++] = new Point(-7,2);
        points[i++] = new Point(10,0);
        points[i++] = new Point(5,-4);
        points[i++] = new Point(2,-8);
        points[i++] = new Point(9,-2);
        points[i++] = new Point(-4,-7);
        points[i++] = new Point(0,-7);
        points[i++] = new Point(2,-11);
        points[i++] = new Point(0,-4);
        points[i++] = new Point(-4,-5);
        points[i++] = new Point(-13,4);
        points[i++] = new Point(-1,9);
        points[i++] = new Point(-7,9);
        points[i++] = new Point(-2,5);
        points[i++] = new Point(-2,2);
        points[i++] = new Point(-2,-12);
        points[i++] = new Point(9,4);
        points[i++] = new Point(-4,6);
        points[i++] = new Point(3,5);
        points[i++] = new Point(-4,-8);
        points[i++] = new Point(-2,-6);
        points[i++] = new Point(7,-4);
        points[i++] = new Point(-8,0);
        points[i++] = new Point(-4,-10);
        points[i++] = new Point(4,5);
        points[i++] = new Point(3,-7);
        points[i++] = new Point(-8,-4);
        points[i++] = new Point(8,3);
        points[i++] = new Point(-5,-11);
        points[i++] = new Point(6,-11);
        points[i++] = new Point(5,-6);
        points[i++] = new Point(7,0);
        points[i++] = new Point(0,3);
        points[i++] = new Point(-5,7);
        points[i++] = new Point(3,6);
        points[i++] = new Point(-8,-10);
        points[i++] = new Point(-1,4);
        points[i++] = new Point(-9,10);
        points[i++] = new Point(-3,0);
        points[i++] = new Point(-7,10);
        
        return points;
    }

    public static Point[] testSet14() {

        Point[] points = new Point[400];

        int i = 0;
        points[i++] = new Point(9,12);
        points[i++] = new Point(-11,-2);
        points[i++] = new Point(-9,19);
        points[i++] = new Point(-5,-9);
        points[i++] = new Point(19,0);
        points[i++] = new Point(-14,2);
        points[i++] = new Point(-12,2);
        points[i++] = new Point(14,21);
        points[i++] = new Point(-24,-7);
        points[i++] = new Point(8,1);
        points[i++] = new Point(3,14);
        points[i++] = new Point(9,25);
        points[i++] = new Point(10,-3);
        points[i++] = new Point(2,11);
        points[i++] = new Point(-2,-20);
        points[i++] = new Point(-2,22);
        points[i++] = new Point(-2,-10);
        points[i++] = new Point(-13,-2);
        points[i++] = new Point(-7,-9);
        points[i++] = new Point(-1,-8);
        points[i++] = new Point(3,-7);
        points[i++] = new Point(6,-6);
        points[i++] = new Point(-12,-14);
        points[i++] = new Point(-4,-6);
        points[i++] = new Point(-19,-13);
        points[i++] = new Point(18,-4);
        points[i++] = new Point(26,9);
        points[i++] = new Point(-16,-22);
        points[i++] = new Point(4,-18);
        points[i++] = new Point(-8,11);
        points[i++] = new Point(-3,19);
        points[i++] = new Point(12,-15);
        points[i++] = new Point(20,13);
        points[i++] = new Point(7,-1);
        points[i++] = new Point(-16,11);
        points[i++] = new Point(26,-9);
        points[i++] = new Point(9,-1);
        points[i++] = new Point(5,-1);
        points[i++] = new Point(5,-3);
        points[i++] = new Point(-3,17);
        points[i++] = new Point(3,4);
        points[i++] = new Point(-1,0);
        points[i++] = new Point(5,2);
        points[i++] = new Point(8,10);
        points[i++] = new Point(2,6);
        points[i++] = new Point(-2,19);
        points[i++] = new Point(-21,10);
        points[i++] = new Point(4,-4);
        points[i++] = new Point(6,-5);
        points[i++] = new Point(15,4);
        points[i++] = new Point(-12,-25);
        points[i++] = new Point(-6,-1);
        points[i++] = new Point(-6,-19);
        points[i++] = new Point(15,8);
        points[i++] = new Point(-14,19);
        points[i++] = new Point(-27,-8);
        points[i++] = new Point(19,21);
        points[i++] = new Point(-22,15);
        points[i++] = new Point(0,-9);
        points[i++] = new Point(-1,7);
        points[i++] = new Point(3,25);
        points[i++] = new Point(-21,14);
        points[i++] = new Point(-13,-18);
        points[i++] = new Point(15,3);
        points[i++] = new Point(22,0);
        points[i++] = new Point(1,-16);
        points[i++] = new Point(0,-3);
        points[i++] = new Point(-24,-3);
        points[i++] = new Point(-19,-2);
        points[i++] = new Point(1,-25);
        points[i++] = new Point(-11,0);
        points[i++] = new Point(3,23);
        points[i++] = new Point(-7,0);
        points[i++] = new Point(16,-9);
        points[i++] = new Point(12,4);
        points[i++] = new Point(-17,-8);
        points[i++] = new Point(10,-4);
        points[i++] = new Point(13,16);
        points[i++] = new Point(5,-24);
        points[i++] = new Point(-7,20);
        points[i++] = new Point(-13,23);
        points[i++] = new Point(-9,-16);
        points[i++] = new Point(-7,-1);
        points[i++] = new Point(-9,-19);
        points[i++] = new Point(3,-15);
        points[i++] = new Point(-1,-17);
        points[i++] = new Point(-12,8);
        points[i++] = new Point(24,-2);
        points[i++] = new Point(-16,-9);
        points[i++] = new Point(2,-22);
        points[i++] = new Point(-3,0);
        points[i++] = new Point(20,-20);
        points[i++] = new Point(18,-3);
        points[i++] = new Point(2,-13);
        points[i++] = new Point(-20,0);
        points[i++] = new Point(10,-24);
        points[i++] = new Point(0,-10);
        points[i++] = new Point(11,13);
        points[i++] = new Point(9,-23);
        points[i++] = new Point(-19,-7);
        points[i++] = new Point(27,0);
        points[i++] = new Point(-16,21);
        points[i++] = new Point(17,-2);
        points[i++] = new Point(14,-5);
        points[i++] = new Point(-4,-3);
        points[i++] = new Point(27,8);
        points[i++] = new Point(25,-11);
        points[i++] = new Point(-2,3);
        points[i++] = new Point(1,0);
        points[i++] = new Point(-4,0);
        points[i++] = new Point(25,-6);
        points[i++] = new Point(-6,6);
        points[i++] = new Point(27,6);
        points[i++] = new Point(3,3);
        points[i++] = new Point(-21,-16);
        points[i++] = new Point(-10,-5);
        points[i++] = new Point(11,-14);
        points[i++] = new Point(-4,-17);
        points[i++] = new Point(22,5);
        points[i++] = new Point(-19,6);
        points[i++] = new Point(-26,-7);
        points[i++] = new Point(-2,-4);
        points[i++] = new Point(-15,-9);
        points[i++] = new Point(-2,24);
        points[i++] = new Point(7,-12);
        points[i++] = new Point(8,14);
        points[i++] = new Point(-18,5);
        points[i++] = new Point(3,-2);
        points[i++] = new Point(-3,-7);
        points[i++] = new Point(7,-2);
        points[i++] = new Point(3,16);
        points[i++] = new Point(5,-18);
        points[i++] = new Point(-9,18);
        points[i++] = new Point(0,-1);
        points[i++] = new Point(10,12);
        points[i++] = new Point(19,5);
        points[i++] = new Point(5,18);
        points[i++] = new Point(-9,-1);
        points[i++] = new Point(6,6);
        points[i++] = new Point(-19,7);
        points[i++] = new Point(7,4);
        points[i++] = new Point(24,-7);
        points[i++] = new Point(4,14);
        points[i++] = new Point(-14,9);
        points[i++] = new Point(10,10);
        points[i++] = new Point(1,2);
        points[i++] = new Point(-2,4);
        points[i++] = new Point(1,-3);
        points[i++] = new Point(-13,25);
        points[i++] = new Point(9,-15);
        points[i++] = new Point(9,7);
        points[i++] = new Point(-8,-6);
        points[i++] = new Point(-13,9);
        points[i++] = new Point(6,7);
        points[i++] = new Point(9,-17);
        points[i++] = new Point(0,2);
        points[i++] = new Point(-3,-3);
        points[i++] = new Point(25,-9);
        points[i++] = new Point(4,-3);
        points[i++] = new Point(5,14);
        points[i++] = new Point(7,15);
        points[i++] = new Point(-7,-10);
        points[i++] = new Point(-25,-7);
        points[i++] = new Point(-12,7);
        points[i++] = new Point(-14,15);
        points[i++] = new Point(4,-22);
        points[i++] = new Point(5,5);
        points[i++] = new Point(-15,10);
        points[i++] = new Point(-19,18);
        points[i++] = new Point(-15,0);
        points[i++] = new Point(12,11);
        points[i++] = new Point(-10,-4);
        points[i++] = new Point(-22,16);
        points[i++] = new Point(-22,0);
        points[i++] = new Point(18,-18);
        points[i++] = new Point(4,19);
        points[i++] = new Point(20,8);
        points[i++] = new Point(-17,-19);
        points[i++] = new Point(-13,13);
        points[i++] = new Point(8,-23);
        points[i++] = new Point(-7,15);
        points[i++] = new Point(-17,-12);
        points[i++] = new Point(0,-2);
        points[i++] = new Point(2,5);
        points[i++] = new Point(2,-11);
        points[i++] = new Point(-11,16);
        points[i++] = new Point(17,3);
        points[i++] = new Point(5,-8);
        points[i++] = new Point(5,-10);
        points[i++] = new Point(-15,-4);
        points[i++] = new Point(-9,-14);
        points[i++] = new Point(18,0);
        points[i++] = new Point(-2,-25);
        points[i++] = new Point(-4,-2);
        points[i++] = new Point(14,-6);
        points[i++] = new Point(12,-4);
        points[i++] = new Point(-6,-15);
        points[i++] = new Point(6,16);
        points[i++] = new Point(-7,-5);
        points[i++] = new Point(3,-21);
        points[i++] = new Point(-1,2);
        points[i++] = new Point(-3,3);
        points[i++] = new Point(0,4);
        points[i++] = new Point(0,-5);
        points[i++] = new Point(11,17);
        points[i++] = new Point(-10,3);
        points[i++] = new Point(0,-13);
        points[i++] = new Point(22,2);
        points[i++] = new Point(0,-4);
        points[i++] = new Point(-11,8);
        points[i++] = new Point(6,0);
        points[i++] = new Point(-13,4);
        points[i++] = new Point(12,-10);
        points[i++] = new Point(-13,-10);
        points[i++] = new Point(-20,-9);
        points[i++] = new Point(-18,-13);
        points[i++] = new Point(-17,-3);
        points[i++] = new Point(-12,-26);
        points[i++] = new Point(7,23);
        points[i++] = new Point(7,-3);
        points[i++] = new Point(6,-16);
        points[i++] = new Point(16,4);
        points[i++] = new Point(10,-10);
        points[i++] = new Point(-6,17);
        points[i++] = new Point(7,11);
        points[i++] = new Point(1,1);
        points[i++] = new Point(4,-15);
        points[i++] = new Point(-11,9);
        points[i++] = new Point(4,1);
        points[i++] = new Point(26,1);
        points[i++] = new Point(7,0);
        points[i++] = new Point(3,0);
        points[i++] = new Point(-3,2);
        points[i++] = new Point(13,9);
        points[i++] = new Point(-1,-2);
        points[i++] = new Point(-7,9);
        points[i++] = new Point(-1,-10);
        points[i++] = new Point(-5,-2);
        points[i++] = new Point(12,-6);
        points[i++] = new Point(4,26);
        points[i++] = new Point(4,-1);
        points[i++] = new Point(20,12);
        points[i++] = new Point(23,-10);
        points[i++] = new Point(9,11);
        points[i++] = new Point(0,-11);
        points[i++] = new Point(-14,-4);
        points[i++] = new Point(-14,-20);
        points[i++] = new Point(-4,-22);
        points[i++] = new Point(7,16);
        points[i++] = new Point(6,18);
        points[i++] = new Point(1,4);
        points[i++] = new Point(-16,14);
        points[i++] = new Point(8,-10);
        points[i++] = new Point(-4,-9);
        points[i++] = new Point(16,-23);
        points[i++] = new Point(-28,-2);
        points[i++] = new Point(23,9);
        points[i++] = new Point(19,11);
        points[i++] = new Point(16,7);
        points[i++] = new Point(-5,0);
        points[i++] = new Point(7,-5);
        points[i++] = new Point(19,-18);
        points[i++] = new Point(6,10);
        points[i++] = new Point(-22,-9);
        points[i++] = new Point(-5,10);
        points[i++] = new Point(-6,-20);
        points[i++] = new Point(-28,3);
        points[i++] = new Point(10,20);
        points[i++] = new Point(-11,15);
        points[i++] = new Point(24,-13);
        points[i++] = new Point(15,21);
        points[i++] = new Point(-2,6);
        points[i++] = new Point(19,-8);
        points[i++] = new Point(8,-2);
        points[i++] = new Point(8,-7);
        points[i++] = new Point(5,8);
        points[i++] = new Point(16,-7);
        points[i++] = new Point(7,-14);
        points[i++] = new Point(11,6);
        points[i++] = new Point(9,-10);
        points[i++] = new Point(-21,16);
        points[i++] = new Point(25,3);
        points[i++] = new Point(-6,-28);
        points[i++] = new Point(-12,-13);
        points[i++] = new Point(-11,4);
        points[i++] = new Point(14,-3);
        points[i++] = new Point(14,-4);
        points[i++] = new Point(6,-26);
        points[i++] = new Point(-4,12);
        points[i++] = new Point(8,16);
        points[i++] = new Point(13,1);
        points[i++] = new Point(12,-11);
        points[i++] = new Point(-22,-15);
        points[i++] = new Point(8,20);
        points[i++] = new Point(-23,17);
        points[i++] = new Point(-8,20);
        points[i++] = new Point(-11,-1);
        points[i++] = new Point(15,-24);
        points[i++] = new Point(-1,1);
        points[i++] = new Point(-21,13);
        points[i++] = new Point(24,-11);
        points[i++] = new Point(3,-1);
        points[i++] = new Point(16,0);
        points[i++] = new Point(-4,6);
        points[i++] = new Point(-7,5);
        points[i++] = new Point(-12,22);
        points[i++] = new Point(15,14);
        points[i++] = new Point(-1,-3);
        points[i++] = new Point(0,7);
        points[i++] = new Point(-5,4);
        points[i++] = new Point(18,16);
        points[i++] = new Point(15,0);
        points[i++] = new Point(-19,2);
        points[i++] = new Point(4,-6);
        points[i++] = new Point(-12,17);
        points[i++] = new Point(-7,3);
        points[i++] = new Point(20,17);
        points[i++] = new Point(-3,-10);
        points[i++] = new Point(-28,2);
        points[i++] = new Point(23,1);
        points[i++] = new Point(8,0);
        points[i++] = new Point(3,28);
        points[i++] = new Point(2,-2);
        points[i++] = new Point(-27,-1);
        points[i++] = new Point(-1,-15);
        points[i++] = new Point(-14,0);
        points[i++] = new Point(11,8);
        points[i++] = new Point(-21,-12);
        points[i++] = new Point(-7,10);
        points[i++] = new Point(-5,15);
        points[i++] = new Point(-13,-25);
        points[i++] = new Point(-1,16);
        points[i++] = new Point(-18,7);
        points[i++] = new Point(-4,-27);
        points[i++] = new Point(-2,-21);
        points[i++] = new Point(-14,1);
        points[i++] = new Point(8,-12);
        points[i++] = new Point(2,0);
        points[i++] = new Point(4,0);
        points[i++] = new Point(5,-11);
        points[i++] = new Point(-4,-4);
        points[i++] = new Point(-1,6);
        points[i++] = new Point(-22,-13);
        points[i++] = new Point(-2,0);
        points[i++] = new Point(-4,-10);
        points[i++] = new Point(-6,2);
        points[i++] = new Point(-10,-24);
        points[i++] = new Point(14,4);
        points[i++] = new Point(0,-14);
        points[i++] = new Point(-16,-17);
        points[i++] = new Point(24,-8);
        points[i++] = new Point(-2,18);
        points[i++] = new Point(6,-3);
        points[i++] = new Point(14,-12);
        points[i++] = new Point(-6,-4);
        points[i++] = new Point(-16,0);
        points[i++] = new Point(-23,0);
        points[i++] = new Point(-2,-1);
        points[i++] = new Point(5,-20);
        points[i++] = new Point(-6,10);
        points[i++] = new Point(-23,-13);
        points[i++] = new Point(12,-3);
        points[i++] = new Point(21,19);
        points[i++] = new Point(1,12);
        points[i++] = new Point(3,-27);
        points[i++] = new Point(6,3);
        points[i++] = new Point(-1,-1);
        points[i++] = new Point(8,4);
        points[i++] = new Point(-26,6);
        points[i++] = new Point(12,-5);
        points[i++] = new Point(5,-2);
        points[i++] = new Point(-2,2);
        points[i++] = new Point(-8,8);
        points[i++] = new Point(5,0);
        points[i++] = new Point(-20,4);
        points[i++] = new Point(24,2);
        points[i++] = new Point(-21,15);
        points[i++] = new Point(2,2);
        points[i++] = new Point(0,0);
        points[i++] = new Point(14,8);
        points[i++] = new Point(6,-15);
        points[i++] = new Point(2,1);
        points[i++] = new Point(3,-19);
        points[i++] = new Point(11,5);
        points[i++] = new Point(-18,1);
        points[i++] = new Point(-19,-16);
        points[i++] = new Point(-8,-5);
        points[i++] = new Point(-4,9);
        points[i++] = new Point(-4,20);
        points[i++] = new Point(28,-4);
        points[i++] = new Point(-7,14);
        points[i++] = new Point(14,10);
        points[i++] = new Point(0,3);
        points[i++] = new Point(14,-2);
        points[i++] = new Point(-2,-8);
        points[i++] = new Point(-4,-18);
        points[i++] = new Point(3,12);
        points[i++] = new Point(6,23);
        points[i++] = new Point(2,4);
        points[i++] = new Point(-24,-5);
     
        return points;
    }

    public static Point[] testSet15() {

        Point[] points = new Point[200];
        int i = 0;
//(2,-7) (0,7) (-4,8) (4,-2) (0,2) (1,2) (-1,-7) (-8,5) (-3,3) (6,0) (-13,-3) (3,0) (-11,2) (-3,0) (1,4) (-3,6) (6,-1) (-2,4) (6,9) (-3,9) (-7,1) (2,0) (2,1) (-7,-2) (3,-1) (8,-6) (-5,-8) (6,5) (1,-1) (-6,-2) (0,-3) (0,-2) (4,-9) (8,4) (0,8) (2,4) (1,-4) (-2,-9) (-1,0) (-1,1) (5,2) (-7,-5) (-7,0) (-10,2) (-5,11) (-9,-3) (7,0) (4,4) (-3,-13) (-5,-5) (-3,2) (0,-1) (-4,-3) (-12,-6) (0,12) (-5,8) (-5,-4) (11,-8) (7,10) (-2,0) (-7,9) (1,9) (0,5) (2,-10) (4,0) (8,-4) (3,-12) (3,-3) (4,1) (7,-1) (9,-6) (-2,1) (-5,-3) (4,-1) (-4,1) (-9,1) (2,2) (-1,3) (-4,-6) (0,6) (-1,-2) (3,-6) (-1,6) (-7,2) (1,-2) (8,-3) (10,-6) (7,-9) (2,6) (-6,-10) (-4,-5) (-9,6) (6,-10) (9,-3) (-3,-7) (-4,0) (-12,0) (-2,-6) (3,-7) (-6,5) (8,9) (8,1) (-2,-7) (11,0) (0,-8) (-9,0) (-6,-4) (-2,9) (-4,-12) (-3,4) (-3,-3) (7,-6) (-5,-11) (0,-13) (11,6) (2,3) (1,-7) (-5,-9) (-1,13) (-11,5) (-2,5) (-6,-7) (0,10) (4,-5) (-1,-8) (5,1) (0,-12) (0,-7) (5,11) (-11,-3) (-1,-3) (-2,2) (-5,10) (-1,8) (7,5) (-1,-9) (0,0) (-10,1) (2,-5) (4,9) (1,5) (12,2) (-8,-2) (7,-5) (2,5) (0,-11) (4,11) (-13,2) (3,3) (-6,0) (5,0) (0,1) (-9,8) (-5,-6) (-2,-12) (-7,-8) (-7,-3) (-12,3) (1,0) (-4,4) (3,11) (1,-3) (-1,-5) (9,-7) (5,-12) (9,-8) (-9,-4) (-3,-6) (9,-9) (-6,2) (-2,3) (2,-9) (-10,0) (4,-13) (7,9) (-12,1) (-8,0) (7,1) (4,3) (-2,-2) (-4,5) (-6,1) (-5,9) (10,0) (5,9) (-1,9) (10,-9) (-1,-13) (8,-2) (12,0) (-7,3) (8,7) (-11,-4) (9,-1) (8,-10) (-3,-1) (3,-11) (6,6) (4,-4) (-6,-1) 
        points[i++] = new Point(0,0);
        points[i++] = new Point(0,-1);
        points[i++] = new Point(0,1);
        points[i++] = new Point(0,-2);
        points[i++] = new Point(0,2);
        points[i++] = new Point(0,-3);
        points[i++] = new Point(0,3);
        points[i++] = new Point(0,-4);
        points[i++] = new Point(0,4);
        points[i++] = new Point(0,5);
        points[i++] = new Point(0,-6);
        points[i++] = new Point(0,7);
        points[i++] = new Point(0,8);
        points[i++] = new Point(0,-10);
        points[i++] = new Point(0,9);
        points[i++] = new Point(13,0);
        points[i++] = new Point(13,2);
        points[i++] = new Point(13,4);
        points[i++] = new Point(12,-5);
        points[i++] = new Point(12,-3);
        points[i++] = new Point(12,0);
        points[i++] = new Point(-12,-3);
        points[i++] = new Point(12,2);
        points[i++] = new Point(-11,6);
        points[i++] = new Point(-11,4);
        points[i++] = new Point(-11,0);
        points[i++] = new Point(-11,-2);
        points[i++] = new Point(-11,-3);
        points[i++] = new Point(11,5);
        points[i++] = new Point(11,6);
        points[i++] = new Point(-11,-8);
        points[i++] = new Point(11,8);
        points[i++] = new Point(-10,9);
        points[i++] = new Point(10,-9);
        points[i++] = new Point(-10,7);
        points[i++] = new Point(10,-7);
        points[i++] = new Point(-10,6);
        points[i++] = new Point(10,-5);
        points[i++] = new Point(10,-4);
        points[i++] = new Point(10,-1);
        points[i++] = new Point(10,5);
        points[i++] = new Point(10,8);
        points[i++] = new Point(9,-8);
        points[i++] = new Point(-9,3);
        points[i++] = new Point(9,-2);
        points[i++] = new Point(-9,1);
        points[i++] = new Point(9,0);
        points[i++] = new Point(-9,-2);
        points[i++] = new Point(9,4);
        points[i++] = new Point(-9,-7);
        points[i++] = new Point(-9,-9);
        points[i++] = new Point(-8,9);
        points[i++] = new Point(-8,8);
        points[i++] = new Point(8,-7);
        points[i++] = new Point(-8,6);
        points[i++] = new Point(8,-4);
        points[i++] = new Point(-8,2);
        points[i++] = new Point(8,-2);
        points[i++] = new Point(-8,0);
        points[i++] = new Point(8,0);
        points[i++] = new Point(-8,-3);
        points[i++] = new Point(8,3);
        points[i++] = new Point(8,4);
        points[i++] = new Point(-8,-6);
        points[i++] = new Point(8,5);
        points[i++] = new Point(8,6);
        points[i++] = new Point(-8,-8);
        points[i++] = new Point(-8,-9);
        points[i++] = new Point(8,10);
        points[i++] = new Point(-7,11);
        points[i++] = new Point(7,-11);
        points[i++] = new Point(-7,7);
        points[i++] = new Point(-7,2);
        points[i++] = new Point(7,-3);
        points[i++] = new Point(-7,1);
        points[i++] = new Point(-7,0);
        points[i++] = new Point(7,0);
        points[i++] = new Point(-7,-2);
        points[i++] = new Point(-7,-3);
        points[i++] = new Point(7,4);
        points[i++] = new Point(7,6);
        points[i++] = new Point(-7,-7);
        points[i++] = new Point(7,8);
        points[i++] = new Point(-6,12);
        points[i++] = new Point(6,-11);
        points[i++] = new Point(-6,9);
        points[i++] = new Point(6,-10);
        points[i++] = new Point(-6,7);
        points[i++] = new Point(6,-8);
        points[i++] = new Point(6,-7);
        points[i++] = new Point(-6,5);
        points[i++] = new Point(-6,4);
        points[i++] = new Point(6,-4);
        points[i++] = new Point(-6,3);
        points[i++] = new Point(6,-3);
        points[i++] = new Point(-6,2);
        points[i++] = new Point(-6,0);
        points[i++] = new Point(-6,-1);
        points[i++] = new Point(-6,-2);
        points[i++] = new Point(6,2);
        points[i++] = new Point(-6,-4);
        points[i++] = new Point(6,9);
        points[i++] = new Point(-5,11);
        points[i++] = new Point(5,-11);
        points[i++] = new Point(5,-9);
        points[i++] = new Point(5,-8);
        points[i++] = new Point(-5,2);
        points[i++] = new Point(-5,1);
        points[i++] = new Point(-5,0);
        points[i++] = new Point(5,0);
        points[i++] = new Point(-5,-1);
        points[i++] = new Point(5,1);
        points[i++] = new Point(-5,-4);
        points[i++] = new Point(-5,-9);
        points[i++] = new Point(-4,13);
        points[i++] = new Point(-5,-12);
        points[i++] = new Point(-4,10);
        points[i++] = new Point(-4,8);
        points[i++] = new Point(-4,4);
        points[i++] = new Point(-4,3);
        points[i++] = new Point(-4,2);
        points[i++] = new Point(-4,1);
        points[i++] = new Point(-4,0);
        points[i++] = new Point(-4,-1);
        points[i++] = new Point(4,0);
        points[i++] = new Point(4,1);
        points[i++] = new Point(-4,-2);
        points[i++] = new Point(4,2);
        points[i++] = new Point(-4,-4);
        points[i++] = new Point(4,3);
        points[i++] = new Point(4,5);
        points[i++] = new Point(4,8);
        points[i++] = new Point(-3,11);
        points[i++] = new Point(-4,-13);
        points[i++] = new Point(3,-10);
        points[i++] = new Point(-3,8);
        points[i++] = new Point(3,-8);
        points[i++] = new Point(3,-7);
        points[i++] = new Point(-3,5);
        points[i++] = new Point(-3,2);
        points[i++] = new Point(-3,1);
        points[i++] = new Point(3,-1);
        points[i++] = new Point(-3,-1);
        points[i++] = new Point(3,0);
        points[i++] = new Point(3,1);
        points[i++] = new Point(3,2);
        points[i++] = new Point(3,3);
        points[i++] = new Point(-3,-5);
        points[i++] = new Point(-3,-6);
        points[i++] = new Point(3,6);
        points[i++] = new Point(-3,-8);
        points[i++] = new Point (-3,-10);
        points[i++] = new Point(3,9);
        points[i++] = new Point(-2,13);
        points[i++] = new Point(-2,12);
        points[i++] = new Point(2,-12);
        points[i++] = new Point(3,12);
        points[i++] = new Point(-2,10);
        points[i++] = new Point(2,-9);
        points[i++] = new Point(-2,6);
        points[i++] = new Point(2,-6);
        points[i++] = new Point(-2,4);
        points[i++] = new Point(2,-4);
        points[i++] = new Point(-2,1);
        points[i++] = new Point(2,-2);
        points[i++] = new Point(-2,0);
        points[i++] = new Point(2,-1);
        points[i++] = new Point(2,0);
        points[i++] = new Point(-2,-1);
        points[i++] = new Point(2,1);
        points[i++] = new Point(2,3);
        points[i++] = new Point(2,4);
        points[i++] = new Point(2,6);
        points[i++] = new Point(-2,-8);
        points[i++] = new Point(-2,-9);
        points[i++] = new Point(-1,12);
        points[i++] = new Point(2,12);
        points[i++] = new Point(-1,8);
        points[i++] = new Point(-1,7);
        points[i++] = new Point(-1,5);
        points[i++] = new Point(1,-6);
        points[i++] = new Point(-1,3);
        points[i++] = new Point(-1,2);
        points[i++] = new Point(1,-2);
        points[i++] = new Point(1,-1);
        points[i++] = new Point(-1,0);
        points[i++] = new Point(1,0);
        points[i++] = new Point(-1,-1);
        points[i++] = new Point(-1,-2);
        points[i++] = new Point(1,1);
        points[i++] = new Point(1,2);
        points[i++] = new Point(-1,-4);
        points[i++] = new Point(1,3);
        points[i++] = new Point(-1,-5);
        points[i++] = new Point(1,4);
        points[i++] = new Point(1,8);
        points[i++] = new Point(-1,-11);
        points[i++] = new Point(1,10);
        points[i++] = new Point(1,11);
        points[i++] = new Point(1,13);
        
        return points;
    }


    public static void runAll(Point[] points) {
        Util.printSet(GrahamScan.convexHull(points.clone()));
        Util.printSet(Jarvis.convexHull(points.clone()));
        Util.printSet(DivideAndConquer.convexHull(points.clone()));
        Util.printSet(Quickhull.convexHull(points.clone()));
        Util.printSet(MonotoneChain.convexHull(points.clone()));
        Util.printSet(Improved.convexHull(points.clone()));
    }

    public static void plotAll() {
        int dx = 250;
        Point[] points1 = testSet1();
        Point[] points2 = testSet2();
        Point[] points3 = testSet3();
        Point[] points4 = testSet4();
        Point[] points5 = testSet5();
        Point[] points6 = testSet6();
        Point[] points7 = testSet7();
        new Plotting(points1, Quickhull.convexHull(points1), true, 0 * dx);
        new Plotting(points2, Quickhull.convexHull(points2), true, 1 * dx);
        new Plotting(points3, Quickhull.convexHull(points3), true, 2 * dx);
        new Plotting(points4, Quickhull.convexHull(points4), true, 3 * dx);
        new Plotting(points5, Quickhull.convexHull(points5), true, 4 * dx);
        new Plotting(points6, Quickhull.convexHull(points6), true, 5 * dx);
        new Plotting(points7, Quickhull.convexHull(points7), true, 6 * dx);
    }

    public static void main(String[] args) {

        System.out.println("LiuChen");
        Point[] points = generateCircle(400, 30);
        Util.printSet(points);
        Point[] hull = LiuChen.convexHull(points);
        new Plotting(points, hull, true, 0);

        //plotAll();

    } // main
    
}
