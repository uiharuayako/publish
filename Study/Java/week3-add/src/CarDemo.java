public class CarDemo {
    public static void main(String[] args) {
        Car demoCar = new Car("��ɫ");
        demoCar.set_number(6868);
        demoCar.show_number();
        demoCar.show_brand();

        Car demoCar1 = new Car("��ɫ");
        demoCar1.set_number(6869);
        demoCar1.show_number();
        demoCar1.show_brand();


    }
}


class Car {
    static String brand = "����";
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
        System.out.println("�ҵĳ�����: " + brand);
        System.out.println("�ҵ���ɫ��: " + color);
    }
}
