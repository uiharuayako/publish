package shape;

public class Rectangle extends Shape{
    private double length=5;
    private double width=4;
    public Rectangle(){}
    public Rectangle(double len,double wid) {
        length=len;
        width=wid;
        times++;
    }
    public void set_rec(double len,double wid) {
        length=len;
        width=wid;
    }
    public double diagonal(){
        return Math.sqrt(length*length+width*width);
    }
    @Override
    public double area() {
        return length*width;
    }

    @Override
    public String name() {
        return "矩形";
    }
}
