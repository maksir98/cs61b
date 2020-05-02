import org.junit.Test;
import static org.junit.Assert.*;

public class testUnionFind {
//    @Test(expected = RuntimeException.class)
//    public void testValidate() {
//        UnionFind u = new UnionFind(100);
//        int input = 101;
//        u.validate(input);
//    }

    @Test
    public void testUnion() {
        UnionFind u = new UnionFind(100);
        u.union(1,2);
        u.union(3,2);
        u.union(3,4);
        Boolean actual = u.connected(1,4);
        Boolean expect = true;
        assertEquals(expect, actual);

        actual = u.connected(1, 5);
        expect = false;
        assertEquals(expect, actual);
    }

    @Test
    public void testSize() {
        UnionFind u = new UnionFind(100);
        u.union(1,2);
        u.union(3,2);
        u.union(3,4);
        int actual = u.sizeOf(2);
        int expect = 4;
        assertEquals(expect, actual);

        u.union(6,7);
        u.union(7,8);
        u.union(8,9);
        u.union(4,6);
        Boolean actual2 = u.connected(1, 6);
        Boolean expect2 = true;
        assertEquals(expect2, actual2);

        actual = u.sizeOf(2);
        expect = 8;
        assertEquals(expect, actual);
    }
}
