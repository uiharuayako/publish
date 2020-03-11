class AnIntegerNamedX {
    int x;       //staticstaticstaticstatic使用了关键字

    public int x() {
        return x;
    }

    public void setX(int newX) {
        x = newX;
    }
}

public class Compare_test {
    public static void main(String args[]) {
        AnIntegerNamedX myX = new AnIntegerNamedX();
        AnIntegerNamedX anotherX = new AnIntegerNamedX();
        myX.setX(1);
        anotherX.setX(2);
        System.out.println("myX.x = " + myX.x());
        System.out.println("anotherX.x = " + anotherX.x());
    }
}
