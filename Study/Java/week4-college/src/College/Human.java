package College;

public abstract class Human extends College{
    String name;
    Sex sex;
    int age;
    String position;
    String religion;
    int termOfOffice;
    abstract void getMoney();//子类均覆盖了这一方法
    //change重载
    void change(String newPosition){
        position=newPosition;
    }
    void change(Sex newSex){
        sex=newSex;
    }
    void growOlder(int years){
        age+=years;
    }
    void growOlder(){
        age++;
    }
    public Human(String collegeName,String collegeLevel,String name,Sex sex,int age,String religion,int termOfOffice){
        super(collegeName,collegeLevel);
        this.name=name;
        this.sex=sex;
        this.age=age;
        this.religion=religion;
        this.termOfOffice=termOfOffice;
    }

    @Override
    public void introduce() {
        if(position.equals("学生"))
        {
            System.out.println("我叫"+name+"，是一名学期"+termOfOffice+"年的"+position+",我来自"+collegeLevel+"高校"+collegeName+"，我今年"+age+"岁了"+"\n我的性别为："+sex+"\n我的信仰是"+religion);

        }
        else {
            System.out.println("我叫"+name+"，是一名任期为" + termOfOffice + "年的" + position + ",我来自" + collegeLevel + "高校" + collegeName + "，我今年" + age + "岁了" + "\n我的性别为：" + sex + "\n我的信仰是" + religion);
        }
        getMoney();
        job();
        System.out.println("\n");
    }

    public abstract void job();//子类均覆盖了这一方法
}
