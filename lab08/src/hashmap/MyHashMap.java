package hashmap;

import java.util.*;

/**
 *  A hash table-backed Map implementation. Provides amortized constant time
 *  access to elements via get(), remove(), and put() in the best case.
 *
 *  Assumes null keys will never be inserted, and does not resize down upon remove().
 *  @author Cacya
 */
public class MyHashMap<K, V> implements Map61B<K, V> {

    private void resize() {
        this.M *= 2;
        Collection<Node>[] newBuckets = createTable(this.M);
        for (Collection<Node> bucket: buckets) {
            for (Node n: bucket) {
                newBuckets[Math.floorMod(n.key.hashCode(), M)].add(n);
            }
        }
        buckets = newBuckets;
    }

    /**
     * Associates the specified value with the specified key in this map.
     * If the map already contains the specified key, replaces the key's mapping
     * with the value specified.
     *
     * @param key
     * @param value
     */
    @Override
    public void put(K key, V value) {
        int has = Math.floorMod(key.hashCode(), this.M);
        for (Node n: buckets[has]) {
            if (n.key.equals(key)) {
                n.value = value;
                return;
            }
        }
        if (loads >= loadFactor) {
            resize();
            has = Math.floorMod(key.hashCode(), this.M);
        }
        buckets[has].add(createNode(key, value));
        size += 1;
        loads = 1.0 * size / M;
    }

    /**
     * Returns the value to which the specified key is mapped, or null if this
     * map contains no mapping for the key.
     *
     * @param key
     */
    @Override
    public V get(K key) {
        int has = Math.floorMod(key.hashCode(), M);
        for (Node n: buckets[has]) {
            if (n.key.equals(key)) {
                return n.value;
            }
        }
        return null;
    }

    /**
     * Returns whether this map contains a mapping for the specified key.
     *
     * @param key
     */
    @Override
    public boolean containsKey(K key) {
        int has = Math.floorMod(key.hashCode(), M);
        for (Node n: buckets[has]) {
            if (n.key.equals(key)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns the number of key-value mappings in this map.
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Removes every mapping from this map.
     */
    @Override
    public void clear() {
        buckets = createTable(16);
        M = 16;
        size = 0;
        loads = 0;
    }

    /**
     * Returns a Set view of the keys contained in this map. Not required for Lab 8.
     * If you don't implement this, throw an UnsupportedOperationException.
     */
    @Override
    public Set<K> keySet() {
        Set<K> set = new HashSet<>();
        for (Collection<Node> bucket: buckets) {
            for (Node n: bucket) {
                set.add(n.key);
            }
        }
        return set;
    }

    /**
     * Removes the mapping for the specified key from this map if present,
     * or null if there is no such mapping.
     * Not required for Lab 8. If you don't implement this, throw an
     * UnsupportedOperationException.
     *
     * @param key
     */
    @Override
    public V remove(K key) {
        int has = Math.floorMod(key.hashCode(), M);
        for(Node n: buckets[has]) {
            if (n.key.equals(key)) {
                V value = n.value;
                buckets[has].remove(n);
                return value;
            }
        }
        return null;
    }

    /**
     * Returns an iterator over elements of type {@code T}.
     *
     * @return an Iterator.
     */
    @Override
    public Iterator iterator() {
        return new MyHashMapIterator();
    }

    private class MyHashMapIterator implements Iterator<Collection<Node>> {
        private int count;

        public MyHashMapIterator() {
            count = 0;
        }

        @Override
        public boolean hasNext() {
            return count < size;
        }

        @Override
        public Collection<Node> next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            Collection<Node> bucket = buckets[count];
            count += 1;
            return bucket;
        }
    }


    /**
     * Protected helper class to store key/value pairs
     * The protected qualifier allows subclass access
     */
    protected class Node {
        K key;
        V value;

        Node(K k, V v) {
            key = k;
            value = v;
        }
    }

    /* Instance Variables */
    private Collection<Node>[] buckets;
    private double loadFactor = 0.75;
    private double loads = 0;
    private int size = 0;
    private int M;

    // You should probably define some more!

    /** Constructors */
    public MyHashMap() {
        buckets = createTable(16);
        this.M = 16;
    }

    public MyHashMap(int initialCapacity) {
        buckets = createTable(initialCapacity);
        this.M = initialCapacity;
    }

    /**
     * MyHashMap constructor that creates a backing array of initialCapacity.
     * The load factor (# items / # buckets) should always be <= loadFactor
     *
     * @param initialCapacity initial size of backing array
     * @param loadFactor maximum load factor
     */
    public MyHashMap(int initialCapacity, double loadFactor) {
        buckets = createTable(initialCapacity);
        this.M = initialCapacity;
        this.loadFactor = loadFactor;
    }

    /**
     * Returns a new node to be placed in a hash table bucket
     */
    private Node createNode(K key, V value) {
        return new Node(key, value);
    }

    /**
     * Returns a data structure to be a hash table bucket
     *
     * The only requirements of a hash table bucket are that we can:
     *  1. Insert items (`add` method)
     *  2. Remove items (`remove` method)
     *  3. Iterate through items (`iterator` method)
     *
     * Each of these methods is supported by java.util.Collection,
     * Most data structures in Java inherit from Collection, so we
     * can use almost any data structure as our buckets.
     *
     * Override this method to use different data structures as
     * the underlying bucket type
     *
     * BE SURE TO CALL THIS FACTORY METHOD INSTEAD OF CREATING YOUR
     * OWN BUCKET DATA STRUCTURES WITH THE NEW OPERATOR!
     */
    protected Collection<Node> createBucket() {
        return new LinkedList<>();
    }

    /**
     * Returns a table to back our hash table. As per the comment
     * above, this table can be an array of Collection objects
     *
     * BE SURE TO CALL THIS FACTORY METHOD WHEN CREATING A TABLE SO
     * THAT ALL BUCKET TYPES ARE OF JAVA.UTIL.COLLECTION
     *
     * @param tableSize the size of the table to create
     */
    private Collection<Node>[] createTable(int tableSize) {
        Collection[] c = new Collection[tableSize];
        for (int i = 0; i < tableSize; i++) {
            c[i] = createBucket();
        }
        return c;
    }
}
