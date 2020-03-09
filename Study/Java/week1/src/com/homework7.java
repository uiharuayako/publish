//计算一个整数之内的素数并输出
package com;

import java.io.IOException;
import java.util.Scanner;

public class homework7 {
    public static void main(final String[] args) throws IOException {
        Scanner input_num = new Scanner(System.in);
        System.out.println("你喜欢的正整数：(如果输入负数会自动转换为正数)");
        int num;
        num = Math.abs(input_num.nextInt());
        for (int i = num; i >= 2; i--) {
            if (is_prime(i)) System.out.print(i + " ");
        }
    }

    private static boolean is_prime(int num) {
        if (num == 2) return true;
        for (int i = 2; i <= Math.sqrt(num); i++) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }
}
