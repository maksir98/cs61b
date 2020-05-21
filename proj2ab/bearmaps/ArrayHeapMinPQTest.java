package bearmaps;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class ArrayHeapMinPQTest {
    @Test
    public void addTest() {
        ArrayHeapMinPQ<Integer> example = new ArrayHeapMinPQ<>();
        for (int i = 55; i > 0; i --) {
            example.add(i,i);
        }
        example.printNode();
        int actual = example.getSmallest();
        int expect = 1;
        assertEquals(expect, actual);
    }

    @Test
    public void removeTest() {
        ArrayHeapMinPQ<Integer> example = new ArrayHeapMinPQ<>();
        for (int i = 20; i > 0; i --) {
            example.add(i, (double)i);
        }

        for (int i = 1; i < 5; i++) {
            example.removeSmallest();
        }
        int actual = example.removeSmallest();
        int expect = 5;
        assertEquals(expect, actual);
    }

    @Test
    public void changePrioritytest () {
        ArrayHeapMinPQ<Integer> example = new ArrayHeapMinPQ<>();
        for (int i = 20; i > 0; i --) {
            example.add(i, (double)i);
        }
        example.changePriority(10, -1);
        int actual = example.getSmallest();
        int expect = 10;
        assertEquals(expect, actual);
    }

    @Test
    public void crazyRemoveTest() {
        ArrayHeapMinPQ<Integer> example = new ArrayHeapMinPQ<>();
        for (int i = 10; i > 0; i --) {
            example.add(i, (double)i);
        }

        for (int i = 0; i < 20; i++) {
            example.removeSmallest();
        }

        assertEquals(0, example.size());
    }

    @Test
    public void timeTest() {
        long start, end;

        NaiveMinPQ<Integer> nPQ = new NaiveMinPQ<>();
        start = System.currentTimeMillis();
        for (int i = 200000; i > 0; i--) {
            nPQ.add(i, i);
        }
        end = System.currentTimeMillis();
        System.out.println("Add NaivePQ:" + (end-start)/1000 + "sec.");

        ArrayHeapMinPQ<Integer> myPQ = new ArrayHeapMinPQ<>();
        start = System.currentTimeMillis();
        for (int i = 200000; i > 0; i--) {
            myPQ.add(i, i);
        }
        end = System.currentTimeMillis();
        System.out.println("Add myPQ:" + (end-start)/1000 + "sec.");

        start = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            nPQ.removeSmallest();
        }
        end = System.currentTimeMillis();
        System.out.println("RemoveSmallest NaivePQ:" + (end-start)/1000 + "sec.");

        start = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            myPQ.removeSmallest();
        }
        end = System.currentTimeMillis();
        System.out.println("RemoveSmallest myPQ:" + (end-start)/1000 + "sec.");
    }
}
