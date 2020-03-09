/*
 * 19.创建一个有两个方法的程序。标准的main()方法初始化两个变量，
 * 一个是String型，另一个是StringBuffer型，
 * 它们将作为第二个方法的输人参数，这个方法将把一个字符串
 * 连接在两个变量后面。
 * 
 */
package additional;

public class TwoKindString {

	public static void main(String args[]) {
		String finalString = "武汉!";// 字符串常量
		StringBuffer variableString = new StringBuffer("中国!");// 字符串变量
		System.out.println("原来的字符串常量:" + finalString);
		System.out.println("原来的字符串变量:" + variableString);
		connect(finalString, variableString);
	}

	public static void connect(String finalStr, StringBuffer variableStr) {
		String another = "加油！";
		System.out.println("字符串连接到字符串常量后的新字符串为： " + finalStr.concat(another));
		System.out.println("字符串连接到字符串变量后的新字符串为： " + variableStr.append(another));
		System.out.println("字符串常量:" + finalStr);
		System.out.println("字符串变量:" + variableStr);
		System.out.println();
	}
}