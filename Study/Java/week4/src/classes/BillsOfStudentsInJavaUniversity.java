package classes;

public class BillsOfStudentsInJavaUniversity {
    public static int numOfBills=0;//账单总数
    public static int schoolFee=0;//收费总数
    public String nameOfSchool="Java州立大学";//设定所有学生的学校名称
    private String name="Bob";//设定学生姓名
    private boolean ifInState=false;//判断学生是否在州内
    private double timeOfCreditCard;//信用卡使用时间
    private int credits=0;//学分
    public BillsOfStudentsInJavaUniversity(String name,boolean ifIn,double time,int credits){
        this.name=name;
        ifInState=ifIn;
        timeOfCreditCard=time;
        this.credits=credits;
        numOfBills++;//初始化一次账单+1
        schoolFee+=charge();
    }//初始化实例（姓名，是否在内，使用时间，账单总数
    public void addCredit(int addCre){
        credits+=addCre;
    }//增加学分
    public void useCard(double addTime){
        timeOfCreditCard+=addTime;
    }//增加信用卡使用时间
    //收费，对stu实例收credit个学分的费用
    public int charge(){
        if(ifInState){
            return 75*credits;
        }
        else{
            return 200*credits;
        }
    }
    //打印账单
    public void printBill(){
        System.out.println(nameOfSchool+"官方账单\n");
        System.out.println("姓名："+name);
        System.out.println("信用卡使用时间："+timeOfCreditCard);
        System.out.println("学分："+credits+"\n学费："+charge()+"\n");
    }
}
