package College;

public class 系 extends Department {
    public 系(String collegeName, String collegeLevel, String originationName, String leader,String research) {
        super(collegeName, collegeLevel, originationName, leader);
        this.research=research;
    }
    String research;
    @Override
    void work() {
        System.out.println("我系主要进行"+research+"方向的教学");
    }
}
