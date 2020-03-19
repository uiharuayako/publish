package App;
import classes.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import static classes.BillsOfStudentsInJavaUniversity.numOfBills;
import static classes.BillsOfStudentsInJavaUniversity.schoolFee;

public class Main {
    public static void main(String args[]) throws Exception {
        System.out.println("1:第三题示例\n其他数字：第四题示例");
        InputStreamReader reader=new InputStreamReader(System.in);
        BufferedReader input=new BufferedReader(reader);
        int cho=1;
        String s = input.readLine();
        try {
            cho = Integer.parseInt(s);
        } catch (NumberFormatException n) {
            System.out.println("输入的不是数字！默认显示第三题示例，按回车继续");
            int i=input.read();//实现暂停
        }
        //第三题的示例部分，请注意，这个链表第一个结点编号为1
        if(cho==1) {
            MyList a = new MyList();
            int b = 2333;
            String c = "abc";
            boolean d = true;
            short e = 12;
            a.add(b);
            a.add(c);
            a.add(d);
            a.add(e);
            a.printList();//生成的演示
            a.add("第三个", 3);
            a.printList();//在中间插入的演示
            a.remove(d);
            a.printList();//按照结点移除的演示，移除了true
            a.removeIndex(3);
            a.printList();//按照索引移除的演示，移除了“第三个”
            System.out.println(a.get(1).getT());//访问的演示
        }
        //第四题的示例部分
        else {
            BillsOfStudentsInJavaUniversity bob=new BillsOfStudentsInJavaUniversity("鲍勃",true,2.5,100);
            BillsOfStudentsInJavaUniversity mike=new BillsOfStudentsInJavaUniversity("麦克",false,100,240);
            BillsOfStudentsInJavaUniversity tom=new BillsOfStudentsInJavaUniversity("汤姆",true,5,30);
            bob.printBill();
            mike.printBill();
            tom.printBill();
            System.out.println("一共有"+numOfBills+"名学生，一共交了"+schoolFee+"$学分费");
        }

    }
}
