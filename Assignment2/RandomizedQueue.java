import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import java.util.Iterator;
import java.util.NoSuchElementException;

// A generic randomized queue where each dequeue removes a random item.
public class RandomizedQueue<Item> implements Iterable<Item> {

    // Internal array to store queue elements
    private Item[] items;

    // Number of elements currently in the queue
    private int size;

    // Constructor to create an empty randomized queue
    public RandomizedQueue() {
        // Start with a small array of size 2
        items = (Item[]) new Object[2];
        size = 0;
    }

    // Returns true if the queue is empty
    public boolean isEmpty() {
        return size == 0;
    }

    // Returns the number of items currently in the queue
    public int size() {
        return size;
    }

    // Adds an item to the queue
    public void enqueue(Item item) {
        if (item == null)
            throw new IllegalArgumentException("Cannot enqueue null item");

        // If the array is full, double its capacity
        if (size == items.length)
            resize(2 * items.length);

        // Insert item at the end
        items[size++] = item;
    }

    // Removes and returns a random item from the queue
    public Item dequeue() {
        if (isEmpty())
            throw new NoSuchElementException("Queue is empty");

        // Choose a random index in the range [0, size)
        int index = StdRandom.uniform(size);

        // Save the item to return
        Item item = items[index];

        // Move the last item into the index being removed (to fill the gap)
        items[index] = items[size - 1];
        items[size - 1] = null; // avoid loitering (memory leak)

        size--;

        // Shrink the array if it's too empty to save space
        if (size > 0 && size == items.length / 4)
            resize(items.length / 2);

        return item;
    }

    // Returns (but does not remove) a random item from the queue
    public Item sample() {
        if (isEmpty())
            throw new NoSuchElementException("Queue is empty");

        // Simply return a random item without removing it
        return items[StdRandom.uniform(size)];
    }

    // Returns an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new RandomizedQueueIterator();
    }

    // Resizes the underlying array to the given capacity
    private void resize(int capacity) {
        // Create a new array and copy all items into it
        Item[] copy = (Item[]) new Object[capacity];
        for (int i = 0; i < size; i++)
            copy[i] = items[i];
        items = copy;
    }

    // Iterator class to iterate through the items in random order
    private class RandomizedQueueIterator implements Iterator<Item> {
        private final Item[] shuffled; // a shuffled copy of items[]
        private int current;

        public RandomizedQueueIterator() {
            // Make a shallow copy of the current items
            shuffled = (Item[]) new Object[size];
            for (int i = 0; i < size; i++)
                shuffled[i] = items[i];

            // Shuffle the copy so we can iterate in random order
            StdRandom.shuffle(shuffled);

            current = 0;
        }

        // Check if there are more elements in the iterator
        public boolean hasNext() {
            return current < shuffled.length;
        }

        // Return the next item from the iterator
        public Item next() {
            if (!hasNext())
                throw new NoSuchElementException("No more items");

            return shuffled[current++];
        }

        // Remove is unsupported in this iterator
        public void remove() {
            throw new UnsupportedOperationException("remove() not supported");
        }
    }

    // Unit testing (optional; can be ignored by autograder)
    public static void main(String[] args) {
        // Create a randomized queue of strings
        RandomizedQueue<String> q = new RandomizedQueue<>();

        // Enqueue a few strings
        q.enqueue("A");
        q.enqueue("B");
        q.enqueue("C");
        q.enqueue("D");

        // Sample a random item (does not remove it)
        StdOut.println("Sample: " + q.sample());

        // Dequeue a random item (removes it)
        StdOut.println("Dequeue: " + q.dequeue());

        // Iterate over the queue (in random order)
        StdOut.println("Iteration:");
        for (String s : q)
            StdOut.println(" - " + s);
    }
}
