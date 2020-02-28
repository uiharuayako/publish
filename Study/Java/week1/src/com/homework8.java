//输入一组整数存放在数组中，比较并输出其中的最大值和最小值；再将数组元素从小到大排序并输出。
package com;
import java.util.Scanner;
import java.util.Arrays;

public class homework8 {
    public static void main(String[] args) {
        Scanner input_num=new Scanner(System.in);
        System.out.print("请输入数组元素个数：");
        int size=input_num.nextInt();
        int[] a=new int[size];
        System.out.println("请输入各个元素，回车确认");
        for(int i=0;i<size;i++) a[i]=input_num.nextInt();
        Arrays.sort(a);
        System.out.println("最大值："+a[size-1]+"  最小值："+a[0]);
        for(int i=0;i<size;i++) System.out.println(a[i]);
    }
}
