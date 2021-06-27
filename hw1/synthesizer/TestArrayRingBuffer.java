package synthesizer;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;

import static org.junit.Assert.*;

/** Tests the ArrayRingBuffer class.
 *  @author Josh Hug
 */

public class TestArrayRingBuffer {
    @Test
    public void someTest() {
       /* ArrayRingBuffer<Integer> arb = new ArrayRingBuffer<>(10);
        assertTrue(arb.isEmpty());
        arb.enqueue(1); //1
        arb.enqueue(2); //1, 2
        arb.enqueue(null); // 1, 2
        assertEquals(2, arb.fillCount());
        arb.enqueue(3); //1, 2, 3
        arb.enqueue(4); //1, 2, 3, 4
        assertEquals((Integer) 1, arb.peek());
        assertEquals((Integer) 1, arb.dequeue()); //2, 3, 4
*/
        ArrayRingBuffer<Integer> arb2 = new ArrayRingBuffer<>(10);
        LinkedList<Integer> al = new LinkedList<>();
        double r;
        String log = "\n";
        for (int i = 0; i < 100; i++) {
            r = StdRandom.uniform();
            if (r < 0.5) {
                if (arb2.isFull()) {
                    i--;
                    continue;
                }
                al.addLast(i);
                arb2.enqueue(i);
                log = log + "add" + i + "\n";
            }
            else {
                i--;
                if (arb2.isEmpty()) {
                    continue;
                }
                log = log + "removeFirst\n";
                Integer expected = al.removeFirst();
                Integer actural = arb2.dequeue();
                assertEquals(log, expected, actural);
            }
        }
    }

    /** Calls tests for ArrayRingBuffer. */
    public static void main(String[] args) {
        jh61b.junit.textui.runClasses(TestArrayRingBuffer.class);
    }
} 
