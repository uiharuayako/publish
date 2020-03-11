package shape;

public class Round extends Shape{
    private double r=5;
    public Round(double r1){
        r=r1;
        times++;
    }
    public Round(){};
    @Override
    public double area() {
        return Math.PI*r*r;
    }

    @Override
    public String name() {
        return "\u5706\u5f62";
    }
}
