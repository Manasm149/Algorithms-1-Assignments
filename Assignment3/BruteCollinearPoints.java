import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;

public final class BruteCollinearPoints {

    private final LineSegment[] foundSegments;

    // Finds all line segments containing 4 collinear points
    public BruteCollinearPoints(Point[] inputPoints) {
        if (inputPoints == null) throw new IllegalArgumentException();
        for (Point p : inputPoints)
            if (p == null) throw new IllegalArgumentException();

        int n = inputPoints.length;
        Point[] sortedPoints = inputPoints.clone();
        LineSegment[] tempSegments = new LineSegment[n * n];
        int totalSegments = 0;

        Arrays.sort(sortedPoints);

        // Check for duplicate points
        for (int i = 0; i < n - 1; i++) {
            if (sortedPoints[i].compareTo(sortedPoints[i + 1]) == 0)
                throw new IllegalArgumentException();
        }

        // Try every combination of 4 points
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                for (int k = j + 1; k < n; k++) {
                    for (int l = k + 1; l < n; l++) {
                        Point p1 = sortedPoints[i];
                        Point p2 = sortedPoints[j];
                        Point p3 = sortedPoints[k];
                        Point p4 = sortedPoints[l];

                        double slope1 = p1.slopeTo(p2);
                        double slope2 = p1.slopeTo(p3);
                        double slope3 = p1.slopeTo(p4);

                        if (slope1 == slope2 && slope2 == slope3) {
                            tempSegments[totalSegments++] = new LineSegment(p1, p4);
                        }
                    }
                }
            }
        }

        foundSegments = new LineSegment[totalSegments];
        for (int i = 0; i < totalSegments; i++) {
            foundSegments[i] = tempSegments[i];
        }
    }

    // Returns the number of line segments
    public int numberOfSegments() {
        return foundSegments.length;
    }

    // Returns a copy of the line segments
    public LineSegment[] segments() {
        LineSegment[] result = new LineSegment[foundSegments.length];
        for (int i = 0; i < foundSegments.length; i++) {
            result[i] = foundSegments[i];
        }
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
        BruteCollinearPoints collinear = new BruteCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }
}
