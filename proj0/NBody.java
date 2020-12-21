public class NBody {
    public static double readRadius(String file) {
        In filename = new In(file);
        int counts = filename.readInt();
		double radius = filename.readDouble();
        return radius;
    }

    public static Planet[] readPlanets(String file) {
        In filename = new In(file);
        int counts = filename.readInt();
		double radius = filename.readDouble();
        Planet[] bodies = new Planet[5];
        for (int i =0; i < 5; i++) {
            double xxPos = filename.readDouble();
            double yyPos = filename.readDouble();
            double xxVel = filename.readDouble();
            double yyVel = filename.readDouble();
            double mass = filename.readDouble();
            String planetName =filename.readString();
            bodies[i] = new Planet(xxPos, yyPos, xxVel, yyVel, mass, planetName);
        }
        return bodies;
    }

    public static void main(String[] args) {
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];
        double radius = readRadius(filename);
        Planet[] planets = readPlanets(filename);

        /**
        *Draw the background
         */
        StdDraw.setScale(-radius, radius);
        StdDraw.clear();
        StdDraw.picture(0, 0, "images/starfield.jpg");

        /**
        *Draw the planets
         */
        for (int i = 0; i < planets.length; i++) {
            planets[i].draw(planets[i].xxPos, planets[i].yyPos, planets[i].imgFileName);
        }
        StdDraw.enableDoubleBuffering();
        
        for (double t = 0; t <= T; t += dt) {
            double[] xForces = new double[planets.length];
            double[] yForces = new double[planets.length];
            for (int i = 0; i < planets.length; i++) {
                 xForces[i] = planets[i].calcNetForceExertedByX(planets);
                 yForces[i] = planets[i].calcNetForceExertedByY(planets);
                 planets[i].update(dt, xForces[i], yForces[i]);
            }
            StdDraw.picture(0, 0, "images/starfield.jpg");
            for (int i = 0; i < planets.length; i++) {
                planets[i].draw(planets[i].xxPos, planets[i].yyPos, planets[i].imgFileName);
            }
            StdDraw.show();
            StdDraw.pause(10);
        }

        /**
        *Pringting the Universe
         */
        StdOut.printf("%d\n", planets.length);
        StdOut.printf("%.2e\n", radius);
        for (int i = 0; i < planets.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                            planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
                            planets[i].yyVel, planets[i].mass, planets[i].imgFileName);   
        }
    }
}