package shape;

public class Triangle extends Shape{
    private double height=2.4;
    private double length=5;
    public Triangle(double len,double hei){
        height=hei;
        length=len;
        times++;
    }
    public Triangle(){};
    @Override
    public double area()
    {
        return 0.5*height*length;
    }

    @Override
    public String name() {
        return "Èý½ÇÐÎ";
    }

}
