package App;

import College.*;

public class Main {
    public static void main(String args[]){
        //Human的四个子类
        Student 小明=new Student("武汉大学","双一流院校","小明", Sex.Man,19,"基督教","父类学院","子类系",123456);
        小明.introduce();
        Headmaster 小军=new Headmaster("Java州立大学","世界级高校","小军",Sex.Trans,45,"佛教");
        小军.introduce();
        Dean 小红=new Dean("C++州立大学","专科学校","小红",Sex.MaletoFemale,28,"伊斯兰教","基类学院");
        小红.introduce();
        Teacher 人民教师=new Teacher("东部大学","教育部直属院校","人民教师",Sex.CisMan,44,"无信仰","国文","语文系");
        人民教师.introduce();
        //Origination的三个子类
        实验中心 重力=new 实验中心("西部大学","很厉害的","重力测试中心","凯特","重力对人的影响");
        重力.introduce();
        研究所 智慧=new 研究所("这个大学","世界第一","智慧研究所","凯文","如何给人增加智慧");
        智慧.introduce();
        系 佛系=new 系("湖北佛学院","很佛的","佛系","阿弥陀佛","如何变佛");
        佛系.introduce();
        //Course类
        Course math=new Course("南开大学","双一流院校","这个老师","那个数学课");
        math.introduce();
    }
}
