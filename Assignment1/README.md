# 📍 Percolation Simulation

This project simulates the **Percolation Problem** — a fundamental model in statistical physics — and estimates the **percolation threshold** using **Monte Carlo simulation**. It's implemented in Java using object-oriented programming and statistical libraries from [algs4](https://algs4.cs.princeton.edu/).

---

## 🔬 Problem Description

We model an `n-by-n` grid of sites. Each site can be either **open** or **blocked**.

- The system **percolates** if a path of open sites connects the top row to the bottom row.
- The **percolation threshold** is the ratio of open sites at the time of percolation.

We run several simulations and compute statistical estimates of this threshold.

---

## 🧱 Components

### 1. `Percolation.java`

A class that models the percolation system using:

- **Union-Find (Disjoint Set)** data structure (`WeightedQuickUnionUF`)
  - Efficiently determines if two sites are connected.
  - Handles backwash by using **virtual top and bottom** nodes.
- **Boolean array** to track open sites.
- **Coordinate conversion**: from 2D grid `(row, col)` to 1D array index.

**Key Concepts Used:**
- Union-Find / Disjoint Set with path compression
- Array-based 2D → 1D mapping
- Grid bounds validation
- Lazy site opening

### 2. `PercolationStats.java`

A class that:
- Performs multiple percolation experiments
- Tracks the percolation threshold for each
- Computes:
  - Mean
  - Standard deviation
  - 95% confidence interval

**Key Concepts Used:**
- **Monte Carlo Simulation**: Random sampling until system percolates
- **Random number generation**: `StdRandom`
- **Descriptive statistics**: `StdStats.mean()`, `stddev()`
- **Confidence Interval**: `mean ± 1.96 * stddev / sqrt(T)`

---

## ⚙️ How to Run

1. **Compile:**
   ```bash
   javac Percolation.java PercolationStats.java

