package ch07IoStream;

import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class SimpleOutputTest {
	public static void main(String args[]) {
		double Pi = 3.1415;
		int i = 10;
		boolean okay = true;
		char cc = 'J';
		String s = "Java C Pascal";
		try {
			FileOutputStream fs_out = new FileOutputStream("sample.dat");
			DataOutputStream out = new DataOutputStream(fs_out);
			out.writeDouble(Pi);
			out.writeInt(i);
			out.writeBoolean(okay);
			out.writeChar(cc);
			out.writeUTF(s);
			out.close();
		} catch (FileNotFoundException fe) {
			System.err.println(fe);
		} catch (IOException ioe) {
			System.out.println(ioe);
		}
	}
}
