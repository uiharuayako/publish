/*
 * 3．使用“==”对相同内容的字符串进行比较，
 *    看会产生什么样的结果。
 * 
 */

package additional;

public class CompareTwoString {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String strA = "Hello！ World";
		String strB = "Hello！ World";
//		String strB = strA;
		String strC = "Hi！There";
		String strD = new String("Hello！ World");
		String strE = new String("Hello！ World");

		System.out.println("strA: " + strA);
		System.out.println("strB: " + strB);
		System.out.println("strA = strB: " + (strA == strB));
		System.out.println();
		System.out.println("strB: " + strB);
		System.out.println("strC: " + strC);
		System.out.println("strA = strB: " + (strB == strC));
		System.out.println();
		System.out.println("strD: " + strD);
		System.out.println("strE: " + strE);
		System.out.println("strD = strE: " + (strD == strE));
		System.out.println("StrD = StrE: " + strD.equals(strE));
	}

}
