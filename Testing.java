
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


    public static Point[] generateCircle(int n, int radius) {
        Point[] points = new Point[n];
        return points;
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


    // TODO: poskrbi za duplikate
    public static Point[] generateRectangle(int n, int xspan, int yspan) {
        Point[] points = new Point[n];

        for(int i = 0; i < n; i++) {

            int x = Util.randomInt(-xspan, xspan);
            int y = Util.randomInt(-yspan, yspan);

            points[i] = new Point(x, y);
        }

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

        Point[] points = generateRectangle(30, 15, 15);
        Util.printSet(points);
        Point[] hull = LiuChen.convexHull(points);

        new Plotting(points, hull, true, 0);

        //plotAll();

    } // main
    
}
