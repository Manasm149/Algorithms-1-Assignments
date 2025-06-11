import edu.princeton.cs.algs4.StdOut;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * A double-ended queue or deque that supports adding and removing items
 * from both the front and the back in constant worst-case time.
 * This implementation uses a doubly-linked list internally.
 */
public class Deque<Item> implements Iterable<Item> {
    // Current number of elements in the deque
    private int size;

    // Pointers to the first and last nodes
    private Node<Item> first;
    private Node<Item> last;

    /**
     * Private nested class for the doubly-linked list node.
     */
    private class Node<Item> {
        Item item;           // The data item
        Node<Item> next;     // Pointer to next node
        Node<Item> previous; // Pointer to previous node
    }

    /**
     * Iterator class for the deque, iterates from front to end.
     */
    private class DequeIterator<Item> implements Iterator<Item> {
        private Node<Item> current;

        public DequeIterator(Node<Item> start) {
            current = start;
        }

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public Item next() {
            if (!hasNext()) throw new NoSuchElementException("No more elements");
            Item item = current.item;
            current = current.next;
            return item;
        }
    }

    /**
     * Initializes an empty deque.
     */
    public Deque() {
        size = 0;
        first = null;
        last = null;
    }

    /**
     * Returns true if the deque is empty.
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Returns the number of items in the deque.
     */
    public int size() {
        return size;
    }

    /**
     * Adds an item to the front of the deque.
     */
    public void addFirst(Item item) {
        if (item == null) throw new NullPointerException("Cannot add null to deque");

        Node<Item> newItem = new Node<>();
        newItem.item = item;
        newItem.next = first;
        newItem.previous = null;

        if (isEmpty()) {
            last = newItem; // Only item in deque
        } else {
            first.previous = newItem; // Link old first to new
        }

        first = newItem;
        size++;
    }

    /**
     * Adds an item to the end of the deque.
     */
    public void addLast(Item item) {
        if (item == null) throw new NullPointerException("Cannot add null to deque");

        Node<Item> newItem = new Node<>();
        newItem.item = item;
        newItem.next = null;
        newItem.previous = last;

        if (isEmpty()) {
            first = newItem; // Only item in deque
        } else {
            last.next = newItem; // Link old last to new
        }

        last = newItem;
        size++;
    }

    /**
     * Removes and returns the item from the front.
     */
    public Item removeFirst() {
        if (isEmpty()) throw new NoSuchElementException("Deque is empty");

        Item item = first.item;
        first = first.next;
        size--;

        if (isEmpty()) {
            last = null; // deque is now empty
        } else {
            first.previous = null; // unlink old node
        }

        return item;
    }

    /**
     * Removes and returns the item from the end.
     */
    public Item removeLast() {
        if (isEmpty()) throw new NoSuchElementException("Deque is empty");

        Item item = last.item;
        last = last.previous;
        size--;

        if (isEmpty()) {
            first = null; // deque is now empty
        } else {
            last.next = null; // unlink old node
        }

        return item;
    }

    /**
     * Returns an iterator over the items in order from front to end.
     */
    public Iterator<Item> iterator() {
        return new DequeIterator<>(first);
    }

    /**
     * Unit test. Tests functionality of the Deque class.
     */
    public static void main(String[] args) {
        Deque<String> deque = new Deque<>();

        // Add items to the front
        deque.addFirst("World");
        StdOut.println("addFirst() with: 'World'");
        deque.addFirst(", ");
        StdOut.println("addFirst() with: ', '");
        deque.addFirst("Hello");
        StdOut.println("addFirst() with: 'Hello'");
        deque.addFirst("Meow, ");
        StdOut.println("addFirst() with: 'Meow, '");

        // Add item to the end
        deque.addLast("^^");
        StdOut.println("addLast() with: '^^'");

        // Remove first and last items
        deque.removeFirst();
        StdOut.println("removeFirst()");
        deque.removeLast();
        StdOut.println("removeLast()");

        // Add item to the end again
        deque.addLast("!");
        StdOut.println("addLast() with: '!'");

        // Iterate over the deque
        StdOut.println("Iterating deque...");
        for (String item : deque) {
            StdOut.println("Iterate element: " + item);
        }
    }
}
