/*
 * 6. 编写Java程序，接受用户输入的1~12之间的整数，输出对应月份的天数。
 * 
 */
package otherWay;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class NonSwitchDayNumbers {
	public static void main(String[] args) throws IOException {
		InputStreamReader reader = new InputStreamReader(System.in);
		BufferedReader bufferedreader = new BufferedReader(reader);
		String[] dayNumbers = { "31", "28(闰年29)", "31", "30", "31", "30", "31", "31", "30", "31", "30", "31" };

		String inputLine;
		int month;

		do {
			System.out.println("请输入要查询天数的月份（1~12），输入0退出程序。\n");

			inputLine = bufferedreader.readLine();
			month = Integer.parseInt(inputLine);
			if (month == 0) {
				System.out.println("已退出程序！\n");
				break;
			} else if (month >= 1 && month <= 12) {
				System.out.print(month);
				System.out.print(" 月份的天数是 : ");
				System.out.println(dayNumbers[month - 1]);
			} else {
				System.out.println("输入无效！\n");
			}
		} while (true);

	}
}
