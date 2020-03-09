/*
 * 创建一个程序把输入字符串的大小写互换。
 * 
 */
package additional;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class LowerUpperSwap {

	public static void main(String[] args) throws Exception {

		// 声明并初始化一个字符输入流
		// 在字符输入流上添加过滤流，以便按行处理从键盘输入的数据
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		// 屏幕上输出用户提示
		System.out.print("Please input some words , end with ENTER\n");
		// 从用户输入读取一行，存入到变量 words;
		String words = reader.readLine();
		String result = "";
		// 将用户刚刚的输入，输出到屏幕;
		System.out.println("You just input ：\n " + words);
		System.out.println("processing ... \n");

		for (int i = 0; i < words.length(); i++) {
			char c = words.charAt(i);

			if ((c >= 'A') && (c <= 'Z')) {
				c = (char) (c + 'a' - 'A');
			} else if ((c >= 'a') && (c <= 'z')) {
				c = (char) (c - ('a' - 'A'));
			}
			result += c;
		}

		reader.close();
		System.out.println(result);
	}

}
