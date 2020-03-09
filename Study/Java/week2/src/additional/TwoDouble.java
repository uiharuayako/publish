/*
 *  16.用一段代码检测两个double型的x和y是否相等。
 *  代码应能分辨这两个数是否是无
 *  穷大或NaN。
 *  如果它们相等，代码都能正确显示这两个数。
 * 
 */
package additional;

public class TwoDouble {
	public static void main(String[] args) {
		compareTwoDouble(3.14159, Math.PI);
	}

	static void compareTwoDouble(double d1, double d2) {
		String result;
		if (isGoodDouble(d1) && isGoodDouble(d2)) {
			if (d1 == d2)
				result = " 相等";
			else
				result = " 不等";
		} else
			result = " 中有数值无效";

		System.out.println(d1 + "与" + d2 + result);

	}

	static boolean isGoodDouble(double d) {
		return (d != Double.NaN) && (d != Double.POSITIVE_INFINITY) && (d != Double.NEGATIVE_INFINITY);
	}
}
