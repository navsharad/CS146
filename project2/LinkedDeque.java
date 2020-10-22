import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

// Deque implementation using a linked list.
public class LinkedDeque<Item> implements Iterable<Item> {
    private Node first;
    private Node last;
    private int size;

    // Helper doubly-linked list class.
    private class Node {
        private Item item;
        private Node next;
        private Node prev;
    }

    // Construct an empty deque.
    public LinkedDeque() {
        first = null;
        last = null;
        size = 0;
    }

    // Is the dequeue empty?
    public boolean isEmpty() {
        return size == 0;
    }

    // The number of items on the deque.
    public int size() {
        return size;
    }

    // Add item to the first of the deque.
    public void addFirst(Item item) {
        if (item == null)
            throw new NullPointerException("addFirst(): Cannot add item of type null.");
        Node n = new Node();
        n.item = item;
        if (isEmpty()) {
            first = n;
            first.next = null;
            first.prev = null;
            last = first;
        } else {
            n.next = first;
            first.prev = n;
            first = n;
        }
        size++;
    }

    // Add item to the last of the deque.
    public void addLast(Item item) {
        if (item == null)
            throw new NullPointerException("addLast(): Cannot add item of type null.");
        Node n = new Node();
        n.item = item;
        if (isEmpty()) {
            last = n;
            last.next = null;
            last.prev = null;
            first = last;
        } else {
            last.next = n;
            n.prev = last;
            last = n;
        }
        size++;
    }

    // Remove and return item from the first of the deque.
    public Item removeFirst() {
        if (isEmpty())
            throw new NoSuchElementException("removeFirst(): Cannot remove item, deque is empty.");
        Item deletedItem = first.item;
        if (size() == 1) {
            first.next = null;
            first.prev = null;
            first = null;
        } else {
            first = first.next;
            first.prev.next = null;
            first.prev = null;
        }
        size--;
        return deletedItem;
    }

    // Remove and return item from the last of the deque.
    public Item removeLast() {
        if (isEmpty())
            throw new NoSuchElementException("removeLast(): Cannot remove item, deque is empty.");
        Item deletedItem = last.item;
        if (size() == 1) {
            last.next = null;
            last.prev = null;
            last = null;
        } else {
            last = last.prev;
            last.next.prev = null;
            last.next = null;
        }
        size--;
        return deletedItem;
    }

    // An iterator over items in the queue in order from first to last.
    public Iterator<Item> iterator() {
        return new DequeIterator();
    }

    // An iterator, doesn't implement remove() since it's optional.
    private class DequeIterator implements Iterator<Item> {
        private Node current;

        DequeIterator() {
            current = first;
        }

        public boolean hasNext() {
            return current != null;
        }

        public void remove() {
            throw new UnsupportedOperationException("remove(): remove operation not supported.");
        }

        public Item next() {
            if (!hasNext())
                throw new NoSuchElementException("next(): No items left to iterate over.");
            Item item = current.item;
            current = current.next;
            return item;
        }
    }

    // A string representation of the deque.
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (Item item : this) {
            s.append(item + " ");
        }
        return s.toString().substring(0, s.length() - 1);
    }

    // Test client
    public static void main(String[] args) {
        LinkedDeque<Character> deque = new LinkedDeque<Character>();
        String quote = "There is grandeur in this view of life, with its "
                + "several powers, having been originally breathed into a few "
                + "forms or into one; and that, whilst this planet has gone "
                + "cycling on according to the fixed law of gravity, from so "
                + "simple a beginning lastless forms most beautiful and most "
                + "wonderful have been, and are being, evolved. ~ "
                + "Charles Darwin, The Origin of Species";
        int r = StdRandom.uniform(0, quote.length());
        for (int i = quote.substring(0, r).length() - 1; i >= 0; i--) {
            deque.addFirst(quote.charAt(i));
        }
        for (int i = 0; i < quote.substring(r).length(); i++) {
            deque.addLast(quote.charAt(r + i));
        }
        StdOut.println(deque.isEmpty());
        StdOut.printf("(%d characters) ", deque.size());
        for (char c : deque) {
            StdOut.print(c);
        }
        StdOut.println();
        double s = StdRandom.uniform();
        for (int i = 0; i < quote.length(); i++) {
            if (StdRandom.bernoulli(s)) {
                deque.removeFirst();
            } else {
                deque.removeLast();
            }
        }
        StdOut.println(deque.isEmpty());
    }
}
