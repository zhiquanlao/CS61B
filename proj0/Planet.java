

public class Planet{
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;
    private static final double Gconst=6.67e-11;


    public Planet(Planet p){
        xxPos=p.xxPos;
        yyPos=p.yyPos;
        xxVel=p.xxVel;
        yyVel=p.yyVel;
        mass=p.mass;
        imgFileName=p.imgFileName;
    }
    public Planet(double xP, double yP, 
    double xV, double yV, double m, String img){
        xxPos=xP;
        yyPos=yP;
        xxVel=xV;
        yyVel=yV;
        mass=m;
        imgFileName=img;
    }
    public double calcDistance(Planet p){
        return Math.sqrt((xxPos-p.xxPos)*(xxPos-p.xxPos)
        +(yyPos-p.yyPos)*(yyPos-p.yyPos));
    }
    public double calcForceExertedBy(Planet p){
        double r=calcDistance(p);
        return Gconst*mass*p.mass/(r*r);
    }
    public double calcForceExertedByX(Planet p){
        return (p.xxPos-xxPos)/calcDistance(p)*calcForceExertedBy(p);
    }
    public double calcForceExertedByY(Planet p){
        return (p.yyPos-yyPos)/calcDistance(p)*calcForceExertedBy(p);
    }
    public double calcNetForceExertedByX(Planet [] allPlanets){
        double tot=0;
        for(int i=0;i<allPlanets.length;++i){
            if(!this.equals(allPlanets[i]))tot+=calcForceExertedByX(allPlanets[i]);
        }
        return tot;
    }
    public double calcNetForceExertedByY(Planet [] allPlanets){
        double tot=0;
        for(int i=0;i<allPlanets.length;++i){
            if(!this.equals(allPlanets[i]))tot+=calcForceExertedByY(allPlanets[i]);
        }
        return tot;
    }
    public void update(double time, double xxForce, double yyForce){
        double xxAce=xxForce/mass;
        double yyAce=yyForce/mass;
        xxVel+=time*xxAce;
        yyVel+=time*yyAce;
        xxPos+=time*xxVel;
        yyPos+=time*yyVel;
    
    }
    public void draw(){
        StdDraw.picture(xxPos, yyPos, "images/"+imgFileName);
    }
}