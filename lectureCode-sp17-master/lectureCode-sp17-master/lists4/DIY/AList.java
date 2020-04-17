/** Array based list.
 *  @author Josh Hug
 */

public class AList {
    int size ;
    int[] item;

    /** Creates an empty list. */
    public AList() {
        size = 0;
        item = new int[100];
    }

    /** Inserts X into the back of the list. */
    public void addLast(int x) {
        if (size == item.length){
            int[] p = new int[size + 1000];
            System.arraycopy(item, 0, p,0,size);
            item = p;
        }
        item[size] = x;
        size ++;
    }

    /** Returns the item from the back of the list. */
    public int getLast() {
        return item[size - 1];
    }
    /** Gets the ith item in the list (0 is the front). */
    public int get(int i) {
        return item[i];
    }

    /** Returns the number of items in the list. */
    public int size() {
        return size;
    }

    /** Deletes item from back of the list and
      * returns deleted item. */
    public int removeLast() {
        size -- ;
        return item[size];
    }
} 