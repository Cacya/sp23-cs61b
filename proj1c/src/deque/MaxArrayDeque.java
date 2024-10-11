package deque;

import java.util.Comparator;

public class MaxArrayDeque<T> extends ArrayDeque {
    private Comparator<T> c;
    public MaxArrayDeque(Comparator<T> c) {
        this.c = c;
    }

    private T getMax(Comparator<T> c) {
        T max_item = (T) get(0);
        for (int i = 0; i < size(); i++) {
            T item = (T) get(i);
            if (c.compare(max_item, item) < 0) {
                max_item = item;
            }
        }
        return max_item;
    }

    public T max() {
        return getMax(c);
    }

    public T max(Comparator<T> c) {
        return getMax(c);
    }
}
