import org.junit.Assert.*;
import org.junit.Test;

public class ArrayDequeTest {

    public static boolean checkEmpty(boolean expected, boolean actual) {
        if (expected != actual) {
            System.out.println("isEmpty() returned " + actual + ", but expected: " + expected);
            return false;
        }
        return true;
    }

    public static boolean checkSize(int expected, int actual) {
        if (expected != actual) {
            System.out.println("size() returned " + actual + ", but expected: " + expected);
            return false;
        }
        return true;
    }

    public static void printTestStatus(boolean passed) {
        if (passed) {
            System.out.println("Test passed!\n");
        } else {
            System.out.println("Test failed!\n");
        }
    }

    public static boolean getTest(int expected, int actual) {
        if (expected != actual) {
            System.out.println("get() returned " + actual + ", but expected: " + expected);
            return false;
        }
        return true;
    }

    @Test
    public void addIsEmptySizeTest() {
        System.out.println("Running add/isEmpty/Size test.");

        ArrayDeque<String> ad1 = new ArrayDeque<>();

        boolean passed = checkEmpty(true, ad1.isEmpty());

        ad1.addFirst("front");

        passed = checkSize(1, ad1.size()) && passed;
        passed = checkEmpty(false, ad1.isEmpty()) && passed;

        ad1.addLast("middle");
        passed = checkSize(2,ad1.size()) && passed;

        System.out.println("Printing out deque");
        ad1.printDeque();

        printTestStatus(passed);
    }

    @Test
    public void addRemoveTest() {
        System.out.println("Running add/remove test.");

        ArrayDeque<Integer> ad1 = new ArrayDeque<>();
        boolean passed = checkEmpty(true, ad1.isEmpty());

        ad1.addFirst(10);
        passed = checkEmpty(false, ad1.isEmpty()) && passed;

        ad1.removeFirst();
        passed = checkEmpty(true, ad1.isEmpty()) && passed;

        //3, 4, 5, 6, 7, 8, 1, 2 ---- 1234567890000000
        ad1.addLast(3);
        ad1.addLast(4);
        ad1.addLast(5);
        ad1.addLast(6);
        ad1.addLast(7);
        ad1.addLast(8);
        ad1.addFirst(2);
        ad1.addFirst(1);
        ad1.addLast(9);

        passed = getTest(3, ad1.get(2)) && passed;
        passed = getTest(9, ad1.get(8)) && passed;

        System.out.println("Printing out deque");
        ad1.printDeque();

        passed = checkSize(9,ad1.size()) && passed;

        //0000007890000000 ---- 78900000
        ad1.removeFirst();//-1
        ad1.removeFirst();//-2
        ad1.removeFirst();//-3
        passed = getTest(4, ad1.get(0)) && passed;
        ad1.removeFirst();
        ad1.removeFirst();
        ad1.removeFirst();

        System.out.println("Printing out deque");
        ad1.printDeque();

        passed = checkSize(3,ad1.size()) && passed;

        printTestStatus(passed);
    }
}
