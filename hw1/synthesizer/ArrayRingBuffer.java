// TODO: Make sure to make this class a part of the synthesizer package
 package synthesizer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

//TODO: Make sure to make this class and all of its methods public
//TODO: Make sure to make this class extend AbstractBoundedQueue<t>
public class ArrayRingBuffer<T> extends AbstractBoundedQueue<T> {
    /* Index for the next dequeue or peek. */
    private int first;            // index for the next dequeue or peek
    /* Index for the next enqueue. */
    private int last;
    /* Array for storing the buffer data. */
    private T[] rb;


    private class ARBIterator implements Iterator<T> {
        private LinkedList<T> ll = new LinkedList<>();

        public ARBIterator() {
            if (first <= last) {
                for (int i = 0; i < fillCount(); i++) {
                    ll.addLast(rb[first + i]);
                }
            }
            else {
                for (int i = 0; i < capacity() - first + 1; i++) {
                    ll.addLast(rb[first + i]);
                }
                for (int j = 0; j < fillCount() + first - capacity(); j++) {
                    ll.addLast(rb[j]);
                }
            }
        }

        @Override
        public boolean hasNext() {
            return ll.isEmpty();
        }

        @Override
        public T next() {
            return ll.removeFirst();
        }
    }

    @Override
    public Iterator<T> iterator() {
        Iterator<T> i = new ARBIterator();
        return i;
    }
    /**
     * Create a new ArrayRingBuffer with the given capacity.
     */
    public ArrayRingBuffer(int capacity) {
        // TODO: Create new array with capacity elements.
        //       first, last, and fillCount should all be set to 0.
        //       this.capacity should be set appropriately. Note that the local variable
        //       here shadows the field we inherit from AbstractBoundedQueue, so
        //       you'll need to use this.capacity to set the capacity.
        rb = (T[]) new Object[capacity];
        first = 0;
        last = 0;
        this.capacity = capacity;
        this.fillCount = 0;
    }

    /**
     * Adds x to the end of the ring buffer. If there is no room, then
     * throw new RuntimeException("Ring buffer overflow"). Exceptions
     * covered Monday.
     */
    @Override
    public void enqueue(T x) {
        // TODO: Enqueue the item. Don't forget to increase fillCount and update last.
        if (isFull()) {
            throw new RuntimeException("Ring Buffer Overflow");
        }
        if (isEmpty()) {
            rb[first] = x;
        }
        else if (last == capacity() - 1) {
            last = 0;
            rb[last] = x;
        }
        else {
            last++;
            rb[last] = x;
        }
        this.fillCount++;
    }

    /**
     * Dequeue oldest item in the ring buffer. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow"). Exceptions
     * covered Monday.
     */
    @Override
    public T dequeue() {
        // TODO: Dequeue the first item. Don't forget to decrease fillCount and update
        if (isEmpty()) {
            throw new RuntimeException("Ring Buffer Underflow");
        }
        this.fillCount--;
        if (fillCount() == 0) {
           return rb[first];
        }
        else if (first == capacity() - 1) {
            first = 0;
            return rb[capacity() - 1];
        }
        else {
            first++;
            return rb[first - 1];
        }
    }

    /**
     * Return oldest item, but don't remove it.
     */
    @Override
    public T peek() {
        // TODO: Return the first item. None of your instance variables should change.
        if (isEmpty()) {
            return null;
        }
        return rb[first];
    }

    // TODO: When you get to part 5, implement the needed code to support iteration.
}
