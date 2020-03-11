package app;

import shape.*;

public class App {
    static void print_area(Shape s){
        System.out.println(s.name()+"的面积为"+s.area());
    }
    public static void main(String[] args) {
        Round cir=new Round(3);
        Rectangle rec=new Rectangle(5,6);
        Square squ=new Square(5);
        Triangle tri=new Triangle(3,4);
        System.out.println("一共初始化了"+Round.times+"个属于Shape抽象类的对象");
        print_area(cir);
        print_area(rec);
        print_area(squ);
        print_area(tri);
    }
}
