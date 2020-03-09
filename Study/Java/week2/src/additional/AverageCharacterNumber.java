package additional;

public class AverageCharacterNumber {

	public static void main(String args[]) {

		String[] StringSentence = { "Java ", "is ", "an ", "object ", "oriented ", "programming ", "language" };

		char[][] charSentence = { { 'j', 'a', 'v', 'a' }, { 'i', 's' }, { 'a', 'n' }, { 'o', 'b', 'j', 'e', 'c', 't' },
				{ 'o', 'r', 'i', 'e', 'n', 't', 'e', 'd' }, { 'p', 'r', 'o', 'g', 'r', 'a', 'm', 'm', 'i', 'n', 'g' },
				{ 'l', 'a', 'n', 'g', 'u', 'a', 'g', 'e' } };
		stringArray(StringSentence);
		charArray(charSentence);
	}

	static void stringArray(String[] sentence) {
		int numChars = 0;

		System.out.println("String[] Version: ");
		for (int i = 0; i < sentence.length; i++) {
			System.out.print(sentence[i]);
			numChars += sentence[i].length();
		}
		System.out.println("Total chars: " + numChars);
		System.out.println("Total words: " + sentence.length);
		System.out.println("Average numbers of every words is " + (double) numChars / sentence.length);

	}

	/**
	 * @param sentence
	 * 
	 *                 注意同样的变量名字，有互相干扰么？
	 */
	static void charArray(char[][] sentence) {
		float numChars = 0; // 定义为浮点类型，做除法时会产生小数点

		System.out.println("Char[][] Version: ");
		for (int i = 0; i < sentence.length; i++) {
			for (int j = 0; j < sentence[i].length; j++)
				System.out.print(sentence[i][j]);
			numChars += sentence[i].length;
			System.out.print(" ");
		}
		System.out.println("Total chars: " + numChars);
		System.out.println("Total words: " + sentence.length);
		System.out.println("Average numbers of every words is " + numChars / sentence.length);
	}
}
