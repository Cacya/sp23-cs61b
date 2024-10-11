import deque.MaxArrayDeque;
import org.junit.jupiter.api.Test;

import java.util.Comparator;

import static com.google.common.truth.Truth.assertThat;

public class MaxArrayDequeTest {
    private static class MaxArrayDequeComp<T extends Comparable<T>> implements Comparator<T> {
        @Override
        public int compare(T o1, T o2) {
            return o1.compareTo(o2);
        }
    }

    private static class MaxArrayDequeCompTwo<T extends Comparable<T>> implements Comparator<T> {
        @Override
        public int compare(T o1, T o2) {
            return o1.compareTo(o2);
        }
    }

    @Test
    public void test01() {
        Comparator<Integer> c = new MaxArrayDequeComp<>();
        MaxArrayDeque<Integer> mad = new MaxArrayDeque<Integer>(c);
        mad.addLast(1);
        mad.addLast(10);
        mad.addLast(7);
        mad.addLast(2);
        mad.addLast(-1);
        assertThat(mad.max()).isEqualTo(10);
    }

    @Test
    public void test02() {
        Comparator<Integer> c = new MaxArrayDequeComp<>();
        Comparator<Integer> c2 = new MaxArrayDequeCompTwo<>();
        MaxArrayDeque<Integer> mad = new MaxArrayDeque<Integer>(c);
        mad.addLast(1);
        mad.addLast(10);
        mad.addLast(7);
        mad.addLast(2);
        mad.addLast(-1);
        assertThat(mad.max(c2)).isEqualTo(10);

    }
}
