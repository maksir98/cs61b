package bearmaps;

import java.util.List;

public class NaivePointSet implements PointSet {
    List<Point> points;

    public NaivePointSet(List<Point> points) {
        this.points = points;
    }

    @Override
    public Point nearest(double x, double y) {
        Point nearest = points.get(0);
        double distance = calcDistance(x, nearest.getX(), y, nearest.getY());
        for (Point point: points) {
            double thisDis = calcDistance(x, point.getX(), y, point.getY());
            if (distance > thisDis) {
                distance = thisDis;
                nearest = point;
            }
        }
        return nearest;
    }

    private double calcDistance(double x1, double x2, double y1, double y2) {
        double x = Math.pow(x1 - x2, 2);
        double y = Math.pow(y1 - y2, 2);
        return x + y;
    }
}
