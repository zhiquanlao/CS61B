public class NBody{
    public static double readRadius(String fileName){
        In in =new In(fileName);
        int no_data=in.readInt();
        double radius=in.readDouble();
        return radius;
    }
    public static Planet [] readPlanets(String fileName){
        In in =new In(fileName);
        int no_data=in.readInt();
        double radius=in.readDouble();
        Planet [] PlanetArr= new Planet [no_data];
        for(int i=0;i<no_data;++i){
            double xxPos=in.readDouble();
            double yyPos=in.readDouble();
            double xxVel=in.readDouble();
            double yyVel=in.readDouble();
            double mass=in.readDouble();
            String imgName=in.readString();
            PlanetArr[i]=new Planet(xxPos,yyPos,xxVel,yyVel,mass,imgName);
        }
        return PlanetArr;
    }
    public static void main(String[] args) {
        //read variables needed
        double T=Double.parseDouble(args[0]);
        double dt=Double.parseDouble(args[1]);
        String filename=args[2];
        double radius=readRadius(filename);
        Planet [] planets=readPlanets(filename);

        //Draw the background
        StdDraw.setScale(-radius,radius);
        StdDraw.picture(0,0,"images/starfield.jpg");
        for(int i=0;i<planets.length;++i){
            planets[i].draw();
        }

        //creating an animation
        StdDraw.enableDoubleBuffering();
        for(double cur_time=0;cur_time<=T;cur_time+=dt){
            double [] xForce=new double[planets.length];
            double [] yForce=new double[planets.length];
            StdDraw.picture(0,0,"images/starfield.jpg");
            for(int i=0;i<planets.length;++i){
                xForce[i]=planets[i].calcNetForceExertedByX(planets);
                yForce[i]=planets[i].calcNetForceExertedByY(planets);
            }
            for (int i = 0; i < planets.length; i++) {
                planets[i].update(dt, xForce[i], yForce[i]);
                planets[i].draw();
            }
            StdDraw.show();;
            StdDraw.pause(10);
            //System.out.printf("cur_time=%f, planets[0].xxPos=%f, yyPos=%f,",cur_time,planets[0].xxPos,planets[0].yyPos);
        }
        StdOut.printf("%d\n", planets.length);
        StdOut.printf("%.2e\n", radius);
        for (int i = 0; i < planets.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                        planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
                        planets[i].yyVel, planets[i].mass, planets[i].imgFileName);   
        }
    }
}