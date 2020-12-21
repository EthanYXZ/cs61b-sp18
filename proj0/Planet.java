public class Planet {
    public double xxPos, yyPos;
    public double xxVel, yyVel;
    public double mass;
    public String imgFileName;
    public static double G = 6.67 * Math.pow(10, -11);

    public Planet(double xP, double yP, double xV, double yV, double m, String img) {
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
    }

    public Planet(Planet b) {
        xxPos = b.xxPos;
        yyPos = b.yyPos;
        xxVel = b.xxVel;
        yyVel = b.yyVel;
        mass = b.mass;
        imgFileName = b.imgFileName;
    }

    public double calcDistance(Planet anotherBody) {
        double distance;
        distance = Math.sqrt(Math.pow((this.xxPos - anotherBody.xxPos), 2) + Math.pow((this.yyPos - anotherBody.yyPos), 2));
        return distance;
    }

    public double calcForceExertedBy(Planet b) {
        double distance = calcDistance(b);
        double force = G * this.mass * b.mass / Math.pow(distance, 2);
        return force;
    }

    public double calcForceExertedByX(Planet b) {
        double xforce = this.calcForceExertedBy(b) * (b.xxPos - this.xxPos) / this.calcDistance(b);
        return xforce;
    }

    public double calcForceExertedByY(Planet b) {
        double yforce = this.calcForceExertedBy(b) * (b.yyPos - this.yyPos) / this.calcDistance(b);
        return yforce;
    }

    public double calcNetForceExertedByX(Planet[] planets) {
        double xF = 0;
        for (Planet body: planets) {
            if (!this.equals(body)) {
                xF += calcForceExertedByX(body);
            }
        }
        return xF;
    }

    
    public double calcNetForceExertedByY(Planet[] planets) {
        double yF = 0;
        for (Planet body: planets) {
            if (!this.equals(body)) {
                yF += calcForceExertedByY(body);
            }
        }
        return yF;
    }

    public void update(double dt, double fX, double fY) {
        double ax = fX / mass;
        double ay = fY / mass;
        xxVel = xxVel + dt * ax;
        yyVel = yyVel + dt * ay;
        xxPos = xxPos + dt * xxVel;
        yyPos = yyPos + dt * yyVel;
    }

    public void draw(double xP, double yP, String img) {
        StdDraw.picture(xP, yP, img);
    }
}