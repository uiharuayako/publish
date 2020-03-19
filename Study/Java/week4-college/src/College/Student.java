package College;

public class Student extends Human {
    @Override
    void getMoney() {
        System.out.println("我需要交学费");
    }
    int id;
    String department;
    String 系;//我真的不想用中文，但是不知道咋翻译
    public Student(String collegeName, String collegeLevel, String name, Sex sex, int age, String religion,String department,String 系,int id) {
        super(collegeName, collegeLevel, name, sex, age, religion,4);
        this.department=department;
        this.id=id;
        this.系=系;
        position="学生";
    }
    public void study(Course c){
        System.out.println("我学了"+c.teacher+"的"+c.nameOfCourse+",感觉收获很多");
    }
    @Override
    public void job() {
        System.out.println("我在"+department+"的"+系+"上课。学号为"+id);
    }
}
