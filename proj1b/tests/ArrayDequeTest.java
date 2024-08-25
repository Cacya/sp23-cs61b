import org.junit.jupiter.api.Test;

import java.util.List;

import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth.assertWithMessage;

public class ArrayDequeTest {
    @Test
    public void addFirstAndGetTest()
    {
        Deque<Integer> ad = new ArrayDeque<>();
        ad.addFirst(3);
        ad.addFirst(2);  //2 3
        assertThat(ad.get(0)).isEqualTo(2);
        assertThat(ad.get(1)).isEqualTo(3);
    }

    @Test
    public void AddLastTest() {
        Deque<Integer> ad = new ArrayDeque<>();
        ad.addFirst(3);  //3
        ad.addFirst(10);  //10 3
        ad.addLast(9);  //10 3 9
        ad.addLast(2);  //10 3 9 2
        List<Integer> lst = ad.toList();
        assertThat(lst).containsExactly(10, 3, 9, 2).inOrder();
    }

    @Test
    public void newAddFirstTest() {
        Deque<Integer> ad = new ArrayDeque<>();
        ad.addFirst(3);  //3
        ad.addFirst(10);  //10 3
        ad.addLast(9);  //10 3 9
        ad.addLast(2);  //10 3 9 2
        ad.addFirst(5);  //3
        ad.addFirst(4);  //3
        ad.addFirst(3);  //3
        ad.addFirst(2);  //2 3 4 5 10 3 9 2
        List<Integer> lst = ad.toList();
        assertThat(lst).containsExactly(2, 3, 4, 5, 10, 3, 9, 2).inOrder();
    }

    @Test
    public void getTest() {
        Deque<Integer> ad = new ArrayDeque<>();
        ad.addFirst(3);  //3
        assertThat(ad.get(0)).isEqualTo(3);
        assertThat(ad.get(-10)).isEqualTo(null);
        assertThat(ad.get(10)).isEqualTo(null);
    }

    @Test
    public void removeFirstTest() {
        Deque<Integer> ad = new ArrayDeque<>();
        ad.addFirst(3);  //3
        ad.addFirst(10);  //10 3
        int first = ad.removeFirst();
        List<Integer> lst = ad.toList();
        assertThat(lst).containsExactly(3).inOrder();
        assertThat(first).isEqualTo(10);
    }
    @Test
    public void removeLastTest() {
        Deque<Integer> ad = new ArrayDeque<>();
        ad.addFirst(3);  //3
        ad.addFirst(9);  //10 3
        int first = ad.removeLast();
        List<Integer> lst = ad.toList();
        assertThat(lst).containsExactly(9).inOrder();
        assertThat(first).isEqualTo(3);
    }
}
