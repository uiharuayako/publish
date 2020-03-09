/*
 *   计算指定的一天是今年的第几天
 * 
 */

package otherWay;

import java.util.Scanner;

public class HowManyDays {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		int daysPassed = 0;
		int month;
		int day;

		// 输入月、日
		System.out.println("请依次输入月份及日期：");
		month = scanner.nextInt();
		day = scanner.nextInt();

		System.out.println("第一解决方案：");

		// 这里不严谨，只是表示要处理
		if (month > 12 || month < 1 || day > 31 || day < 1)
			System.out.println("日期输入错误");
		else
			switch (month) {
			case 12:
				daysPassed += 30;
			case 11:
				daysPassed += 31;
			case 10:
				daysPassed += 30;
			case 9:
				daysPassed += 31;
			case 8:
				daysPassed += 31;
			case 7:
				daysPassed += 30;
			case 6:
				daysPassed += 31;
			case 5:
				daysPassed += 30;
			case 4:
				daysPassed += 31;
			case 3:
				daysPassed += 29;
			case 2:
				daysPassed += 31;
			case 1:
				daysPassed += day;
			}
		System.out.println(month + "月" + day + "日" + "是今年的第" + daysPassed + "天");

		// 面向对象方案
//		System.out.println("第二解决方案：");
//		Calendar c = Calendar.getInstance();
//		c.set(2020, month - 1, day, 0, 0, 0); // 年月日时分秒（月份0代表1月）
//		System.out.println(month + "月" + day + "日" + "是今年的第" + c.get(Calendar.DAY_OF_YEAR) + "天");

	}

}
