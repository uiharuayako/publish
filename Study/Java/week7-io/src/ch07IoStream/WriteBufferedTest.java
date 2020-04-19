package ch07IoStream;

import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class WriteBufferedTest {
	public static void main(String args[]) {
		try {
			long start = System.currentTimeMillis();
			FileOutputStream fs_out = new FileOutputStream("sample.dat");
			BufferedOutputStream bfs_out = new BufferedOutputStream(fs_out);
			DataOutputStream out = new DataOutputStream(bfs_out);
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
