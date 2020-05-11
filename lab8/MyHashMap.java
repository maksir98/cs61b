import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class MyHashMap<K, V> implements Map61B<K, V>{
    private HashSet<K> keys;
    private ArrayList<Pair>[] buckets;
    private double loadFactor = 0.75;
    private int size = 0;
    private final int resizeFactor = 2;

    private class Pair {
        int hashCode;
        V value;

        public Pair(int hashCode, V value) {
            this.hashCode = hashCode;
            this.value = value;
        }
    }

    public MyHashMap() {
        this.buckets = (ArrayList<Pair>[])new ArrayList[16];
        bucketInit(this.buckets);
        keys = new HashSet<K>();
    }

    public MyHashMap(int initialSize) {
        this.buckets = (ArrayList<Pair>[])new ArrayList[initialSize];
        bucketInit(this.buckets);
        keys = new HashSet<K>();
    }

    public MyHashMap(int initialSize, double loadFactor) {
        this.buckets = (ArrayList<Pair>[])new ArrayList[initialSize];
        bucketInit(this.buckets);
        this.loadFactor = loadFactor;
        keys = new HashSet<K>();
    }

    private void bucketInit(ArrayList<Pair>[] b) {
        for (int i = 0; i < b.length; i++){
            b[i] = new ArrayList<Pair>();
        }
    }

    @Override
    public void clear() {
        size = 0;
        this.buckets = (ArrayList<Pair>[]) new ArrayList[16];
        bucketInit(this.buckets);
        keys.clear();
    }

    @Override
    public boolean containsKey(K key) {
        return keys.contains(key);
    }

    @Override
    public V get(K key) {
        int hCode = key.hashCode();
        int bucketNum = getBucketNum(hCode, buckets.length);
        for (Pair item: buckets[bucketNum]) {
            if (item.hashCode == hCode) return item.value;
        }
        return null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void put(K key, V value) {
        if (((double)size)/((double) buckets.length) > loadFactor) {
            resize();
        }
        //keys
        if (!keys.contains(key)){
            keys.add(key);
            size ++;

            //buckets
            int hCode = key.hashCode();
            int bucketNum = getBucketNum(hCode, buckets.length);
            Pair pair = new Pair(hCode, value);
            buckets[bucketNum].add(pair);
        } else {
            int hCode = key.hashCode();
            int bucketNum = getBucketNum(hCode, buckets.length);
            for (Pair item: buckets[bucketNum]) {
                if (item.hashCode == hCode) item.value = value;
            }
        }
    }

    private int getBucketNum(int hashCode, int bucketLength) {
        if (hashCode < 0) hashCode = -hashCode;
        return hashCode % bucketLength;
    }

    private void resize() {
        //创造一个新的buckets
        int bucketLength = resizeFactor * buckets.length;
        ArrayList<Pair>[] newBuckets = (ArrayList<Pair>[]) new ArrayList[bucketLength];
        bucketInit(newBuckets);

        //将旧buckets里的东西装进新buckets里
        for (ArrayList<Pair> bucket: buckets) {
            for (Pair item: bucket){
                int bucketNum = getBucketNum(item.hashCode, bucketLength);
                Pair pair = new Pair(item.hashCode, item.value);
                newBuckets[bucketNum].add(pair);
            }
        }

        this.buckets = newBuckets;
    }

    @Override
    public Set<K> keySet() {
        return keys;
    }

    @Override
    public V remove(K key) {
        throw new UnsupportedOperationException();
    }

    @Override
    public V remove(K key, V value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterator<K> iterator() {
        return keys.iterator();
    }
}
