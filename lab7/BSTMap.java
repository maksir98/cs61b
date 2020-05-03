import java.util.Iterator;
import java.util.Set;

public class BSTMap <K extends Comparable, V> implements Map61B <K, V> {
    private Node root;
    private int size;

    private class Node{
        private K key;
        private V value;
        private Node left = null;
        private Node right = null;

        public Node(K inputKey, V inputValue){
            key = inputKey;
            value = inputValue;
        }
    }

    public BSTMap() {
        size = 0;
    }

    @Override
    public void clear() {
        root = null;
        size = 0;
    }

    @Override
    public boolean containsKey(K key) {
        V result = get(key);
        if (result == null) {
            return false;
        }
        return true;
    }

    @Override
    public V get(K key) {
        if (key == null) throw new IllegalArgumentException("calls get() with a null key");
        return get(root, key);
    }

    private V get(Node x, K key) {
        if (x == null) {
            return null;
        }
        int cmp = key.compareTo(x.key);
        if (cmp < 0) {
            return get(x.left, key);
        } else if (cmp > 0) {
            return get(x.right, key);
        } else {
            return x.value;
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void put(K key, V value) {
        if (key == null) throw new IllegalArgumentException("calls put() with a null key");
        root = put(root, key, value);
        size ++ ;
    }
    private Node put(Node x,K key, V value) {
        if (x == null) {
            return new Node(key, value);
        }
        int cmp = key.compareTo(x.key);
        if (cmp < 0) {
            x.left = put(x.left, key, value);
        } else if(cmp > 0) {
            x.right = put(x.right, key, value);
        } else {
            x.value = value;
        }
        return x;
    }

    @Override
    public Set<K> keySet() {
        throw new UnsupportedOperationException();
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
        throw new UnsupportedOperationException();
    }

    public void printInOrder() {
        printNode(root);
        return;
    }
    private void printNode(Node x){
        if (x == null) {
            return;
        }
        //1.Print right
        printNode(x.right);
        //2.Print self
        System.out.println(x.key);
        //3.Print left
        printNode(x.left);
        return;
    }
}
