class OverLoad {
    public static String doubleIt(String data) {    //����һ��String����
        System.out.println(data + data);
        return data + data;
    }

    public static int doubleIt(int data) {            //����һ��int����
        System.out.println(2 * data);
        return data + data;
    }


    public static void main(String[] args) {

        System.out.println("Hello World!" + doubleIt("aa") + doubleIt(66));

    }
}