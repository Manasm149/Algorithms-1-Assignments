# Kd-Trees and PointSET

## Overview

This project implements two data types for handling 2D geometric point data in the unit square `[0, 1] × [0, 1]`:

1. `PointSET` — A brute-force implementation using Java’s `TreeSet` for efficient point set operations.
2. `KdTree` — An optimized 2d-tree (k-d tree) implementation that supports fast **range search** and **nearest-neighbor** queries.

These implementations are essential for understanding geometric data structures and enable real-time spatial queries in 2D.

---

## 📁 Files

- `PointSET.java`: Brute-force point set using `java.util.TreeSet`.
- `KdTree.java`: Efficient implementation of 2D-tree with recursive spatial partitioning.
- *Note*: Requires `algs4.jar` (provided by the course) for `Point2D` and `RectHV` geometric primitives.

---

## 💡 Features

### PointSET

- Stores a set of points.
- Supports:
  - Insertion: `insert(Point2D p)`
  - Membership check: `contains(Point2D p)`
  - Range search: `range(RectHV rect)`
  - Nearest-neighbor search: `nearest(Point2D p)`
  - Visualization: `draw()`
- Implementation uses a balanced BST (`TreeSet<Point2D>`).

### KdTree

- Binary tree that alternates between x and y comparisons at each level.
- Divides space into nested rectangles (used for pruning).
- Supports:
  - Efficient point insertion
  - Fast `nearest()` and `range()` queries with geometric pruning
  - Tree visualization via `draw()`, showing:
    - Red lines for vertical splits
    - Blue lines for horizontal splits

---

## 🖱️ Visualization Clients

Interactive clients provided for testing (not submitted):

- `KdTreeVisualizer.java`
- `RangeSearchVisualizer.java`
- `NearestNeighborVisualizer.java`

---

## 📊 Performance Notes

- `PointSET`: Simple but slow for large datasets (`O(n)` for nearest/range).
- `KdTree`: Optimized with `~log(n)` insertion and fast pruning in search.
- Nearest-neighbor search explores optimal subtree first for better pruning.
- Range queries only traverse intersecting rectangles.

---

## 📦 Submission

Zip and submit only:

- `PointSET.java`
- `KdTree.java`

Do **not** submit `algs4.jar` or client files.

---

## 📚 Credits

Assignment developed by Bob Sedgewick and Kevin Wayne  
© 2004 Princeton University
