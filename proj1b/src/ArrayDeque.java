import java.util.ArrayList;
import java.util.List;

public class ArrayDeque<T> implements Deque<T> {
    private T[] items;
    private int size;
    private int first;  //ne_first
    private int last;

    public ArrayDeque() {
        items = (T[])new Object[4];
        size = 0;
        first = 1;
        last = 2;
    }

    private void resize(int capacity) {
        T[] a = (T[]) new Object[capacity];
        for(int i = 0; i < size; i++) {
            a[i] = get(i);
        }
        items = a;
        first = items.length - 1;
        last = size;
    }

    @Override
    public void addFirst(T x) {
        if(size == items.length) {
            resize(size * 2);
        }
        items[first] = x;
        size++;

        if(first == 0) {
            first = items.length - 1;
        } else {
            first -= 1;
        }
    }

    @Override
    public void addLast(T x) {
        if(size == items.length) {
            resize(size * 2);
        }
        items[last] = x;
        size++;

        if(last == items.length - 1) {
            last = 0;
        } else {
            last += 1;
        }
    }

    @Override
    public List<T> toList() {
        List<T> lst = new ArrayList<>();
        for(int i = 0; i < size; i++) {
            lst.add(get(i));
        }
        return lst;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public T removeFirst() {
        if(first == items.length - 1) {
            first = 0;
        } else {
            first += 1;
        }
        T temp = items[first];
        items[first] = null;
        size--;
        if(items.length >= 16 && items.length > size * 2 * 2) {
            resize(items.length / 2);
        }
        return temp;
    }

    @Override
    public T removeLast() {
        if(last == 0) {
            last = items.length - 1;
        } else {
            last -= 1;
        }
        T temp = items[last];
        items[last] = null;
        size--;
        if(items.length >= 16 && items.length > size * 2 * 2) {
            resize(items.length / 2);
        }
        return temp;
    }

    @Override
    public T get(int index) {
        if(index < 0 || index > size) {
            return null;
        }
        int real_index = first + 1 + index;
        if(real_index > items.length - 1) {
            return items[real_index - items.length];
        }
        return items[real_index];
    }

    public static void main(String[] args) {
        Deque<Integer> ad = new ArrayDeque<>();
        ad.addFirst(3);
        ad.addFirst(2);
        ad.addFirst(1);  //1 2 3
    }
}