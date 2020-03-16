package shape;

public class Square extends Shape{
    private double s_length=10;
    public Square(){}
    public Square(double len)
    {
        s_length=len;
        times++;
    }
    public void set_len(double len)
    {
        s_length=len;
    }
    @Override
    public double area() {
        return s_length*s_length;
    }

    @Override
    public String name() {
        return "正方形";
    }
}
