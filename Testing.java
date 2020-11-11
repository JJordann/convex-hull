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
        //Chan's
        //(8,9) (-7,-15) (7,6) (-6,26) (26,0) (-1,0) (-4,1) (-9,-27) (-3,-7) (9,-16) (-3,21) (-21,11) (3,-1) (-7,4) (17,-20) (-11,-5) (1,5) (-18,-7) (5,-22) (-4,-24) (-17,2) (-11,-24) (-8,-11) (-6,-1) (-9,-18) (10,-4) (-25,9) (-5,6) (-25,-13) (-1,14) (3,22) (-8,22) (-1,-13) (15,-21) (-12,2) (-3,2) (11,12) (1,-4) (-16,-17) (9,9) (17,-10) (-3,-9) (-4,17) (-7,5) (11,-5) (-10,0) (9,-1) (-2,-8) (-7,-23) (19,21) (-1,-20) (-11,-15) (-9,-7) (-18,-11) (6,-18) (16,15) (3,11) (-13,-8) (27,-5) (-4,2) (21,-4) (2,1) (4,16) (-5,-20) (-17,10) (17,-7) (13,-16) (-12,-14) (-26,-11) (3,-8) (-1,12) (16,7) (-2,-1) (10,24) (0,8) (-5,10) (-20,-3) (-2,-3) (-15,-16) (-2,4) (8,-4) (11,-4) (2,26) (-24,-14) (-10,-10) (-2,8) (20,-4) (-1,-21) (-9,15) (8,-13) (-21,-9) (-1,3) (14,19) (5,16) (2,2) (-10,-27) (10,-9) (-19,18) (8,13) (7,7) (9,3) (7,0) (3,-26) (13,-2) (-1,5) (-27,9) (-18,2) (-6,3) (12,4) (-1,-1) (-15,17) (3,12) (-5,28) (7,-2) (22,0) (-22,9) (-9,0) (8,11) (10,-10) (11,13) (-5,-5) (2,-8) (14,1) (14,-12) (-21,-18) (10,9) (7,20) (-12,8) (-9,-13) (-9,-14) (0,14) (10,-24) (1,4) (5,8) (3,1) (-12,-17) (-6,10) (13,13) (-4,-3) (-23,1) (-18,-15) (-3,1) (8,3) (10,-15) (19,-13) (-12,14) (-5,-3) (5,-5) (1,-12) (5,21) (-4,6) (3,-9) (9,12) (0,-4) (4,0) (16,-23) (-28,-2) (24,-9) (-1,2) (-3,-24) (2,6) (-19,17) (24,-12) (-12,24) (14,-9) (24,-10) (0,6) (1,26) (13,-18) (8,-8) (-12,-13) (-24,-1) (23,0) (-16,-7) (-22,-2) (4,-8) (0,-27) (-3,9) (11,23) (-11,12) (-10,-13) (-25,-6) (8,0) (14,9) (-2,-15) (-4,15) (0,20) (3,-15) (3,-6) (-14,11) (-14,-7) (0,3) (10,-25) (-20,9) (4,1) (0,-21) (-2,28) (26,-7) (21,-12) (-16,15) (-21,-16) (-5,4) (4,-5) (24,12) (0,0) (11,2) (1,16) (-18,-12) (-12,-10) (11,-12) (2,-9) (6,0) (19,-12) (-15,-1) (13,-4) (20,-5) (-4,24) (3,15) (24,-11) (19,0) (6,-10) (0,-2) (-6,6) (-25,-5) (11,-18) (-1,13) (15,1) (-17,-8) (2,-5) (-17,-19) (11,6) (6,-13) (-6,-16) (14,0) (12,-8) (25,4) (6,10) (6,-25) (-17,-5) (-3,-2) (19,15) (-7,-8) (2,20) (-12,-11) (23,-9) (-12,1) (-1,-19) (1,1) (5,0) (0,-17) (5,10) (28,1) (7,-9) (0,22) (16,14) (2,-2) (-11,-9) (4,7) (3,-12) (-25,12) (2,4) (23,-15) (-4,-7) (3,8) (-7,-16) (-3,18) (5,19) (6,7) (-18,7) (6,26) (15,-9) (-11,9) (21,0) (2,-11) (1,27) (8,12) (-20,-12) (0,5) (4,-6) (12,-6) (-1,21) (4,-21) (-11,5) (-25,-10) (-20,-16) (-2,2) (23,-7) (0,9) (10,-22) (-24,-5) (-7,0) (7,27) (-2,-13) (-15,24) (-4,9) (7,26) (26,-6) (1,0) (-22,1) (-9,-17) (1,-1) (-18,10) (-26,1) (9,10) (-10,7) (-2,0) (0,-1) (-13,14) (-11,-12) (-14,-5) (-18,20) (-19,1) (-8,-5) (-20,3) (-3,17) (7,14) (-3,-21) (12,13) (10,23) (-10,-20) (-4,-12) (0,2) (20,-18) (-13,-10) (-22,11) (2,7) (-6,11) (-21,18) (-6,0) (25,-4) (-21,-19) (2,-1) (-18,-22) (5,-26) (1,-25) (0,-8) (-9,-9) (16,-15) (-6,1) (0,4) (-2,-22) (21,-18) (9,-12) (-6,-7) (20,0) (-3,4) (-2,27) (-7,-19) (7,-4) (10,-12) (-12,-23) (13,21) (-3,8) (-8,6) (1,3) (-17,13) (9,7) (18,-16) (-25,-1) (7,-7) (21,6) (-3,24) (0,-15) (-9,-3) (-4,4) (-20,-2) (-11,15) (-13,0) (-14,0) (3,9) (16,-3) (-5,-2) (-15,-17) (-19,-7) (2,-22) (7,-19) (7,24) (5,-11) (-16,-12) (-14,15) (22,5) (-23,-15) (-25,6) (-3,0) (3,5) (2,15) (-13,-12) (-20,-15) (4,28) (-20,-19) (6,4) (-6,21) (15,14) (0,-3) (9,20) (0,11) (8,-14) (7,13) (8,20) (0,16) (5,2) (-6,-6) (4,4) (-3,-18) (17,-13) (-6,-2) (1,28) (-9,6) (2,9) (15,10) (3,-11) (2,-20) (7,-10) (-21,5) (12,0) (-9,10) (19,-9) (-15,21) (-1,9) (-5,3) (9,0) (23,-13) (-9,4) (9,-5) (10,-2) (23,3) (17,3) (18,-2) (14,-17) (-3,-3) (24,2) (24,-7) (4,-10) (-6,9) (-25,1) (20,14) (-1,4) (5,13) (-6,18) (0,-9) (2,-25) (11,-9) (-13,7) (10,-1) (10,4) (-11,-4) (-12,9) (5,-4) (-5,-25) (-6,15) (9,11) (1,14) (-8,13) (-18,13) (1,-5) (0,-23) (3,-10) (5,22) (-10,10) (2,12) (1,8) (-11,-21) (-8,2) (-14,17) (19,20) (1,-3) (6,11) (24,5) (-9,-8) (14,14) (-5,11) (-22,0) (10,-26) (-11,-10) (1,-7) (2,3) (6,3) (-9,11) (5,-1) (-1,-23) (-15,-23) (-25,5) (9,8) (21,-2) (-4,-2) (18,7) (-7,2) (-14,21) (4,3) (-9,5) (-18,18) (-19,8) (-3,-6) (14,3) (-20,-1) (-4,-6) (-1,-2) (11,3) (2,5) (-1,6) (12,-15) (17,-22) (-13,-13) (-5,5) (-14,2) (-13,24) (-24,3) (-15,4) (12,12) (-22,-6) (-5,0) (-20,1) (0,13) (-11,-13) (0,-10) (-7,-10) (-1,1) (-8,-15) (-17,0) (5,-7) (11,-19) (3,0) (-26,5) (-2,17) (-21,-4) (5,7) (-10,-15) (10,11) (-3,12) (10,6) (-21,-7) (-13,-1) (-2,1) (-4,0) (20,6) (-22,-9) (17,-9) (-22,2) (19,9) (0,1) (-19,-8) (7,17) (-12,-16) (-14,-11) (4,-11) (7,-3) (4,5) (-5,-18) (-7,21) (6,12) (4,6) (-8,-1) (-22,7) (26,12) (20,15) (20,9) (-15,-4) (5,6) (-27,2) (10,2) (0,26) (-9,23) (-5,-4) (0,27) (3,-17) (0,-6) (16,-5) (3,-3) (-2,5) (0,15) (3,-5) (9,-3) (-7,1) (-26,-9) (-12,3) (-5,1) (0,18) (3,-13) (7,-24) (6,-19) (13,7) (12,-5) (14,-3) (-1,-5) (17,0) (17,-8) (-3,-5) (9,-6) (-1,-11) (-6,7) (-6,5) (2,-6) (-6,4) (-13,-20) (-6,-28) (13,15) (20,18) (7,9) (-1,7) (9,-8)
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

        System.out.println("Torch");
        Point[] points = generateCircle(200, 30);
        Util.printSet(points);
        Point[] hull = Torch.convexHull(points);
        new Plotting(points, hull, true, 0);

        //plotAll();

    } // main
    
}
