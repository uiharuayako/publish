/*
 *     5．编写一个程序，把变量n的初始值设置为1678，
 *     然后利用除法运算和取余运算把变量
 *     的每位数字都提出来并打印，
 *     输出结果为：n的每位数字是1，6，7，8。
 * 	   n值为39，则输出结果为：n ＝ 3，9
 */

package otherWay;

public class DigitInNumber {

	public static void main(String[] args) {

		int n = 1678;
		cutNumber(n);

		cutNumber(39);
		cutNumber(20200302);
	}

	static void cutNumber(int n) {
		String stringN = n + "";
		for (int i = 0; i < stringN.length(); i++) {
			System.out.print(stringN.charAt(i));
			System.out.print(",");
		}
		System.out.println();
	}

}
