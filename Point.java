public class Point {
    int x;
    int y;

    Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "(" + Integer.toString(this.x) + ","
                   + Integer.toString(this.y) + ")";
    }


    public boolean equals(Point b) {
        return (this.x == b.x) && 
               (this.y == b.y);
    }


    public int distance(Point p) {
        return (this.x - p.x) * (this.x - p.x)
             + (this.y - p.y) * (this.y - p.y);
    }

    public int distanceFromLine(Point p, Point q) {
        int dist = (this.y - p.y) * (q.x - p.x) -
                   (this.x - p.x) * (q.y - p.y);

        return Math.abs(dist);
    }

    public boolean isInTriangle(Point a, Point b, Point c) {
        float d1 = Util.sign(this, a, b);
        float d2 = Util.sign(this, b, c);
        float d3 = Util.sign(this, c, a);

        boolean has_neg = (d1 < 0) || (d2 < 0) || (d3 < 0);
        boolean has_pos = (d1 > 0) || (d2 > 0) || (d3 > 0);

        return !(has_neg && has_pos);
    }


} // Point