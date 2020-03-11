
class AnIntegerNamedX {
    static int x;

    static public int x() {
        return x;
    }

    static public void setX(int newX) {
        x = newX;
    }
}

public class Static_test {
    public static void main(String args[]) {
        AnIntegerNamedX myX = new AnIntegerNamedX();
        AnIntegerNamedX anotherX = new AnIntegerNamedX();
        myX.setX(1);
        anotherX.x = 2;
        System.out.println("myX.x = " + myX.x());
        System.out.println("anotherX.x = " + anotherX.x());
    }
}