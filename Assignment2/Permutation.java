import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/**
 * This class reads a sequence of strings from standard input,
 * enqueues them into a RandomizedQueue, and then prints out
 * a specified number of strings chosen uniformly at random
 * (without replacement).
 */
public class Permutation {

    public static void main(String[] args) {
        // Parse the number of items to print from command-line argument
        int count = Integer.parseInt(args[0]);

        // Create an instance of the RandomizedQueue
        RandomizedQueue<String> queue = new RandomizedQueue<>();

        // Read all input strings from standard input and enqueue them
        while (!StdIn.isEmpty()) {
            String item = StdIn.readString();  // read one string
            queue.enqueue(item);               // enqueue it into the queue
        }

        // Dequeue and print 'count' random strings from the queue
        for (int i = 0; i < count; i++) {
            StdOut.println(queue.dequeue());
        }
    }
}
