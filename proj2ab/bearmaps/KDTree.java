package bearmaps;

import java.util.Comparator;
import java.util.List;

public class KDTree implements PointSet{
    private Node root;

    private class Node implements Comparable<Node> {
        double x;
        double y;
        int currentDimension;//0: x; 1: y;
        Node leftChild;
        Node rightChild;


        public Node(Point point, int dimension) {
            x = point.getX();
            y = point.getY();
            currentDimension = dimension;
        }

        public double distance(Point point) {
            return Math.pow(x - point.getX(), 2) + Math.pow(y - point.getY(), 2);
        }

        public void addNode(Point point) {
            int nextDimension;
            if (currentDimension == 0)  nextDimension = 1;
            else                        nextDimension = 0;

            Node newNode = new Node(point, nextDimension);
            if (this.compareTo(newNode) > 0) {
                if (leftChild == null)  leftChild = newNode;
                else                    leftChild.addNode(point);
            } else {
                if (rightChild == null) rightChild = newNode;
                else                    rightChild.addNode(point);
            }
        }


        @Override
        public int compareTo(Node o) {
            if (currentDimension == 0) {
                return (int) (x - o.x);
            } else {
                return (int) (y - o.y);
            }
        }
    }

    public KDTree(List<Point> points) {
        root = new Node(points.get(0), 0);
        for(int i = 1; i < points.size(); i++) {
            root.addNode(points.get(i));
        }
    }

    @Override
    public Point nearest(double x, double y) {
        Point point = new Point(x, y);
        Node nearst = nearest(root, point, root);
        Point result = new Point(nearst.x, nearst.y);
        return result;
    }

    private Node nearest(Node n, Point goal, Node best) {
        if (n == null)  return best;
        if (n.distance(goal) < best.distance(goal)) best = n;

        Node goalNode = new Node(goal, 0);
        Node goodSide, badSide;
        if (n.compareTo(goalNode) > 0) {
            goodSide = n.leftChild;
            badSide = n.rightChild;
        } else {
            goodSide = n.rightChild;
            badSide = n.leftChild;
        }
        best = nearest(goodSide, goal, best);

        Node badSideClosest;
        if (n.currentDimension == 0)    badSideClosest = new Node(new Point(n.x, goal.getY()), 1);
        else                            badSideClosest = new Node(new Point(goal.getX(), n.y), 0);
        if (badSideClosest.distance(goal) < best.distance(goal)) {
            best = nearest(badSide, goal, best);
        }

        return best;
    }



    public static void main(String[] arg) {

    }
}
