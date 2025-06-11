import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {

    // Array to store items
    private Item[] items;

    // Number of elements in the queue
    private int size;

    // Constructs an empty randomized queue
    public RandomizedQueue() {
        items = (Item[]) new Object[2]; // initial capacity
        size = 0;
    }

    // Returns true if the queue is empty
    public boolean isEmpty() {
        return size == 0;
    }

    // Returns the number of items on the queue
    public int size() {
        return size;
    }

    // Adds an item to the queue
    public void enqueue(Item item) {
        if (item == null)
            throw new IllegalArgumentException("Cannot add null");

        if (size == items.length)
            resize(2 * items.length); // double size if full

        items[size++] = item;
    }

    // Removes and returns a random item
    public Item dequeue() {
        if (isEmpty())
            throw new NoSuchElementException("Queue is empty");

        int index = StdRandom.uniform(size);
        Item item = items[index];

        // Move last item into the removed spot to keep array compact
        items[index] = items[size - 1];
        items[size - 1] = null;
        size--;

        if (size > 0 && size == items.length / 4)
            resize(items.length / 2); // shrink size if too empty

        return item;
    }

    // Returns (but does not remove) a random item
    public Item sample() {
        if (isEmpty())
            throw new NoSuchElementException("Queue is empty");

        return items[StdRandom.uniform(size)];
    }

    // Returns an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new RandomizedQueueIterator();
    }

    // Resizes the internal array to the given capacity
    private void resize(int capacity) {
        Item[] copy = (Item[]) new Object[capacity];
        for (int i = 0; i < size; i++)
            copy[i] = items[i];
        items = copy;
    }

    // Iterator class that returns items in random order
    private class RandomizedQueueIterator implements Iterator<Item> {
        private final Item[] shuffled;
        private int current;

        public RandomizedQueueIterator() {
            shuffled = (Item[]) new Object[size];
            for (int i = 0; i < size; i++)
                shuffled[i] = items[i];

            StdRandom.shuffle(shuffled); // shuffle items
            current = 0;
        }

        public boolean hasNext() {
            return current < shuffled.length;
        }

        public Item next() {
            if (!hasNext())
                throw new NoSuchElementException();
            return shuffled[current++];
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    // Unit test (optional, ignored in grading)
    public static void main(String[] args) {
        RandomizedQueue<String> q = new RandomizedQueue<>();
        q.enqueue("A");
        q.enqueue("B");
        q.enqueue("C");
        q.enqueue("D");

        StdOut.println("Sample: " + q.sample());
        StdOut.println("Dequeue: " + q.dequeue());

        for (String s : q)
            StdOut.println("Iterated: " + s);
    }
}
