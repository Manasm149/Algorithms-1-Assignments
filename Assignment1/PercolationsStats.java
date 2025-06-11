import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/**
 * This class performs a series of percolation experiments (trials) on an n-by-n grid.
 * It calculates the mean, standard deviation, and 95% confidence interval of the
 * percolation threshold — the fraction of open sites required for the system to percolate.
 */
public class PercolationStats {

    // Cached statistics after running all trials
    private final double mean_value;
    private final double stddev_value;
    private final double confidenceLo_value;
    private final double confidenceHi_value;

    /**
     * Perform `trials` independent experiments on an `n`-by-`n` percolation grid.
     * 
     * @param n the dimension of the grid
     * @param trials number of independent trials to perform
     * @throws IllegalArgumentException if n <= 0 or trials <= 0
     */
    public PercolationStats(int n, int trials) {

        if (n <= 0 || trials <= 0) {
            throw new IllegalArgumentException("Grid size and number of trials must be > 0");
        }

        double[] thresholds = new double[trials]; // stores percolation threshold for each trial

        // Perform all trials
        for (int i = 0; i < trials; i++) {
            Percolation p = new Percolation(n);

            // Repeatedly open random blocked sites until the system percolates
            while (!p.percolates()) {
                int row, col;
                do {
                    row = StdRandom.uniformInt(1, n + 1); // pick random row in [1, n]
                    col = StdRandom.uniformInt(1, n + 1); // pick random col in [1, n]
                } while (p.isOpen(row, col)); // avoid reopening

                p.open(row, col); // open the chosen site
            }

            // Record the fraction of open sites when percolation occurred
            thresholds[i] = (double) p.numberOfOpenSites() / (n * n);
        }

        // Compute statistical results using helper class StdStats
        mean_value = StdStats.mean(thresholds);
        stddev_value = StdStats.stddev(thresholds);

        // Compute the 95% confidence interval
        double margin = (1.96 * stddev_value) / Math.sqrt(trials);
        confidenceLo_value = mean_value - margin;
        confidenceHi_value = mean_value + margin;
    }

    // Returns the sample mean of percolation threshold
    public double mean() {
        return mean_value;
    }

    // Returns the sample standard deviation of percolation threshold
    public double stddev() {
        return stddev_value;
    }

    // Returns the low endpoint of the 95% confidence interval
    public double confidenceLo() {
        return confidenceLo_value;
    }

    // Returns the high endpoint of the 95% confidence interval
    public double confidenceHi() {
        return confidenceHi_value;
    }

    /**
     * Test client for the PercolationStats class.
     * Expects two integers from standard input: grid size `n` and number of trials.
     * Then prints statistical results.
     */
    public static void main(String[] args) {
        int n = StdIn.readInt();      // read grid size
        int trials = StdIn.readInt(); // read number of trials

        try {
            PercolationStats stats = new PercolationStats(n, trials);

            StdOut.println("mean                    = " + stats.mean());
            StdOut.println("stddev                  = " + stats.stddev());
            StdOut.println("95% confidence interval = [" + 
                           stats.confidenceLo() + ", " + stats.confidenceHi() + "]");
        } catch (IllegalArgumentException e) {
            StdOut.println("Invalid input: " + e.getMessage());
        }
    }
}
