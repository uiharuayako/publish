package College;

public class 实验中心 extends Department{
    public 实验中心(String collegeName, String collegeLevel, String originationName, String leader,String research) {
        super(collegeName, collegeLevel, originationName, leader);
        this.research=research;
    }
    String research;
    @Override
    void work() {
        System.out.println("中心主要进行"+research+"方面的实验");
    }
}
