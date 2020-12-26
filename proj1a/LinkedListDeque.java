import java.util.LinkedList;

public class LinkedListDeque<Type> {
    /** The basement LinkList class. */
    private class LinkList {
        public LinkList pre;
        public Type item;
        public LinkList next;

        public LinkList(Type i, LinkList p, LinkList n) {
            pre = p;
            item = i;
            next = n;
        }
    }

    private LinkList sentinel = new LinkList(null, null, null);
    private LinkList last;
    public int size;

    /** Creates an empty linked list deque. */
    public LinkedListDeque() {
        last = sentinel;
        sentinel.pre = last;
        sentinel.next = sentinel;
        size = 0;
    }

    public LinkedListDeque(Type i) {
        last = new LinkList(i, sentinel, sentinel);
        sentinel.pre = last;
        sentinel.next = last;
        size = 1;
    }

    /** Adds an item of type T to the front of the deque. */
    public void addFirst(Type item) {
        sentinel.next = new LinkList(item, sentinel, sentinel.next);
        if (last == sentinel) {
            last = sentinel.next;
        }
        size ++;
    }

    /** Adds an item of type T to the back of the deque. */
    public void addLast(Type item) {
        last = new LinkList(item, last, null);
        sentinel.pre = last;
        size ++;
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

    /** Prints the items in the deque from first to last, separated by a space. */
    public void printDeque() {
        LinkList p = sentinel.next;
        while (p.next != null) {
            System.out.print(p.item + " ");
            p = p.next;
        }
        System.out.println(p.item);
    }

    /** Removes and returns the item at the front of the deque. If no such item exists, returns null.*/
    public Type removeFirst() {
        if (sentinel.next == sentinel) {
            return null;
        }
        Type i = sentinel.next.item;
        sentinel.next = sentinel.next.next;
        sentinel.next.pre = sentinel;
        size --;
        if (size == 0) {
            last = sentinel;
        }
        return i;
    }

    /** Removes and returns the item at the back of the deque. If no such item exists, returns null. */
    public Type removeLast() {
        if (last == sentinel) {
            return null;
        }
        Type i = last.item;
        last = last.pre;
        last.next = sentinel;
        sentinel.pre = last;
        size --;
        return i;
    }

    /** Gets the item at the given index, where 0 is the front, 1 is the next item, and so forth. If no such item exists, returns null. Must not alter the deque! */
    public Type get(int index) {
        if (sentinel.next == sentinel) {
            return null;
        }
        LinkList p = sentinel.next;
        while (index != 0){
            p = p.next;
            index --;
        }
        return p.item;
    }

    /** Gets the item at the given index by recursion. */
    public Type getRecursive(int index) {
        LinkedListDeque<Type> p = this;
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
