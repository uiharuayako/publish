package question4;

import java.io.IOException;
import java.util.Scanner;

public class ID {
    public static void main(String args[]) throws IOException/*这个异常..我就不处理了*/ {
        try {
            String ID;
            Scanner input = new Scanner(System.in);
            ID = input.nextLine();
            if(ID.length()!=13){
                LengthException L=new LengthException(ID.length());
                throw L;
            }//长度
            for(int i=0;i<=12;i++){
                if(ID.charAt(i)>'9'||ID.charAt(i)<'0'){
                    NumberException N=new NumberException(i+1);
                    throw N;
                }
            }//数字
            int year=Integer.parseInt(ID.substring(0,4));
            if(year!=2019){
                YearException Y=new YearException(year);
                throw Y;
            }
            //专业
            int code=Integer.parseInt(ID.substring(4,8));
            if(code!=3021) {
                RSException C = new RSException(code);
                throw C;
            }
            System.out.println("这个学号属于遥感专业的2019级，但由于后五位的不确定性，不能确定是否有学生拥有此学号");

        }
        catch (LengthException L){
            System.out.println("您输入的字符串为"+L.num+"位，而标准的学号为13位，请重新输入！");
        }
        catch (NumberException N){
            System.out.println("您输入的字符串的第"+N.num+"位不为数字，请重新输入！");
        }
        catch (YearException Y){
            System.out.println("您输入的年份为"+Y.num+"位不为2019，请重新输入！");
        }
        catch (RSException C){
            System.out.println("您输入的院系代码为"+C.num+"，而遥感为3021，请重新输入！");
        }
    }

}
abstract class MyException extends Exception{
    int num;
    MyException(int num){
        this.num=num;
    }
}
class LengthException extends MyException{
    LengthException(int num) {
        super(num);
    }
}//定义长度异常
class NumberException extends MyException{
    NumberException(int num) {
        super(num);
    }
}//定义数字异常
class YearException extends MyException{
    YearException(int num) {
        super(num);
    }
}//定义年份异常
class RSException extends MyException{
    RSException(int num) {
        super(num);
    }
}//定义遥感异常

