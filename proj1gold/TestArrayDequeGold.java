import static org.junit.Assert.*;
import org.junit.Test;

public class TestArrayDequeGold {
    @Test
    public void Test() {
        StudentArrayDeque<Integer> sad1 = new StudentArrayDeque<>();
        ArrayDequeSolution<Integer> ads = new ArrayDequeSolution<>();
        String log = "";

        for (int i = 0; i < 1000; i++) {
            double r1 = StdRandom.uniform();

            if (r1 <= 0.25) {
                sad1.addFirst(i);
                ads.addFirst(i);
                log = log + "addFirst(" + i + ")\n";
            }
            else if (r1 > 0.25 & r1 <= 0.5){
                sad1.addLast(i);
                ads.addLast(i);
                log = log + "addLast(" + i + ")\n";
            }
            else if (r1 > 0.5 & r1 <= 0.75) {
                if (ads.size() == 0) {
                    i--;
                    continue;
                }
                log = log + "removeFirst()\n";
                assertEquals(log, ads.removeFirst(), sad1.removeFirst());
            }
            else {
                if (ads.size() == 0) {
                    i--;
                    continue;
                }
                log = log + "removeLast()\n";
                assertEquals(log, ads.removeLast(), sad1.removeLast());
            }
        }
    }
}
