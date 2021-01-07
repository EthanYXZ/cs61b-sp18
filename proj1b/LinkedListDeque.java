public class LinkedListDeque<T> implements Deque<T> {
    /** The basement LinkList class. */
    private class LinkList {
        private LinkList pre;
        private T item;
        private LinkList next;

        private LinkList(T i, LinkList p, LinkList n) {
            pre = p;
            item = i;
            next = n;
        }
    }

    private LinkList sentinel = new LinkList(null, null, null);
    private LinkList last;
    private int size;

    /** Creates an empty linked list deque. */
    public LinkedListDeque() {
        last = sentinel;
        sentinel.pre = last;
        sentinel.next = sentinel;
        size = 0;
    }

    /**
    public LinkedListDeque(T i) {
        last = new LinkList(i, sentinel, sentinel);
        sentinel.pre = last;
        sentinel.next = last;
        size = 1;
    }
     */

    /** Adds an item of T T to the front of the deque. */
    @Override
    public void addFirst(T item) {
        LinkList first = new LinkList(item, sentinel, sentinel.next);
        sentinel.next.pre = first;
        sentinel.next = first;
        if (last == sentinel) {
            last = sentinel.next;
            sentinel.pre = last;
        }
        size += 1;
    }

    /** Adds an item of T T to the back of the deque. */
    @Override
    public void addLast(T item) {
        last = new LinkList(item, last, sentinel);
        last.pre.next = last;
        sentinel.pre = last;
        size += 1;
    }

    /** Returns true if deque is empty, false otherwise. */
    @Override
    public boolean isEmpty() {
        if (size == 0) {
            return true;
        }
        return false;
    }

    /** Returns the number of items in the deque. */
    @Override
    public int size() {
        return size;
    }

    /** Prints the items in the deque from first to last,
     *  separated by a space.
     */
    @Override
    public void printDeque() {
        LinkList p = sentinel.next;
        while (p.next != null) {
            System.out.print(p.item + " ");
            p = p.next;
        }
        System.out.println(p.item);
    }

    /**
     * Removes and returns the item at the front of the deque.
     * If no such item exists, returns null.
     */
    @Override
    public T removeFirst() {
        if (sentinel.next == sentinel) {
            return null;
        }
        T i = sentinel.next.item;
        sentinel.next = sentinel.next.next;
        sentinel.next.pre = sentinel;
        size -= 1;
        if (size == 0) {
            last = sentinel;
        }
        return i;
    }

    /**
     * Removes and returns the item at the back of the deque.
     * If no such item exists, returns null.
     */
    @Override
    public T removeLast() {
        if (last == sentinel) {
            return null;
        }
        T i = last.item;
        last = last.pre;
        last.next = sentinel;
        sentinel.pre = last;
        size -= 1;
        return i;
    }

    /**
     * Gets the item at the given index,
     * where 0 is the front, 1 is the next item, and so forth.
     * If no such item exists, returns null.
     * Must not alter the deque!
     */
    @Override
    public T get(int index) {
        if (sentinel.next == sentinel) {
            return null;
        }
        LinkList p = sentinel.next;
        while (index != 0) {
            p = p.next;
            index -= 1;
        }
        return p.item;
    }

    /** Gets the item at the given index by recursion. */
    public T getRecursive(int index) {
        LinkedListDeque<T> p = this;
        if (sentinel.next == sentinel) {
            return null;
        }
        if (index == 0) {
            return sentinel.next.item;
        }
        p.sentinel.next = p.sentinel.next.next;
        return p.get(index - 1);
    }
}
