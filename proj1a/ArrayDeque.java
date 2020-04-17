public class ArrayDeque<T> {
    private int size;
    private int nextFirst;
    private int nextLast;
    private T[] items;

    public ArrayDeque(){
        size = 0;
        items = (T[]) new Object[8];
        nextFirst = 3;
        nextLast = 4;
    }

    public ArrayDeque(ArrayDeque other){
        size = 0;
        items = (T[]) new Object[8];
        nextFirst = 3;
        nextLast = 4;

        for (int i = 0; i < other.size(); i++){
            addLast((T) other.get(i));
        }

    }

    private void resize(int capability){
        T[] newItems = (T[]) new Object[capability];
        int newItemsIndex = newItems.length/4;
        int itemsIndex = nextLast;
        for (int i = 0;i < items.length; i++) {
            newItems[newItemsIndex] = items[itemsIndex];
            newItemsIndex ++;
            if (newItemsIndex == (items.length - 1)){
                newItemsIndex = 0;
            }else {
                newItemsIndex ++;
            }
        }

        items  = newItems;
        nextFirst = items.length/4 - 1;
        nextLast = items.length - items.length/4;

    }

    private void nextFirstMove() {
        if (nextFirst == 0){
            nextFirst = items.length - 1;
        }else {
            nextFirst --;
        }
    }

    private void nextLastMove() {
        if (nextLast == items.length - 1){
            nextLast = 0;
        }else {
            nextLast ++ ;
        }
    }

    public void addFirst(T item){
        if (size == items.length) {
            resize(size * 2);
        }

        size ++;
        items[nextFirst] = item;
        nextFirstMove();
    }

    public void addLast(T item){
        if (size == items.length) {
            resize(size * 2);
        }

        size ++;
        items[nextLast] = item;
        nextLastMove();
    }

    public boolean isEmpty(){
        if (size == 0){
            return true;
        }
        return false;
    }

    public int size(){
        return size;
    }

    public void printDeque(){
        int index = nextFirst;
        if (nextFirst == items.length - 1){
            index = 0;
        }else {
            index ++;
        }

        for (int i = 0; i < size;i++){
            System.out.print(items[index]);
            System.out.print(' ');
            if (index == items.length - 1){
                index = 0;
            } else {
                index ++;
            }
        }
    }

    public T removeFirst(){
        if (nextFirst == items.length - 1){
            nextFirst = 0;
        }else {
            nextFirst ++;
        }
        T result = items[nextFirst];
        items[nextFirst] = null;
        size --;
        return result;
    }

    public T removeLast(){
        if (nextLast == 0) {
            nextLast = items.length - 1;
        } else {
            nextLast --;
        }
        T result = items[nextLast];
        items[nextLast] = null;
        size --;
        return result;
    }

    public T get(int index){
        int realIndex = nextFirst + 1 + index;
        if (realIndex >= items.length) {
            realIndex  = realIndex - items.length;
        }
        return items[index];
    }
}
