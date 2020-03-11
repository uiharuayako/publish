class ConcentricCircles {
    public static int x, y = 100;
    public int r;
}

public class ConcentricCirclesTester {
    public static void main(String args[]) {
        ConcentricCircles.x += 100;
        ConcentricCircles.y += 100;
        ConcentricCircles t1 = new ConcentricCircles();
        ConcentricCircles t2 = new ConcentricCircles();
        t1.x += 100;
        t1.r = 50;
        t2.y += 100;
        t2.r += 150;
        System.out.println("T1: x = " + t1.x + ", y = " + t1.y + ", r = " + t1.r);
        System.out.println("T2: x = " + t2.x + ", y = " + t2.y + ", r = " + t2.r);
    }
}
