package College;

public class Teacher extends Human {
    @Override
    void getMoney() {
        System.out.println("拿教师的工资");
    }
    String department;
    String course;
    public Teacher(String collegeName, String collegeLevel, String name, Sex sex, int age, String religion,String course,String department) {
        super(collegeName, collegeLevel, name, sex, age, religion,20);
        this.department=department;
        position="教师";
        this.course=course;
    }

    @Override
    public void job() {
        System.out.println("我在"+department+"教"+course);
    }
}
