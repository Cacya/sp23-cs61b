import deque.Deque;
import deque.LinkedListDeque;
import org.junit.Test;

import static com.google.common.truth.Truth.assertThat;

public class LinkedListDequeTest {
    @Test
    public void linkedListDequeIteratorTest2() {
        Deque<Integer> d1 = new LinkedListDeque();
        d1.addFirst(2);
        d1.addFirst(1);
        d1.addLast(3);
        int i = 1;
        for(int item: d1) {
            assertThat(item).isEqualTo(i);
            i += 1;
        }
    }

    @Test
    public void lldTestEqualDeques() {
        Deque<String> lld1 = new LinkedListDeque<>();
        Deque<String> lld2 = new LinkedListDeque<>();

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
        Deque<String> ad = new LinkedListDeque<>();
        ad.addLast("front");
        ad.addLast("middle");
        ad.addLast("back");

        String s = "[front, middle, back]";
        assertThat(s).isEqualTo(ad.toString());
    }
}
