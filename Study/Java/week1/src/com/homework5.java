/*编写一个程序，利用除法运算和取余运算把整型变量的每位数字都提出来并打印。
例如n值为1678，则输出结果为：n ＝ 1，6，7，8； n值为39，则输出结果为：n ＝ 3，9。 */
package com;

import java.util.*;

public class homework5 {
    public static void main(final String[] args) {
        Scanner input_num = new Scanner(System.in);
        System.out.println("请输入想要分解的数字n：");
        int num = Math.abs(input_num.nextInt());//取正数，毕竟说的是每位数字
        int num_digit = nums(num)-1;
        //下面这方法可能简单很多，但是感觉失去了本意
        //String temp = num + "";
        //int num_digit = temp.length();
        System.out.print("n=");
        int digit;
        while (num_digit!=0) {
            digit=(int) Math.floor(num / Math.pow(10, num_digit));
            num-=digit*Math.pow(10,num_digit);
            System.out.print(digit+",");
            num_digit--;
        }
        System.out.print(num);//最后一个数字
    }

    private static int nums(int num)//有一个比这个简单100倍的方法...
    {
        int num_digit = 1;
        for (int i = num; i != 0; i = num / 10) {
            num /= 10;
            num_digit++;
        }
        return num_digit;
    }

}
