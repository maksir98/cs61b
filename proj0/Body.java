public class Body {
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;

    /**
     * 
     * @param xP Its current x position
     * @param yP Its current y position
     * @param xV Its current velocity in the x direction
     * @param yV Its current velocity in the y direction
     * @param m Its mass
     * @param img The name of the file that corresponds to the image that depicts the body (for example, jupiter.gif)
     */
    public Body (double xP,double yP,double xV,double yV,double m,String img) {
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
    }

    public Body (Body b) {
        xxPos = b.xxPos;
        yyPos = b.yyPos;
        xxVel = b.xxVel;
        yyVel = b.yyVel;
        mass = b.mass;
        imgFileName = b.imgFileName;
    }

    /**
     * calculates the distance between two Bodys
     * @param b 
     * @return distance
     */
    public double calcDistance (Body b) {
        double xDistance = b.xxPos - this.xxPos;
        double yDistance = b.yyPos - this.yyPos;
        double distance = Math.sqrt(xDistance * xDistance + yDistance * yDistance);
        return distance;
    }

    /**
     * returns a double describing the force exerted on this body by the given body
     * @param b
     * @return
     */
    public double calcForceExertedBy (Body b) {
        double G = 6.67e-11;
        double force = G*this.mass*b.mass/Math.pow(calcDistance(b), 2);
        return force;
    }

    public double calcForceExertedByX (Body b) {
        double distance = calcDistance(b);
        double xDistance = b.xxPos - this.xxPos;
        double force = calcForceExertedBy(b);
        double forceExertedByX = force*xDistance/distance;
        return forceExertedByX;
    }

    public double calcForceExertedByY (Body b) {
        double distance = calcDistance(b);
        double yDistance = b.yyPos - this.yyPos;
        double force = calcForceExertedBy(b);
        double forceExertedByY = force*yDistance/distance;
        return forceExertedByY;
    }
    
    public double calcNetForceExertedByX (Body[] b) {
        double netForceExertedByX = 0;
        for (int i = 0; i < b.length; i++) {
            if (b[i].equals(this)) {continue;}
            double force = calcForceExertedByX(b[i]);
            netForceExertedByX = netForceExertedByX + force;
        }
        return netForceExertedByX;
    }

    public double calcNetForceExertedByY (Body[] b) {
        double netForceExertedByY = 0;
        for (int i = 0; i < b.length; i++) {
            if (b[i].equals(this)) {continue;}
            double force = calcForceExertedByY(b[i]);
            netForceExertedByY = netForceExertedByY + force;
        }
        return netForceExertedByY;
    }

    /**
     * determines how much the forces exerted on the body will cause that body to accelerate, 
     * and the resulting change in the body’s velocity and position in a small period of time dt.
     * @param dt
     * @param xNetForce
     * @param yNetForce
     */
    public void update (double dt, double xNetForce, double yNetForce) {
        //Calculate the acceleration using the provided x- and y-forces.
        double aNetX = xNetForce/this.mass;
        double aNetY = yNetForce/this.mass;

        //Calculate the new velocity
        this.xxVel = this.xxVel + aNetX*dt;
        this.yyVel = this.yyVel + aNetY*dt;

        //Calculate the new position
        this.xxPos = xxPos + this.xxVel*dt;
        this.yyPos = yyPos + this.yyVel*dt;
    }
}