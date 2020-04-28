import java.util.Iterator;

public class ArraySet<T> implements Iterable<T> {
    private T[] items;
    private int size;

    public ArraySet() {
        items = (T[]) new Object[100];
        size = 0;
    }

    /* Returns true if this map contains a mapping for the specified key.
     */
    public boolean contains(T x) {
        for (int i = 0; i < size; i++) {
            if (items[i].equals(x)) {
                return true;
            }
        }
        return false;
    }

    /* Associates the specified value with the specified key in this map. 
       Throws an IllegalArgumentException if the key is null. */
    public void add(T x) {
        if (x == null) {
            throw new IllegalArgumentException("You can't add null to an ArraySet.");
        }
        if (contains(x)) {
            return;
        }
        items[size] = x;
        size ++;
        return;
    }

    /* Returns the number of key-value mappings in this map. */
    public int size() {
        return size;
    }

    public static void main(String[] args) {
        ArraySet<String> s = new ArraySet<>();
//        s.add(null);
        s.add("horse");
        s.add("fish");
        s.add("house");
        s.add("fish");
        ArraySet<String> b = new ArraySet<>();
        b.add("horse");
        b.add("house");
        b.add("fish");
        System.out.println(b.equals(s));
    }

    @Override
    public Iterator<T> iterator() {
        Iterator<T> i = new ArraySetIterator();
        return i;
    }

    /* Also to do:
    1. Make ArraySet implement the Iterable<T> interface.
    2. Implement a toString method.
    3. Implement an equals() method.
    */
    public class ArraySetIterator implements Iterator<T> {
        private int wizPos;

        public ArraySetIterator() {
            wizPos = 0;
        }

        @Override
        public boolean hasNext() {
            return wizPos < size;
        }

        @Override
        public T next() {
            T returnItem = items[wizPos];
            wizPos ++;
            return returnItem;
        }
    }

    @Override
    public String toString() {
        StringBuilder returnString = new StringBuilder("{");
        for (int i = 0; i < size - 1; i ++ ){
            returnString.append(items[i]);
            returnString.append(",");
        }
        returnString.append(items[size - 1]);
        returnString.append("}");
        return returnString.toString();
    }


    @Override
    public boolean equals(Object other) {
        if (this == other) { return true; }
        if (other == null) { return false; }
        if (other.getClass() != this.getClass()) {
            return false;
        }
        ArraySet<T> o = (ArraySet<T>) other;
        if (this.size() != o.size()) {
            return false;
        }
        for (T i : this) {
            if (!o.contains(i)) {
                return false;
            }
        }
        return true;
    }

}
