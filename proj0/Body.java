/**
Creat a body
*/
public class Body{
	public double xxPos;
	public double yyPos;
	public double xxVel;
	public double yyVel;
	public double mass;
	public String imgFileName;
	public static double G =6.67e-11;

	public Body(double xP, double yP, double xV, double yV, double m, String img){
		xxPos=xP;
		yyPos=yP;
		xxVel=xV;
		yyVel=yV;
		mass=m;
		imgFileName=img;
	}
	public Body(Body b){
		xxPos = b.xxPos;
        yyPos = b.yyPos;
        xxVel = b.xxVel;
        yyVel = b.yyVel;
        mass = b.mass;
        imgFileName = b.imgFileName;
	}
	/**Calculate the distance between two bodies*/
	public double calcDistance(Body b){
		double dx = this.xxPos-b.xxPos;
		double dy = this.yyPos-b.yyPos;
		double r = Math.hypot(dx,dy);
		return r;
	}

	public double calcForceExertedBy(Body b){
		double F = (G*this.mass*b.mass)/Math.pow(this.calcDistance(b),2);
		return F;
	}
	public double calcForceExertedByX(Body b){
		double dx = b.xxPos-this.xxPos;
		return (this.calcForceExertedBy(b)*dx)/this.calcDistance(b);
	}
	public double calcForceExertedByY(Body b){
		double dy = b.yyPos-this.yyPos;
		return (this.calcForceExertedBy(b)*dy)/this.calcDistance(b);
	}

	 public double calcNetForceExertedByX(Body[] arrayB) {
        double FxNet = 0;
        for(Body b : arrayB){
        	if(!this.equals(b)){
        		FxNet += this.calcForceExertedByX(b);
        	}
        }
        return FxNet;
    }
    public double calcNetForceExertedByY(Body[] arrayB) {
        double FyNet = 0;
        for(Body b : arrayB){
        	if(!this.equals(b)){
        		FyNet += this.calcForceExertedByY(b);
        	}
        }
        return FyNet;
    }

    public void update(double dt, double Fx, double Fy){
    	double ax = Fx/this.mass;
    	double ay = Fy/this.mass;
    	this.xxVel += (dt*ax);
    	this.yyVel += (dt*ay);
    	this.xxPos += (dt*this.xxVel);
    	this.yyPos += (dt*this.yyVel);
    }

    public void draw(){
    	StdDraw.picture(this.xxPos, this.yyPos, "images/"+this.imgFileName);
    }
}