package deque;

import java.util.ArrayList;
import java.util.Iterator;
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

    @Override
    public T getRecursive(int index) {
        return get(index);
    }

    @Override
    public Iterator<T> iterator() {
        return new ArrayDequeIterator();
    }

    private class ArrayDequeIterator implements Iterator<T> {
        private int wizPos;
        private int count;

        public ArrayDequeIterator() {
            wizPos = 0;
            count = 0;
        }

        public boolean hasNext() {
            return count < size;
        }

        public T next() {
            T returnItem = get(wizPos);
            count += 1;
            wizPos += 1;
            return returnItem;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (o instanceof ArrayDeque oad) {
            if (oad.size != this.size) {
                return false;
            }
            for (int i = 0; i < size; i += 1) {
                if (!oad.get(i).equals(this.get(i))) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        StringBuilder returnSB = new StringBuilder("[");
        for (int i = 0; i < size - 1; i += 1) {
            returnSB.append(this.get(i));
            returnSB.append(", ");
        }
        returnSB.append(this.get(size - 1));
        returnSB.append("]");
        return returnSB.toString();
    }

    public static void main(String[] args) {
        Deque<Integer> ad = new ArrayDeque<>();
        ad.addFirst(3);
        ad.addFirst(2);
        ad.addFirst(1);  //1 2 3
    }
}