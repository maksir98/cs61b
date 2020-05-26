package bearmaps;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.junit.Assert.assertEquals;

public class KDTreeTest {
    @Test
    public void nearestTest() {
        Point p1 = new Point(1.1, 2.2); // constructs a Point with x = 1.1, y = 2.2
        Point p2 = new Point(3.3, 4.4);
        Point p3 = new Point(-2.9, 4.2);
        NaivePointSet nn = new NaivePointSet(List.of(p1, p2, p3));
        Point ret = nn.nearest(3.0, 4.0); // returns p2
        assertEquals(3.3, ret.getX(), 0.01);
        assertEquals(4.4, ret.getY(), 0.01);

        Point p4 = new Point(2, 3);
        Point p5 = new Point(4, 2);
        Point p6 = new Point(4, 5);
        Point p7 = new Point(3, 3);
        Point p8 = new Point(1, 5);
        Point p9 = new Point(4, 4);
        KDTree k = new KDTree(List.of(p4,p5,p6,p7,p8,p9));
        Point ret2 = k.nearest(0, 7);
        assertEquals(1, ret2.getX(), 0.01);
        assertEquals(5, ret2.getY(), 0.01);
    }

    @Test
    public void buildTreeWithDouble() {
        Point p1 = new Point(2, 3);
        Point p2 = new Point(2, 3);
        KDTree k = new KDTree(List.of(p1, p2));
        Point ret = k.nearest(0, 7);
    }

    @Test
    public void randomTest() {
        long curr, end;
        Random num = new Random(255);
        List<Point> pointList = new ArrayList<>();
        for (long i = 0; i < 2500000; i++) {
            pointList.add(new Point(num.nextDouble()*100, num.nextDouble()*100));
        }
        curr = System.currentTimeMillis();
        KDTree k = new KDTree(pointList);
        end = System.currentTimeMillis();
        System.out.println("Construct KDTree: " + (end - curr) + "ms.");

        curr = System.currentTimeMillis();
        NaivePointSet n = new NaivePointSet(pointList);
        end = System.currentTimeMillis();
        System.out.println("Construct NaiveTree: " + (end - curr) + "ms.");


        curr = System.currentTimeMillis();
        Point kRet = k.nearest(1,1);
        end = System.currentTimeMillis();
        System.out.println("KDTree nearest: " + (end - curr) + "ms.");

        curr = System.currentTimeMillis();
        Point nRet = n.nearest(1,1);
        end = System.currentTimeMillis();
        System.out.println("NaiveTree nearest: " + (end - curr) + "ms.");

        assertEquals(kRet.getX(), nRet.getX(), 0.001);
        assertEquals(kRet.getY(), nRet.getY(), 0.001);
    }
}