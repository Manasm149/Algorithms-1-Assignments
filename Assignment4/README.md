README.txt

Project: 8-Puzzle Solver using A* Search Algorithm
--------------------------------------------------

This program solves the n-by-n sliding tile puzzle (e.g., 8-puzzle) using the A* search algorithm with Manhattan priority. It consists of two Java classes: `Board` and `Solver`, and follows all project specifications from the Princeton Algorithms course (Kevin Wayne & Robert Sedgewick).

======================================
I. Board.java – Sliding Puzzle Model
======================================

The `Board` class models an immutable sliding puzzle board.

Constructor:
------------
- `Board(int[][] tiles)`  
  Takes an n-by-n 2D array of integers representing tiles.
  Tiles contain integers 0 to n²-1, where 0 represents the blank.

Key Methods:
------------
- `int dimension()`  
  Returns the board size n.

- `int hamming()`  
  Returns the number of tiles that are not in their goal positions.
  (i.e., tiles[i][j] != goal[i][j] and tiles[i][j] ≠ 0)

- `int manhattan()`  
  Returns the sum of the Manhattan distances between each tile and its goal position.  
  (vertical + horizontal distance for each tile)

- `boolean isGoal()`  
  Returns true if the board is the goal board (tiles in order, with 0 at bottom-right).

- `Iterable<Board> neighbors()`  
  Returns all neighboring boards by sliding a tile into the blank space.
  Depending on the position of the blank (0), there may be 2, 3, or 4 neighbors.

- `Board twin()`  
  Returns a new board by swapping any two non-blank tiles. Used to determine solvability.

- `boolean equals(Object y)`  
  Checks whether two boards are equal (i.e., identical tile layout).

- `String toString()`  
  Returns a string representation of the board, including dimension and tile layout.

Performance:
------------
- All methods operate in O(n²) time or better.

======================================
II. Solver.java – A* Solver Algorithm
======================================

The `Solver` class implements the A* search algorithm to solve the puzzle optimally (i.e., in the least number of moves).

SearchNode Class:
-----------------
A private helper class within `Solver` to store:
- The current `Board`
- The number of moves made so far
- The total priority = moves + board.manhattan()
- A reference to the predecessor node (used to reconstruct the solution path)

Priority Function:
------------------
- A* priority = `moves` + `manhattan()`  
  This guarantees optimal solution because Manhattan distance never overestimates remaining cost.

Dual A* Search:
---------------
- One search is run on the **original board**
- One search is run on the **twin board**
- We alternate between them using one `MinPQ`.
- If the twin reaches the goal first, the original is unsolvable.

Why It Works:
-------------
The 8-puzzle is only solvable if the number of inversions is even.
The twin board (by swapping 2 tiles) has opposite parity, so **exactly one of the original or twin will reach the goal**.

Optimizations:
--------------
1. **Critical Optimization**:  
   Do not enqueue a neighbor if it's equal to the previous state (to avoid going back and forth).

2. **Cached Manhattan Distance**:  
   Manhattan priority is computed once when the `SearchNode` is created, and reused in comparisons.

Solver API:
-----------
- `Solver(Board initial)`  
  Runs A* search starting from initial.

- `boolean isSolvable()`  
  Returns whether the initial board is solvable.

- `int moves()`  
  Returns the minimum number of moves to solve the puzzle (or -1 if unsolvable).

- `Iterable<Board> solution()`  
  Returns an iterable of boards from initial to goal state (or null if unsolvable).

- `main(String[] args)`  
  Reads a puzzle from standard input or file, solves it, and prints the solution.


