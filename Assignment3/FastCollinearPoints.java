import java.util.Arrays;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.In;

public final class FastCollinearPoints {

    private final LineSegment[] foundSegments;

    // finds all line segments containing 4 or more collinear points
    public FastCollinearPoints(Point[] inputPoints) {
        if (inputPoints == null) throw new IllegalArgumentException();
        for (Point p : inputPoints)
            if (p == null) throw new IllegalArgumentException();

        int n = inputPoints.length;
        Point[] sortedPoints = inputPoints.clone();
        Point[] originalOrder;
        int totalSegments = 0;
        double currentSlope;
        Point origin;
        LineSegment[] tempSegments = new LineSegment[n * n];

        Arrays.sort(sortedPoints);
        originalOrder = sortedPoints.clone();  // preserve original point order

        // check for duplicate points
        for (int i = 0; i < n - 1; i++) {
            if (sortedPoints[i].compareTo(sortedPoints[i + 1]) == 0)
                throw new IllegalArgumentException();
        }

        for (int i = 0; i < n; i++) {
            origin = originalOrder[i];

            Arrays.sort(sortedPoints);
            Arrays.sort(sortedPoints, origin.slopeOrder());

            int j = 0;
            int collinearCount;

            while (j < n - 2) {
                currentSlope = origin.slopeTo(sortedPoints[j]);
                collinearCount = 1;

                while (j + collinearCount < n &&
                        currentSlope == origin.slopeTo(sortedPoints[j + collinearCount])) {
                    collinearCount++;
                }

                collinearCount--;

                if (collinearCount >= 2) {
                    if (origin.compareTo(sortedPoints[j]) < 0 &&
                        origin.compareTo(sortedPoints[j + collinearCount]) < 0) {
                        tempSegments[totalSegments] = new LineSegment(origin, sortedPoints[j + collinearCount]);
                        totalSegments++;
                    }
                    j += collinearCount;
                }

                j++;
            }
        }

        foundSegments = new LineSegment[totalSegments];
        for (int i = 0; i < totalSegments; i++) {
            foundSegments[i] = tempSegments[i];
        }
    }

    // returns the number of line segments
    public int numberOfSegments() {
        return foundSegments.length;
    }

    // returns the line segments
    public LineSegment[] segments() {
        int length = numberOfSegments();
        LineSegment[] result = new LineSegment[length];
        for (int i = 0; i < length; i++) result[i] = foundSegments[i];
        return result;
    }

    public static void main(String[] args) {

        // read the n points from a file
        In in = new In(args[0]);
        int n = in.readInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
        }

        // draw the points
        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        for (Point p : points) {
            p.draw();
        }
        StdDraw.show();

        // find and draw the line segments
        FastCollinearPoints collinear = new FastCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }
}
