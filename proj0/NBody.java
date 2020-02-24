public class NBody {
    public static double readRadius (String txt) {
        In in = new In(txt);
        String line = in.readAllStrings()[1];
        double radius = Double.parseDouble(line);
        return radius;
    }

    public static Body[] readBodies (String txt) {
        In in = new In(txt);
        int plantNum = in.readInt();
        in.readDouble();
        Body[] bodies = new Body[plantNum];
        for(int i = 0; i < plantNum; i++) {
            double xP = in.readDouble();
            double yP = in.readDouble();
            double xV = in.readDouble();
            double yV = in.readDouble();
            double m = in.readDouble();
            String img = in.readString();
            bodies[i] = new Body(xP, yP, xV, yV, m, img);
        }
        return bodies;
    }

    public static void main (String[] args) {
        int t = 0;
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];
        double radius = NBody.readRadius(filename);
        Body[] bodies = NBody.readBodies(filename);
        String backgroundImg = "images/starfield.jpg";

        StdDraw.enableDoubleBuffering();
        StdDraw.setScale(-radius, radius);
        StdDraw.clear();
        StdDraw.picture(0, 0, backgroundImg);
        StdDraw.show();

        for(int i = 0; i < bodies.length; i++) {
            bodies[i].draw();
        }
        StdDraw.show();

        for (t = 0; t <= T; t += dt) {
            double[] xForce = new double[bodies.length];
            double[] yForce = new double[bodies.length];
            for (int i = 0 ; i < bodies.length; i++) {
                xForce[i] = bodies[i].calcNetForceExertedByX(bodies);
                yForce[i] = bodies[i].calcNetForceExertedByY(bodies);
                bodies[i].update(dt, xForce[i], yForce[i]);
            }
            StdDraw.clear();
            StdDraw.picture(0, 0, backgroundImg);
            for(int i = 0; i < bodies.length; i++) {
                bodies[i].draw();
            }
            StdDraw.show();
            StdDraw.pause(10);
        }
    }
}