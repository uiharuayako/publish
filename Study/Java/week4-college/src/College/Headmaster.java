package College;

public class Headmaster extends Human {
    @Override
    void getMoney() {
        System.out.println("拿校长的工资");
    }

    @Override
    public void job() {
        System.out.println("我在管理"+collegeName);
    }

    public Headmaster(String collegeName, String collegeLevel, String name, Sex sex, int age, String religion) {
        super(collegeName, collegeLevel, name, sex, age, religion,5);
        position="校长";
    }

    void changeCollegeName(String newName){
        collegeName=newName;
    }
}
