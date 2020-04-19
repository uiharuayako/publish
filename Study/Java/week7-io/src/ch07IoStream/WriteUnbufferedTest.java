package ch07IoStream;

import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class WriteUnbufferedTest {
	public static void main(String args[]) {
		try {
			long start = System.currentTimeMillis();
			FileOutputStream fs_out = new FileOutputStream("sample.dat");
			DataOutputStream out = new DataOutputStream(fs_out);
			for (int i = 0; i < 500000; i++)
				out.writeDouble(Math.random());
			out.close();
			long stop = System.currentTimeMillis();
			System.out.println("Time passed: " + (stop - start));
		} catch (IOException ioe) {
			System.out.println(ioe);
		}
	}
}
