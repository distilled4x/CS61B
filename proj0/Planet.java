import java.lang.Math;

public class Planet {
	public double xxPos; 
	public double yyPos;
	public double xxVel;
	public double yyVel;
	public double mass;
	public String imgFileName; 
	public final double g = 6.67e-11;

	public Planet(double xP, double yP, double xV, double yV, double m, String img) {
		xxPos = xP;
		yyPos = yP;
		xxVel = xV;
		yyVel = yV;
		mass = m;
		imgFileName = img;
	}

	public Planet(Planet p) {
		this(p.xxPos, p.yyPos, p.xxVel, p.yyVel, p.mass, p.imgFileName);
	}

	public double calcDistance(Planet another) {
		double dx = this.xxPos - another.xxPos;
		double dy = this.yyPos - another.yyPos; 

		double sqDist = (dx * dx) + (dy * dy);

		return Math.sqrt(sqDist);
	}

	public double calcForceExertedBy(Planet another) {
		double sqDist = calcDistance(another) * calcDistance(another);

		return (g * this.mass * another.mass) / sqDist;

	}

	/** removed abschange for sim*/ 
	public double calcForceExertedByX(Planet another) {
		//double change = this.xxPos - another.xxPos; 
		double change = another.xxPos - this.xxPos;
		//double absChange = getAbsChange(change);
		return (calcForceExertedBy(another) * change) / calcDistance(another);
	}

	/** removed abschange for sim*/ 
	public double calcForceExertedByY(Planet another) {
		//double change = this.yyPos - another.yyPos;
		double change = another.yyPos - this.yyPos;
		//double absChange = getAbsChange(change);
		return (calcForceExertedBy(another) * change) / calcDistance(another);

	}

	public double getAbsChange(double change) {
		if (change < 0) {
			return change * -1;
		} else {
			return change; 
		}
	}

	public double calcNetForceExertedByX(Planet[] planets) {
		double netForceX = 0; 

		for (Planet planet : planets) {
			if (!this.equals(planet)) {
				netForceX += calcForceExertedByX(planet);
			}
		}
		return netForceX; 
	}

	public double calcNetForceExertedByY(Planet[] planets) {
		double netForceY = 0; 

		for (Planet planet : planets) {
			if (!this.equals(planet)) {
				netForceY += calcForceExertedByY(planet);
			}
		}
		return netForceY; 
	}

	public void update(double dt, double fX, double fY) {
		double accelX = fX/mass;
		double accelY = fY/mass; 

		xxVel += dt * accelX;
		yyVel += dt * accelY;

		xxPos += dt * xxVel;
		yyPos += dt * yyVel;
	}

	public void draw() {
		StdDraw.picture(this.xxPos, this.yyPos, "images/" + imgFileName);
	}

}