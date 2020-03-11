public class CarDemo {
    public static void main(String[] args) {
        Car demoCar = new Car("红色");
        demoCar.set_number(6868);
        demoCar.show_number();
        demoCar.show_brand();

        Car demoCar1 = new Car("黑色");
        demoCar1.set_number(6869);
        demoCar1.show_number();
        demoCar1.show_brand();


    }
}


class Car {
    static String brand = "宝马";
    int car_number;
    String color;

    Car(String color) {
        this.color = color;
    }

    void set_number(int car_num) {
        car_number = car_num;
    }

    void show_number() {
        System.out.println("My car No. is : " + car_number);
    }

    void show_brand() {
        System.out.println("我的车牌是: " + brand);
        System.out.println("我的颜色是: " + color);
    }
}
