package College;

public class 研究所 extends Department{
    public 研究所(String collegeName, String collegeLevel, String originationName, String leader,String research) {
        super(collegeName, collegeLevel, originationName, leader);
        this.research=research;
    }
    String research;
    @Override
    void work() {
        System.out.println("研究所主要进行"+research+"方面的研究");
    }
}
