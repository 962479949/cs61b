/* Planet */

public  class Planet{
	public double xxPos;
	public double yyPos;
	public double xxVel;
	public double yyVel;
	public double mass;
	private final static double g = 6.67e-11;
	public String imgFileName;
	public Planet(double xP, double yP, double xV,
	              double yV, double m, String img){
	              xxPos = xP;
	              yyPos = yP;
	              xxVel = xV;
	              yyVel = yV;
	              mass = m;
	              imgFileName = img;
	              }
	public Planet(Planet p){
		this.xxPos = p.xxPos;
		this.yyPos = p.yyPos;
		this.xxVel = p.yyVel;
		this.yyVel = p.yyVel;
		this.mass = p.mass;
		this.imgFileName = p.imgFileName;
	}

	/** Calculate the disstance between p and given planet */
	public double calcDistance(Planet p){
		double distance;
		distance = Math.sqrt((this.xxPos - p.xxPos) * (this.xxPos - p.xxPos)
			+ (this.yyPos - p.yyPos) * (this.yyPos - p.yyPos));
		return distance;
	}

	/** Get the total force. */
	public double calcForceExertedBy(Planet p) {
		double force;
		force = g * this.mass * p.mass
		    / (Math.pow(calcDistance(p),2));
		return force;
	}

	/** Get the force in x direction */
	public double calcForceExertedByX(Planet p) {
		double force_dx;
		force_dx = calcForceExertedBy(p) * (p.xxPos - this.xxPos)
		    / calcDistance(p);
		return force_dx;
	}
	/** Get the force in y direction */
	public double calcForceExertedByY(Planet p) {
		double force_dy;
		force_dy = calcForceExertedBy(p) * (p.yyPos - this.yyPos)
		    / calcDistance(p);
		return force_dy;
	}

	public double calcNetForceExertedByX(Planet[] allPlanets) {
		double sum = 0.0;
		if (allPlanets == null || allPlanets.length <= 1) {
            return sum;
		}
		for (Planet p : allPlanets) {
			if (p.equals(this)){
					continue;
					}
		
		    sum += this.calcForceExertedByX(p);
		}
		return sum; 
	}
    public double calcNetForceExertedByY(Planet[] allPlanets) {
    	double sum = 0.0;
    	if (allPlanets == null || allPlanets.length <= 1) {
    		return sum;
    	}
    	for (Planet p : allPlanets) {
    		if (p.equals(this)) {
    			continue;
    		}
    		sum += this.calcForceExertedByY(p);
    	}
    	return sum;
    }
    /**   Calculate the final velocity and position
     * get the acceleration first
     * then get the speed and position */
    public void update(double dt, double fX, double fY) {
    	double acceleration_x = fX / this.mass;
    	double acceleration_y = fY / this.mass;
        // double velocity_x = this.xxVel + dt * acceleration_x;
        // double velocity_y = this.yyVel + dt * acceletayino_y;
        // double pos_x = this.xxPos + dt * velocity_x;
        // double pos_y = this.yyPos + dt * velocity_y;
        this.xxVel = this.xxVel + dt * acceleration_x;
        this.yyVel = this.yyVel + dt * acceleration_y;
        this.xxPos = this.xxPos + dt * this.xxVel;
        this.yyPos = this.yyPos + dt * this.yyVel;
    }
    public void draw() {
    String imgToDraw = "images/" + this.imgFileName;

    StdDraw.picture(this.xxPos, this.yyPos, imgToDraw);
    }
}