public class ArrayDeque<T> {
    private T[] items;
    private int size;
    private int nextfirst;
    private int nextlast;

    private int caseChecker(){
        int c;
        if (nextfirst < nextlast && size != items.length) {
            c = 1;
        }
        else if (items.length - nextfirst - 1 == 0 && size != items.length) {
            c = 2;
        }
        else if (nextlast == 0 && size != items.length) {
            c = 3;
        }
        else if (nextfirst < nextlast && size == items.length) {
            c = 5;
        }
        else if (nextfirst == items.length - 1 && nextlast == 0 && size == items.length) {
            c = 6;
        }
        else{
            c = 4;
        }
        return c;
    }

    /**
     * Creates an empty array deque.
     */
    public ArrayDeque() {
         items = (T []) new Object[8];
         size = 0;
         nextfirst = items.length - 1;
    }

    /**
     * Resize the array when its full.
     */
    private void upperResize(){
        T[] i = (T []) new Object[items.length * 2];
        int c = caseChecker();
        if (c == 6) {
            System.arraycopy(items, 0, i, 0, items.length);
        }
        else {
            System.arraycopy(items, nextfirst + 1, i, 0, items.length - nextfirst - 1);
            System.arraycopy(items, 0, i, items.length - nextfirst - 1, nextlast);
        }
        items = i;
        nextfirst = items.length - 1;
        nextlast = size;
    }

    /**
     * Resize the array when usage ratio < 0.25.
     */
    private void lowerResize() {
        T[] i = (T []) new Object[items.length / 2];
        int c = caseChecker();
        switch(c) {
            case 1:
                System.arraycopy(items, nextfirst + 1, i, 0, nextlast - nextfirst - 1);
                break;
            case 2:
                System.arraycopy(items, 0, i, 0, nextlast);
                break;
            case 3:
                System.arraycopy(items, nextfirst + 1, i, 0, items.length - nextfirst - 1);
                break;
            case 4:
                System.arraycopy(items, nextfirst + 1, i, 0, items.length - nextfirst - 1);
                System.arraycopy(items, 0, i, size - nextfirst - 1, nextlast);
        }
        items = i;
        nextfirst = items.length - 1;
        nextlast = size;
    }

    /** Adds an item of T T to the front of the deque. */
    public void addFirst(T item) {
        if (size == items.length) {
            upperResize();
        }
        items[nextfirst] = item;
        if (nextfirst == 0) {
            nextfirst = items.length - 1;
        }
        else {
            nextfirst --;
        }
        size ++;
    }

    /** Adds an item of T T to the back of the deque. */
    public void addLast(T item) {
        if (size == items.length) {
            upperResize();
        }
        items[nextlast] = item;
        if (nextlast == items.length - 1) {
            nextlast = 0;
        }
        else {
            nextlast ++;
        }
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

    /** Prints the items in the deque from first to last,
     *  separated by a space.
     */
    public void printDeque() {
        int c = caseChecker();
        int p;
        switch(c) {
            case 1:
                for (p = nextfirst + 1; p < nextlast; p ++) {
                    System.out.print(items[p] + " ");
                }
                break;
            case 2:
                for (p = 0; p < nextlast; p ++) {
                    System.out.print(items[p] + " ");
                }
                break;
            case 3:
                for (p = nextfirst + 1; p <items.length; p ++) {
                    System.out.print(items[p] + " ");
                }
                break;
            case 4:
            case 5:
                for (p = nextfirst + 1; p < items.length; p ++) {
                    System.out.print(items[p] + " ");
                }
                for (p = 0; p < nextlast; p ++) {
                    System.out.print(items[p] + " ");
                }
            case 6:
                for (p = 0; p < items.length; p ++) {
                    System.out.print(items[p] + " ");
                }
        }
        System.out.println();
    }

    /**
     * Removes and returns the item at the front of the deque.
     * If no such item exists, returns null.
     */
    public T removeFirst() {
        int c = caseChecker();
        T returnitem = null;

        if (size == 0) {
            return null;
        }

        switch(c) {
            case 2:
            case 6:
                nextfirst = 0;
                returnitem = items[0];
                items[0] = null;
                break;
            case 3:
                nextfirst ++;
                returnitem = items[nextfirst];
                items[nextfirst] = null;
                break;
            default:
                nextfirst ++;
                returnitem = items[nextfirst];
                items[nextfirst] = null;
        }

        size --;
        double usageratio = Double.valueOf(size) / items.length;

        if (usageratio < 0.25 && items.length > 8) {
            lowerResize();
        }

        return returnitem;
    }

    /**
     * Removes and returns the item at the back of the deque.
     * If no such item exists, returns null.
     */
    public T removeLast() {
        int c = caseChecker();
        T returnitem = null;

        if (size == 0) {
            return null;
        }

        switch(c) {
            case 3:
            case 6:
                nextlast = items.length - 1;
                returnitem = items[nextlast];
                items[nextlast] = null;
                break;
            default:
                nextlast = nextlast - 1;
                returnitem = items[nextlast];
                items[nextlast] = null;
        }

        size --;
        double usageratio = size / items.length;

        if (usageratio < 0.25 && items.length > 8) {
            lowerResize();
        }

        return returnitem;
    }

    /**
     * Gets the item at the given index,
     * where 0 is the front, 1 is the next item, and so forth.
     * If no such item exists, returns null.
     * Must not alter the deque!
     */
    public T get(int index) {
        if (index >= size) {
            return null;
        }
        int position = findPosition(index);
        return items[position];
    }

    private int findPosition(int index) {
        int c = caseChecker();
        int position = 0;
        switch (c) {
            case 1:
            case 5:
                position = nextfirst + 1 + index;
                break;
            case 2:
            case 6:
                position = index;
                break;
            case 3:
                position = nextfirst + 1 + index;
                break;
            case 4:
                int f = items.length - nextfirst - 1;
                int l = nextlast;
                if (index + 1 <= f) {
                    position = nextfirst + 1 + index;
                }
                else{
                    position = index - f;
                }
        }
        return position;
    }
}
