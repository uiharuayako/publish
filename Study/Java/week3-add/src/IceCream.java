//: c05:IceCream.java
// Demonstrates "private" keyword.

class Sundae {
    private int x;

    private Sundae() {
    }

    ;

    static Sundae makeASundae() {
        return new Sundae();
    }

    public int getX() {
        return x;
    }
}

public class IceCream {
    public static void main(String[] args) {
        //! Sundae x = new Sundae();
        Sundae x = Sundae.makeASundae();
        System.out.print(x.x);

    }
} 