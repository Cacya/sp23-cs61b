import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LinkedListDeque<T> implements Deque<T> {
    public static class IntNode<T> {
        IntNode prev;
        T item;
        IntNode next;

        public IntNode(IntNode p, T i, IntNode n) {
            prev = p;
            item = i;
            next = n;
        }
    }

    private IntNode sentinel;
    private int size;

    public LinkedListDeque() {
        sentinel = new IntNode(null,-5,null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel.prev;
        size = 0;
    }

    @Override
    public void addFirst(T x) {
        sentinel.next = new IntNode(sentinel, x, sentinel.next);
        sentinel.next.next.prev = sentinel.next;
        size += 1;
    }

    @Override
    public void addLast(T x) {
        sentinel.prev = new IntNode(sentinel.prev, x, sentinel);
        sentinel.prev.prev.next = sentinel.prev;
        size += 1;
    }

    @Override
    public List<T> toList() {
        List<T> returnList = new ArrayList<>();
        IntNode p = sentinel.next;
        while(p != sentinel) {
            returnList.add((T) p.item);
            p = p.next;
        }
        return returnList;
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
        if(size == 0) {
            return null;
        }
        T first_item = (T) sentinel.next.item;
        sentinel.next = sentinel.next.next;
        sentinel.next.prev = sentinel;
        size -= 1;
        return first_item;
    }

    @Override
    public T removeLast() {
        if(size == 0) {
            return null;
        }
        T last_item = (T) sentinel.prev.item;
        sentinel.prev = sentinel.prev.prev;
        sentinel.prev.next = sentinel;
        size -= 1;
        return last_item;
    }

    @Override
    public T get(int index) {
        if(index >= size || index < 0) {
            return null;
        }
        IntNode p = sentinel;
        for(int i = 0; i <= index; i++) {
            p = p.next;
        }
        return (T) p.item;
    }

    public T getRecursive(IntNode p, int index) {
        if(index == 0) {
            return (T) p.item;
        }
        return getRecursive(p.next, index - 1);
    }

    @Override
    public T getRecursive(int index) {
        if(index >= size || index < 0) {
            return null;
        }
        return getRecursive(sentinel.next, index);
    }

    public static void main(String[] args) {
        Deque<Integer> lld = new LinkedListDeque<Integer>();
        lld.addFirst(1);
        lld.addFirst(2);
        lld.addFirst(3);
        List<Integer> list= lld.toList();
        System.out.println(Arrays.toString(list.toArray()));
    }
}
