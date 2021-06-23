public class LinkedListDeque<T> {
    /** The basement LinkList class. */
    private class LinkList {
        public LinkList pre;
        public T item;
        public LinkList next;

        public LinkList(T i, LinkList p, LinkList n) {
            pre = p;
            item = i;
            next = n;
        }
    }

    private LinkList sentinel;
    private int size;

    /** Creates an empty linked list deque. */
    public LinkedListDeque() {
        sentinel = new LinkList(null, null, null);
        sentinel.pre = sentinel;
        sentinel.next = sentinel;
        size = 0;
    }

    /** Adds an item of T T to the front of the deque. */
    public void addFirst(T item) {
        size++;
        LinkList first = new LinkList(item, sentinel, sentinel.next);
        sentinel.next.pre = first;
        sentinel.next = first;
        if (sentinel.pre == sentinel) {
            sentinel.pre = sentinel.next;
        }
    }

    /** Adds an item of T T to the back of the deque. */
    public void addLast(T item) {
        size++;
        sentinel.pre = new LinkList(item, sentinel.pre, sentinel);
        sentinel.pre.pre.next = sentinel.pre;
    }

    /** Returns true if deque is empty, false otherwise. */
    public boolean isEmpty() {
        if (size == 0) {
            return true;
        }
        return false;
    }

    /** Returns the number of items in the deque. */
    public int size() {
        return size;
    }

    /** Prints the items in the deque from first to last,
     *  separated by a space.
     */
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
    public T removeFirst() {
        if (sentinel.next == sentinel) {
            return null;
        }
        size--;
        T i = sentinel.next.item;
        sentinel.next = sentinel.next.next;
        sentinel.next.pre = sentinel;
        return i;
    }

    /**
     * Removes and returns the item at the back of the deque.
     * If no such item exists, returns null.
     */
    public T removeLast() {
        if (sentinel.pre == sentinel) {
            return null;
        }
        size--;
        T i = sentinel.pre.item;
        sentinel.pre = sentinel.pre.pre;
        sentinel.pre.next = sentinel;
        return i;
    }

    /**
     * Gets the item at the given index,
     * where 0 is the front, 1 is the next item, and so forth.
     * If no such item exists, returns null.
     * Must not alter the deque!
     */
    public T get(int index) {
        if (sentinel.next == sentinel) {
            return null;
        }
        LinkList p = sentinel.next;
        while (index != 0) {
            p = p.next;
            index--;
        }
        return p.item;
    }
    /** Gets the item at the given index by recursion. */
    public T getRecursive(int index) {
        if (sentinel.next == sentinel) {
            return null;
        }
        LinkList p = sentinel.next;
        return getHelper(index, p);
    }

    private T getHelper(int index, LinkList p) {
        if (index == 0) {
            return p.item;
        }
        index--;
        p = p.next;
        return getHelper(index, p);
    }
}
