public class NBody {

	public static void main(String[] args) {
		double T = Double.parseDouble(args[0]);
		double dt = Double.parseDouble(args[1]);
		String filename = args[2];

		Planet[] planets = readPlanets(filename);
		double radius = readRadius(filename);

		StdDraw.setScale(-radius, radius);

		StdDraw.enableDoubleBuffering(); 

		double time = 0; 

		while (time < T) {
			double[] xForces = new double[planets.length];
			double[] yForces = new double[planets.length];

			for (int i = 0; i < planets.length; i++) {
				xForces[i] = planets[i].calcNetForceExertedByX(planets);
				yForces[i] = planets[i].calcNetForceExertedByY(planets);
			}

			for (int j = 0; j < planets.length; j++) {
				planets[j].update(dt, xForces[j], yForces[j]);				
			}

			StdDraw.picture(0, 0, "images/starfield.jpg");

			for (Planet planet : planets) {
				planet.draw();
			}

			StdDraw.show();
			StdDraw.pause(10);
			time += dt;
		}

		StdOut.printf("%d\n", planets.length);
		StdOut.printf("%.2e\n", radius);
		for (int i = 0; i < planets.length; i++) {
    		StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                  planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
                  planets[i].yyVel, planets[i].mass, planets[i].imgFileName);   
		}
	}
	

	public static double readRadius(String file) {
		In in = new In(file);

		int first = in.readInt(); 
		double second = in.readDouble(); 

		return second; 
	}

	public static Planet[] readPlanets(String file) {
		In in = new In(file);

		int size = in.readInt(); 
		double rad = in.readDouble(); 

		Planet[] planets = new Planet[size];

		for (int i = 0; i < size; i++) {

			double xxPos = in.readDouble(); 
			double yyPos = in.readDouble(); 
			double xxVel = in.readDouble(); 
			double yyVel = in.readDouble(); 
			double mass = in.readDouble(); 
			String img = in.readString(); 
			planets[i] = new Planet(xxPos,yyPos,xxVel,yyVel,mass,img);
		}

		return planets; 
	}
}