import java.util.Comparator;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

public class Point implements Comparable<Point> {

    private final int x; // x-coordinate of this point
    private final int y; // y-coordinate of this point

    /**
     * Constructs a point with the given (x, y) coordinates.
     */
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Draws this point using standard draw.
     */
    public void draw() {
        StdDraw.point(x, y);
    }

    /**
     * Draws the line segment between this point and the given point.
     */
    public void drawTo(Point that) {
        StdDraw.line(this.x, this.y, that.x, that.y);
    }

    /**
     * Compares this point to the specified point by y-coordinate,
     * breaking ties by x-coordinate.
     *
     * Returns:
     * - a negative number if this point is less than the other
     * - 0 if the points are equal
     * - a positive number if this point is greater than the other
     */
    public int compareTo(Point that) {
        if (this.y < that.y || (this.y == that.y && this.x < that.x)) return -1;
        if (this.y == that.y && this.x == that.x) return 0;
        return 1;
    }

    /**
     * Computes the slope between this point and the specified point.
     *
     * Returns:
     * - Double.NEGATIVE_INFINITY for a degenerate line (points are equal)
     * - +0.0 for a horizontal line
     * - Double.POSITIVE_INFINITY for a vertical line
     * - (y1 - y0) / (x1 - x0) for general case
     */
    public double slopeTo(Point that) {
        if (this.x == that.x && this.y == that.y) return Double.NEGATIVE_INFINITY; // degenerate
        if (this.x == that.x) return Double.POSITIVE_INFINITY; // vertical
        if (this.y == that.y) return 0.0; // horizontal
        return (double) (that.y - this.y) / (that.x - this.x); // slope
    }

    /**
     * Returns a comparator that compares two points by the slopes they make with this point.
     */
    public Comparator<Point> slopeOrder() {
        return new BySlope();
    }

    // Comparator class to compare points by slope with respect to the current point
    private class BySlope implements Comparator<Point> {
        public int compare(Point a, Point b) {
            double slopeA = Point.this.slopeTo(a);
            double slopeB = Point.this.slopeTo(b);
            return Double.compare(slopeA, slopeB);
        }
    }

    /**
     * Returns a string representation of this point in the format (x, y).
     */
    public String toString() {
        return "(" + x + ", " + y + ")";
    }

    /**
     * Unit testing (optional, for debugging).
     */
    public static void main(String[] args) {
        Point p = new Point(1, 1);
        Point q1 = new Point(2, 1); // horizontal
        Point q2 = new Point(1, 2); // vertical
        Point q3 = new Point(1, 1); // same

        StdOut.println("p.compareTo(q1): " + p.compareTo(q1));
        StdOut.println("p.slopeTo(q1) [horizontal]: " + p.slopeTo(q1));
        StdOut.println("p.slopeTo(q2) [vertical]: " + p.slopeTo(q2));
        StdOut.println("p.slopeTo(q3) [same point]: " + p.slopeTo(q3));

        Point[] arr = { q1, q2, q3 };
        StdOut.println("Sorted by slope w.r.t. p:");
        java.util.Arrays.sort(arr, p.slopeOrder());
        for (Point pt : arr) {
            StdOut.println(pt + " slope: " + p.slopeTo(pt));
        }
    }
}
