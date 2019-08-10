public class NBody{
	public static double readRadius (String filename) {
		In in = new In(filename);
		in.readInt();
		double radius = in.readDouble();
		return radius;
	}

	public static Body[] readBodies(String filename){
		In in = new In(filename);
		int num = in.readInt();
		Body[] Planets = new Body[num];
		in.readDouble();
		for(int i=0;i<num;i++){
			double xP = in.readDouble();
            double yP = in.readDouble();
            double xV = in.readDouble();
            double yV = in.readDouble();
            double m = in.readDouble();
            String img = in.readString();
            Planets[i] = new Body(xP, yP, xV, yV, m, img);
		}
		return Planets;
	}

	public static void main(String[] args){
		double T = Double.parseDouble(args[0]);
		double dt = Double.parseDouble(args[1]);
		String filename = args[2];
		double radius = readRadius(filename);
		Body[] Planets = readBodies(filename);

		StdDraw.setScale(-radius, radius);
		StdDraw.clear();
		StdDraw.picture(0,0,"images/starfield.jpg");

		for(Body planet : Planets){
			planet.draw();
		}

		StdDraw.enableDoubleBuffering();

		for(double t=0; t<=T; t+=dt){
			double[] xForces = new double[Planets.length];
			double[] yForces = new double[Planets.length];
			for(int i=0; i<Planets.length; i++){
				xForces[i] = Planets[i].calcNetForceExertedByX(Planets);
				yForces[i] = Planets[i].calcNetForceExertedByY(Planets);
			}

			for(int i=0; i<Planets.length; i++){
				Planets[i].update(dt, xForces[i], yForces[i]);
			}
			StdDraw.picture(0, 0, "images/starfield.jpg");

			for(Body planet : Planets){
				planet.draw();
			}	
			StdDraw.show();
			StdDraw.pause(10);

		}
		StdOut.printf("%d\n", Planets.length);
		StdOut.printf("%.2e\n", radius);
		for (int i = 0; i < Planets.length; i++) {
    		StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                  		  Planets[i].xxPos, Planets[i].yyPos, Planets[i].xxVel,
                  		  Planets[i].yyVel, Planets[i].mass, Planets[i].imgFileName);   
}


	}


}