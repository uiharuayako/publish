//编写Java程序，接受用户输入的1~12之间的整数，若不符合条件则重输入，利用switch语句输出对应月份的天数。
package com;

import java.io.IOException;
import java.util.Scanner;

public class homework6 {
    public static void main(final String[] args) throws IOException {
        Scanner input_num = new Scanner(System.in);
        System.out.println("请输入1-12间的整数：");
        int num;
        num = input_num.nextInt();
        while(num>12||num<1)
        {
            System.out.println("请输入正确的数字");
            num = Math.abs(input_num.nextInt());
        }
        switch(num)
        {
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                System.out.println(num+"月有31天");
                break;
            case 4:
            case 6:
            case 9:
            case 11:
                System.out.println(num+"月有30天");
                break;
            case 2:
                System.out.println(num+"月有28或29天");
                break;
        }
    }
}
