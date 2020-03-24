package question5;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Phone implements clock,dial,camera,laptop{
    Scanner input=new Scanner(System.in);
    @Override
    public void dealNum(int phoneNumber) {
        System.out.println("假装正在拨号给"+phoneNumber);
    }

    @Override
    public void getTime() {
        SimpleDateFormat time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        System.out.println(time.format(new Date()));// new Date()为获取当前系统时间
    }

    @Override
    public void timer(){
        System.out.println("按下回车开始计时");
        String tmp=input.nextLine();
        long start=System.currentTimeMillis();
        System.out.println("计时已开始，按下回车结束计时");
        tmp=input.nextLine();
        long end=System.currentTimeMillis();
        System.out.println("总计"+(end-start)+"ms");
    }//秒表

    @Override
    public void photo() {
        System.out.println("拍照指南：拿起手机，你就可以拍照啦！");
    }

    @Override
    public void pickUp() {
        System.out.println("把电脑提起来，就是手提电脑啦！");
    }
    //main
    public static void main(String args[]){
        //演示clock功能，其他功能过于没有意义就不演示了
        Phone i=new Phone();
        i.getTime();
        i.timer();
    }
}
interface dial{
    void dealNum(int phoneNumber);
}//拨号
interface clock{
    void getTime();
    void timer();
}//时钟
interface camera{
    void photo();
}//相机
interface laptop{
    void pickUp();
}//手提电脑


