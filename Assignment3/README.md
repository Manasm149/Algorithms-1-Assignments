# Collinear Points Detection

This project implements a system to detect **collinear points** — that is, to identify line segments formed by 4 or more points lying on the same straight line in a 2D plane.

There are two implementations:
- **BruteCollinearPoints.java** — A brute-force solution using combinatorics.
- **FastCollinearPoints.java** — An optimized, sorting-based approach.

---

## 💡 Problem Statement

Given **n** distinct points in a 2D plane, find every **maximal** line segment containing **4 or more collinear points**.

This problem models real-world scenarios like:
- Computer Vision (feature detection)
- Statistical Pattern Recognition
- Geographic data clustering

---

## 🧠 Concepts Used

### 1. **Geometry Basics**
- A **line segment** is a straight line connecting two points.
- **Collinear points** are those that lie on the same line.
- **Slope** is used to determine collinearity:
  
  > If the slopes between `a-b`, `a-c`, and `a-d` are equal, then points `a, b, c, d` are collinear.

### 2. **Brute-Force Approach**
- Checks every combination of 4 points.
- Time complexity: **O(n⁴)** — acceptable for small datasets.

### 3. **Sorting-Based Approach (FastCollinearPoints)**
- Fix one point `p`.
- Compute slopes from `p` to every other point.
- Sort points by slope.
- Scan for consecutive groups of 3+ equal slopes.
- This uses the **"sweep-line" + "sort"** strategy.

- Time complexity: **O(n² log n)**

### 4. **Comparator and Comparable**
- `Point` implements `Comparable` for sorting by coordinates.
- Defines custom `Comparator<Point>` via `slopeOrder()`.

### 5. **Defensive Programming**
- The code checks:
  - `null` input arrays
  - `null` points
  - duplicate points
- Throws `IllegalArgumentException` to enforce correctness.

---

## 📁 File Overview

| File                     | Description                                |
|--------------------------|--------------------------------------------|
| `Point.java`             | Represents a 2D point with drawing/slope   |
| `LineSegment.java`       | Represents a segment between two points    |
| `BruteCollinearPoints.java` | Finds 4-point collinear segments using brute-force |
| `FastCollinearPoints.java`  | Finds all maximal collinear segments (4+ points) using sorting |

---



