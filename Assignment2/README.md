Overview
--------
This project implements two generic data structures for the Princeton Algorithms course:

1. Deque<Item>: A double-ended queue that supports insertion and deletion from both ends.
2. RandomizedQueue<Item>: A queue where items are removed uniformly at random.
3. Permutation.java: A client program that reads a sequence of strings from standard input and prints exactly k of them, chosen uniformly at random.

All implementations meet the required performance and memory constraints and handle corner cases as specified.

Files Submitted
---------------
1. Deque.java
   - Implements a generic deque using a doubly-linked list.
   - All operations (add/remove from front/back) run in constant worst-case time.
   - Implements Iterable<Item> with a forward iterator.
   - Throws proper exceptions for null input and illegal operations.

2. RandomizedQueue.java
   - Implements a randomized queue using a resizing array.
   - Supports enqueue, dequeue, and sample operations.
   - Items are removed or returned at random using StdRandom.uniform().
   - The iterator returns all items in random order and is independent of other iterators.
   - Iterator operations run in constant time; construction is linear.

3. Permutation.java
   - Takes an integer k from command-line arguments.
   - Reads a sequence of strings from StdIn.
   - Prints exactly k strings, uniformly at random.
   - Uses only one RandomizedQueue object of size ≤ n.

How to Compile and Run
-----------------------
To compile:

    javac Deque.java
    javac RandomizedQueue.java
    javac Permutation.java

To run Permutation:

    java Permutation <k> < input.txt

Example:

    java Permutation 3 < distinct.txt

This will output 3 strings chosen uniformly at random from the file.

API Restrictions and Libraries Used
-----------------------------------
Only the following libraries are used:

- java.util.Iterator
- java.util.NoSuchElementException
- edu.princeton.cs.algs4.StdIn
- edu.princeton.cs.algs4.StdOut
- edu.princeton.cs.algs4.StdRandom

No usage of java.util.ArrayList or java.util.LinkedList or other Java collections.

Performance and Memory Requirements
-----------------------------------
- All deque operations run in constant worst-case time.
- All randomized queue operations (except iterator construction) run in constant amortized time.
- Each iterator has constant-time hasNext() and next().
- All data structures use at most 48n + 192 bytes of memory for n items.

Testing
-------
Each class includes a main() method for unit testing.
All public methods are exercised with sample inputs and output to StdOut.


