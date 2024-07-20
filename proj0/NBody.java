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
        StdDraw.show();
    }
}