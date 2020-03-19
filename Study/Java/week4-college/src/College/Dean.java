package College;

public class Dean extends Human {
    @Override
    void getMoney() {
        System.out.println("拿院长的工资");
    }
    String department;
    public Dean(String collegeName, String collegeLevel, String name, Sex sex, int age, String religion,String department) {
        super(collegeName, collegeLevel, name, sex, age, religion,10);
        this.department=department;
        position="院长";
    }

    @Override
    public void job() {
        System.out.println("我在管理"+department);
    }
}
