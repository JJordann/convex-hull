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


} // Point