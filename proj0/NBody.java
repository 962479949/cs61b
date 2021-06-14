/** NBody */
public class NBody {
	public static double readRadius(String path) {
    	In in = new In(path);
    	int number = in.readInt();
    	double radius = in.readDouble();
    	return radius;
	}
	/** return the name of planets */
	public static Planet[] readPlanets(String path) {
		In in = new In(path);
	    int number = in.readInt();
    	double radius = in.readDouble();
    	Planet[] allPlanet = new Planet[number];
    	int count = 0;
    	while(count < number) {
    		double xP = in.readDouble();
    		double yP = in.readDouble();
    		double xV = in.readDouble();
    		double yV = in.readDouble();
    		double mass = in.readDouble();
    		String fig = in.readString();
    		allPlanet[count] = new Planet(xP, yP, xV, yV, mass, fig);
    		count++;
    	}
    	return allPlanet;	
	}
	public static void main(String[] args) {
		double T = Double.parseDouble(args[0]);
		double dt = Double.parseDouble(args[1]);
		String filename = args[2];
		String imageToDraw = "images/starfield.jpg";
		double radius = readRadius(filename);
		Planet[] allPlanet = readPlanets(filename);

		StdDraw.setScale(-radius, radius);
		StdDraw.enableDoubleBuffering();
/*		StdDraw.picture(0,0,imageToDraw);

		for(Planet p : allPlanet) {
			p.draw();
		}*/

        int time = 0;

        while(time != T) {
        	double[] xForces = new double[allPlanet.length];
        	double[] yForces = new double[allPlanet.length];
        	int num = 0;
        	for(Planet p : allPlanet){
        		xForces[num] = p.calcNetForceExertedByX(allPlanet);
        		yForces[num] = p.calcNetForceExertedByY(allPlanet);
        		p.update(dt, xForces[num], yForces[num]);
        		num++;
        	}
        	StdDraw.picture(0, 0, imageToDraw);
			for(Planet p : allPlanet) {
			p.draw();
			}
			StdDraw.show();   
			StdDraw.pause(1);     	
        	time += dt;
        }
        StdOut.printf("%d\n", allPlanet.length);
		StdOut.printf("%.2e\n", radius);
		for (int i = 0; i < allPlanet.length; i++) {
  			StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
            allPlanet[i].xxPos, allPlanet[i].yyPos, allPlanet[i].xxVel,
            allPlanet[i].yyVel, allPlanet[i].mass, allPlanet[i].imgFileName);   
		}
	}
}