import deque.ArrayDeque;
import deque.Deque;
import org.junit.Test;

import static com.google.common.truth.Truth.assertThat;

public class ArrayDequeTest {
    @Test
    public void arrayDqueIteratorTest1() {
        Deque<Integer> d1 = new ArrayDeque();
        d1.addLast(2);
        d1.addFirst(1);
        d1.addLast(3);
        int i = 1;
        for(int item: d1) {
            assertThat(item).isEqualTo(i);
            i += 1;
        }
    }

    @Test
    public void adTestEqualDeques() {
        Deque<String> lld1 = new ArrayDeque<>();
        Deque<String> lld2 = new ArrayDeque<>();

        lld1.addLast("front");
        lld1.addLast("middle");
        lld1.addLast("back");

        lld2.addLast("front");
        lld2.addLast("middle");
        lld2.addLast("back");

        assertThat(lld1).isEqualTo(lld2);
    }

    @Test
    public void adToStringTest() {
        Deque<Double> ad = new ArrayDeque<>();
        ad.addLast(1.0);
        ad.addLast(3.3);
        ad.addLast(9.9);

        String s = "[1.0, 3.3, 9.9]";
        assertThat(s).isEqualTo(ad.toString());
    }
}
