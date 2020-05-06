package hw2;

import org.junit.Test;
import static org.junit.Assert.*;

public class testPercolation {

    @Test
    public void testIsOpen() {
        Percolation obj = new Percolation(5);
        obj.open(1,1);
        obj.open(0,1);
        obj.open(1, 0);
        Boolean actual = obj.isOpen(1, 0);
        Boolean expect = true;
        assertEquals(expect, actual);
        actual = obj.isOpen(1 , 2);
        expect = false;
        assertEquals(expect, actual);
    }

    @Test
    public void testIsFull() {
        Percolation obj = new Percolation(5);
        obj.open(1,1);
        obj.open(0,1);
        obj.open(1, 0);
        Boolean actual = obj.isFull(1, 0);
        Boolean expect = true;
        assertEquals(expect, actual);
    }

    @Test
    public void testPercolates() {
        Percolation obj = new Percolation(5);
        obj.open(1,1);
        obj.open(0,1);
        obj.open(1, 0);
        obj.open(2, 0);
        obj.open(3, 0);
        obj.open(4, 0);
        Boolean actual = obj.percolates();
        Boolean expect = true;
        assertEquals(expect, actual);
    }
}
