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
}