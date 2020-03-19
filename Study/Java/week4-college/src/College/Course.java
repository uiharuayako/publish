package College;

public class Course extends College {
    public Course(String collegeName, String collegeLevel,String teacher,String nameOfCourse) {
        super(collegeName, collegeLevel);
        this.nameOfCourse=nameOfCourse;
        this.teacher=teacher;
    }
    @Override
    public void introduce() {
     System.out.println("这是"+teacher+"教的"+nameOfCourse);
    }
    String teacher;
    String nameOfCourse;
}
