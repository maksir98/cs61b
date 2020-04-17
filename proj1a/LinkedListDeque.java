public class LinkedListDeque <T>{
    private IntNode sentinel;
    private int size;

    private class IntNode {
        IntNode prev;
        IntNode next;
        T item;

        public IntNode(T i, IntNode p, IntNode n) {
            item = i;
            prev = p;
            next = n;
        }
    }

    // Creates an empty linked list deque.
    public LinkedListDeque() {
        size = 0;
        sentinel = new IntNode(null, null,null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
    }

    public LinkedListDeque(LinkedListDeque other) {
        size = 0;
        sentinel = new IntNode(null, null,null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;

        for(int i = 0;i < other.size(); i++) {
            addLast((T) other.get(i));
        }
    }

    public void addFirst(T item) {
        IntNode newNode = new IntNode(item, sentinel, sentinel.next);
        sentinel.next = newNode;
        sentinel.next.next.prev = newNode;
        size ++;
    }

    public void addLast(T item) {
        IntNode newNode = new IntNode(item, sentinel.prev, sentinel);
        sentinel.prev = newNode;
        sentinel.prev.prev.next = newNode;
        size ++;
    }

    public boolean isEmpty() {
        if (sentinel.next == sentinel) {
            return true;
        } else {
            return false;
        }
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        IntNode p = sentinel;
        while (p.next != sentinel) {
            p = p.next;
            System.out.print(p.item + " ");
        }
        System.out.print('\n');
    }

    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        T result = sentinel.next.item;
        sentinel.next = sentinel.next.next;
        sentinel.next.prev = sentinel;
        return result;
    }

    public T removeLast() {
        if (size == 0) {
            return null;
        }
        T result = sentinel.prev.item;
        sentinel.prev = sentinel.prev.prev;
        sentinel.prev.next = sentinel;
        return result;
    }

    public T get(int index) {
        if (index + 1 > size) {
            return null;
        }
        IntNode p = sentinel.next;
        while (index != 0) {
            p = p.next;
            index --;
        }
        return  p.item;
    }

    public T getRecursive(int index) {
        return getRecursiveFunc(index, sentinel.next);
    }

    private T getRecursiveFunc(int i, IntNode p) {
        if (i == 0) {
            return p.item;
        }
        i = i - 1;
        return getRecursiveFunc(i, p.next);
    }
}