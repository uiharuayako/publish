package ch07IoStream;

import java.io.IOException;

class Input {
	public static void main(String args[]) throws IOException {
		byte buf[] = new byte[20];
		try {
			System.out.println("please input data:");
			System.in.read(buf);

		} catch (IOException e) {
			System.out.println(e.toString());
		}
		String str = new String(buf); // ��һ���ֽ�������ת�����ַ�������
		System.out.println("you had input the data:\n" + buf);
		System.out.println("you had input the data:\n" + str);
	}
}
