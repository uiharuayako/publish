class Abc {
    public static String doubleIt(String data) {    //返回一个String类型
        System.out.println(data + data);
        return data + data;
    }

    public static int doubleIt(int data) {            //返回一个int类型
        System.out.println(2 * data);
        return data + data;
    }


    public static void main(String[] args) {

        System.out.println("Hello World!" + doubleIt("aa") + doubleIt(66));
    }
}
