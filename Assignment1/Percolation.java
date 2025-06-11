import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

/**
 * This class models an n-by-n percolation system using the
 * Weighted Quick Union-Find data structure with two virtual nodes:
 * - one at the top (index 0)
 * - one at the bottom (index n*n+1)
 * 
 * A site is either blocked or open. The system percolates when there's
 * a path from any open site in the top row to any open site in the bottom row.
 */
public class Percolation {

    private final boolean[] sites;           // Tracks if each site is open (true) or blocked (false)
    private final WeightedQuickUnionUF uf;   // Union-Find data structure for connectivity
    private final int n;                     // Grid dimension (n x n)
    private int openSites;                   // Number of open sites

    /**
     * Creates an n-by-n grid with all sites initially blocked.
     * 
     * @param n the dimension of the grid
     * @throws IllegalArgumentException if n <= 0
     */
    public Percolation(int n) {
        if (n <= 0) throw new IllegalArgumentException("Grid size must be greater than 0");

        this.n = n;
        this.uf = new WeightedQuickUnionUF(n * n + 2); // includes two virtual nodes (top and bottom)
        this.sites = new boolean[n * n + 2];           // one extra at beginning (0) and end (n*n+1)
        this.openSites = 0;                            // initially, no open sites
    }

    /**
     * Opens the site at (row, col) if it is not open already,
     * and connects it to adjacent open sites.
     */
    public void open(int row, int col) {
        checkBounds(row, col); // validate indices

        int index = to1D(row, col); // map 2D to 1D

        // Only act if the site is currently blocked
        if (!sites[index]) {
            sites[index] = true;  // mark site as open
            openSites++;          // increment open site count

            // Connect to virtual top node if it's in the top row
            if (row == 1) {
                uf.union(index, 0);
            }

            // Connect to virtual bottom node if it's in the bottom row
            if (row == n) {
                uf.union(index, n * n + 1);
            }

            // Connect to open neighbors (up, down, left, right) if they exist
            if (row > 1 && isOpen(row - 1, col)) {
                uf.union(index, to1D(row - 1, col));
            }
            if (row < n && isOpen(row + 1, col)) {
                uf.union(index, to1D(row + 1, col));
            }
            if (col > 1 && isOpen(row, col - 1)) {
                uf.union(index, to1D(row, col - 1));
            }
            if (col < n && isOpen(row, col + 1)) {
                uf.union(index, to1D(row, col + 1));
            }
        }
    }

    /**
     * Returns true if the site at (row, col) is open.
     */
    public boolean isOpen(int row, int col) {
        checkBounds(row, col);
        return sites[to1D(row, col)];
    }

    /**
     * Returns true if the site at (row, col) is full,
     * i.e., it is connected to the virtual top node.
     */
    public boolean isFull(int row, int col) {
        checkBounds(row, col);
        return isOpen(row, col) && uf.connected(to1D(row, col), 0);
    }

    /**
     * Returns the number of open sites in the grid.
     */
    public int numberOfOpenSites() {
        return openSites;
    }

    /**
     * Returns true if the system percolates, i.e., if there is a path
     * from the virtual top node to the virtual bottom node.
     */
    public boolean percolates() {
        return uf.connected(0, n * n + 1);
    }

    /**
     * Checks whether the (row, col) indices are within bounds.
     */
    private void checkBounds(int row, int col) {
        if (row < 1 || row > n || col < 1 || col > n) {
            throw new IllegalArgumentException("Row and column must be between 1 and " + n);
        }
    }

    /**
     * Maps a 2D (row, col) coordinate to a 1D index in the union-find structure.
     */
    private int to1D(int row, int col) {
        return (row - 1) * n + (col - 1) + 1; // +1 to offset for virtual top node at 0
    }

    /**
     * Main method — can be used to test the class.
     */
    public static void main(String[] args) {
        // Optional test client
    }
}
